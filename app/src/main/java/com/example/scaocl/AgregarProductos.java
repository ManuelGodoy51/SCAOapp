package com.example.scaocl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AgregarProductos extends AppCompatActivity {
    TextView TVusuario;
    EditText ETcodigoBarra,ETnombreP,ETDfechaRecepcion,ETnumeroGP,ETmarca,ETcantidad,
            ETDfechaVencimiento,ETlote,ETaccionCorrectiva,ETtemRecepcion,ETverificador,ETobservacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);
        recibirDatos();
    }




    private void recibirDatos(){
        Bundle extra = getIntent().getExtras();
        String Nusuario = extra.getString("NombreUsuario");
        String IdUsuario = extra.getString("IdUsuario");
        TVusuario = (TextView) findViewById(R.id.TVusuario);
        TVusuario.setText(Nusuario);
    }

}