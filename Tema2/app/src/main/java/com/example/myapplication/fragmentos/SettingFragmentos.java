package com.example.myapplication.fragmentos;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.myapplication.R;

//Con esta clase nos generará un fragmento de una ventana tipo tablet donde estará la configuración
//Tambien se puede pensar que un fragment es una ventana dentro de otra ventana y se pueden hacer
//varias veces, esto nos sirve como he dicho anteriormente para pantallas de gran tamaño
//Cada fragmanto de nuestra pantalla tendra su ciclo de vida independiente a otros fragmentos

public class SettingFragmentos extends PreferenceFragmentCompat {
    //Este metodo nos creara el fragmento que necesitemos
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.configtexto, rootKey);
    }
}
