package cosc426.assign38expensesmanager;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbManager = new DatabaseManager(this);

        updateView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        updateView();
    }

    public void updateView()
    {
        LinkedList<DataUnit> list = dbManager.all();



        if(list.size() > 0)
        {

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int slotWidth = size.x / 3;

            TableGenerator grid = new TableGenerator(this, slotWidth, list);

            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
            scroll.addView(grid);

        }
        else
        {
            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
        }
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
        if (id == R.id.menu_add) {

            Intent addActivity = new Intent(this, AddActivity.class);
            startActivity(addActivity);
        }
        else if (id == R.id.menu_delte) {

            Intent deleteActivity = new Intent(this, DeleteActivity.class);
            startActivity(deleteActivity);
        }
        else if (id == R.id.menu_viewByName) {

            Intent viewNameActivity = new Intent(this, ViewNameActivity.class);
            startActivity(viewNameActivity);
        }
        else if (id == R.id.menu_viewByPrice) {

            Intent viewPriceActivity = new Intent(this, ViewPriceActivity.class);
            startActivity(viewPriceActivity);
        }
        else if (id == R.id.menu_viewByDate) {

            Intent viewDateActivity = new Intent(this, ViewDateActivity.class);
            startActivity(viewDateActivity);
        }

        return true;
    }
}
