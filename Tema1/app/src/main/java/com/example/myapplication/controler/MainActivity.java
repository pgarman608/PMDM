package com.example.myapplication.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adaptador.RecyclerAdapter;
import com.example.myapplication.model.Videojuegos;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private androidx.appcompat.view.ActionMode actionMode;
    private ArrayList<Videojuegos> videojuegosArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rView);

        videojuegosArrayList = new ArrayList<>();
        addListGames();

        recyclerAdapter = new RecyclerAdapter(videojuegosArrayList);


        LinearLayoutManager la1 = new LinearLayoutManager(this);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(la1);
        recyclerAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int item = recyclerView.getChildAdapterPosition(view);
                Toast.makeText(view.getContext(), videojuegosArrayList.get(item).getTitulo(),Toast.LENGTH_SHORT).show();
            }
        });
        //Tendremos que crear dentro del adaptador una variable un objecto de la clase onLongClickListener
        //, tener un metodo que nos lo guarde y parsarlo al view del adaptador
        recyclerAdapter.setLongClickL(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Tendremos una variable boleana para comprobar si ya esta creado el menu
                boolean res = false;
                if (actionMode == null){
                    viewp = view;
                    //Inicaremos el menu con el .startSupportActionMode() metiendole un parametro
                    actionMode = startSupportActionMode(startActionMode);
                    res = true;
                }
                return res;
            }
        });
    }
    private View viewp;
    private int add = 1;
    private ActionMode.Callback startActionMode = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_interaccion,menu);
            mode.setTitle("Modificar");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch (id){
                case R.id.mAdd:
                    videojuegosArrayList.add(new Videojuegos(R.drawable.random,"Titulo " +add,"Definicion " + add));
                    recyclerAdapter.notifyItemInserted(videojuegosArrayList.size());
                    mySank("Videojuego añadido");
                    add++;
                    break;
                case R.id.mDelete:
                    int eliminar = recyclerView.getChildAdapterPosition(viewp);
                    videojuegosArrayList.remove(eliminar);
                    recyclerAdapter.notifyItemRemoved(eliminar);
                    mySank("Videojuego eliminado");
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    private void addListGames() {
        videojuegosArrayList.add(new Videojuegos(R.drawable.mario,"Mario","Juego de plataformas"));
        videojuegosArrayList.add(new Videojuegos(R.drawable.kirby,"Kirby","Juego de plataformas"));
        videojuegosArrayList.add(new Videojuegos(R.drawable.zelda,"Zelda","Juego de accion y aventura"));
        videojuegosArrayList.add(new Videojuegos(R.drawable.smash,"Smash","Juego de plataformas y de peleas"));;
    }
    public void mySank(String msg){
        Snackbar.make(findViewById(android.R.id.content).getRootView(),msg,Snackbar.LENGTH_SHORT).show();
    }
}