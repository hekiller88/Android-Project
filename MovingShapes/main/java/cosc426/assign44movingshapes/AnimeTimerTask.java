package cosc426.assign44movingshapes;

import java.util.TimerTask;

/**
 * Created by lhe on 11/25/17.
 */

public class AnimeTimerTask extends TimerTask {

    private Shape[] shapes;
    private AnimeView animeView;

    public AnimeTimerTask(Shape[] shapes, AnimeView animeView) {
        this.shapes = shapes;
        this.animeView = animeView;
    }

    @Override
    public void run() {

        for(Shape shape: shapes)
        {
            shape.move();
            animeView.postInvalidate();
        }
    }
}
