package cosc426.assign34direction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    Button[][] direction;
    private static final int SIZE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buildInterface();
    }

    @Override
    protected void onStart() {
        super.onStart();

        buildInterface();
    }

    public void buildInterface()
    {
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        int width = screenSize.x / 2;
        int height = (int)(screenSize.y / 2.5);

        GridLayout grid = new GridLayout(this);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(0,0);
        params1.width = screenSize.x;
        params1.height = screenSize.y;
        grid.setLayoutParams(params1);
        grid.setRowCount(SIZE);
        grid.setColumnCount(SIZE);

        ButtonHandler buttonHandler = new ButtonHandler();
        direction = new Button[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
            {
                direction[i][j] = new Button(this);
                direction[i][j].setId(Button.generateViewId());
                direction[i][j].setBackgroundColor(Color.parseColor("#33CC8F"));
                direction[i][j].setTextColor(Color.BLACK);
                direction[i][j].setTextSize((int)(width * 0.05));
                direction[i][j].setGravity(Gravity.CENTER);
                direction[i][j].setOnClickListener(buttonHandler);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = width;
                params.height = height;
                params.rowSpec = GridLayout.spec(i, 1);
                params.columnSpec = GridLayout.spec(j, 1);
                params.topMargin = params.bottomMargin = 5;
                params.leftMargin = params.rightMargin = 5;
                direction[i][j].setLayoutParams(params);

                if(i == 0 && j == 0)
                    direction[i][j].setText("North");
                else if (i == 0 && j == 1)
                    direction[i][j].setText("South");
                else if (i == 1 && j == 0)
                    direction[i][j].setText("West");
                else
                    direction[i][j].setText("East");

                grid.addView(direction[i][j]);

            }

        setContentView(grid);




    }

    private class ButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {

            if(view.getId() == direction[0][0].getId()) //north
            {
                Intent northActivity = new Intent(MainActivity.this, NorthActivity.class);
                MainActivity.this.startActivity(northActivity);
                overridePendingTransition(R.anim.north_incoming, R.anim.north_outgoing);
            }
            else if(view.getId() == direction[0][1].getId()) //south
            {
                Intent southActivity = new Intent(MainActivity.this, SouthActivity.class);
                MainActivity.this.startActivity(southActivity);
                overridePendingTransition(R.anim.south_incoming, R.anim.south_outgoing);
            }
            else if(view.getId() == direction[1][0].getId()) //west
            {
                Intent westActivity = new Intent(MainActivity.this, WestActivity.class);
                MainActivity.this.startActivity(westActivity);
                overridePendingTransition(R.anim.west_incoming, R.anim.west_outgoing);
            }
            else	//east
            {
            	Intent eastActivity = new Intent(MainActivity.this, EastActivity.class);
                MainActivity.this.startActivity(eastActivity);
                overridePendingTransition(R.anim.east_incoming, R.anim.east_outgoing);
            }
        }
    }
}
