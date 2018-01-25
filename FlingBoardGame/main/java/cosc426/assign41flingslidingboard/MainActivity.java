package cosc426.assign41flingslidingboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int WIDTH_DP;
    private int DP;

    private GameBoardView gameBoardView;
    private Gamer gamer;
    private GestureDetector gestureDetector;
    private boolean gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gamer = new Gamer();

        //for test only
//        gamer.setCurrentMat(new int[][]{{1,2,3},
//                                         {4,5,6},
//                                         {7,0,8}});

        DP = (int)(getResources().getDisplayMetrics().density);
        WIDTH_DP = 300 * DP;

        gameBoardView = new GameBoardView(this, WIDTH_DP);
        gameBoardView.setBoardText(gamer.getCurrentMat());

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.removeAllViewsInLayout();
        relativeLayout.addView(gameBoardView);

        TouchHandler tmp = new TouchHandler();
        gestureDetector = new GestureDetector(this, tmp);
        gestureDetector.setOnDoubleTapListener(tmp);

        gameOver = false;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(!gameOver) gestureDetector.onTouchEvent(event);

        return true;
    }

    private class TouchHandler extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (outOfView(e1) || outOfView(e2)) {
                Toast.makeText(MainActivity.this, "Error: Out of Windows!!", Toast.LENGTH_SHORT).show();
                return true;
            }

            int startRow = getY(e1)/(WIDTH_DP / 3);
            int startCol = getX(e1)/(WIDTH_DP / 3);

            int destRow = getY(e2)/(WIDTH_DP / 3);
            int destCol = getX(e2)/(WIDTH_DP / 3);

            //either start or end at empty slot
            if((gamer.isEmpty(startRow, startCol) && gamer.isNeighbour(destRow, destCol))
                    || (gamer.isEmpty(destRow, destCol) && gamer.isNeighbour(startRow, startCol))){

                gamer.swapBoard(startRow, startCol, destRow, destCol);
                gameBoardView.setBoardText(gamer.getCurrentMat());

            }
            else
                Toast.makeText(MainActivity.this, "Error: Didn't Choose Empty!!", Toast.LENGTH_SHORT).show();

            if(gamer.isWin()) {
                gameOver = true;
                gameBoardView.gameOver();
            }

            return true;
        }

        //check if the touch coordinate is out of view
        public boolean outOfView(MotionEvent event)
        {
            return (getX(event) > WIDTH_DP || getX(event) < 0) ||
                    (getY(event) > WIDTH_DP || getY(event) < 0);
        }

        public int getX(MotionEvent event)
        {
            return (int)(event.getX() - 30 * DP) ;
        }

        public int getY(MotionEvent event)
        {
            return (int)(event.getY() - 80 * DP - 100 * DP);
        }
    }


}
