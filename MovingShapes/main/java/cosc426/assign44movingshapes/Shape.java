package cosc426.assign44movingshapes;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by lhe on 11/24/17.
 */

public class Shape {

    public static final int SQUARE = 0;
    public static final int CIRCLE = 1;
    private int[] colors = new int[]{Color.CYAN, Color.WHITE, Color.GRAY, Color.RED,
            Color.GREEN, Color.BLUE, Color.YELLOW, Color.parseColor("#8B4513")};

    //position
    private int screenHeight;
    private int screenWidth;

    private int shape;
    private int color;
    private Direction direction;

    private int centerX;
    private int centerY;
    private int radius;
    private int speed;

    private Paint paint;

    public Shape(int screenWidth, int screenHeight, int shape) {

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

        Random random = new Random();

        this.shape = shape;

        color = colors[random.nextInt(7)];

        int tmpDir = random.nextInt(3);
        switch (tmpDir){
            case 0:
                direction = Direction.NORTH;
                break;
            case 1:
                direction = Direction.SOUTH;
                break;
            case 2:
                direction = Direction.EAST;
                break;
            case 3:
                direction = Direction.WEST;
                break;
        }

        centerX = random.nextInt(screenWidth);
        centerY = random.nextInt(screenHeight);

        //25 ~ 75
        radius = 25 + random.nextInt(51);

        //5 ~ 30
        speed = 5 + random.nextInt(26);

        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

    }

    private enum Direction
    {
        NORTH, SOUTH, EAST, WEST
    }

    public void move()
    {
        //if touch the boundry, appear in the opposite boundry
        switch (direction) {
            case NORTH:
                centerY = centerY <= 0 ? screenHeight : centerY - speed;
                break;
            case SOUTH:
                centerY = centerY >= screenHeight ? 0 : centerY + speed;
                break;
            case EAST:
                centerX = centerX >= screenWidth ? 0 : centerX + speed;
                break;
            case WEST:
                centerX = centerX <= 0 ? screenWidth: centerX - speed;
                break;
        }

    }

    public int getCenterX()
    {
        return centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }

    public int getRadius()
    {
        return radius;
    }

    public int getShape()
    {
        return shape;
    }

    public Paint getPaint()
    {
        return paint;
    }


}
