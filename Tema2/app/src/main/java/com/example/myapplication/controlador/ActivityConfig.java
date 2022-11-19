package com.example.myapplication.controlador;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.fragmentos.SettingFragmentos;

/**
 * Tendremos un xml activity cual vamos a dividir en fragment para nuestras preferencias
 */

public class ActivityConfig extends AppCompatActivity {
    @Override
    //Le pondremos un oncreate para cuando creamos el fragment
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        //R.id.config_contenedor -> Es nuestro Constraint layout
        //Crearemos el Fragmento con las preferencias
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.configcontenedor, new SettingFragmentos())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){

            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    //Tendremos que ponerle la accion de salir del fragment cuando pulsemos el boton

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
