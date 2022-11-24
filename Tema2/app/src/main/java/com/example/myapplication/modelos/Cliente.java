package com.example.myapplication.modelos;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nombre;
    private String contrasena;

    public Cliente(String nombre,String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
    public Cliente(){
        this.nombre = "";
        this.contrasena = "";
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre + "-" + this.contrasena;
    }
}
