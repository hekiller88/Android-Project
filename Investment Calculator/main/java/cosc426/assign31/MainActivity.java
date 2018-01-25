package cosc426.assign31;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View v)
    {
        EditText currentPrincipalET = (EditText)findViewById(R.id.currentPrincipal);
        String currentPrincipalStr = currentPrincipalET.getText().toString();

        EditText annualAdditionET = (EditText)findViewById(R.id.annualAddition);
        String annualAdditionStr = annualAdditionET.getText().toString();

        EditText yearsET = (EditText)findViewById(R.id.years);
        String yearsStr = yearsET.getText().toString();

        EditText ratesET = (EditText)findViewById(R.id.rates);
        String ratesStr = ratesET.getText().toString();


        int currentPrincipal, annualAddition, years, rates;

        try {
            currentPrincipal = Integer.parseInt(currentPrincipalStr);
            annualAddition = Integer.parseInt(annualAdditionStr);
            years = Integer.parseInt(yearsStr);
            rates = Integer.parseInt(ratesStr);
        }
        catch(NumberFormatException e)
        {
            Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
            return;
        }

        Intent secondActivity = new Intent(this, SecondActivity.class);
        secondActivity.putExtra("Current Principal", currentPrincipal);
        secondActivity.putExtra("Annual Addition", annualAddition);
        secondActivity.putExtra("Years", years);
        secondActivity.putExtra("Rates", rates);

        startActivity(secondActivity);
    }
}
