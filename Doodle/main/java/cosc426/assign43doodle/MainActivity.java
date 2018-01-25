package cosc426.assign43doodle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GraphicView graphicView;
    private RelativeLayout relativeLayout;
    private Palette tmpPalette;
    private TextView paletteTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphicView = new GraphicView(this);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.removeAllViewsInLayout();
        relativeLayout.addView(graphicView);

        TouchHandler tmp = new TouchHandler();
        paletteTV = (TextView)findViewById(R.id.palette);
        paletteTV.setOnTouchListener(tmp);

        tmpPalette = new Palette();

    }

    private class TouchHandler implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            int action = event.getAction();

            if(action == MotionEvent.ACTION_DOWN ) {

                int color = tmpPalette.nextColor();
                graphicView.setColor(color);
                paletteTV.setBackgroundColor(color);

            }

            return true;
        }
    }
}
