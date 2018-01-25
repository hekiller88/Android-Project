package cosc426.assign45;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by lhe on 11/25/17.
 */

public class GameView extends View {

    private Gamer gamer;
    private double screenHeight;
    private double screenWidth;

    public GameView(Context context, Gamer gamer, double screenHeight, double screenWidth) {
        super(context);

        setBackgroundColor(Color.WHITE);

        this.gamer = gamer;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        double ballX = gamer.getBallX();
        double ballY = gamer.getBallY();
        double ballRadius = gamer.getBallRadius();

        double bulletX = gamer.getBulletX();
        double bulletY = gamer.getBulletY();
        double bulletRadius = gamer.getBulletRadius();

        double gunX = gamer.getGunX();
        double gunY = gamer.getGunY();

        Paint paint = new Paint();

        //background
        paint.setColor(Color.parseColor("#AAAAAA"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, (float)screenWidth, (float)screenHeight, paint);

        //ball
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        if(!gamer.isHit() && !gamer.isBallDisappear())
            canvas.drawCircle((float)ballX, (float)ballY, (float)ballRadius, paint);

        //gun
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);
        canvas.drawLine(0, (float)gunY, (float)gunX, (float)gunY, paint);

        //bullet
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(15);
        if(!gamer.isBulletDisappear())
            canvas.drawCircle((float)bulletX, (float)bulletY, (float)bulletRadius, paint);

    }
}
