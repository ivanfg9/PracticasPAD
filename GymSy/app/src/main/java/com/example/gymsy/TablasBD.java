package com.example.gymsy;

import android.provider.BaseColumns;

public class TablasBD {
    public static abstract class EjercicioEntry implements BaseColumns {
        public static final String NOMBRE_TABLA = "ejercicios";

        public static final String NOMBRE = "nombre";
        public static final String MUSCULO = "musculo";
        public static final String IMAGEN_URI = "imagenUri";
    }

    public static abstract class UsuarioEntry implements BaseColumns {
        public static final String NOMBRE_TABLA = "usuarios";

        public static final String TOKEN = "token";
        public static final String NOMBRE_USUARIO = "username";
        public static final String CONTRASENA = "password";
        public static final String ALTURA = "altura";
        public static final String PESO = "peso";
        public static final String ETAPA = "etapa";
        public static final String FREC_ACTUAL = "frecuenciaEjercicioActual";
        public static final String FREC_OBJ = "frecuenciaEjercicioObjetivo";
        public static final String LESION_SI_NO = "lesionSiLesionNo";
        public static final String ZONA_LESION = "zonaLesion";
    }

    public static abstract class RutinaEntry implements BaseColumns {
        public static final String NOMBRE_TABLA = "rutina";

        public static final String DIA_ENTRENO = "diaEntreno";
        public static final String ID_EJERCICIO = "idEjercicio";
        public static final String REPETICIONES = "repeticiones";
        public static final String PESO_LEVANTADO = "pesoLevantado";
    }

    public static abstract class AsociacionRutinaEntry implements BaseColumns {
        public static final String NOMBRE_TABLA = "asociacionRutinaEjercicio";

        public static final String ID_RUTINA = "idRutina";
        public static final String ID_USUARIO = "idUsuario";
    }
}
