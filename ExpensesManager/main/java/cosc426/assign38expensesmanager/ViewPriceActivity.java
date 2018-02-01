package cosc426.assign38expensesmanager;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.LinkedList;

public class ViewPriceActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_price);

        dbManager = new DatabaseManager(this);

    }

    public void updateView()
    {
        EditText inputLowerEditeText = (EditText)findViewById(R.id.input_price_lower);
        String lowerStr = inputLowerEditeText.getText().toString();

        EditText inputUpperEditeText = (EditText)findViewById(R.id.input_price_upper);
        String upperStr = inputUpperEditeText.getText().toString();

        float lower, upper;
        try {
            lower = Float.parseFloat(lowerStr);
            upper = Float.parseFloat(upperStr);
        }
        catch(NumberFormatException e)
        {
            Toast.makeText(this, "Invalid Input!", Toast.LENGTH_LONG).show();
            return;
        }

        LinkedList<DataUnit> list = dbManager.select_price(lower, upper);

        if(list.size() > 0)
        {
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int slotWidth = size.x / 3;

            TableGenerator grid = new TableGenerator(this, slotWidth, list);

            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
            scroll.addView(grid);

        }
        else
        {
            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();

            Toast.makeText(this, "Item Didn't Find!", Toast.LENGTH_LONG).show();
        }
    }

    public void submit(View v)
    {
        updateView();
    }

    public void back(View v)
    {
        finish();
    }
}
