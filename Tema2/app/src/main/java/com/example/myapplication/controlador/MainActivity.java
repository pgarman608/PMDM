package com.example.myapplication.controlador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.view.ActionMode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private TextView leHolaMundo;
    private ConstraintLayout constLayout;
    private androidx.appcompat.view.ActionMode actionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leHolaMundo = (TextView) findViewById(R.id.tvHolaMundo);
        constLayout = (ConstraintLayout) findViewById(R.id.constLayout);
        leHolaMundo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                boolean res = false;
                if (actionMode == null){
                    //Inicaremos el menu con el .startSupportActionMode() metiendole un parametro
                    actionMode = startSupportActionMode(startActionMode);
                    res = true;
                }
                return res;
            }
        });
        cargarConfig();
    }

    private ActionMode.Callback startActionMode = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menupreferencias, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if(itemId == R.id.menuOption){
                Intent intent = new Intent(MainActivity.this, ActivityConfig.class);
                startActivity(intent);
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            actionMode = null;
        }
    };

    private void cargarConfig() {
        /**
         * En este metodo cargaremos la configuracion cambiada en el setting
         *
         * Gracias al patron singleton tendremos un solo objeto a que cogeremos toda la información
         */
        SharedPreferences informacion = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        // Una vez que tengamos acceso a la informacion compartida accederemos a ella con una clave
        String preferences;
        preferences = informacion.getString("itemColorFondo","Negro");
        /**String colorFondo = informacion.getString("itemColorFondo","Negro");
         * String colorLetra = informacion.getString("itemColorTexto","Negro");
        String tamanioLetra = informacion.getString("itemTamanio",""+14);
        leHolaMundo.setForeground(new ColorDrawable(Color.rgb(getCTexto(colorFondo)[0],getCTexto(colorFondo)[1],getCTexto(colorFondo)[2])));
        leHolaMundo.setBackground(new ColorDrawable(Color.rgb(getCTexto(colorLetra)[0],getCTexto(colorLetra)[1],getCTexto(colorLetra)[2])));
        leHolaMundo.setTextSize(Integer.valueOf(tamanioLetra));*/
    }
    private int[] getCTexto(String color){
        int[] colores = new int[3];
        switch (color){
            case "Negro":
                colores[0] = 0;
                colores[1] = 0;
                colores[2] = 0;
                break;
            case "Rojo":
                colores[0] = 255;
                colores[1] = 0;
                colores[2] = 0;
                break;
            case "Verde":
                colores[0] = 0;
                colores[1] = 255;
                colores[2] = 0;
                break;
            case "Azul":
                colores[0] = 0;
                colores[1] = 0;
                colores[2] = 255;
                break;
        }
        return colores;
    }
}