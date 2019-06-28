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
        TextView mercadosmonto = (TextView) findViewById(R.id.mercadosmonto);
        TextView alquilermonto = (TextView) findViewById(R.id.alquilermonto);
        TextView transportemonto = (TextView) findViewById(R.id.transportemonto);
        TextView impuestosmonto = (TextView) findViewById(R.id.impuestosmonto);
        TextView otrosmonto = (TextView) findViewById(R.id.otrosmonto);
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
        while(!fila1.isAfterLast()){
            String texto1 = fila1.getString(0);
            if (texto1 == "MERCADOS") {mercadosmonto.setText(fila1.getString(1));}
            if (texto1 == "ALQUILER") {alquilermonto.setText(fila1.getString(1));}
            if (texto1 == "TRANSPORTE") {transportemonto.setText(fila1.getString(1));}
            if (texto1 == "IMPUESTOS") {impuestosmonto.setText(fila1.getString(1));}
            if (texto1 =="OTROS") {otrosmonto.setText(fila1.getString(1));}
            fila1.moveToNext();
        }
    }
    public void cancelar(View view){
        finish();
    }
}
