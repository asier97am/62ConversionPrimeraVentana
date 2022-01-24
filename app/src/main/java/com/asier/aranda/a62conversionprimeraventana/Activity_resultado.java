package com.asier.aranda.a62conversionprimeraventana;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_resultado extends AppCompatActivity {

    TextView tvDatos, tvBinario;
    String mensaje;
    Button btnConvertirBinario, btAvanzar;
    Bundle extras;

    ActivityResultLauncher<Intent> my_ActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //extras = this.getIntent().getExtras();

        btAvanzar = findViewById(R.id.btAvanzar);
        btnConvertirBinario = findViewById(R.id.btnConvertirBinario);

        tvDatos = findViewById(R.id.tvDatos);
        tvBinario = findViewById(R.id.tvBinario);

        my_ActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            Intent intent_vuelta_resultadoCompleo = result.getData();


                            String resultado_anterior_completo = intent_vuelta_resultadoCompleo.getStringExtra("centenas_decenas_unidades").toString();

                            //String resultado_total = extras.getString("centenas_decenas_unidades");

                            int numero = Integer.parseInt(resultado_anterior_completo);

                            //hay que pasar String o sino castear
                            tvDatos.setText(resultado_anterior_completo);

                            String binario = Integer.toBinaryString(numero);
                            tvBinario.setText(binario);

                            /*Context context = getApplicationContext();
                            //INDICO DURACION TOAST
                            int duration= Toast.LENGTH_LONG;
                            //TOAST
                            Toast toast=Toast.makeText(context,resultado_anterior_completo,duration);
                            toast.show();*/
                        } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                            //ACCIONES SI FALLA
                            String mensaje_vuelta_decenas = "sin mensaje de vuelta";
                            Context context = getApplicationContext();
                            int duration = Toast.LENGTH_LONG;
                            //TOAST
                            Toast toast = Toast.makeText(context, mensaje_vuelta_decenas, duration);
                            toast.show();
                        }
                    }
                });


    }

    public void btAvanzar1(View vista) {
        Intent intent_Resultado_to_centenas = new Intent(Activity_resultado.this, Activity_centenas.class);
        my_ActivityResultLauncher.launch(intent_Resultado_to_centenas);

    }

}

