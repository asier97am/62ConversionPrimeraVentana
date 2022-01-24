package com.asier.aranda.a62conversionprimeraventana;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_centenas extends AppCompatActivity {

    private EditText editText1;


    ActivityResultLauncher<Intent> my_ActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centenas);

        my_ActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            //ACCIONES CUANDO VA OK
                            Intent intent_vuelta_decenas = result.getData();
                            String decenas = intent_vuelta_decenas.getStringExtra("decenas_vuelta").toString();
                            String unidades = intent_vuelta_decenas.getStringExtra("unidades_vuelta").toString();


                            String centenas = editText1.getText().toString();
                            String resultado = unidades + decenas + centenas;

                            String mensaje = Integer.toBinaryString(Integer.parseInt(resultado)) + " --> " + resultado;
                            Context context = getApplicationContext();
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, mensaje, duration);
                            toast.show();

//                            tvDatos.setText(resultado);
//
//                            int numero = Integer.parseInt(resultado);
//
//
//                            String binario = Integer.toBinaryString(numero);
//                            tvBinario.setText(binario);


                        } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                            String mensaje_vuelta = "Sin mensaje de vuelta";
                            Context context = getApplicationContext();
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, mensaje_vuelta, duration);
                            toast.show();
                        }
                    }
                });
    }

    public void lanzar_actividad(View vista) {
        Intent intent_decenas = new Intent(this, Activity_decenas.class);
        my_ActivityResultLauncher.launch(intent_decenas);
    }
}