package cosc426.assign45;

import java.util.TimerTask;

/**
 * Created by lhe on 11/25/17.
 */

public class GameTimerTask extends TimerTask {

    private Gamer gamer;
    private GameView gameView;

    public GameTimerTask(Gamer gamer, GameView gameView) {
        this.gamer = gamer;
        this.gameView = gameView;
    }

    @Override
    public void run() {
        gamer.update();

        gameView.postInvalidate();
    }
}
