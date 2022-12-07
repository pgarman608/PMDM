package com.example.myapplication.controladores;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    public static final int ADD = 1;
    public static final int MOD = 2;

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

        imageHolder.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerForContextMenu(view);
            }
        });
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_float_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.btnMenuAdd:
                Intent intent = new Intent(ListActivity.this,AddActivity.class);
                startActivityForResult(intent,ADD);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD){
            rellenarArray();
            imageHolder.setImagenes(imagenes);
            recyclerView.setAdapter(imageHolder);
        }
    }
}