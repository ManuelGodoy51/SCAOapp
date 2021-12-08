package com.example.scaocl;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    EditText ETrut,ETdv,ETpassword;
    Button btnIngresar;
    JSONArray ja;
    String rut,dv,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ETrut = (EditText)findViewById(R.id.ETrut);
        ETdv = (EditText)findViewById(R.id.ETdv);
        ETpassword = (EditText)findViewById(R.id.ETpassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rut=ETrut.getText().toString();
                dv=ETdv.getText().toString();
                password=ETpassword.getText().toString();
/////////////////////////////////////Encripta contraseña traida del xml
                byte[] md5Input = ETpassword.getText().toString().getBytes();
                BigInteger md5Data = null;
                try {
                    md5Data= new BigInteger(1,md5.encrypMD5(md5Input));
                }catch (Exception e){
                    e.printStackTrace();
                }
                String md5Str = md5Data.toString(16);
////////////////////////////////////Se entregan los valores tanto del web service como de la contraseña encritada al metodo consultapass
                if(!rut.isEmpty() && !dv.isEmpty() && !password.isEmpty()){
                    ConsultaPass("http://192.168.1.7/ejemplologin/consultarusuario.php?rut="+ETrut.getText().toString()+"&dv="+ETdv.getText().toString(), md5Str);
                }else{
                    Toast.makeText(MainActivity.this,"No se permiten campos vacios",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    ////////////////////////////////////////////metodo para comparar las dos contraseñas
    private void ConsultaPass(String URL, String md5Str){
        Log.i("url",""+URL);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ja = new JSONArray(response);
                    String contra = ja.getString(7);
                    String usuario = ja.getString(3);
                    String rol = ja.getString(5);
                    String estado = ja.getString(10);

                    if(estado.equals("1")) {
                        if(rol.equals("4") || rol.equals("2")) {
                            if (contra.equals(md5Str)) {

                                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, inicio.class);
                                intent.putExtra("NombreUsuario", usuario);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "verifique su contraseña", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Este usuario no cuenta con el rol requerido para esta app", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "El usuario esta inactivo, por favor contacte con un supervisor", Toast.LENGTH_SHORT).show();
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "El usuario no existe en la base de datos", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }
}
