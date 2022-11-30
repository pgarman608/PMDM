package com.example.myapplication.controladores;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ReciclerAdapterImag;
import com.example.myapplication.modelos.IAImagen;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvVacio;

    private ReciclerAdapterImag imageHolder;

    private ArrayList<IAImagen> imagenes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getWindow().setStatusBarColor(Color.parseColor("#FCC2FC"));
        ActionBar barra = getSupportActionBar();
        barra.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FCC2FC")));
        barra.setTitle(Html.fromHtml("<font color='#006ca0'>Imagenes Guardadas</font>"));
        getWindow().setNavigationBarColor(Color.parseColor("#FCC2FC"));

        rellenarArray();

        recyclerView = (RecyclerView) findViewById(R.id.rvImagenes);
        tvVacio = (TextView) findViewById(R.id.tvVacio);

        imageHolder = new ReciclerAdapterImag(imagenes);

        if (imagenes.size() > 0){
            tvVacio.setText("");
        }

        GridLayoutManager matrizlayout = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(matrizlayout);
        recyclerView.setAdapter(imageHolder);
    }

    private void rellenarArray(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SQLCliente sqlCliente = new SQLCliente(ListActivity.this);
                imagenes = sqlCliente.imagenesUsuario(LoginActivity.clienteLog.getNombre());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simple_list,menu);
        return true;
    }

}