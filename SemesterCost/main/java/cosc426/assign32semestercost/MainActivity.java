package cosc426.assign32semestercost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();
    }

    public void onStart()
    {
        super.onStart();
    }

    public void next(View v)
    {
        Intent creditActivity = new Intent(this, CreditActivity.class);
        startActivity(creditActivity);
    }
}
