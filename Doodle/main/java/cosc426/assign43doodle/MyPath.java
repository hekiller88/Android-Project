package cosc426.assign43doodle;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by lhe on 11/24/17.
 *
 * a special class to save path and paint for drawPath method
 * so that it can save the previous path with different color
 */

public class MyPath {

    public Path path;
    public Paint paint;

    public MyPath(Path path, Paint paint) {
        this.path = new Path(path);
        this.paint = new Paint(paint);
    }


}
