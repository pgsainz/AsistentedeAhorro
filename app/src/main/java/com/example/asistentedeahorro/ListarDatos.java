package com.example.asistentedeahorro;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListarDatos extends AppCompatActivity {
    private static final int AMOUNT = 100;
    TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_datos);
        Initinterfaz();
        SetNumberInTableLayout();
    }

    private void SetNumberInTableLayout() {
        for (int i = 0; i < AMOUNT; i++)
            {
            TableRow currentRow = new TableRow(getBaseContext());
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
            TextView currentTextView = new TextView(getBaseContext());
            currentTextView.setText("Fila numero"+(i+1));
            currentTextView.setTextSize(20);
            currentTextView.setTextColor(Color.BLACK);
            currentRow.setLayoutParams(params);
            currentRow.addView(currentTextView);
            table.addView(currentRow);
            }
    }

    private void Initinterfaz() {
        table = (TableLayout) findViewById(R.id.tabla1);
    }
}
