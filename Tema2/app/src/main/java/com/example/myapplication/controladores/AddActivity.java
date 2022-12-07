package com.example.myapplication.controladores;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.modelos.ApiCNTRL;

public class AddActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etDescripcion;
    private Button btCrear;
    private Button btGuardar;
    private ConnectTask connectTask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        getWindow().setStatusBarColor(Color.parseColor("#FCC2FC"));
        ActionBar barra = getSupportActionBar();
        barra.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FCC2FC")));
        barra.setTitle(Html.fromHtml("<font color='#006ca0'>Crear Imagen</font>"));
        getWindow().setNavigationBarColor(Color.parseColor("#FCC2FC"));
        btCrear = (Button) findViewById(R.id.btnCrear);

        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectTask = new ConnectTask();
                connectTask.execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnBack:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private class ConnectTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ApiCNTRL.generarPrediccion("Prueba");
            return null;
        }
    }
}
