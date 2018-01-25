package cosc426.assign37passwordmanager;

/**
 * Created by lhe on 11/12/17.
 */

public class DataUnit {

    private String place;
    private String password;

    public DataUnit(String place, String password)
    {
        this.place = place;
        this.password = password;
    }

    public String getPlace()
    {
        return place;
    }

    public String getPassword()
    {
        return password;
    }
}
