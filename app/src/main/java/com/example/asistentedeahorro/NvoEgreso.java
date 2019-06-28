package com.example.asistentedeahorro;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NvoEgreso extends AppCompatActivity {
    private Spinner fspinner1,fspinner2;
    private EditText ffechaeg,fmonto;
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvo_egreso);
        fspinner1 = (Spinner) findViewById(R.id.spinner1);
        fspinner2 = (Spinner) findViewById(R.id.spinner2);
        ffechaeg = (EditText) findViewById(R.id.fechaeg);
        fmonto = (EditText) findViewById(R.id.monto);
        String[] opciones={"ALQUILER","MERCADOS","TRANSPORTE","IMPUESTOS","SERVICIOS","ESPARCIMIENTO","OTROS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones);
        fspinner1.setAdapter(adapter);
        String[] opciones1 = {"EFECTIVO","DEBITO","TRANSFERENCIA","CREDITO"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,opciones1);
        fspinner2.setAdapter(adapter1);

    }
    public void cancelarclick(View view){
        finish();
    }
    public void elijefecha(View view){
        obtenerFecha();
    }
    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                ffechaeg.setText(diaFormateado + "/" + mesFormateado + "/" + year);
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }
   public void grabaregreso(View view){
       AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"dbahorro1",null,1);
       SQLiteDatabase db1 = admin.getWritableDatabase();
       String tipomov = "E";
       String concepto = fspinner2.getSelectedItem().toString();
       String categoria = fspinner1.getSelectedItem().toString();
       String fecha = ffechaeg.getText().toString();
       String importe = String.valueOf(Float.parseFloat(fmonto.getText().toString())*(-1));
       if (!fecha.isEmpty() && !importe.isEmpty() && !concepto.isEmpty() && !categoria.isEmpty()) {
           ContentValues registro = new ContentValues();
           registro.put("tipomov", tipomov);
           registro.put("concepto", concepto);
           registro.put("categoria", categoria);
           registro.put("fecha", fecha);
           registro.put("importe", importe);
           db1.insert("movimientos", null, registro);
           Toast.makeText(this, "Egreso Grabado", Toast.LENGTH_SHORT).show();
           db1.close();
           fspinner1.setSelection(0);
           fspinner2.setSelection(0);
           ffechaeg.setText("");
           fmonto.setText("");
       }
       else {Toast.makeText(this, "Faltan datos reintente.", Toast.LENGTH_SHORT).show();}
   }

}
