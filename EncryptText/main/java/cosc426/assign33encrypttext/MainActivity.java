package cosc426.assign33encrypttext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static Cipher cipher;
    EditText inputEditText;
    Button encryptButton;
    Button decrptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cipher = new Cipher();
        inputEditText = (EditText)findViewById(R.id.input_text);
        encryptButton = (Button)findViewById(R.id.encrypt);
        decrptButton = (Button)findViewById(R.id.decrypt);

        updateView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateView();
    }

    public void updateView()
    {
        inputEditText.setText(cipher.getText());
    }


    public void encrypt(View v)
    {
        String text = inputEditText.getText().toString();
        cipher.setText(text);
        inputEditText.setText(cipher.encrypts());

        //forbid the user continuesly click
        //the same cipher button
        encryptButton.setClickable(false);
        decrptButton.setClickable(true);
    }

    public void decrypt(View v)
    {
        String text = inputEditText.getText().toString();
        cipher.setText(text);
        inputEditText.setText(cipher.decrypts());

        //forbid the user continuesly click
        //the same cipher button
        encryptButton.setClickable(true);
        decrptButton.setClickable(false);
    }

    public void keyChange(View v)
    {
        String text = inputEditText.getText().toString();
        cipher.setText(text);

        Intent keyActivity = new Intent(this, KeyActivity.class);
        startActivity(keyActivity);
    }
}
