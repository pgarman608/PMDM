package com.example.myapplication.modelos;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.myapplication.R;

public class ConfigFragmento extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.list_config,rootKey);
    }
}
