package cosc426.assign43doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lhe on 11/24/17.
 */

public class GraphicView extends View {


    private int currentColor;

    private Path path;
    private Paint paint;
    private ArrayList<MyPath> myPaths;

    public GraphicView(Context context)
    {
        super(context);

        currentColor = Color.BLACK;
        setBackgroundColor(Color.WHITE);

        path = new Path();

        paint = new Paint();
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);

        myPaths = new ArrayList<>();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(myPaths.size() == 0)
            canvas.drawPath(path, paint);
        else {
            for (MyPath myPath : myPaths)
                canvas.drawPath(myPath.path, myPath.paint);

            canvas.drawPath(path, paint);
        }

    }

    public void setColor(int color) {

        paint.setColor(color);
        currentColor = color;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(x, y);
                invalidate();
                myPaths.add(new MyPath(path, paint));
                path = new Path();
                paint = new Paint();
                paint.setColor(currentColor);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(15);
                break;
        }

        return true;
    }
}
