package com.example.asistentedeahorro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class DetalleForm extends AppCompatActivity {
    private TableLayout tablamov,sumario;
    private TableRow filat,fila1t;
    private TextView col1,col2,col3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_form);
        DecimalFormat formato = new DecimalFormat("¤ #######0.00");
        Fechas ff = new Fechas(this);
        tablamov = (TableLayout) findViewById(R.id.tablamov);
        sumario = (TableLayout) findViewById(R.id.sumario);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"dbahorro",null,1);
        SQLiteDatabase db1=admin.getWritableDatabase();
        Cursor fila = db1.rawQuery("select * from movimientos order by fecha",null);
        fila.moveToFirst();
        int nfila=0;
        int ncol=0;
        while (!fila.isAfterLast())
            {
            filat = new TableRow(this);
            filat.setId(nfila);
            col1 = new TextView(this);
            col1.setId(ncol);
            col1.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            col1.setTextColor(Color.BLACK);
            col1.setPadding(20,10,20,10);
            col1.setWidth(70);
            col1.setTop(1);
            col1.setBottom(1);
            col1.setText(ff.fechadmy(fila.getString(3)));
            ncol ++;
            col2 = new TextView(this);
            col2.setId(ncol);
            col2.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            col2.setTextColor(Color.BLACK);
            col2.setPadding(20,10,20,10);
            col2.setText(fila.getString(1));
            col2.setWidth(100);
            ncol ++;
            float importe = (float) fila.getFloat(4);
            col3 = new TextView(this);
            col3.setId(ncol);
            col3.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            if (importe >= 0) {col3.setTextColor(Color.BLACK);} else {col3.setTextColor(Color.RED);}
            col3.setPadding(10,10,10,10);
            col3.setText(formato.format(importe));
            col3.setWidth(50);
            ncol ++;
            filat.addView(col1);
            filat.addView(col2);
            filat.addView(col3);
            tablamov.addView(filat);
            nfila ++;
            fila.moveToNext();
            }
        fila.close();
        Cursor fila1 = db1.rawQuery("select categoria,sum(importe*(-1)) as total from movimientos where tipomov='E' group by categoria",null);
        fila1.moveToFirst();
        nfila = 0; ncol = 0;
        while (!fila1.isAfterLast()) {
               /* ArrayList<String> elementos = new ArrayList<String>();
                elementos.add(fila1.getString(0));
                elementos.add(fila1.getString(1));
                sumario.agregarFilaTabla(elementos);*/
               fila1t = new TableRow(this);
               fila1t.setId(nfila);
               fila1t.setLeftTopRightBottom(1,1,1,1);
               col1 = new TextView(this);
               col1.setId(ncol);
               col1.setWidth(200);
               col1.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
               col1.setTextColor(Color.BLACK);
               col1.setText(fila1.getString(0));
               ncol++;
               col2 = new TextView(this);
               col2.setId(ncol);
               col2.setWidth(150);
               col2.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
               col2.setTextColor(Color.BLACK);
               col2.setText(formato.format(fila1.getFloat(1)));
               fila1t.addView(col1);
               fila1t.addView(col2);
               sumario.addView(fila1t);
               nfila++;
               fila1.moveToNext();
            }
        fila1.close();
    }
    public void cancelar(View view){
        finish();
    }
}
