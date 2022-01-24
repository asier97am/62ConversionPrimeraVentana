package com.asier.aranda.a62conversionprimeraventana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_unidades extends AppCompatActivity {

    TextView tv3;
    EditText et3;
    Button bt3;
    String mensaje;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidades);

        bt3 = findViewById(R.id.bt3);
        extras = this.getIntent().getExtras();
        /*tv3 = findViewById(R.id.tv3);

        mensaje = extras.getString("decenas");
        tv3.setText(mensaje);*/
    }

    public void salir(View vista) {
        et3 = findViewById(R.id.et3);
        String mensaje_vuelta_unidades = et3.getText().toString();
        Intent intent_unidades = new Intent();
        intent_unidades.putExtra("vuelta_unidades", mensaje_vuelta_unidades);
        setResult(RESULT_OK, intent_unidades);
        this.finish();

    }
}