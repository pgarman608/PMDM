package com.example.myapplication.controladores;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.modelos.ApiCNTRL;
import com.example.myapplication.modelos.IAImagen;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etDescripcion;
    private Button btCrear;
    private Button btGuardar;
    private ConnectTask connectTask;
    private ImageView imageAdd;

    private String image;

    private CircularProgressDrawable progressbar;
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
        btGuardar = (Button) findViewById(R.id.btnAniadir);
        btGuardar.setVisibility(View.INVISIBLE);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        imageAdd = (ImageView) findViewById(R.id.imgAdd);
        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectTask = new ConnectTask();
                connectTask.execute();
            }
        });
        String accion = getIntent().getAction();
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!accion.equals("aniadir") && accion.equals("editar")){
                    IAImagen imagenMod = new IAImagen();
                    imagenMod.setCodigo_Imagen(Integer.parseInt(getIntent().getStringExtra("cod")));
                    imagenMod.setDescripcion(etDescripcion.getText().toString());
                    SQLCliente db = new SQLCliente(AddActivity.this);
                    db.updateImagen(imagenMod);
                    Intent modImage = new Intent();
                    modImage.putExtra("result","mod");
                    setResult(Activity.RESULT_OK,modImage);
                    finish();
                }else{
                    IAImagen imagenTemp = new IAImagen(LoginActivity.clienteLog.getNombre(),
                            etNombre.getText().toString(),etDescripcion.getText().toString(),image);
                    SQLCliente db = new SQLCliente(AddActivity.this);
                    db.insertarImagen(imagenTemp);
                    Intent addImage = new Intent();
                    addImage.putExtra("result","add");
                    setResult(Activity.RESULT_OK,addImage);
                    finish();
                }
            }
        });
        progressbar = new CircularProgressDrawable(this);
        progressbar.setStrokeWidth(10f);
        progressbar.setStyle(CircularProgressDrawable.LARGE);
        progressbar.setCenterRadius(30f);
        progressbar.start();
        if (!accion.equals("aniadir")) {
            switch (accion) {
                case "mostrar":
                    btCrear.setVisibility(View.INVISIBLE);
                    btGuardar.setVisibility(View.INVISIBLE);
                    etDescripcion.setEnabled(false);
                    etNombre.setEnabled(false);
                    barra.setTitle(Html.fromHtml("<font color='#006ca0'>Mostrar Imagen</font>"));
                    Log.i(TAG, "onCreate: "+ getIntent().getStringExtra("name"));
                    etNombre.setText(getIntent().getStringExtra("name"));
                    etDescripcion.setText(getIntent().getStringExtra("desc"));
                    Glide.with(AddActivity.this)
                            .load(getIntent().getStringExtra("url"))
                            .placeholder(progressbar)
                            .error(R.drawable.error404)
                            .into(imageAdd);
                case "editar":
                    btCrear.setVisibility(View.INVISIBLE);
                    btGuardar.setText("Modificar");
                    etNombre.setEnabled(false);
                    btGuardar.setEnabled(true);
                    barra.setTitle(Html.fromHtml("<font color='#006ca0'>Editar Imagen</font>"));
                    etNombre.setText(getIntent().getStringExtra("name"));
                    etDescripcion.setText(getIntent().getStringExtra("desc"));
                    Glide.with(AddActivity.this)
                            .load(getIntent().getStringExtra("url"))
                            .placeholder(progressbar)
                            .error(R.drawable.error404)
                            .into(imageAdd);
                    break;
                default:

            }
        }
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
    private class ConnectTask extends AsyncTask<Void, String, String> {
        @Override
        protected void onPreExecute() {
            btCrear.setVisibility(View.INVISIBLE);
            etNombre.setVisibility(View.INVISIBLE);
            etDescripcion.setEnabled(false);
        }

        @Override
        protected String doInBackground(Void... voids) {
            String allJSON = ApiCNTRL.generarPrediccion(etNombre.getText().toString());
            Log.i(TAG, allJSON);
            return allJSON;
        }

        @Override
        protected void onPostExecute(String s) {
            String url = ApiCNTRL.getURLImage(s);
            Glide.with(AddActivity.this)
                    .load(url)
                    .placeholder(progressbar)
                    .error(R.drawable.error404)
                    .into(imageAdd);
            image = url;
            etDescripcion.setEnabled(true);
            btGuardar.setVisibility(View.VISIBLE);
        }

        public void cancel() {
        }
    }

}