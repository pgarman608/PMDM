package com.example.myapplication;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.modelos.IAImagen;

import java.util.List;

public class ReciclerAdapterImag extends RecyclerView.Adapter<ReciclerAdapterImag.ImageHolder> {

    List<IAImagen> imagenes;
    public List<IAImagen> getImagenes(){
        return imagenes;
    }

    public void setImagenes(List<IAImagen> imagenes) {
        this.imagenes = imagenes;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,parent,false);
        ImageHolder imageHolder = new ImageHolder(view);

        return imageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        IAImagen iaImagen = imagenes.get(position);
        holder.iaImagen.setImageResource(iaImagen.getUrl());
    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }


    public class ImageHolder extends RecyclerView.ViewHolder {
        ImageView iaImagen;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            iaImagen = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
