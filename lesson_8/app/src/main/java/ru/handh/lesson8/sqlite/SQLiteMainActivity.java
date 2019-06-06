package ru.handh.lesson8.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.handh.lesson8.R;

public class SQLiteMainActivity extends AppCompatActivity {

    private FeedReaderDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new FeedReaderDbHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "Заголовок " + System.currentTimeMillis());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "Подзаголовок");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
//
//d
//        //****************************************************************************************************************************************************
//        //****************************************************************************************************************************************************
//        //****************************************************************************************************************************************************
//
//
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = {"Заголовок 1532360008036"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<String> itemTitles = new ArrayList<>();

        while (cursor.moveToNext()) {
            //TODO расширить до модельки
            String item = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            itemTitles.add(item);
        }
        cursor.close();

        for (String str : itemTitles) {
            Log.d("LES", str);
        }


        Cursor cursor1 = db.rawQuery("SELECT * FROM entry", null);
        itemTitles = new ArrayList<>();
        while (cursor1.moveToNext()) {
            //TODO расширить до модельки
            String item = cursor1.getString(cursor1.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            itemTitles.add(item);
        }
        cursor1.close();

        for (String str : itemTitles) {
            Log.d("LES", str);
        }
////
////        //****************************************************************************************************************************************************
////        //****************************************************************************************************************************************************
////        //****************************************************************************************************************************************************
////
////
//        // New value for one column
//        String title = "MyNewTitle";
//        values = new ContentValues();
//        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
//
//// Which row to update, based on the title
//        selection = FeedReaderContract.FeedEntry._ID + " = ?";
//        String[] selectionArgs2 = {"1"};
//
//        int count = db.update(
//                FeedReaderContract.FeedEntry.TABLE_NAME,
//                values,
//                selection,
//                selectionArgs2);
////
//////
//////        //****************************************************************************************************************************************************
//////        //****************************************************************************************************************************************************
//////        //****************************************************************************************************************************************************
//////
//////
//        // Define 'where' part of query.
//        selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
//// Specify arguments in placeholder order.
//        String[] selectionArgs3 = {"MyNewTitle"};
//// Issue SQL statement.
//        int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs3);
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}
