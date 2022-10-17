package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btToast = (Button) findViewById(R.id.buttonT);
        Button btAlert = (Button) findViewById(R.id.buttonA);
        Button btSnack = (Button) findViewById(R.id.buttonS);

        btToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastLargo("Esto es un toast");
            }
        });
        btAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertd = createAlertDialog("Alert","Esto es un alert");
                alertd.show();
            }
        });
        btSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Esto es un SnakBar",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private AlertDialog createAlertDialog(String titulo, String txt) {
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setMessage(txt).setTitle(titulo);
        ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"SIIII",Toast.LENGTH_SHORT).show();
            }
        });
        ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Noooo",Toast.LENGTH_SHORT).show();
            }
        });
        return ad.create();
    }

    private void toastLargo(String txt) {
        Toast.makeText(MainActivity.this,txt,Toast.LENGTH_LONG).show();
    }
}