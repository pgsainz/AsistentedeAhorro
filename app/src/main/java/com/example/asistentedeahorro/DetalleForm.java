package com.example.asistentedeahorro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetalleForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_form);
        TextView mercadosmonto = (TextView) findViewById(R.id.monto);
        Tabla tabla = new Tabla(this, (TableLayout) findViewById(R.id.tablamov));
        //tabla.agregarCabecera(R.array.cabecera_tabla);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"dbahorro1",null,1);
        SQLiteDatabase db1=admin.getWritableDatabase();
        Cursor fila = db1.rawQuery("select * from movimientos order by fecha",null);
        int ii = fila.getCount();
        if (ii>0) {
            fila.moveToFirst();
            for (int i = 0; i < ii; i++) {
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(fila.getString(3));
                elementos.add(fila.getString(1));
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
        Cursor fila1 = db1.rawQuery("select categoria,sum(importe*(-1)) as total from movimientos where tipomov='E' group by categoria",null);
        fila1.moveToFirst();
        Tabla sumario = new Tabla(this, (TableLayout) findViewById(R.id.sumario));
        int ii2 = fila1.getCount();
        if (ii2>0) {
            fila1.moveToFirst();
            for (int i = 0; i < ii2; i++) {
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(fila1.getString(0));
                elementos.add(fila1.getString(1));
                sumario.agregarFilaTabla(elementos);
                fila1.moveToNext();
            }
        } else {
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add("------------");
            elementos.add("No hay datos");
            elementos.add("------------");
            sumario.agregarFilaTabla(elementos);
        }
        fila1.close();
    }
    public void cancelar(View view){
        finish();
    }
}
