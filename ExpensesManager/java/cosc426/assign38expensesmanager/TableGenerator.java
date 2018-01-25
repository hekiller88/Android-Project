package cosc426.assign38expensesmanager;

import android.content.Context;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by lhe on 11/13/17.
 */


//This is a GridLayout, to show the printout table
public class TableGenerator extends GridLayout {

    private TextView[] names;
    private TextView[] prices;
    private TextView[] dates;

    private int slotWidth;
    private int slotHeight;

    public TableGenerator(Context context, int slotWidth, LinkedList<DataUnit> list)
    {
        super(context);

        int rows = list.size() + 1;
        int cols = 3;

        setRowCount(rows);
        setColumnCount(cols);

        this.slotWidth = slotWidth;
        slotHeight = (int)(slotWidth / 2.5);

        ScrollView.LayoutParams paramsGrid = new ScrollView.LayoutParams(0, 0);
        paramsGrid.width = cols * slotWidth ;
        paramsGrid.height = rows * slotHeight;
        setLayoutParams(paramsGrid);

        names = new TextView[rows];
        prices = new TextView[rows];
        dates = new TextView[rows];

        names[0] = new TextView(context);
        setTextView(names[0], "Name", 0, 0);
        names[0].setBackgroundColor(Color.parseColor("#33CC8F"));
        addView(names[0]);

        prices[0] = new TextView(context);
        setTextView(prices[0], "Price", 0, 1);
        prices[0].setBackgroundColor(Color.parseColor("#33CC8F"));
        addView(prices[0]);

        dates[0] = new TextView(context);
        setTextView(dates[0], "Date", 0, 2);
        dates[0].setBackgroundColor(Color.parseColor("#33CC8F"));
        addView(dates[0]);

        for(int i = 1; i < rows; i++)
        {
            DataUnit data = list.get(i - 1);

            names[i] = new TextView(context);
            setTextView(names[i], data.getName(), i, 0);
            addView(names[i]);

            prices[i] = new TextView(context);
            setTextView(prices[i], String.format("%.2f", data.getPrice()) , i, 1);
            addView(prices[i]);

            dates[i] = new TextView(context);
            setTextView(dates[i], data.getDate(), i, 2);
            addView(dates[i]);
        }


    }

    public void setTextView(TextView tv, String text, int row, int col)
    {
        int DP = (int)(getResources().getDisplayMetrics().density);

        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.parseColor("#ABC5F1"));
        tv.setPadding(10*DP, 10*DP, 10*DP, 10*DP);
        tv.setGravity(Gravity.CENTER);

        tv.setMaxLines(1);
        tv.setMovementMethod(new ScrollingMovementMethod());

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = slotWidth;
        params.height = slotHeight;
        params.rowSpec = GridLayout.spec(row, 1);
        params.columnSpec = GridLayout.spec(col, 1);
        params.bottomMargin = params.topMargin = 1;
        params.leftMargin = params.rightMargin = 1;
        tv.setLayoutParams(params);
    }

}
