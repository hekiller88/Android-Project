package cosc426.assign38expensesmanager;

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

    private static final String DATABASE_NAME = "EXPENSES_DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "EXPENSES_TABLE";

    //as flag to contral all()
    public static int SORT = 0;
    public static int UNSORT = 1;


    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String command = "create table " + TABLE_NAME + "(" +
                "_ID integer primary key autoincrement, " +
                "NAME text, " +
                "PRICE real, " +
                "DATE text)";

        db.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_NAME );
        // Re-create tables
        onCreate( db );

    }

    public void add(DataUnit data)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("NAME", data.getName());
        row.put("PRICE", data.getPrice());
        row.put("DATE", data.getDate());
        db.insert(TABLE_NAME, null, row);

        db.close();
    }

    //deprecate, it delete all the item with same name
    public void deleteByName(String name)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, "NAME = ?", new String[]{name});

        db.close();
    }


    public void deleteById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_NAME;
        sqlDelete += " where _ID" + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }


    //default, sorted version of show all
    public LinkedList<DataUnit> all()
    {
        SQLiteDatabase db = getWritableDatabase();

        LinkedList<DataUnit> list = new LinkedList<>();

        Cursor cursor = db.query(TABLE_NAME, new String[]{"NAME", "PRICE", "DATE"},
                null, null, null, null, null);

        while(cursor.moveToNext())
        {
            String name = cursor.getString(0);
            float price = cursor.getFloat(1);
            String date = cursor.getString(2);
            DataUnit tmp = new DataUnit(name, price, date);
            list.addLast(tmp);
        }

        sortList_Date(list);

        cursor.close();
        db.close();

        return list;
    }

    //using for DeleteActivity only, you can choose to SORT or UNSORT the all()
    //delete by ID, so that you can only delete one row at one operation
    //instead of deleting multiple rows with same name or some other attributes
    public LinkedList<DataUnit> all(int flag)
    {
        String sqlQuery = "select * from " + TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        LinkedList<DataUnit> list = new LinkedList<>();

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String tmpName = cursor.getString(1);
            float price = cursor.getFloat(2);
            String date = cursor.getString(3);

            DataUnit data = new DataUnit(id, tmpName, price, date);
            list.addLast(data);
        }

        if(flag == SORT)
            sortList_Date(list);

        cursor.close();
        db.close();

        return list;
    }

    public LinkedList<DataUnit> select_name(String name)
    {
        SQLiteDatabase db = getWritableDatabase();

        LinkedList<DataUnit> list = new LinkedList<>();

        Cursor cursor = db.query(TABLE_NAME, new String[]{"NAME", "PRICE", "DATE"},
                "NAME = ?", new String[]{name},
                null, null, null);

        while(cursor.moveToNext())
        {
            String tmpName = cursor.getString(0);
            float price = cursor.getFloat(1);
            String date = cursor.getString(2);

            DataUnit data = new DataUnit(tmpName, price, date);
            list.addLast(data);
        }

        db.close();

        sortList_Date(list);

        return list;
    }

    public LinkedList<DataUnit> select_price(float lower, float upper)
    {
        if(lower > upper)
            throw new IllegalArgumentException("Error: Range Fault!");

        SQLiteDatabase db = getWritableDatabase();

        LinkedList<DataUnit> list = new LinkedList<>();

        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        sqlQuery += " WHERE PRICE BETWEEN " + String.valueOf(lower)  + " AND " + String.valueOf(upper);

        Cursor cursor = db.rawQuery( sqlQuery, null );

        while(cursor.moveToNext())
        {
            //in this case, we got _ID in 0 index
            String tmpName = cursor.getString(1);
            float price = cursor.getFloat(2);
            String date = cursor.getString(3);

            DataUnit data = new DataUnit(tmpName, price, date);
            list.addLast(data);
        }

        cursor.close();
        db.close();

        sortList_Date(list);

        return list;
    }

    public LinkedList<DataUnit> select_date(String lower, String upper)
    {
        if(!compareDate(upper, lower))
            throw new IllegalArgumentException("Error: Range Fault!");

        SQLiteDatabase db = getWritableDatabase();

        LinkedList<DataUnit> allList = all();
        LinkedList<DataUnit> list = new LinkedList<>();

        for(int i = 0; i < allList.size(); i++)
        {
            DataUnit tmp = allList.get(i);

            if(compareDate(tmp.getDate(), lower) && compareDate(upper, tmp.getDate()))
                list.addLast(tmp);
        }

        db.close();

        sortList_Date(list);
        return list;
    }


    //sort list by date, from most recent date , descending
    public void sortList_Date(LinkedList<DataUnit> list)
    {
        //error check
        if(list.isEmpty() || list.size() == 1)
            return;

        for(int i = 0; i < list.size(); i++)
        {
            int maxIndex = i;

            for(int j = i + 1; j < list.size(); j++)
                if(compareDate(list.get(j).getDate(), list.get(maxIndex).getDate()))
                    maxIndex = j;

            DataUnit tmp = list.get(i);
            list.set(i, list.get(maxIndex));
            list.set(maxIndex, tmp);
        }
    }

    //return true if lDate >= rDate
    //return false if lDate < rDate
    public boolean compareDate(String lDate, String rDate)
    {
        //format mm/DD/YYYY
        String[] lDateArr = lDate.split("/");
        String[] rDateArr = rDate.split("/");

        //error check: date format
        if(lDateArr.length != 3 || rDateArr.length != 3
                || lDateArr[0].length() != 2 || rDateArr[0].length() != 2
                || lDateArr[1].length() != 2 || rDateArr[1].length() != 2
                || lDateArr[2].length() != 4 || rDateArr[2].length() != 4)
            throw new IllegalArgumentException("Error!Wrong Date Format!");

        int lMonth, lDay, lYear;
        int rMonth, rDay, rYear;

        try
        {
            lMonth = Integer.parseInt(lDateArr[0]);
            lDay = Integer.parseInt(lDateArr[1]);
            lYear = Integer.parseInt(lDateArr[2]);

            rMonth = Integer.parseInt(rDateArr[0]);
            rDay = Integer.parseInt(rDateArr[1]);
            rYear = Integer.parseInt(rDateArr[2]);

            //error check:date number range
            if( lMonth < 1 || lMonth > 12 || rMonth < 1 || rMonth > 12      //month shold be 1~12
                    || lDay < 1 || lDay > 31 || rDay < 1 || rDay > 31       //day should be 1~31
                    || lYear < 0 || rYear < 0)                              //year should > 0
                throw new IllegalArgumentException("Error!Wrong Date Format!");

        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Error!Wrong Date Format!");
        }

        if(lYear != rYear)
            return lYear > rYear;

        if(lMonth != rMonth)
            return lMonth > rMonth;

        return lDay >= rDay;

    }

}
