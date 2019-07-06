package com.example.asistentedeahorro;
import android.app.Activity;
public class Fechas {

    public Fechas(Activity actividad) {

    }

    public static String right(String value, int length) {
        // To get right characters from a string, change the begin index.
        return value.substring(value.length() - length);
    }

    public static String left(String value, int length) {
        return value.substring(0,length-1);
    }

    public String fechamsqlite(String fechatomada){
        String dia="",mes="",anio = "";
        int j1,j2;
        j1 = fechatomada.indexOf("/",0);
        j2 = fechatomada.indexOf("/",j1+1);
        dia = right("0"+fechatomada.substring(0,j1),2);
        mes = right("0"+fechatomada.substring(j1+1,j2),2);
        anio = fechatomada.substring(j2+1);
        return anio+"-"+mes+"-"+dia;
    }
    public String fechadmy(String fechatomada){
        String dia="",mes="",anio = "";
        int j1,j2;
        j1 = fechatomada.indexOf("-",0);
        j2 = fechatomada.indexOf("-",j1+1);
        dia = fechatomada.substring(j2+1);
        mes = fechatomada.substring(j1+1,j2);
        anio = fechatomada.substring(0,j1);
        return dia+"/"+mes+"/"+anio;
    }
}
