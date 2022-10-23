package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class secondActivity3 extends AppCompatActivity {

    private EditText ptS3;

    private Button btS3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second3);

        ptS3 = (EditText) findViewById(R.id.ptS3);

        btS3 = (Button) findViewById(R.id.btS3);

        btS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = ptS3.getText().toString();
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