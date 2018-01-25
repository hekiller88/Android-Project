package cosc426.assign44movingshapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

/**
 * Created by lhe on 11/25/17.
 */

public class AnimeView extends View {

    private Shape[] shapes;


    public AnimeView(Context context, Shape[] shapes ) {
        super(context);

        setBackgroundColor(Color.BLACK);

        this.shapes = shapes;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for(Shape shape: shapes)
        {
            if(shape.getShape() == Shape.CIRCLE)
                canvas.drawCircle(shape.getCenterX(), shape.getCenterY(),
                        shape.getRadius(), shape.getPaint());
            else if(shape.getShape() == Shape.SQUARE)
                canvas.drawRect(shape.getCenterX() - shape.getRadius(),
                        shape.getCenterY() - shape.getRadius(),
                        shape.getCenterX() + shape.getRadius(),
                        shape.getCenterY() + shape.getRadius(),
                        shape.getPaint());
        }
    }
}
