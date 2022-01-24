package com.asier.aranda.a62conversionprimeraventana;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_centenas extends AppCompatActivity {

    public EditText editText1;
    public Button bt1;
    Bundle extras;

    ActivityResultLauncher<Intent> my_ActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centenas);

        bt1 = findViewById(R.id.bt1);

        //extras = this.getIntent().getExtras();

        my_ActivityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            //ACCIONES CUANDO VA OK
                            Intent intent_vuelta_decenas=result.getData();
                            String unidades_decenas = intent_vuelta_decenas.getStringExtra("decenas_unidades").toString();

                            //String unidades_decenas=extras.getString("decenas_unidades");
                            String centenas= editText1.getText().toString();

                            Intent intent_llamada_decenas = new Intent();

                            String resultado=unidades_decenas+centenas;
                            intent_llamada_decenas.putExtra("centenas_decenas_unidades", resultado);

                            setResult(RESULT_OK, intent_llamada_decenas);


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
                        finish();
                    }
                });
    }

    /*@Override
    protected void onStop(){
        super.onStop();
        setResult(RESULT_CANCELED,null);
    }*/

    public void lanzar_actividad(View vista) {
        Intent intent_decenas = new Intent(this, Activity_decenas.class);
        my_ActivityResultLauncher.launch(intent_decenas);
    }
}