package com.example.gymsy;

public class Ejercicio {
    private String nombreEjercicio;
    private String musculo;

    public Ejercicio(String nombre, String zonaMuscular){
        nombreEjercicio = nombre;
        musculo = zonaMuscular;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public String getMusculo() {
        return musculo;
    }
}
