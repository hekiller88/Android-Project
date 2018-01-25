package cosc426.assign38expensesmanager;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.LinkedList;

public class ViewNameActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_name);

        dbManager = new DatabaseManager(this);

    }

    public void updateView()
    {
        EditText inputEditeText = (EditText)findViewById(R.id.input_name);
        String name = inputEditeText.getText().toString();

        LinkedList<DataUnit> list = dbManager.select_name(name);

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
