package cosc426.assign45;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Gamer gamer;
    private GameView gameView;
    private GestureDetector gestureDetector;

    double screenHeight;
    double screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int DP = (int)(getResources().getDisplayMetrics().density);

        //get screenHeight accurately,
        //refer to textbook
        TypedValue tv = new TypedValue( );
        int actionBarHeight = ( int ) ( DP * 56 );
        if( getTheme( ).resolveAttribute( android.R.attr.actionBarSize,
                tv, true ) )
            actionBarHeight = TypedValue.complexToDimensionPixelSize( tv.data,
                    getResources().getDisplayMetrics() );

        int statusBarHeight = ( int ) ( DP * 24 );
        int resourceId =
                getResources().getIdentifier( "status_bar_height", "dimen", "android" );
        if( resourceId != 0 ) // found resource for status bar height
            statusBarHeight = getResources().getDimensionPixelSize( resourceId );

        //get screen size
        screenWidth = size.x;
        //screenHeight = size.y - actionBarHeight - statusBarHeight;
        screenHeight = size.y - statusBarHeight;

        createScene();

    }

    public void createScene()
    {
        gamer = new Gamer(screenWidth, screenHeight);

        gameView = new GameView(this, gamer, screenHeight, screenWidth);

        setContentView(gameView);

        Timer timer = new Timer();
        GameTimerTask task = new GameTimerTask(gamer, gameView);
        timer.schedule(task, 5000, 20);

        TouchHandler tmp = new TouchHandler();
        gestureDetector = new GestureDetector(this, tmp);
        gestureDetector.setOnDoubleTapListener(tmp);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        return true;
    }

    private class TouchHandler extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onDoubleTap(MotionEvent e) {

            gamer.fire();

            return true;
        }
    }
}
