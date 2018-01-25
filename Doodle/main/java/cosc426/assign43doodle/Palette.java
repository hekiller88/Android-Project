package cosc426.assign43doodle;

import android.graphics.Color;

/**
 * Created by lhe on 11/24/17.
 *
 * this is the class for Palette
 */


public class Palette {

    private int[] colors;
    private int index;

    public Palette() {

        colors = new int[]{Color.BLACK, Color.WHITE, Color.GRAY, Color.RED,
                Color.GREEN, Color.BLUE, Color.YELLOW, Color.parseColor("#8B4513")};

        index = 0;

    }

    public int nextColor()
    {
        if(index == 7)
            index = 0;
        else
            index++;

        return colors[index];
    }
}
