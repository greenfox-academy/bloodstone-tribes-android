package com.greenfox.tribesoflagopusandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "greenfox.com.tribesoflagopus.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        intent.putExtra("exampleExtra", "some data");
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(message);
    }
<<<<<<< HEAD:TribesOfLagopus/app/src/main/java/greenfox/com/tribesoflagopus/MainActivity.java

=======
>>>>>>> 604dd461ce9807171a0caa9a9e29d1e0ddaffae2:app/src/main/java/com/greenfox/tribesoflagopusandroid/MainActivity.java

    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(message);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
