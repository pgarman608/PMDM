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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.modelos.Cliente;
import com.prathameshmore.toastylibrary.Toasty;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvLogin;
    private EditText etNombre;
    private EditText etContrasena;
    private Button btRegister;

    private SQLCliente sqlCliente;
    private Toasty toasty;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toasty = new Toasty(RegisterActivity.this);
        getWindow().setStatusBarColor(Color.parseColor("#D9EE66"));
        getSupportActionBar().hide();
        getWindow().setNavigationBarColor(Color.parseColor("#D9EE66"));


        tvLogin = (TextView) findViewById(R.id.tvLogin);
        etNombre = (EditText) findViewById(R.id.etNombreRegister);
        etContrasena = (EditText) findViewById(R.id.etContrasenaRegister);
        btRegister = (Button) findViewById(R.id.btnRegister);

        sqlCliente = new SQLCliente(this);

        tvLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
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
                    if (sqlCliente.existeCliente(cliente) != sqlCliente.insertarCliente(cliente)){
                        toasty.successToasty(this,"Usuario añadio",Toasty.BOTTOM,Toasty.LENGTH_SHORT);
                        Intent intentregister = new Intent();
                        intentregister.putExtra("result",cliente.toString());
                        setResult(Activity.RESULT_OK,intentregister);
                        finish();
                    }else{
                        toasty.dangerToasty(this,"Ya existe ese usuario",Toasty.LENGTH_SHORT,Toasty.CENTER);
                    }
                }else{
                    toasty.dangerToasty(this,"Introduce datos en los campos",Toasty.LENGTH_SHORT,Toasty.CENTER);
                }
                break;
        }
    }

}
