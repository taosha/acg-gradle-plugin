package com.example.bookstore.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

/**
 * Implement your custom database creation or upgrade code here.
 *
 * This file will not be overwritten if you re-run the content provider generator.
 */
public class BookStoreSQLiteOpenHelperCallbacks {
    private static final String TAG = BookStoreSQLiteOpenHelperCallbacks.class.getSimpleName();

    public void onOpen(final Context context, final SQLiteDatabase db) {
        // Insert your db open code here.
    }

    public void onPreCreate(final Context context, final SQLiteDatabase db) {
        // Insert your db creation code here. This is called before your tables are created.
    }

    public void onPostCreate(final Context context, final SQLiteDatabase db) {
        // Insert your db creation code here. This is called after your tables are created.
    }

    public void onUpgrade(final Context context, final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        // Insert your upgrading code here.
    }
}
