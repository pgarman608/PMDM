package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_A1;
    TextView tv_A2;
    TextView tv_A3;

    Button bt_A1;
    Button bt_A2;
    Button bt_A3;

    public static int CODEA1 = 1;
    public static int CODEA2 = 2;
    public static int CODEA3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_A1 = (TextView) findViewById(R.id.textView);
        tv_A2 = (TextView) findViewById(R.id.textView2);
        tv_A3 = (TextView) findViewById(R.id.textView3);

        bt_A1 = (Button) findViewById(R.id.button3);
        bt_A2 = (Button) findViewById(R.id.button2);
        bt_A3 = (Button) findViewById(R.id.button);
        bt_A1.setOnClickListener(this);
        bt_A2.setOnClickListener(this);
        bt_A2.setOnClickListener(this);

    }
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d("main","onActivityResult");
                    Log.d("main", result.getResultCode()+"");
                }
            });
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3:
                Intent i1 = new Intent(getApplicationContext(), Activity1.class);
                i1.putExtra("message_key", "boton1");
                mStartForResult.launch(i1);
                break;
            case R.id.button2:
                Intent i2 = new Intent(MainActivity.this, Activity2.class);
                startActivity(i2);
                break;
            case R.id.button:
                Intent i3 = new Intent(MainActivity.this, Activity3.class);
                startActivity(i3);
                break;

        }
    }
}