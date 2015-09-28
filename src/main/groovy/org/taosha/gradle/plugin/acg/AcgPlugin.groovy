package org.taosha.gradle.plugin.acg

import org.gradle.api.DefaultTask
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

class AcgPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('generateProvider', type: GenerateTask) {
            group = 'Generator'
        }

        project.task('generateLibrary', type: GenerateTask) {
            group = 'Generator'
            library = true
        }

        def generate
        try {
            generate = project.tasks['generate']
        } catch (e) {
            generate = project.task('generate') { group = 'Generator' }
        }
        generate.dependsOn('generateProvider')

        project.extensions.create('acg', AcgExtension, project.container(Schema))
    }
}

class GenerateTask extends DefaultTask {
    def library

    @TaskAction
    def generateAll() {
        project.acg.schemas.each { schema ->
            generateSchema(schema)
        }
    }

    def synchronized generateSchema(schema) {
        def schemaDir = project.file(project.acg.schemaDir)

        def inputDir = new File(schemaDir, schema.name).path
        final args = [
                '--input', "$inputDir",
                '--output', "${project.file(project.acg.sourceDir)}"
        ]

        if (project.acg.customDir != null) {
            args << '--custom' << "${project.file(project.acg.customDir)}"
        }

        if (library) {
            args << '--library'
        }

        String[] arguments = new String[args.size()]
        for (int i = 0; i < args.size(); i++) {
            arguments[i] = args[i]
        }

        org.jraf.androidcontentprovidergenerator.Main.main(arguments)
    }
}

class AcgExtension {
    final NamedDomainObjectContainer<Schema> schemas

    def schemaDir
    def sourceDir
    def customDir

    AcgExtension(schemas) {
        this.schemas = schemas
    }

    def schemas(Closure closure) {
        schemas.configure(closure)
    }

    def schemaDir(dir) {
        this.schemaDir = dir
    }

    def sourceDir(dir) {
        this.sourceDir = dir
    }

    def customDir(dir) {
        this.customDir = dir
    }
}

class Schema {
    final String name

    Schema(name) {
        this.name = name
    }
}