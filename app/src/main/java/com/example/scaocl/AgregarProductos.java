package com.example.scaocl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class AgregarProductos extends AppCompatActivity {
    TextView TVusuario;
    Spinner spTipoAlimento;
    EditText ETcodigoBarra,ETnombreP,ETDfechaRecepcion,ETnumeroGP,ETmarca,ETcantidad,
            ETDfechaVencimiento,ETlote,ETaccionCorrectiva,ETtemRecepcion,ETverificador,ETobservacion;
    Button BTNguardar;
    String Estado,Color,Textura,Olor,Aprovacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos);
        spTipoAlimento=findViewById(R.id.spTipoAlimento);
        ETcodigoBarra=findViewById(R.id.ETcodigoBarra);
        ETnombreP=findViewById(R.id.ETnombreP);
        ETDfechaRecepcion=findViewById(R.id.ETDfechaRecepcion);
        ETnumeroGP=findViewById(R.id.ETnumeroGP);
        ETmarca=findViewById(R.id.ETmarca);
        ETcantidad=findViewById(R.id.ETcantidad);
        ETDfechaVencimiento=findViewById(R.id.ETDfechaVencimiento);
        ETlote=findViewById(R.id.ETlote);
        ETaccionCorrectiva=findViewById(R.id.ETaccionCorrectiva);
        ETtemRecepcion=findViewById(R.id.ETtemRecepcion);
        ETverificador=findViewById(R.id.ETverificador);
        ETobservacion=findViewById(R.id.ETobservacion);
        BTNguardar=findViewById(R.id.BTNguardar);
        recibirDatos();
        LlenadoSpinner();
//datos para enviar codigo_barra,nombre_producto,numero_gp,marca,cantidad,lote,temrecepcion//
        BTNguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbEstadoBueno:
                if (checked)
                    Estado="1";
                    break;
            case R.id.rbEstadoMalo:
                if (checked)
                    // Ninjas rule
                    Estado="0";
                    break;
            case R.id.rbColorSi:
                if (checked)
                    // Ninjas rule
                    Color="1";
                    break;
            case R.id.rbColorNo:
                if (checked)
                    // Ninjas rule
                    Color="0";
                    break;
            case R.id.rbTexturaSi:
                if (checked)
                    // Ninjas rule
                    Textura="1";
                    break;
            case R.id.rbTexturaNo:
                if (checked)
                    // Ninjas rule
                    Textura="0";
                    break;
            case R.id.rbOlorSi:
                if (checked)
                    // Ninjas rule
                    Olor="1";
                    break;
            case R.id.rbOlorNo:
                if (checked)
                    // Ninjas rule
                    Olor="0";
                    break;
            case R.id.rbAprovado:
                if (checked)
                    // Ninjas rule
                    Aprovacion="1";
                    break;
            case R.id.rbRechazado:
                if (checked)
                    // Ninjas rule
                    Aprovacion="0";
                    break;
        }

    }
}