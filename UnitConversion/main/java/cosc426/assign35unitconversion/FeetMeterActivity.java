package cosc426.assign35unitconversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FeetMeterActivity extends AppCompatActivity {

	private static final int FEET = 1;
    private static final int METER = 2;
    EditText feetET;
    EditText meterET;
    TextChangeHandler forFeet;
    TextChangeHandler forMeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feet_meter);

        feetET = (EditText)findViewById(R.id.input_feet);
        forFeet = new TextChangeHandler(FEET);
        feetET.addTextChangedListener(forFeet);

        meterET = (EditText)findViewById(R.id.input_meter);
        forMeter = new TextChangeHandler(METER);
        meterET.addTextChangedListener(forMeter);
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
                Toast.makeText(FeetMeterActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();

                input = 0;
            }

            if(whichOne == FEET) {
                output = Convertor.feet2meter(input);
                meterET.removeTextChangedListener(forMeter);
                meterET.setText(output);
                meterET.addTextChangedListener(forMeter);
            }
            else if(whichOne == METER) {
                output = Convertor.meter2feet(input);
                feetET.removeTextChangedListener(forFeet);
                feetET.setText(output);
                feetET.addTextChangedListener(forFeet);
            }

        }
    }
}
