package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {
    Button bt_A2;
    EditText et_A2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        bt_A2 = (Button) findViewById(R.id.button5);
        et_A2 = (EditText) findViewById(R.id.editTextTextPersonName2);

        bt_A2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = et_A2.getText().toString();
                if (msg != null && !msg.equals("")){
                    Intent ri = new Intent();
                    ri.putExtra("result",msg);
                    setResult(2,ri);
                    finish();
                }
            }
        });
    }
}