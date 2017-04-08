package sheva.economicprovider.mvp.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sheva.economicprovider.IConstants;

/**
 * Created by shevc on 06.04.2017.
 * k
 */

public class DB extends SQLiteOpenHelper {
    private static String DB_NAME = "NewsDB";
    private static int DB_VERSION = 1;

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_NAME = "News_table";
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " +
                "(" +
                IConstants.DB.NEWS_ID +     " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                IConstants.DB.TIME +        " INTEGER NOT NULL," +
                IConstants.DB.DATE +        " TEXT NOT NULL," +
                IConstants.DB.AUTHOR_NAME + " TEXT NOT NULL," +
                IConstants.DB.TITLE +       " TEXT NOT NULL," +
                IConstants.DB.DESCRIPTION + " TEXT NOT NULL," +
                IConstants.DB.URL +         " TEXT NOT NULL," +
                IConstants.DB.URL_TO_IMAGE+ " TEXT NOT NULL," +
                IConstants.DB.PUBLISHED_AT+ " TEXT NOT NULL," +
                " UNIQUE (" + IConstants.DB.NEWS_ID + ") ON CONFLICT REPLACE" +
                ");";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static void deleteDB(Context context) {
        context.deleteDatabase(DB_NAME);
    }
}
