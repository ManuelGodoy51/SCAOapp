package com.example.scaocl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class inicio extends AppCompatActivity {
    TextView twUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        recibirDatos();
    }

    private void recibirDatos(){
        Bundle extra = getIntent().getExtras();
        String Nusuario = extra.getString("NombreUsuario");
        twUsuario = (TextView) findViewById(R.id.twUsuario);
        twUsuario.setText(Nusuario);
    }

}