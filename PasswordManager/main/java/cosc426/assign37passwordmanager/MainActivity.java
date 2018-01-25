package cosc426.assign37passwordmanager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
            int screenSize = size.x;
            int DP = (int)(getResources().getDisplayMetrics().density);
            int rows = list.size();
            int columns = 2;

            GridLayout grid = new GridLayout(this);
            grid.setRowCount(rows);
            grid.setColumnCount(columns);
            ScrollView.LayoutParams paramsGrid = new ScrollView.LayoutParams(0, 0);
            paramsGrid.width = screenSize;
            paramsGrid.height = rows * (screenSize / 5);
            grid.setLayoutParams(paramsGrid);

            TextView[] places = new TextView[list.size()];
            TextView[] passwords = new TextView[list.size()];
            for(int i = 0; i < list.size(); i++)
            {
                DataUnit data = list.get(i);

                GridLayout.LayoutParams params;

                places[i] = new TextView(this);
                places[i].setText(data.getPlace());
                places[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                places[i].setTextColor(Color.BLACK);
                places[i].setBackgroundColor(Color.parseColor("#33CC8F"));
                places[i].setPadding(10*DP, 10*DP, 10*DP, 10*DP);
                places[i].setGravity(Gravity.CENTER);

                //allow textview to scroll in case of too long text
                places[i].setMaxLines(1);
                places[i].setMovementMethod(new ScrollingMovementMethod());

                params = new GridLayout.LayoutParams();
                params.width = (int)(screenSize * 0.3);
                params.height = screenSize / 5;
                params.rowSpec = GridLayout.spec(i, 1);
                params.columnSpec = GridLayout.spec(0, 1);
                params.bottomMargin = params.topMargin = 1;
                params.leftMargin = params.rightMargin = 1;
                places[i].setLayoutParams(params);
                grid.addView(places[i]);

                passwords[i] = new TextView(this);
                passwords[i].setText(data.getPassword());
                passwords[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                passwords[i].setTextColor(Color.BLACK);
                passwords[i].setBackgroundColor(Color.parseColor("#ABC5F1"));
                passwords[i].setPadding(10*DP, 10*DP, 10*DP, 10*DP);
                passwords[i].setGravity(Gravity.CENTER);

                //allow textview to scroll in case of too long text
                passwords[i].setMaxLines(1);
                passwords[i].setMovementMethod(new ScrollingMovementMethod());

                params = new GridLayout.LayoutParams();
                params.width = (int)(screenSize * 0.7);
                params.height = screenSize / 5;
                params.rowSpec = GridLayout.spec(i, 1);
                params.columnSpec = GridLayout.spec(1, 1);
                params.bottomMargin = params.topMargin = 1;
                params.leftMargin = params.rightMargin = 1;
                passwords[i].setLayoutParams(params);
                grid.addView(passwords[i]);
            }

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
        else if(id == R.id.menu_delete)
        {
            Intent deleteActivity = new Intent(this, DeleteActivity.class);
            startActivity(deleteActivity);
        }
        else if(id == R.id.menu_update)
        {
            Intent updateActivity = new Intent(this, UpdateActivity.class);
            startActivity(updateActivity);
        }


        return true;
    }
}
