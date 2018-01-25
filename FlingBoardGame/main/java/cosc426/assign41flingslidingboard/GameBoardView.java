package cosc426.assign41flingslidingboard;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lhe on 11/22/17.
 */

public class GameBoardView extends GridLayout {

    private static final int SIZE = 3;
    private TextView[][] textViews_grid;
    private Context context;

    public GameBoardView(Context context, int width){

        super(context);

        this.context = context;

        setRowCount(SIZE);
        setColumnCount(SIZE);

        textViews_grid = new TextView[SIZE][SIZE];

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){

                textViews_grid[i][j] = new TextView(context);
                textViews_grid[i][j].setBackgroundColor(Color.parseColor("#D8D8D8"));
                textViews_grid[i][j].setTextColor(Color.BLACK);
                textViews_grid[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                textViews_grid[i][j].setGravity(Gravity.CENTER);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width =  (int)(width / 3);
                params.height = (int)(width / 3);
                params.rowSpec = GridLayout.spec(i,1);
                params.columnSpec = GridLayout.spec(j, 1);
                params.topMargin = params.bottomMargin = 1;
                params.leftMargin = params.rightMargin = 1;

                textViews_grid[i][j].setText("1");

                textViews_grid[i][j].setLayoutParams(params);

                addView(textViews_grid[i][j]);
            }

    }

    public void setBoardText(int[][] board)
    {
        if(board.length != 3 || board[0].length != 3){

            Toast.makeText(context, "Error: Wrong Board!",Toast.LENGTH_LONG).show();
            return;

        }

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){

                textViews_grid[i][j].setText(board[i][j] == 0 ? "" : String.valueOf(board[i][j]));

            }

    }

    public void gameOver()
    {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++){

                textViews_grid[i][j].setBackgroundColor(Color.RED);

            }
    }
}
