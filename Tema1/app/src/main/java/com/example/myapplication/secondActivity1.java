package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class secondActivity1 extends AppCompatActivity {

    private EditText ptS1;

    private Button btS1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second1);

        ptS1 = (EditText) findViewById(R.id.ptS1);

        btS1 = (Button) findViewById(R.id.btS1);

        btS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = ptS1.getText().toString();
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