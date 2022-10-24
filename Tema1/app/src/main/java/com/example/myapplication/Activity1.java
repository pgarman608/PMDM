package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity1 extends AppCompatActivity {
    Button bt_A1;
    EditText et_A1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        bt_A1 = (Button) findViewById(R.id.button6);
        et_A1 = (EditText) findViewById(R.id.editTextTextPersonName3);

        bt_A1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = et_A1.getText().toString();
                if (msg != null && !msg.equals("")){
                    Intent ri = new Intent();
                    ri.putExtra("result",msg);
                    setResult(1,ri);
                    finish();
                }
            }
        });
    }
}