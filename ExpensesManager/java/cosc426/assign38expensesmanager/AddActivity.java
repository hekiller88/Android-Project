package cosc426.assign38expensesmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbManager = new DatabaseManager(this);
    }

    public void submit(View v)
    {
        EditText nameEditText = (EditText)findViewById(R.id.input_name);
        String name = nameEditText.getText().toString();

        EditText priceEditText = (EditText)findViewById(R.id.input_price);
        String price = priceEditText.getText().toString();

        EditText dateEditText = (EditText)findViewById(R.id.input_date);
        String date = dateEditText.getText().toString();

        if(name == "" || price == "" || date == "") {
            Toast.makeText(this, "Fail!Pleas Input Sth!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!DataUnit.dateFormatCheck(date)){
            Toast.makeText(this, "Fail!Incorrect Date Format!", Toast.LENGTH_SHORT).show();
            return;
        }

        DataUnit data = new DataUnit(name, Float.parseFloat(price), date);
        dbManager.add(data);

        Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();
    }

    public void back(View v)
    {
        finish();
    }

}
