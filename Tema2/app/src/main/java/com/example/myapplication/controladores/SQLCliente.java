package com.example.myapplication.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.modelos.Cliente;
import com.example.myapplication.modelos.IAImagen;

public class SQLCliente extends SQLiteOpenHelper {

    private static final String DB_AIESCENE = "DB_AIESCENE";

    private static final String DB_NAME_TABLE_CLIENTE = "DB_TABLE_CLIENTE";
    private static final String DB_NAME_TABLE_IMAGENES = "DB_TABLE_IMAGENES";

    private static final int VERSION = 1;

    //Columnas de las dos tablas

    private static final String COL_CLIENTE_NOMBRE = "NOMBRE";
    private static final String COL_CLIENTE_CONTRASENIA = "CONTRASENIA";

    private static final String COL_IMAGENES_NOMBRE_CLIENTE = "NOMBRE_CLIENTE";
    private static final String COL_IMAGENES_URL = "URL_IMAGEN";

    private Context context;

    public SQLCliente(@Nullable Context context) {
        super(context, DB_AIESCENE, null, VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_CLIENTE = "CREATE TABLE " + DB_NAME_TABLE_CLIENTE +"(" + COL_CLIENTE_NOMBRE
                + "TEXT," + COL_CLIENTE_CONTRASENIA + "TEXT);";
        String CREATE_TABLE_IMAGENES = "CREATE TABLE " + DB_NAME_TABLE_IMAGENES +"(" + COL_IMAGENES_NOMBRE_CLIENTE
                + "TEXT," + COL_IMAGENES_URL+ "TEXT," +
                " FOREIGN KEY("+ COL_IMAGENES_NOMBRE_CLIENTE+") REFERENCES " + DB_NAME_TABLE_CLIENTE + "(" + COL_CLIENTE_NOMBRE +"));";

        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENTE);
        sqLiteDatabase.execSQL(CREATE_TABLE_IMAGENES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

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

        ContentValues values = new ContentValues();

        values.put(COL_IMAGENES_NOMBRE_CLIENTE,iaImagen.getNombre_Cliente());
        values.put(COL_IMAGENES_URL,iaImagen.getUrl());

        error = db.insert(DB_NAME_TABLE_IMAGENES,null,values);

        db.close();

        return error;
    }

    public long existeCliente(Cliente cliente){
        int error = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DB_NAME_TABLE_CLIENTE
                + " WHERE " + COL_CLIENTE_NOMBRE + " = " + cliente.getNombre() + " AND " + COL_CLIENTE_CONTRASENIA + " = " + cliente.getContrasena();
        Cursor cursor = db.rawQuery(query,null);

        error = cursor.getCount();

        db.close();
        cursor.close();

        return error;
    }
}
