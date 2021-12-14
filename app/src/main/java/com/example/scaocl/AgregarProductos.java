package com.example.scaocl;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class AgregarProductos extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    TextView TVusuario,TVidUsuario;
    Spinner spTipoAlimento;
    EditText ETcodigoBarra,ETnombreP,ETDfechaRecepcion,ETnumeroGP,ETmarca,ETcantidad,
            ETDfechaVencimiento,ETlote,ETaccionCorrectiva,ETtemRecepcion,ETverificador,ETobservacion;
    Button BTNguardar, BTNcamara;
    String Estado,Color,Textura,Olor,Aprovacion;

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    private int dia,mes,ano,dia2,mes2,ano2;
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
        BTNcamara=findViewById(R.id.BTNcamara);
        request=Volley.newRequestQueue(this);
        recibirDatos();
        LlenadoSpinner();
//datos para enviar codigo_barra,nombre_producto,numero_gp,marca,cantidad,lote,temrecepcion//
        BTNguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extra = getIntent().getExtras();
                String IdUsuario = extra.getString("IdUsuario");
                cargarWebService();
            }
        });
        ETDfechaVencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarfechaV();
            }
        });
        ETDfechaRecepcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarfechaR();
            }
        });

        BTNcamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrador= new IntentIntegrator(AgregarProductos.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - CDP");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result !=null){
            if(result.getContents() == null){
                Toast.makeText(this,"Scanner cancelado",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,result.getContents(),Toast.LENGTH_SHORT).show();
                ETcodigoBarra.setText(result.getContents());
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void cargarWebService() {

        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();
        String url = "http://192.168.1.7/ejemplologin/registroproducto.php?codigo_barra=" + ETcodigoBarra.getText().toString() +
                "&nombre_producto=" + ETnombreP.getText().toString() + "&n_guia_despacho=" + ETnumeroGP.getText().toString() +
                "&marca=" + ETmarca.getText().toString() + "&cantidad=" + ETcantidad.getText().toString() + "&lote=" + ETlote.getText().toString() +
                "&t_recepcion=" + ETtemRecepcion.getText().toString() + "&fecha_recepcion="+ETDfechaRecepcion.getText().toString()+
                "&fecha_vencimiento="+ETDfechaVencimiento.getText().toString()+"&id_user="+TVidUsuario.getText().toString()+"&id_tipo_alimento="+(spTipoAlimento.getSelectedItemId()+1)+
                "&accion_correctiva="+ETaccionCorrectiva.getText().toString()+"&verificador="+ETverificador.getText().toString()+"&observacion="+ETobservacion.getText().toString()+"";
        url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(),"Se ha registrado correctamente",Toast.LENGTH_SHORT).show();
        progreso.hide();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getApplicationContext(),"No se pudo registrar "+error.toString(),Toast.LENGTH_SHORT).show();
    }

    private void recibirDatos(){
        Bundle extra = getIntent().getExtras();
        String Nusuario = extra.getString("NombreUsuario");
        String IdUsuario = extra.getString("IdUsuario");
        TVusuario = (TextView) findViewById(R.id.TVusuario);
        TVidUsuario=(TextView)findViewById(R.id.TVidUser);
        TVidUsuario.setText(IdUsuario);
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
    }
    ////////////Se carga la misma fecha en los dos edit text asi que se debe arreglar///////////////////
    private void cargarfechaV(){
        final Calendar c=Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ETDfechaVencimiento.setText(ano+"-"+ (mes+1) +"-"+dia);
            }
        },ano,mes,dia);
        datePickerDialog.show();
    }

    private void cargarfechaR(){
        final Calendar c=Calendar.getInstance();
        dia2=c.get(Calendar.DAY_OF_MONTH);
        mes2=c.get(Calendar.MONTH);
        ano2=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ETDfechaRecepcion.setText(ano2+"-"+ (mes2+1) +"-"+dia2);
            }
        },ano2,mes2,dia2);
        datePickerDialog.show();
    }
}