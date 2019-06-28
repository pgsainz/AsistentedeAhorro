package com.example.asistentedeahorro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //inicializo los elementos al arrancar
    private TextView fechasaldo,saldoactual,totIngresos,totEgresos,totTCred;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fechasaldo = (TextView) findViewById(R.id.fechasaldo);
        totIngresos = (TextView) findViewById(R.id.totIngresos);
        saldoactual = (TextView) findViewById(R.id.saldoactual);
        totEgresos = (TextView) findViewById(R.id.totEgresos);
        totTCred = (TextView) findViewById(R.id.totTCred);
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        fechasaldo.setText(thisDate);
        actualizaSaldo();
    }
    public void nvoIngresoClick(View view){
        Intent i = new Intent(this,NvoIngreso.class);
        startActivity(i);
    }
    public void nvoEngresoClick(View view){
    Intent e = new Intent(this,NvoEgreso.class);
    startActivity(e);
    }
    public void btnDetalle(View view){
    Intent d = new Intent(this,DetalleForm.class);
    startActivity(d);
    }
    public void actualizaSaldo(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"dbahorro",null,1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor fila = bd.rawQuery("select sum(importe) as saldo from movimientos",null);
        fila.moveToFirst();
        if (fila.getCount()>0) {
            float numero = Float.parseFloat(fila.getString(0));
            DecimalFormat formato = new DecimalFormat("¤ #######0.00");
            saldoactual.setText(formato.format(numero));
            if (numero > 15000) {saldoactual.setTextColor(Color.GREEN); }
            if ((numero >= 7000) && (numero <=15000)) {saldoactual.setTextColor(Color.rgb(200,200,50)); }
            if (numero < 7000) {saldoactual.setTextColor(Color.RED); }
        } else {saldoactual.setText("0.00");}
        bd.close();

    }
}
