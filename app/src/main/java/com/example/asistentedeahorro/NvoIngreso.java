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

public class NvoIngreso extends AppCompatActivity {
    private Spinner spinner1;
    private EditText fechaing,monto;
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvo_ingreso);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        fechaing = (EditText) findViewById(R.id.fechaeg);
        String[] opciones={"SUELDO","PRESTAMO","OTROS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones);
        spinner1.setAdapter(adapter);
        monto = (EditText) findViewById(R.id.monto);
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
                fechaing.setText(diaFormateado + "/" + mesFormateado + "/" + year);
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }
    public void aceptar(View view){
    //realizo el alta del registro
    Fechas ff = new Fechas(this);
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"dbahorro",null,1);
    SQLiteDatabase db1 = admin.getWritableDatabase();
    String tipomov = "I";
    String concepto = spinner1.getSelectedItem().toString();
    String categoria = "";
    String fecha = ff.fechamsqlite(fechaing.getText().toString());
    String importe = monto.getText().toString();
    if (!fecha.isEmpty() && !importe.isEmpty() && !concepto.isEmpty()) {
        ContentValues registro = new ContentValues();
        registro.put("tipomov", tipomov);
        registro.put("concepto", concepto);
        registro.put("categoria",categoria);
        registro.put("fecha", fecha);
        registro.put("importe", importe);
        db1.insert("movimientos", null, registro);
        Toast.makeText(this, "Ingreso Grabado", Toast.LENGTH_SHORT).show();
        db1.close();
        spinner1.setSelection(0);
        fechaing.setText("");
        monto.setText("");
        }
      else {Toast.makeText(this, "Faltan datos reintente.", Toast.LENGTH_SHORT).show();}
    }
}
