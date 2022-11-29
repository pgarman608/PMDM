package com.example.myapplication.controladores;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.modelos.Cliente;
import com.prathameshmore.toastylibrary.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvRegister;
    private EditText etNombre;
    private EditText etContrasena;
    private Button btLogin;
    public final static int REGISTERINT = 1;
    public static Cliente clienteLog;
    private Toasty toasty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toasty = new Toasty(this);
        getWindow().setStatusBarColor(Color.parseColor("#FCC2FC"));
        getSupportActionBar().hide();

        tvRegister = (TextView) findViewById(R.id.tvRegistrar);
        etNombre = (EditText) findViewById(R.id.etNombreLogin);
        etContrasena = (EditText) findViewById(R.id.etContrasenaLogin);
        btLogin = (Button) findViewById(R.id.btnLogin);

        tvRegister.setOnClickListener(this);
        btLogin.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            String[] cliente = data.getStringExtra("result").split("-");
            etNombre.setText(cliente[0]);
            etContrasena.setText(cliente[1]);
        }
    }

    @Override
    public void onClick(View view) {
        try{
            switch (view.getId()){
                case R.id.tvRegistrar:
                    Intent intentRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivityForResult(intentRegister,1);
                    break;
                case R.id.btnLogin:
                    clienteLog = new Cliente(etNombre.getText().toString(),etContrasena.getText().toString());
                    if(clienteLog.isEmpty() == 0){
                        SQLCliente sqlCliente = new SQLCliente(this);
                        toasty.dangerToasty(this,""+sqlCliente.existeCliente(clienteLog),Toasty.LENGTH_SHORT,Toasty.CENTER);
                        if(sqlCliente.existeCliente(clienteLog)!=0) {
                            Intent intentLogin = new Intent(LoginActivity.this,ListActivity.class);
                            startActivity(intentLogin);
                        }else{
                            toasty.dangerToasty(this,"No existe el usuario",Toasty.LENGTH_SHORT,Toasty.CENTER);
                        }
                    }else{
                        toasty.dangerToasty(this,"Introduce datos en los campos", Toasty.LENGTH_SHORT,Toasty.CENTER);
                    }
                    break;
            }
        }catch(Exception ex){
            toasty.dangerToasty(this,ex.getMessage(),Toasty.LENGTH_SHORT,Toasty.CENTER);
        }
    }
}