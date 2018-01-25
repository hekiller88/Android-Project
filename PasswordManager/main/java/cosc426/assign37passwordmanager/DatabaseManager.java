package cosc426.assign37passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

/**
 * Created by lhe on 11/12/17.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PASSWORD_MANAGER_DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "PASSWORD_MANAGER_TABLE";


    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String command = "create table " + TABLE_NAME + "(" +
                         "_ID integer primary key autoincrement, " +
                         "PLACE text, " +
                         "PASSWORD text)";

        db.execSQL(command);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void add(DataUnit data)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("PLACE", data.getPlace());
        row.put("PASSWORD", data.getPassword());
        db.insert(TABLE_NAME, null, row);

        db.close();
    }

    public void delete(String place)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, "PLACE = ?", new String[]{place});

        db.close();
    }

    public void update(DataUnit data)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("PLACE", data.getPlace());
        row.put("PASSWORD", data.getPassword());
        db.update(TABLE_NAME, row, "PLACE = ?", new String[]{data.getPlace()});

        db.close();
    }

    public LinkedList<DataUnit> all()
    {
        SQLiteDatabase db = getWritableDatabase();

        LinkedList<DataUnit> list = new LinkedList<>();

        Cursor cursor = db.query(TABLE_NAME, new String[]{"PLACE", "PASSWORD"},
                                 null, null, null, null, null);

        while(cursor.moveToNext())
        {
            String place = cursor.getString(0);
            String password = cursor.getString(1);
            DataUnit tmp = new DataUnit(place, password);
            list.addLast(tmp);
        }

        cursor.close();
        db.close();

        return list;
    }

    public void clear()
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, null, null);

        db.close();
    }
}
