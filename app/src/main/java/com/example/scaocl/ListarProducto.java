package com.example.scaocl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListarProducto extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
EditText ETcodigoBarraLista;
TextView TVcodigoBarra,TVnombre,TVmarca,TVstock;
Button BTNcamaraa;


ProgressDialog progreso;
RequestQueue request;
JsonObjectRequest jsonObjectRequest;
JSONArray ja;

RecyclerView recyclerProductos;
ArrayList<producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_producto);

        ETcodigoBarraLista=findViewById(R.id.ETcodigoBarraLista);
        BTNcamaraa=findViewById(R.id.BTNcamaraa);
        TVcodigoBarra=findViewById(R.id.TVcodigoBarra);
        TVnombre=findViewById(R.id.TVnombre);
        TVmarca=findViewById(R.id.TVmarca);
        TVstock=findViewById(R.id.TVstock);

        request = Volley.newRequestQueue(this);
        listaProductos = new ArrayList<>();
        recyclerProductos = (RecyclerView) findViewById(R.id.idRecycler);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));
        recyclerProductos.setHasFixedSize(true);
        
        cargarWebService();
        
        BTNcamaraa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrador= new IntentIntegrator(ListarProducto.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - CDB");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
            }
        });
        ETcodigoBarraLista.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Consultaproducto("http://192.168.1.7/ejemplologin/consultaproducto.php?codigo_barra="+ETcodigoBarra.getText().toString()+"");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ///Esta parte no es necesaria////
            }

            @Override
            public void afterTextChanged(Editable s) {
                String codigoBarra = ETcodigoBarraLista.getText().toString();
                if(!codigoBarra.isEmpty()){
                    productoBusqueda("http://192.168.1.3/ejemplologin/consultaproducto.php?codigo_barra=" + ETcodigoBarraLista.getText().toString() + "");
                }
            }
        });
    }

    private void cargarWebService() {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Consultando...");
        progreso.show();
        String url = "http://192.168.1.3/ejemplologin/listarproducto.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getApplicationContext(),"No se pudo conectar con servidor "+error.toString(),Toast.LENGTH_SHORT).show();
        progreso.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        producto producto=null;
        JSONArray json = response.optJSONArray("productos");
        try {
        for(int i=0; i<json.length();i++){
            producto = new producto();
            JSONObject jsonObject= null;
            jsonObject=json.getJSONObject(i);

            producto.setCodigo_barra(jsonObject.optString("a_codigo_barra"));
            producto.setNombre(jsonObject.optString("a_nombre"));
            producto.setMarca(jsonObject.optString("a_marca"));
            producto.setStock(jsonObject.optInt("a_stock"));

            listaProductos.add(producto);

            }
        progreso.hide();
        productosAdapter adapter = new productosAdapter(listaProductos);
        recyclerProductos.setAdapter(adapter);
        }catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"No se pudo conectar con servidor "+response.toString(),Toast.LENGTH_SHORT).show();
            progreso.hide();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result !=null){
            if(result.getContents() == null){
                Toast.makeText(this,"Scanner cancelado",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,result.getContents(),Toast.LENGTH_SHORT).show();
                ETcodigoBarraLista.setText(result.getContents());
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void productoBusqueda(String URL){
        Log.i("url",""+URL);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ja = new JSONArray(response);
                    Toast.makeText(getApplicationContext(), "El producto "+ja.getString(2)+"tiene "+ja.getString(4)+" Stock en almacen", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "no existe producto en base de datos", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }
}