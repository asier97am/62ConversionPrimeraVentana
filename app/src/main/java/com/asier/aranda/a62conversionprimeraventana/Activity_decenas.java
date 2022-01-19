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

public class Activity_decenas extends AppCompatActivity {

    TextView tv2;
    EditText et2;
    Button bt2;
    String mensaje;
    Bundle extras;

    ActivityResultLauncher<Intent> my_ActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decenas);

        bt2 = findViewById(R.id.bt2);

        tv2 = findViewById(R.id.tv2);
        et2 = findViewById(R.id.et2);
        extras = this.getIntent().getExtras();
        mensaje = extras.getString("centenas");
        tv2.setText(mensaje);

        my_ActivityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            //ACCIONES CUANDO VA OK
                            Intent intent_vuelta_unidades=result.getData();
                            String mensaje_vuelta_unidades = intent_vuelta_unidades.getStringExtra("vuelta_unidades").toString();
                            Context context = getApplicationContext();
                            //INDICO DURACION TOAST
                            int duration= Toast.LENGTH_LONG;
                            //TOAST
                            Toast toast=Toast.makeText(context,mensaje_vuelta_unidades,duration);
                            toast.show();

                            Intent intent_decenas_to_centenas = new Intent(Activity_decenas.this, Activity_centenas.class);
                            setResult(RESULT_OK, intent_decenas_to_centenas);
                            finish();

                        }
                        else if (result.getResultCode()==Activity.RESULT_CANCELED){
                            //ACCIONES SI FALLA
                            String mensaje_vuelta_unidades= "sin mensaje de vuelta";
                            Context context=getApplicationContext();
                            int duration = Toast.LENGTH_LONG;
                            //TOAST
                            Toast toast=Toast.makeText(context,mensaje_vuelta_unidades,duration);
                            toast.show();
                        }
                    }
                });
    }

    public void siguiente(View view) {
        et2 = findViewById(R.id.et2);
        String decenas = et2.getText().toString();
        Intent intent_decenas = new Intent(this, Activity_unidades.class);
        intent_decenas.putExtra("decenas", decenas);

        my_ActivityResultLauncher.launch(intent_decenas);

    }
}


