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
    public static final int SEE = 0;
    public static final int ADD = 1;
    public static final int MOD = 2;
    public static IAImagen iaSelect;
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
                int select = recyclerView.getChildAdapterPosition(view);
                iaSelect = imagenes.get(select);
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
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.btnVer:
                Intent intent2 = new Intent(ListActivity.this,AddActivity.class);
                intent2.putExtra("name",iaSelect.getNombre_Imagen());
                intent2.putExtra("desc",iaSelect.getDescripcion());
                intent2.putExtra("url",iaSelect.getUrl());
                intent2.setAction("mostrar");
                startActivityForResult(intent2,SEE);
                break;
            case R.id.btnMod:
                Intent intent3 = new Intent(ListActivity.this,AddActivity.class);
                intent3.putExtra("cod",""+iaSelect.getCodigo_Imagen());
                intent3.putExtra("name",iaSelect.getNombre_Imagen());
                intent3.putExtra("desc",iaSelect.getDescripcion());
                intent3.putExtra("url",iaSelect.getUrl());
                intent3.setAction("editar");
                startActivityForResult(intent3,MOD);
                break;
            case R.id.btnDel:
                SQLCliente db = new SQLCliente(ListActivity.this);
                Log.i(TAG, "onContextItemSelected: " + iaSelect.getCodigo_Imagen());
                db.deleteImagen(iaSelect);
                actualizarRV();
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.btnMenuAdd:
                Intent intent1 = new Intent(ListActivity.this,AddActivity.class);
                intent1.setAction("aniadir");
                startActivityForResult(intent1,ADD);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD || requestCode == MOD){
            actualizarRV();
        }
    }
    private void actualizarRV(){
        rellenarArray();
        imageHolder.setImagenes(imagenes);
        recyclerView.setAdapter(imageHolder);
        if (imagenes.size() > 0){
            tvVacio.setText("");
        }else{
            tvVacio.setText("No hay Imagenes");
        }
    }
}