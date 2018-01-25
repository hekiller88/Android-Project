package cosc426.assign37passwordmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.LinkedList;

public class DeleteActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        dbManager = new DatabaseManager(this);

        updateView();

    }

    private void updateView()
    {
        LinkedList<DataUnit> list = dbManager.all();

        if(list.size() > 0)
        {
            RadioGroup group = new RadioGroup(this);

            RadioButton[] buttons = new RadioButton[list.size()];

            for(int i = 0; i < list.size(); i++)
            {
                DataUnit data = list.get(i);

                buttons[i] = new RadioButton(this);
                buttons[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                buttons[i].setText(data.getPlace());

                RadioButtonHandler tmp = new RadioButtonHandler(data.getPlace());
                buttons[i].setOnClickListener(tmp);

                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(0, 0);
                params.width = RadioGroup.LayoutParams.WRAP_CONTENT;
                params.height = RadioGroup.LayoutParams.WRAP_CONTENT;
                params.leftMargin = params.rightMargin = 30;
                params.topMargin = params.bottomMargin = 5;
                buttons[i].setLayoutParams(params);

                group.addView(buttons[i]);

            }

            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
            scroll.addView(group);
        }
        else
        {
            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
        }
    }

    private class RadioButtonHandler implements  View.OnClickListener
    {
        private String place;

        public RadioButtonHandler(String place)
        {
            this.place = place;
        }

        @Override
        public void onClick(View view) {

            dbManager.delete(place);

            Toast.makeText(DeleteActivity.this, "Data Deleted!", Toast.LENGTH_SHORT).show();

            updateView();
        }
    }

    public void back(View v)
    {
        finish();
    }
}
