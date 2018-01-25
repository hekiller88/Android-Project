package cosc426.assign38expensesmanager;

/**
 * Created by lhe on 11/12/17.
 */

public class DataUnit {

    private String name;
    private float price;
    private String date;
    private int id;         //using for database ID colomn

    public DataUnit(String name, float price, String date)
    {
        this.name = name;
        this.price = price;
        this.date = date;
        id = 0;
    }

    public DataUnit(int id, String name, float price, String date)
    {
        this.name = name;
        this.price = price;
        this.date = date;
        this.id = id;
    }

    public int getId(){ return id;}
    public String getName()
    {
        return name;
    }

    public float getPrice()
    {
        return price;
    }

    public String getDate()
    {
        return date;
    }

    //check the format of date
    public static boolean dateFormatCheck(String date)
    {
        if(!date.contains("/"))
            return false;

        String[] dateArr = date.split("/");

        if(dateArr.length != 3 ||
                dateArr[0].length() != 2 || dateArr[1].length() != 2 || dateArr[2].length() != 4)
            return false;

        int month, day, year;
        try{
            month = Integer.parseInt(dateArr[0]);
            day = Integer.parseInt(dateArr[1]);
            year = Integer.parseInt(dateArr[2]);

            if( month < 1 || month > 12 ||
                    day < 1 || day > 31 ||
                    year < 0)
                return false;

        }
        catch(NumberFormatException e)
        {
            return false;
        }

        return true;
    }

}
