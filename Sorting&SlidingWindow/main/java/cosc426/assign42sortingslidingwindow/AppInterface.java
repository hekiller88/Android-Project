package cosc426.assign42sortingslidingwindow;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lhe on 11/23/17.
 */

public class AppInterface extends LinearLayout {

    private final int SIZE = 10;
    private TextView[] numbersTV;
    private TextView status;

    private int DP;
    private int window_upperSlotIndex;

    public AppInterface(Context context, int screenHeight)
    {
        super(context);

        setOrientation(LinearLayout.VERTICAL);

        DP = (int)(getResources().getDisplayMetrics().density);

        RelativeLayout.LayoutParams paramsL = new RelativeLayout.LayoutParams(0, 0);
        paramsL.width = 200 * DP;
        paramsL.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        setLayoutParams(paramsL);

        int slotWidth = 200 * DP;
        int slotHeight = screenHeight / (SIZE + 2);

        numbersTV = new TextView[SIZE];
        for( int i = 0; i < SIZE; i++)
        {
            numbersTV[i] = new TextView(context);
            numbersTV[i].setId(TextView.generateViewId());
            numbersTV[i].setBackgroundColor(Color.parseColor("#D8D8D8"));
            numbersTV[i].setTextColor(Color.BLACK);
            numbersTV[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            numbersTV[i].setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);
            params.width = slotWidth;
            params.height = slotHeight;
            params.topMargin = 1;
            numbersTV[i].setLayoutParams(params);
            addView(numbersTV[i]);
        }

        status = new TextView(context);
        status.setId(TextView.generateViewId());
        status.setBackgroundColor(Color.YELLOW);
        status.setTextColor(Color.BLACK);
        status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        status.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams param_s = new LinearLayout.LayoutParams(0, 0);
        param_s.width = slotWidth;
        param_s.height = slotHeight * 2;
        param_s.topMargin = 1;
        status.setLayoutParams(param_s);
        addView(status);

        startWindow();
    }

    public int getUpperSlotIndex()
    {
        return window_upperSlotIndex;
    }

    //the window's upper slot index
    // can only be 0 ~ 8, not include 9
    public void startWindow()
    {
        window_upperSlotIndex = (int)(Math.random() * 9);

        numbersTV[window_upperSlotIndex].setBackgroundColor(Color.parseColor("#ABC5F1"));
        numbersTV[window_upperSlotIndex + 1].setBackgroundColor(Color.parseColor("#ABC5F1"));
    }

    //slide down window and refill color
    public void slideDownWindow()
    {
        numbersTV[window_upperSlotIndex++].setBackgroundColor(Color.parseColor("#D8D8D8"));

        if(window_upperSlotIndex == 9)
        {
            numbersTV[window_upperSlotIndex].setBackgroundColor(Color.parseColor("#D8D8D8"));

            window_upperSlotIndex = 0;
            numbersTV[window_upperSlotIndex].setBackgroundColor(Color.parseColor("#ABC5F1"));
            numbersTV[window_upperSlotIndex + 1].setBackgroundColor(Color.parseColor("#ABC5F1"));
        }
        else {
            numbersTV[window_upperSlotIndex].setBackgroundColor(Color.parseColor("#ABC5F1"));
            numbersTV[window_upperSlotIndex + 1].setBackgroundColor(Color.parseColor("#ABC5F1"));
        }
    }

//    public void swapWindow()
//    {
//
//    }

    public void setNumbers(int[] numbers)
    {
        for(int i = 0; i < SIZE; i++)
            numbersTV[i].setText(numbers[i] + "");
    }

    public void setStatusText(String text)
    {
        status.setText(text);
    }
}
