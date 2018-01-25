package cosc426.assign32semestercost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private Calculator calculator = MainActivity.calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        updateView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateView();
    }

    public void updateView()
    {
        TextView outputTextView = (TextView)findViewById(R.id.output);
        outputTextView.setText(calculator.getSummary());
    }


    public void back(View v)
    {
        finish();
    }


}
