acg-gradle-plugin
=================

Android ContentProvider Generator gradle plugin

What is Android ContentProvider Generator?
------------------------------------------
https://github.com/BoD/android-contentprovider-generator

Example
-------

Project structure:

    .
    ├── build.gradle
    ├── libs
    └── src
        └── main
            ├── acg
            │   └── bookstore
            │       ├── _config.json
            │       └── book.json
            └── java

In build.gradle,

    buildscript {
        repositories {
            maven {
                url 'http://maven.taosha.org'
            }
        }
        dependencies {
            classpath 'org.taosha.gradle:acg-gradle-plugin:0.1'
        }
    }

    apply plugin: 'java'
    apply plugin: 'org.taosha.acg'

    acg {
        schemaDir 'src/main/acg'
        sourceDir 'build/generated/acg'
        customDir 'src/main/java'

        schemas {
            bookstore {
            }
        }
    }

    // Add generated files to source sets
    sourceSets {
        main {
            java {
                srcDir acg.sourceDir
                srcDir acg.customDir
            }
        }
    }

    // Generate content provider before build
    compileJava.dependsOn(generateProvider)

    dependencies {
        compile 'com.google.android:android:4.+'
    }


Usage
-----

TODO: DSL syntax introduction


For Android ContentProvider Generator configuration file syntax, refer to https://github.com/BoD/android-contentprovider-generator
