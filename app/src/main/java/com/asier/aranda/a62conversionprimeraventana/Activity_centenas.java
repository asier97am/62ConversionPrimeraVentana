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

    ActivityResultLauncher<Intent> my_ActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centenas);


        bt1 = findViewById(R.id.bt1);

        my_ActivityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            //ACCIONES CUANDO VA OK
                            Intent intent_vuelta_decenas=result.getData();
                            String mensaje_vuelta_decenas = intent_vuelta_decenas.getStringExtra("vuelta_decenas").toString();
                            Context context = getApplicationContext();
                            //INDICO DURACION TOAST
                            int duration= Toast.LENGTH_LONG;
                            //TOAST
                            Toast toast=Toast.makeText(context,mensaje_vuelta_decenas,duration);
                            toast.show();

                            Intent intent_centenas_to_main = new Intent(Activity_centenas.this, Activity_resultado.class);
                            setResult(RESULT_OK, intent_centenas_to_main);
                            finish();

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
    }
    @Override
    protected void onStop(){
        super.onStop();
        setResult(RESULT_CANCELED,null);
    }

    public void lanzar_actividad(View vista) {
        editText1 = findViewById(R.id.editText1);
        String centenas = editText1.getText().toString();
        Intent intent_decenas = new Intent(this, Activity_decenas.class);
        intent_decenas.putExtra("centenas", centenas);

        my_ActivityResultLauncher.launch(intent_decenas);
    }
}