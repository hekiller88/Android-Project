package cosc426.assign34direction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_east);
    }

    public void back(View v)
    {
        finish();
        overridePendingTransition(R.anim.back_incoming, 0);
    }
}
