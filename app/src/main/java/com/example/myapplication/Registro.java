package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    private Button registrar;
    EditText nombre,email,clave,sexo,direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registrar=findViewById(R.id.registrar);
        nombre=findViewById(R.id.nombre);
        email=findViewById(R.id.email);
        clave=findViewById(R.id.clave);
        sexo=findViewById(R.id.sexo);
        direccion=findViewById(R.id.direccion);

        registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.registrar){
            setDatos();
        }
    }

    private void setDatos() {

        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams req=new RequestParams();
        req.put("name",nombre.getText().toString());
        req.put("email",email.getText().toString());
        req.put("password",clave.getText().toString());
        req.put("sex",sexo.getText().toString());
        req.put("address",direccion.getText().toString());

        client.post(Data.HOST_USER,req,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String res=response.getString("message");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    limpiarForm();
                    Registro.this.finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void limpiarForm() {
        nombre.setText("");
        email.setText("");
        clave.setText("");
        sexo.setText("");
        direccion.setText("");
    }
}
