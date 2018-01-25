package cosc426.assign37passwordmanager;

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

        EditText placeEditText = (EditText)findViewById(R.id.input_place);
        String place = placeEditText.getText().toString();

        EditText passwordEditText = (EditText)findViewById(R.id.input_password);
        String password = passwordEditText.getText().toString();

        if(place == "" || password == "") {
            Toast.makeText(this, "Fail!Pleas Input sth!", Toast.LENGTH_SHORT).show();
            return;
        }

        DataUnit data = new DataUnit(place, password);
        dbManager.add(data);

        Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();


    }

    public void back(View v)
    {
        finish();
    }
}
