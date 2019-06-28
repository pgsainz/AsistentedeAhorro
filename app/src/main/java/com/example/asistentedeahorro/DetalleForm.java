package com.example.asistentedeahorro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetalleForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_form);
        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        AccesoDB admin = new AccesoDB(this,"datos",null,3);
        SQLiteDatabase db1=admin.getWritableDatabase();
        Cursor fila = db1.rawQuery("select * from movimientos order by fecha",null);
        int ii = fila.getCount();
        ArrayList<String> elementos = new ArrayList<String>();
        if (ii>0) {
            fila.moveToFirst();
            for (int i = 0; i < ii; i++) {
                elementos.add(fila.getString(1));
                elementos.add(fila.getString(2));
                elementos.add(fila.getString(2));
                tabla.agregarFilaTabla(elementos);
                fila.moveToNext();
            }
        } else {
            elementos.add("No hay datos");
            tabla.agregarFilaTabla(elementos);
        }
        fila.close();
    }
    public void cancelar(){
        finish();
    }
}
