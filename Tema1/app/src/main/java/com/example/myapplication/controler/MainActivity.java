package com.example.myapplication.controler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adaptador.RecyclerAdapter;
import com.example.myapplication.model.Videojuegos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private RecyclerAdapter ra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rView);

        ra = new RecyclerAdapter(getListGames());

        LinearLayoutManager la1 = new LinearLayoutManager(this);

        rv.setAdapter(ra);
        rv.setLayoutManager(la1);

        ra.setContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                //Crearemos un menu flotante con getMenuInflater().inflater(r,menu)
                getMenuInflater().inflate(R.menu.menu_flotante,menu);
                //Le pondremos una cabezera
                menu.setHeaderTitle("Menu Flotante");
            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        createToast("Hola");
        return true;
    }

    public void onContextMenuClosed(@NonNull Menu menu) {

    }

    private void createToast(String msg){
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    private List<Videojuegos> getListGames() {
        ArrayList<Videojuegos> videojuegosArrayList = new ArrayList<>();
        videojuegosArrayList.add(new Videojuegos(R.drawable.mario,"Mario","Juego de plataformas"));
        videojuegosArrayList.add(new Videojuegos(R.drawable.kirby,"Kirby","Juego de plataformas"));
        videojuegosArrayList.add(new Videojuegos(R.drawable.zelda,"Zelda","Juego de accion y aventura"));
        videojuegosArrayList.add(new Videojuegos(R.drawable.smash,"Smash","Juego de plataformas y de peleas"));
        return videojuegosArrayList;
    }
}