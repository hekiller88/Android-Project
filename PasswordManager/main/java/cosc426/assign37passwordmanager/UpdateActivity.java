package cosc426.assign37passwordmanager;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class UpdateActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    private TextView[] places;
    private EditText[] passwords;
    private Button[] updates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbManager = new DatabaseManager(this);

        updateView();
    }

    private void updateView()
    {
        LinkedList<DataUnit> list = dbManager.all();

        if(list.size() > 0)
        {
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int screenSize = size.x;
            int DP = (int)(getResources().getDisplayMetrics().density);
            int rows = list.size();
            int columns = 3;

            places = new TextView[rows];
            passwords = new EditText[rows];
            updates = new Button[rows];

            GridLayout grid = new GridLayout(this);
            grid.setRowCount(rows);
            grid.setColumnCount(columns);
            ScrollView.LayoutParams paramsGrid = new ScrollView.LayoutParams(0, 0);
            paramsGrid.width = screenSize;
            paramsGrid.height = rows * (screenSize / 5);
            grid.setLayoutParams(paramsGrid);

            for(int i = 0; i < list.size(); i++)
            {
                DataUnit data = list.get(i);
                GridLayout.LayoutParams params;

                places[i] = new TextView(this);
                places[i].setText(data.getPlace());         
                places[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                places[i].setTextColor(Color.BLACK);
                places[i].setBackgroundColor(Color.parseColor("#ABC5F1"));      
                places[i].setPadding(10*DP, 10*DP, 10*DP, 10*DP);
                places[i].setGravity(Gravity.CENTER);
                params = new GridLayout.LayoutParams();
                params.width = (int)(screenSize * 0.3);
                params.height = screenSize / 5;
                params.rowSpec = GridLayout.spec(i, 1);
                params.columnSpec = GridLayout.spec(0, 1);      
                params.leftMargin = params.rightMargin = 1;
                params.topMargin = params.bottomMargin = 1;
                places[i].setLayoutParams(params);
                grid.addView(places[i]);                        

                passwords[i] = new EditText(this);                  
                passwords[i].setText(data.getPassword());         
                passwords[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                passwords[i].setTextColor(Color.BLACK);
                passwords[i].setBackgroundColor(Color.parseColor("#D8D8D8"));      
                passwords[i].setPadding(10*DP, 10*DP, 10*DP, 10*DP);
                passwords[i].setGravity(Gravity.CENTER);

                //allow input box to scroll horizontally
                passwords[i].setMaxLines(1);
                passwords[i].setHorizontallyScrolling(true);

                params = new GridLayout.LayoutParams();
                params.width = (int)(screenSize * 0.5);
                params.height = screenSize / 5;
                params.rowSpec = GridLayout.spec(i, 1);
                params.columnSpec = GridLayout.spec(1, 1);      
                params.leftMargin = params.rightMargin = 1;
                params.topMargin = params.bottomMargin = 1;
                passwords[i].setLayoutParams(params);
                grid.addView(passwords[i]);                        

                updates[i] = new Button(this);                  
                updates[i].setText("Up");
                updates[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                updates[i].setTextColor(Color.RED);
                updates[i].setBackgroundColor(Color.parseColor("#2A2452"));      
                updates[i].setPadding(5*DP, 5*DP, 5*DP, 5*DP);
                updates[i].setGravity(Gravity.CENTER);
                params = new GridLayout.LayoutParams();
                params.width = (int)(screenSize * 0.18);
                params.height = screenSize / 5;
                params.rowSpec = GridLayout.spec(i, 1);
                params.columnSpec = GridLayout.spec(2, 1);      
                params.leftMargin = params.rightMargin = 1;
                params.topMargin = params.bottomMargin = 1;
                updates[i].setLayoutParams(params);

                ButtonHandler tmp = new ButtonHandler(i);
                updates[i].setOnClickListener(tmp);

                grid.addView(updates[i]);                        

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

    private class ButtonHandler implements View.OnClickListener
    {
        private int rowNumber;

        public ButtonHandler(int row)
        {
            rowNumber = row;
        }

        @Override
        public void onClick(View view) {

            String place = places[rowNumber].getText().toString();
            String password = passwords[rowNumber].getText().toString();

            DataUnit data = new DataUnit(place, password);

            dbManager.update(data);

            Toast.makeText(UpdateActivity.this, "Data Updated!", Toast.LENGTH_SHORT).show();

        }
    }

    public void back(View v)
    {
        finish();
    }
}
