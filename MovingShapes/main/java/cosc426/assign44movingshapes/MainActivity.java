package cosc426.assign44movingshapes;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;

import java.util.Timer;

public class MainActivity extends Activity {

    private Shape[] shapes;
    private AnimeView animeView;

    private final int SIZE = 20;

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
        int screenWidth = size.x;
        //int screenHeight = size.y - actionBarHeight - statusBarHeight;
        int screenHeight = size.y - statusBarHeight;

        //roughly half circles, half squares
        shapes = new Shape[SIZE];
        int roughHalf = (int)(Math.random()*5 + 8); // 8 ~ 12
        for(int i = 0; i < roughHalf; i++)
        {
            shapes[i] = new Shape(screenWidth, screenHeight, Shape.CIRCLE);
        }
        for(int i = roughHalf; i < SIZE; i++)
        {
            shapes[i] = new Shape(screenWidth, screenHeight, Shape.SQUARE);
        }

        animeView = new AnimeView(this, shapes);

        setContentView(animeView);

        Timer timer = new Timer();
        AnimeTimerTask task = new AnimeTimerTask(shapes, animeView);
        timer.schedule(task, 2000, 10);
    }
}
