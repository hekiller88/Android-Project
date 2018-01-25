package cosc426.assign31;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        updateView();
    }

    public void updateView()
    {
        Intent intent = getIntent();
        int currentPrincipal = intent.getIntExtra("Current Principal", 0);
        int annualAddition = intent.getIntExtra("Annual Addition", 0);
        int years = intent.getIntExtra("Years", 0);
        int rates = intent.getIntExtra("Rates", 0);

        calculator = new Calculator(currentPrincipal, annualAddition, rates, years);

        String[] yearsStr = calculator.getYearsStr();
        String[] amountStr = calculator.getAmountStr();
        int len = yearsStr.length + 1;

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x / 2;
        int height = size.y * 6 / 10;

        int DP = (int)(getResources().getDisplayMetrics().density);

        //Log.w("Log", "width: " + width + "\t" + "height: " + height + "\t" + "SceenY: " + size.y);

        GridLayout grid = new GridLayout(this);
        grid.setRowCount(len);
        grid.setColumnCount(2);
        ScrollView.LayoutParams paramsSc = new ScrollView.LayoutParams(0, 0);
        paramsSc.width = width * 2;
        //paramsSc.height = size.y * 6 / 10;
        paramsSc.height = ScrollView.LayoutParams.WRAP_CONTENT;
        grid.setLayoutParams(paramsSc);

        TextView[] yearsTV = new TextView[len];
        TextView[] amountTV = new TextView[len];

        int j = 0;
        for(int i = 0; i < len; i++)
        {
            GridLayout.LayoutParams params;

            yearsTV[i] = new TextView(this);
            if(i == 0)
                yearsTV[i].setText("Year");
            else
                yearsTV[i].setText(yearsStr[j]);
            yearsTV[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            yearsTV[i].setTextColor(Color.BLACK);
            yearsTV[i].setBackgroundColor(Color.parseColor("#ABC5F1"));
            yearsTV[i].setPadding(10*DP, 10*DP, 10*DP, 10*DP);
            yearsTV[i].setGravity(Gravity.CENTER);
            params = new GridLayout.LayoutParams();
            params.width = width;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.rowSpec = GridLayout.spec(i, 1);
            params.columnSpec = GridLayout.spec(0, 1);
            params.topMargin = params.bottomMargin = 1;
            params.leftMargin = params.rightMargin = 1;
            yearsTV[i].setLayoutParams(params);
            grid.addView(yearsTV[i]);

            amountTV[i] = new TextView(this);
            if(i == 0)
                amountTV[i].setText("Amount");
            else
                amountTV[i].setText(amountStr[j++]);
            amountTV[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            amountTV[i].setTextColor(Color.BLACK);
            amountTV[i].setBackgroundColor(Color.parseColor("#D8D8D8"));
            amountTV[i].setPadding(10*DP, 10*DP, 10*DP, 10*DP);
            amountTV[i].setGravity(Gravity.CENTER);
            amountTV[i].setHorizontallyScrolling(true);
            params = new GridLayout.LayoutParams();
            params.width = width;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.rowSpec = GridLayout.spec(i, 1);
            params.columnSpec = GridLayout.spec(1, 1);
            params.topMargin = params.bottomMargin = 1;
            params.leftMargin = params.rightMargin = 1;
            amountTV[i].setLayoutParams(params);

            grid.addView(amountTV[i]);
        }

        ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
        scroll.removeAllViewsInLayout();
        scroll.addView(grid);


    }

    public void back(View v)
    {
        SecondActivity.this.finish();
    }
}
