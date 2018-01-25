package cosc426.assign33encrypttext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class KeyActivity extends AppCompatActivity {

    private Cipher cipher = MainActivity.cipher;

    EditText keyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);

        keyEditText = (EditText)findViewById(R.id.input_key);

        updateView();
    }

    public void updateView()
    {
        keyEditText.setText(cipher.getKey() + "");
    }

    public void submit(View v)
    {
        String keyStr = keyEditText.getText().toString();

        int newKey;
        try
        {
            newKey = Integer.parseInt(keyStr);
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, "Invalid Key", Toast.LENGTH_LONG).show();
            return;
        }

        cipher.setKey(newKey);

        finish();
    }
}
