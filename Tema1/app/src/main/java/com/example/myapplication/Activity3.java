package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity3 extends AppCompatActivity {
    EditText et_A3;
    Button bt_A3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        et_A3 = (EditText) findViewById(R.id.editTextTextPersonName);
        bt_A3 = (Button) findViewById(R.id.button4);

        bt_A3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = et_A3.getText().toString();
                if (msg != null && !msg.equals("")){
                    Intent ri = new Intent();
                    ri.putExtra("result",msg);
                    setResult(3,ri);
                    finish();
                }
            }
        });
    }
}