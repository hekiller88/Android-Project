package cosc426.assign32semestercost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreditActivity extends AppCompatActivity {

    private Calculator calculator = MainActivity.calculator;
    EditText inputCreditsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        inputCreditsEditText = (EditText)findViewById(R.id.input_credits);
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateView();
    }

    public void updateView()
    {
        inputCreditsEditText.setText(calculator.getCredits() + "");
    }

    public void submit()
    {
        String inputCreditsStr = inputCreditsEditText.getText().toString();

        int inputCredis;
        try{
            inputCredis = Integer.parseInt(inputCreditsStr);

        }
        catch(NumberFormatException e)
        {
            Toast.makeText(CreditActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
            return;
        }

        calculator.setCredits(inputCredis);
    }

    public void next(View v)
    {
        submit();

        Intent degreeActivity = new Intent(this, DegreeActivity.class);
        startActivity(degreeActivity);

    }

    public void back(View v)
    {
        submit();

        finish();
    }




}
