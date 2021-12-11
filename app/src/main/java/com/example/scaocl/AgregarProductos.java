package com.example.scaocl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class AgregarProductos extends AppCompatActivity {
    TextView TVusuario;
    Spinner spTipoAlimento;
    EditText ETcodigoBarra,ETnombreP,ETDfechaRecepcion,ETnumeroGP,ETmarca,ETcantidad,
            ETDfechaVencimiento,ETlote,ETaccionCorrectiva,ETtemRecepcion,ETverificador,ETobservacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);
        recibirDatos();
        spTipoAlimento=findViewById(R.id.spTipoAlimento);
        LlenadoSpinner();
    }

    private void recibirDatos(){
        Bundle extra = getIntent().getExtras();
        String Nusuario = extra.getString("NombreUsuario");
        String IdUsuario = extra.getString("IdUsuario");
        TVusuario = (TextView) findViewById(R.id.TVusuario);
        TVusuario.setText(Nusuario);
    }
    private void LlenadoSpinner(){
        ArrayList<TipoAlimento> tipoProducto = new ArrayList();

        tipoProducto.add(new TipoAlimento(1,"refrigerados(0°- 5°max)"));
        tipoProducto.add(new TipoAlimento(2,"congelados(-15°max)"));
        tipoProducto.add(new TipoAlimento(3,"abarrotes"));
        tipoProducto.add(new TipoAlimento(4,"huevos,frutas y verduras"));
        ArrayAdapter<TipoAlimento> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,tipoProducto);
        spTipoAlimento.setAdapter(adapter);
    }
}