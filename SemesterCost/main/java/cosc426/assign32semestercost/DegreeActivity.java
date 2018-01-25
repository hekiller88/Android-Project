package cosc426.assign32semestercost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class DegreeActivity extends AppCompatActivity {

    private Calculator calculator = MainActivity.calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree);
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateView();
    }

    public void updateView()
    {
        RadioButton radioButton_undergradute = (RadioButton)findViewById(R.id.radio_undergraduate);
        RadioButton radioButton_gradute = (RadioButton)findViewById(R.id.radio_graduate);

        Calculator.Degree d = calculator.getDegree();

        if(d == Calculator.Degree.UNDERGRADUATE)
            radioButton_undergradute.setChecked(true);
        else
            radioButton_gradute.setChecked(true);
    }

    public void submit()
    {
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radio);

        int id_checked = radioGroup.getCheckedRadioButtonId();

        if(id_checked == R.id.radio_undergraduate)
            calculator.setDegree(Calculator.Degree.UNDERGRADUATE);
        else if(id_checked == R.id.radio_graduate)
            calculator.setDegree(Calculator.Degree.GRADUATE);
        else;
    }

    public void next(View v)
    {
        submit();

        Intent livingActivity = new Intent(this, LivingActivity.class);
        startActivity(livingActivity);

    }

    public void back(View v)
    {
        submit();

        finish();
    }
}
