package cosc426.assign32semestercost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;


public class LivingActivity extends AppCompatActivity {

    private Calculator calculator = MainActivity.calculator;

    CheckBox dormCheckBox;
    CheckBox dinningCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living);

        dormCheckBox = (CheckBox)findViewById(R.id.dorm);
        dinningCheckBox = (CheckBox)findViewById(R.id.dinning);

    }

    @Override
    protected void onStart() {
        super.onStart();

        updateView();
    }

    public void updateView()
    {
        dormCheckBox.setChecked(calculator.getLivingDorm());
        dinningCheckBox.setChecked(calculator.getLivingDinning());

    }

    public void submit()
    {
        if(dormCheckBox.isChecked())
            calculator.setLivingDorm(true);
        else
            calculator.setLivingDorm(false);

        if(dinningCheckBox.isChecked())
            calculator.setLivingDinning(true);
        else
            calculator.setLivingDinning(false);
    }

    public void next(View v)
    {
        submit();

        Intent summaryActivity = new Intent(this, SummaryActivity.class);
        startActivity(summaryActivity);

    }

    public void back(View v)
    {
        submit();

        finish();
    }
}
