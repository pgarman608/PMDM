package com.example.myapplication.modelos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.myapplication.controladores.AddActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReciclerAdapterImag extends RecyclerView.Adapter<ReciclerAdapterImag.ImageHolder> {

    private List<IAImagen> imagenes;
    private CircularProgressDrawable progresoDrawableImage;
    private View view;
    private View.OnClickListener clickListener;

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public List<IAImagen> getImagenes(){
        return imagenes;
    }

    public void setImagenes(List<IAImagen> imagenes) {
        this.imagenes = imagenes;
    }

    private CircularProgressDrawable progressBar;

    public ReciclerAdapterImag(List<IAImagen> imagenes){
        this.imagenes = imagenes;
    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,parent,false);
        ImageHolder imageHolder = new ImageHolder(view);

        //Circulo de carga
        view.setOnClickListener(clickListener);

        return imageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        IAImagen iaImagen = imagenes.get(position);
        int codigo = imagenes.get(position).getCodigo_Imagen();

        progressBar = new CircularProgressDrawable(view.getContext());
        progressBar.setStrokeWidth(10f);
        progressBar.setStyle(CircularProgressDrawable.LARGE);
        progressBar.setCenterRadius(30f);
        progressBar.start();

        Glide.with(view)
                .load(iaImagen.getUrl())
                .placeholder(progressBar)
                .error(R.drawable.error404)
                .into(holder.iaImagen);
    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }


    public class ImageHolder extends RecyclerView.ViewHolder {
        ImageView iaImagen;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            iaImagen = (ImageView) itemView.findViewById(R.id.ivItem);
        }
    }
}
