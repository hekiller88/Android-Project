package cosc426.assign35unitconversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InchCentimeterActivity extends AppCompatActivity {

	private static final int INCH = 1;
    private static final int CENTIMETER = 2;
    EditText inchET;
    EditText centimeterET;
    TextChangeHandler forInch;
    TextChangeHandler forCentimeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inch_centimeter);

        inchET = (EditText)findViewById(R.id.input_inch);
        forInch = new TextChangeHandler(INCH);
        inchET.addTextChangedListener(forInch);

        centimeterET = (EditText)findViewById(R.id.input_centimeter);
        forCentimeter = new TextChangeHandler(CENTIMETER);
        centimeterET.addTextChangedListener(forCentimeter);
    }

    public void back(View v)
    {
        finish();
    }


    public class TextChangeHandler implements TextWatcher
    {
        private int whichOne;         //using to identify the EditText

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
                Toast.makeText(InchCentimeterActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();

                input = 0;
            }

            if(whichOne == INCH) {
                output = Convertor.inch2centimeter(input);
                centimeterET.removeTextChangedListener(forCentimeter);
                centimeterET.setText(output);
                centimeterET.addTextChangedListener(forCentimeter);

            }
            else if(whichOne == CENTIMETER) {
                output = Convertor.centimeter2inch(input);
                inchET.removeTextChangedListener(forInch);
                inchET.setText(output);
                inchET.addTextChangedListener(forInch);
            }

        }
    }
}
