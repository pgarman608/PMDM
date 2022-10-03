package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView cColor = (TextView) findViewById(R.id.tvChangeColor);
        TextView tvRed = (TextView) findViewById(R.id.tvRed);
        TextView tvGreen = (TextView) findViewById(R.id.tvGreen);
        TextView tvBlue = (TextView) findViewById(R.id.tvBlue);

        SeekBar sbRed = (SeekBar) findViewById(R.id.sbRed);
        SeekBar sbGreen = (SeekBar) findViewById(R.id.sbGreen);
        SeekBar sbBlue = (SeekBar) findViewById(R.id.sbBlue);
        
    }
}