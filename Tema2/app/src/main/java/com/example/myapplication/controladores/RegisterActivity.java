package com.example.myapplication.controladores;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.modelos.Cliente;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvLogin;
    private EditText etNombre;
    private EditText etContrasena;
    private Button btRegister;

    private SQLCliente sqlCliente;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setStatusBarColor(Color.parseColor("#D9EE66"));
        getSupportActionBar().hide();

        tvLogin = (TextView) findViewById(R.id.tvLogin);
        etNombre = (EditText) findViewById(R.id.etNombreRegister);
        etContrasena = (EditText) findViewById(R.id.etContrasenaRegister);
        btRegister = (Button) findViewById(R.id.btnRegister);

        sqlCliente = new SQLCliente(this);

        tvLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvLogin:
                Intent intentLogin1 = new Intent();
                intentLogin1.putExtra("result","");
                setResult(Activity.RESULT_CANCELED,intentLogin1);
                finish();
                break;
            case R.id.btnRegister:
                Cliente cliente = new Cliente(etNombre.getText().toString(),etContrasena.getText().toString());
                if (cliente.isEmpty() == 0){
                    if (sqlCliente.existeCliente(cliente) == 1){

                    }else{

                    }
                }else{

                }
                break;
        }
    }

}
