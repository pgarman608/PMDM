package com.example.myapplication.controladores;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.modelos.Cliente;
import com.example.myapplication.modelos.IAImagen;

import java.util.ArrayList;

public class SQLCliente extends SQLiteOpenHelper {

    private static final String DB_AIESCENE = "DB_AIESCENE";

    private static final String DB_NAME_TABLE_CLIENTE = "DB_TABLE_CLIENTE";
    private static final String DB_NAME_TABLE_IMAGENES = "DB_TABLE_IMAGENES";

    private static final int VERSION = 10;

    //Columnas de las dos tablas

    private static final String COL_CLIENTE_NOMBRE = "NOMBRE";
    private static final String COL_CLIENTE_CONTRASENIA = "CONTRASENIA";

    private static final String COL_IMAGENES_NOMBRE_CLIENTE = "NOMBRE_CLIENTE";
    private static final String COL_IMAGENES_CODIGO_IMAGEN = "CODIGO_IMAGEN";
    private static final String COL_IMAGENES_NOMBRE = "NOMBRE_IMAGEN" ;
    private static final String COL_IMAGENES_DESCRIPCION = "DESCRIPCION";
    private static final String COL_IMAGENES_URL = "URL_IMAGEN";

    private Context context;

    public SQLCliente(@Nullable Context context) {
        super(context, DB_AIESCENE, null, VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_CLIENTE = "CREATE TABLE " + DB_NAME_TABLE_CLIENTE +" ( " + COL_CLIENTE_NOMBRE
                + " TEXT primary key," + COL_CLIENTE_CONTRASENIA + " TEXT );";
        String CREATE_TABLE_IMAGENES = "CREATE TABLE " + DB_NAME_TABLE_IMAGENES +" ( " + COL_IMAGENES_NOMBRE_CLIENTE
                + " TEXT," + COL_IMAGENES_CODIGO_IMAGEN + " TEXT," + COL_IMAGENES_NOMBRE+ " TEXT,"
                + COL_IMAGENES_DESCRIPCION + " TEXT," + COL_IMAGENES_URL+ " TEXT);";

        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENTE);
        sqLiteDatabase.execSQL(CREATE_TABLE_IMAGENES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public long insertarCliente(Cliente cliente){
        long error = -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_CLIENTE_NOMBRE,cliente.getNombre());
        values.put(COL_CLIENTE_CONTRASENIA,cliente.getContrasena());

        error = db.insert(DB_NAME_TABLE_CLIENTE,null,values);

        db.close();

        return error;
    }

    public long insertarImagen(IAImagen iaImagen){
        long error = -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(COL_IMAGENES_NOMBRE_CLIENTE,iaImagen.getNombre_Cliente());
        valores.put(COL_IMAGENES_CODIGO_IMAGEN,iaImagen.getCodigo_Imagen());
        valores.put(COL_IMAGENES_NOMBRE,iaImagen.getNombre_Imagen());
        valores.put(COL_IMAGENES_DESCRIPCION,iaImagen.getDescripcion());
        valores.put(COL_IMAGENES_URL,iaImagen.getUrl());

        error = db.insert(DB_NAME_TABLE_IMAGENES,null,valores);

        db.close();

        return error;
    }

    public long existeCliente(Cliente cliente){
        int error = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columnas = new String[]{COL_CLIENTE_NOMBRE};
        String[] where = new String[]{cliente.getNombre()};

        Cursor cursor = db.query(DB_NAME_TABLE_CLIENTE,columnas,"NOMBRE = ?",where,null,null,null);;

        error = cursor.getCount();

        db.close();
        cursor.close();

        return error;
    }

    //Metodo pesado

    public ArrayList<IAImagen> imagenesUsuario(String nombre){
        ArrayList<IAImagen> imagenes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columnas = new String[]{COL_IMAGENES_NOMBRE,COL_IMAGENES_DESCRIPCION,COL_IMAGENES_URL};
        String[] where = new String[]{nombre};

        Cursor cursor = db.query(DB_NAME_TABLE_IMAGENES,columnas,"NOMBRE_CLIENTE = ? ",where,null,null,null,null);
        if (cursor.moveToNext()){
            do {
                IAImagen iaImagen = new IAImagen("Cliente",cursor.getString(0),cursor.getString(1),cursor.getString(2));
                imagenes.add(iaImagen);
            }while (cursor.moveToNext());
        }
        return imagenes;
    }
    public void updateImagen(IAImagen imagenMod){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(COL_IMAGENES_DESCRIPCION,imagenMod.getDescripcion());

        String[] cod = new String[]{String.valueOf(imagenMod.getCodigo_Imagen())};

        db.update(DB_NAME_TABLE_IMAGENES,valores,"CODIGO_IMAGEN = ?",cod);

        db.close();
    }
    public void deleteImagen(IAImagen imagenDel){
        SQLiteDatabase db = this.getWritableDatabase();

        String[] cod = new String[]{""+imagenDel.getCodigo_Imagen()};

        int devolver = db.delete(DB_NAME_TABLE_IMAGENES,"CODIGO_IMAGEN = ?",cod);
        Log.i(TAG, "deleteImagen: " + devolver);
        db.close();
    }
}
