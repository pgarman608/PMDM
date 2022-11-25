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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvRegister;
    private EditText etNombre;
    private EditText etContrasena;
    private Button btLogin;
    public final static int REGISTERINT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(Color.parseColor("#FCC2FC"));
        getSupportActionBar().hide();

        tvRegister = (TextView) findViewById(R.id.tvRegistrar);
        etNombre = (EditText) findViewById(R.id.etNombreLogin);
        etContrasena = (EditText) findViewById(R.id.etContrasenaLogin);
        btLogin = (Button) findViewById(R.id.btnLogin);

        tvRegister.setOnClickListener(this);
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
        switch (view.getId()){
            case R.id.tvRegistrar:
                Intent intentRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intentRegister,1);
                break;
            case R.id.btnLogin:
                break;
        }
    }
}