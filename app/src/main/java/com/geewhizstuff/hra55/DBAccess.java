package com.geewhizstuff.hra55;

import android.database.sqlite.*;
import android.content.Context;

/**
 * Created by sapanbhatia on 10/15/15.
 */

class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE table qna(id INTEGER PRIMARY KEY AUTOINCREMENT, date DATE DEFAULT CURRENT_TIMESTAMP, question VARCHAR, answer VARCHAR, count INT, played INT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db,int a, int b) {
    }

    public void onDelete(SQLiteDatabase db) {
    }
}

public class DBAccess {
    private DBHelper mDBHelper;

    public DBAccess(Context context) {
        super();
        mDBHelper = new DBHelper(context);
    }

    public void ping () {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        // Creates the database if it's not there
    }

    public void addQuestionToDB(String question, String answer, String count) {
    }
}
