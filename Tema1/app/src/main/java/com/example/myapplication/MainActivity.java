package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvA1;
    private TextView tvA2;
    private TextView tvA3;

    private Button btA1;
    private Button btA2;
    private Button btA3;

    public final static int SA1 = 1;
    public final static int SA2 = 2;
    public final static int SA3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvA1 = (TextView) findViewById(R.id.tvA1);
        this.tvA2 = (TextView) findViewById(R.id.tvA2);
        this.tvA3 = (TextView) findViewById(R.id.tvA3);

        this.btA1 = (Button) findViewById(R.id.btA1);
        this.btA2 = (Button) findViewById(R.id.btA2);
        this.btA3 = (Button) findViewById(R.id.btA3);

        btA1.setOnClickListener(this);
        btA2.setOnClickListener(this);
        btA3.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View view) {
        switch (view.getId()){
            case R.id.btA1:
                Intent i1 = new Intent(MainActivity.this,secondActivity1.class);
                //i1.putExtra(String,String)
                startActivityForResult(i1,SA1);
                break;
            case R.id.btA2:
                Intent i2 = new Intent(MainActivity.this,secondActivity2.class);
                startActivityForResult(i2,SA2);
                break;
            case R.id.btA3:
                Intent i3 = new Intent(MainActivity.this,secondActivity3.class);
                startActivityForResult(i3,SA3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SA1:
                    tvA1.setText(data.getStringExtra("result"));
                    break;
                case SA2:
                    tvA2.setText(data.getStringExtra("result"));
                    break;
                case SA3:
                    tvA3.setText(data.getStringExtra("result"));
                    break;
            }
        }
    }
}