package cosc426.assign42sortingslidingwindow;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Gamer gamer;
    private AppInterface appInterface;
    private GestureDetector gestureDetector;
    private boolean gamerOver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gamer = new Gamer();

        appInterface = new AppInterface(this, screenHeight());
        appInterface.setNumbers(gamer.getNumbers());
        updateStatus();

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.removeAllViewsInLayout();
        relativeLayout.addView(appInterface);

        TouchHandler tmp = new TouchHandler();
        gestureDetector = new GestureDetector(this, tmp);
        gestureDetector.setOnDoubleTapListener(tmp);

        gamerOver = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(!gamerOver) gestureDetector.onTouchEvent(event);

        return true;
    }

    private void updateStatus()
    {
        if(gamer.isWin()) {
            appInterface.setStatusText("Your Win!!");
            gamerOver = true;
            return;
        }

        if(gamer.isLoose()) {
            appInterface.setStatusText("Your Loose!!");
            gamerOver = true;
            return;
        }

        appInterface.setStatusText(gamer.getSwapLeft() + " swaps left.\n"
                                    + "Game in the progress");
    }

    private int screenHeight()
    {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        int DP = (int)(getResources().getDisplayMetrics().density);

        return size.y - 80 * DP;
    }

    private class TouchHandler extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            appInterface.slideDownWindow();

            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            gamer.swapInWindows(appInterface.getUpperSlotIndex());
            appInterface.setNumbers(gamer.getNumbers());

            updateStatus();
            return true;
        }
    }
}
