package com.example.asistentedeahorro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"dbahorro",null,1);
        SQLiteDatabase db1=admin.getWritableDatabase();
        Cursor fila = db1.rawQuery("select * from movimientos order by fecha",null);
        int ii = fila.getCount();
        if (ii>0) {
            fila.moveToFirst();
            for (int i = 0; i < ii; i++) {
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(fila.getString(3));
                elementos.add(fila.getString(0));
                elementos.add(fila.getString(4));
                tabla.agregarFilaTabla(elementos);
                fila.moveToNext();
            }
        } else {
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add("------------");
            elementos.add("No hay datos");
            elementos.add("------------");
            tabla.agregarFilaTabla(elementos);
        }
        fila.close();
    }
    public void cancelar(View view){
        finish();
    }
}
