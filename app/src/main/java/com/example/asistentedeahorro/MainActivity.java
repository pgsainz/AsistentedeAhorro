package com.example.asistentedeahorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        actualizaSaldo(0);
    }
    public void nvoIngresoClick(View view){
        Intent i = new Intent(this,NvoIngreso.class);
        startActivity(i);
    }
    public void nvoEngresoClick(View view){

    }
    public void btnDetalle(View view){

    }
    public void actualizaSaldo(float numero){
        DecimalFormat formato = new DecimalFormat("¤ #######0.00");
        if (numero > 15000) {saldoactual.setTextColor(Color.GREEN); }
        if ((numero >= 7000) && (numero <=15000)) {saldoactual.setTextColor(Color.YELLOW); }
        if (numero < 7000) {saldoactual.setTextColor(Color.RED); }
        saldoactual.setText(formato.format(numero));
    }
}
