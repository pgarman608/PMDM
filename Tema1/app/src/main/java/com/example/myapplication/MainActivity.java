package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView solucion = (TextView) findViewById(R.id.solucion);
        EditText numeroTop = (EditText) findViewById(R.id.NumeroTop);
        EditText numerobuton = (EditText) findViewById(R.id.numeroButton);
        Button sumar = (Button) findViewById(R.id.btsuma);
        Button restar = (Button) findViewById(R.id.btresta);
        Button dividi = (Button) findViewById(R.id.btdividir);
        Button multiplica = (Button) findViewById(R.id.btmultiplicar);

        sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solucion.setText(calcular(sumar,numeroTop,numerobuton));
                solucion.setBackgroundColor(Color.parseColor("#80B906"));
            }
        });
        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solucion.setText(calcular(restar,numeroTop,numerobuton));
                solucion.setBackgroundColor(Color.parseColor("#80B906"));
            }
        });
        dividi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solucion.setText(calcular(dividi,numeroTop,numerobuton));
                solucion.setBackgroundColor(Color.parseColor("#80B906"));
            }
        });
        multiplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solucion.setText(calcular(multiplica,numeroTop,numerobuton));
                solucion.setBackgroundColor(Color.parseColor("#80B906"));
            }
        });
    }
    private String calcular(Button b1, EditText num1,EditText num2){
        int sol = 0;
        switch (b1.getId()){
            case R.id.btsuma:
                sol = Integer.parseInt(num1.getText().toString()) + Integer.parseInt(num2.getText().toString());
                break;
            case R.id.btresta:
                sol = Integer.parseInt(num1.getText().toString()) - Integer.parseInt(num2.getText().toString());
                break;
            case R.id.btdividir:
                sol = Integer.parseInt(num1.getText().toString()) / Integer.parseInt(num2.getText().toString());
                break;
            case R.id.btmultiplicar:
                sol = Integer.parseInt(num1.getText().toString()) * Integer.parseInt(num2.getText().toString());
                break;
        }
        return ""+sol;
    }
}