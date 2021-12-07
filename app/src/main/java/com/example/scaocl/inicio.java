package com.example.scaocl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class inicio extends AppCompatActivity {
    TextView twUsuario;
    Button btnAgregarProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        recibirDatos();
        btnAgregarProducto = (Button) findViewById(R.id.btnAgregarProducto);
        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inicio.this, AgregarProductos.class);
                startActivity(intent);
            }
        });
    }

    private void recibirDatos(){
        Bundle extra = getIntent().getExtras();
        String Nusuario = extra.getString("NombreUsuario");
        twUsuario = (TextView) findViewById(R.id.twUsuario);
        twUsuario.setText(Nusuario);
    }

}