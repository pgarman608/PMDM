package com.example.myapplication.controladores;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.modelos.ConfigFragmento;

public class ConfigActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        getWindow().setStatusBarColor(Color.parseColor("#FCC2FC"));
        ActionBar barra = getSupportActionBar();
        barra.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FCC2FC")));
        barra.setTitle(Html.fromHtml("<font color='#006ca0'>Crear Imagen</font>"));
        getWindow().setNavigationBarColor(Color.parseColor("#FCC2FC"));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.configContainer,new ConfigFragmento())
                .commit();
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
}

