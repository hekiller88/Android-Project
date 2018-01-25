package cosc426.assign35unitconversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_mile_kilometer) {

            Intent mileKilometerActivity = new Intent(this, MileKilometerActivity.class);
            startActivity(mileKilometerActivity);

        }
        else if(id == R.id.menu_feet_meter)
        {
            Intent feetMeterActivity = new Intent(this, FeetMeterActivity.class);
            startActivity(feetMeterActivity);

        }
        else if(id == R.id.menu_inch_centimeter)
        {
            Intent inchCentimeterActivity = new Intent(this, InchCentimeterActivity.class);
            startActivity(inchCentimeterActivity);

        }

        return true;
    }
}
