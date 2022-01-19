package com.asier.aranda.a62conversionprimeraventana;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    Button btnConvertirBinario;
    Bundle extras;

    ActivityResultLauncher<Intent> my_ActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        extras = this.getIntent().getExtras();

        btnConvertirBinario = findViewById(R.id.btnConvertirBinario);
        tvDatos = findViewById(R.id.tvDatos);
        tvBinario = findViewById(R.id.tvBinario);
        my_ActivityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            //ACCIONES CUANDO VA OK
                            Intent intent_vuelta_centenas=result.getData();
                            String mensaje_vuelta_centenas = intent_vuelta_centenas.getStringExtra("vuelta_centenas").toString();
                            Context context = getApplicationContext();
                            //INDICO DURACION TOAST
                            int duration= Toast.LENGTH_LONG;
                            //TOAST
                            Toast toast=Toast.makeText(context,mensaje_vuelta_centenas,duration);
                            toast.show();

//                            Intent intent_centenas_to_main = new Intent();
//                            setResult(RESULT_OK, intent_centenas_to_main);
//                            finish();

                        }
                        else if (result.getResultCode()==Activity.RESULT_CANCELED){
                            //ACCIONES SI FALLA
                            String mensaje_vuelta_decenas= "sin mensaje de vuelta";
                            Context context=getApplicationContext();
                            int duration = Toast.LENGTH_LONG;
                            //TOAST
                            Toast toast=Toast.makeText(context,mensaje_vuelta_decenas,duration);
                            toast.show();
                        }
                    }
                });


//        String centenas=extras.getString("vuelta_centenas");
//        String decenas=extras.getString("vuelta_decenas");
//        String unidades=extras.getString("vuelta_unidades");
        String centenas=extras.getString("centenas");
        String decenas=extras.getString("decenas");
        String unidades=extras.getString("unidades");

        String resultado=centenas+decenas+unidades;
        int numero=Integer.parseInt(resultado);

        //hay que pasar String o sino castear
        tvDatos.setText(resultado);

        String binario = Integer.toBinaryString(numero);
        tvBinario.setText(binario);
    }
}

