package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        bt_A3 = (Button) findViewById(R.id.button1);
        bt_A1.setOnClickListener(this);
        bt_A2.setOnClickListener(this);
        bt_A3.setOnClickListener(this);

    }
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent iexist = result.getData();
                    Bundle bun1 = iexist.getExtras();
                    Log.d("main",iexist.getStringExtra("result"));
                    switch (result.getResultCode()){
                        case 1:
                            tv_A1.setText(iexist.getStringExtra("result"));
                            break;
                        case 2:
                            tv_A2.setText(bun1.getString("result"));
                            break;
                        case 3:
                            tv_A3.setText(bun1.getString("result"));
                            break;
                        default:
                            Toast.makeText(null,"Error de activity",Toast.LENGTH_LONG);
                    }
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
                mStartForResult.launch(i2);
                break;
            case R.id.button1:
                Log.d("main","PRueba1");
                Intent i3 = new Intent(MainActivity.this, Activity3.class);
                mStartForResult.launch(i3);
                break;

        }
    }
}