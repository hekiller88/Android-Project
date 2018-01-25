package cosc426.assign35unitconversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MileKilometerActivity extends AppCompatActivity {

    private static final int MILE = 1;
    private static final int KILOMETER = 2;
    EditText mileET;
    EditText kilometerET;
    TextChangeHandler forMile;
    TextChangeHandler forKilometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mile_kilometer);

        mileET = (EditText)findViewById(R.id.input_mile);
        forMile = new TextChangeHandler(MILE);
        mileET.addTextChangedListener(forMile);

        kilometerET = (EditText)findViewById(R.id.input_kilometer);
        forKilometer = new TextChangeHandler(KILOMETER);
        kilometerET.addTextChangedListener(forKilometer);
    }

    public void back(View v)
    {
        finish();
    }


    public class TextChangeHandler implements TextWatcher
    {
        private int whichOne;           //using to identify the EditText

        public TextChangeHandler(int i)
        {
            whichOne = i;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            float input;
            String output = "";

            try
            {
                input = Float.parseFloat(charSequence.toString());
            }
            catch (NumberFormatException e)
            {
                Toast.makeText(MileKilometerActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();

                input = 0;
            }

            if(whichOne == MILE) {
                output = Convertor.mile2kilometer(input);
                kilometerET.removeTextChangedListener(forKilometer);
                kilometerET.setText(output);
                kilometerET.addTextChangedListener(forKilometer);

            }
            else if(whichOne == KILOMETER) {
                output = Convertor.kilometer2mile(input);
                mileET.removeTextChangedListener(forMile);
                mileET.setText(output);
                mileET.addTextChangedListener(forMile);
            }

        }
    }
}
