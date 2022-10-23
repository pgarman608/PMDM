package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class secondActivity2 extends AppCompatActivity {

    private EditText ptS2;

    private Button btS2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        btS2 = (Button) findViewById(R.id.btS2);

        ptS2 = (EditText) findViewById(R.id.ptS2);

        btS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = ptS2.getText().toString();
                if (msg != null && !msg.equals("")){
                    Intent i = new Intent();
                    i.putExtra("result",msg);
                    setResult(Activity.RESULT_OK,i);
                    finish();
                }
            }
        });
    }
}