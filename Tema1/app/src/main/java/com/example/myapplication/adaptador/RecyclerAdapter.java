package com.example.myapplication.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Videojuegos;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    List<Videojuegos> listGames;

    public RecyclerAdapter(List<Videojuegos> listMovies) {
        this.listGames = listMovies;
    }
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_items,parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Videojuegos videojuegos = listGames.get(position);
        holder.ivJuego.setImageResource(videojuegos.getImagenID());
        holder.tvTitle.setText(videojuegos.getTitulo());
        holder.tvDescripcion.setText(videojuegos.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listGames.size();
    }
    public class RecyclerHolder extends ViewHolder{
        ImageView ivJuego;
        TextView  tvTitle;
        TextView  tvDescripcion;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            ivJuego  = (ImageView) itemView.findViewById(R.id.ivVideojuegos);
            tvTitle = (TextView)  itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = (TextView)  itemView.findViewById(R.id.tvDescripcion);


        }
    }
}

