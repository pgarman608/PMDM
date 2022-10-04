package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private TextView cColor;
    private TextView tvRed;
    private TextView tvGreen;
    private TextView tvBlue;
    private SeekBar sbRed;
    private SeekBar sbGreen;
    private SeekBar sbBlue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cColor = (TextView) findViewById(R.id.tvChangeColor);
        tvRed = (TextView) findViewById(R.id.tvRed);
        tvGreen = (TextView) findViewById(R.id.tvGreen);
        tvBlue = (TextView) findViewById(R.id.tvBlue);

        sbRed = (SeekBar) findViewById(R.id.sbRed);
        sbGreen = (SeekBar) findViewById(R.id.sbGreen);
        sbBlue = (SeekBar) findViewById(R.id.sbBlue);
        //Este es para cambiar el color al circulo del seekbar
        sbRed.getThumb().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
        //Este es para cambiar el color de la barra del seekbar
        sbRed.getProgressDrawable().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
        sbGreen.getThumb().setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
        sbGreen.getProgressDrawable().setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
        sbBlue.getThumb().setColorFilter(Color.parseColor("#0000FF"), PorterDuff.Mode.SRC_IN);
        sbBlue.getProgressDrawable().setColorFilter(Color.parseColor("#0000FF"), PorterDuff.Mode.SRC_IN);
        sbRed.setMax(255);
        sbRed.setMin(0);
        sbGreen.setMax(255);
        sbGreen.setMin(0);
        sbBlue.setMax(255);
        sbBlue.setMin(0);
        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);
    }
    private int rojo = 0;
    private int azul = 0;
    private int verde = 0;
    @Override
    public void onProgressChanged(@NonNull SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()){
            case R.id.sbRed:
                seekBar.setProgress(i);
                tvRed.setText("Rojo: " + i);
                rojo = i;
                cColor.setBackgroundColor(Color.rgb(rojo, azul,verde));
                break;
            case R.id.sbBlue:
                seekBar.setProgress(i);
                azul = i;
                tvBlue.setText("Azul: " + i);
                cColor.setBackgroundColor(Color.rgb(rojo, verde,azul));
                break;
            case R.id.sbGreen:
                seekBar.setProgress(i);
                verde = i;
                tvGreen.setText("Green: " + i);
                cColor.setBackgroundColor(Color.rgb(rojo, verde,azul));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}