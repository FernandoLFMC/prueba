package com.example.myapplication;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.utils.Data;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    GoogleApiClient client;
    TextView registra;
    EditText em,pass;
    Button accion;
    private int GOOGLE_CODE=11235;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        client =new GoogleApiClient.Builder(this)
          .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,options)
                .build();
        loadComponents();
    }

    private void loadComponents() {

        em=findViewById(R.id.emaillogin);
        pass=findViewById(R.id.passwordlogin);
        accion=findViewById(R.id.accion);
        accion.setOnClickListener(this);

        registra=findViewById(R.id.registra);
        registra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg=new Intent(LoginActivity.this,Registro.class);
                LoginActivity.this.startActivity(intentReg);
            }

        });
        SignInButton googlebtn =(SignInButton)this.findViewById(R.id.googlebuton);
        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent =  Auth.GoogleSignInApi.getSignInIntent(client);
             startActivityForResult(intent,GOOGLE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_CODE) {
            GoogleSignInResult result= Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()) {

                 Intent loginr= new Intent(this, Login_Result.class);
                 loginr.putExtra("avatar",result.getSignInAccount().getPhotoUrl());
                 loginr.putExtra("email",result.getSignInAccount().getEmail());
                 loginr.putExtra("nombre",result.getSignInAccount().getDisplayName());
                 startActivity(loginr);
            //result.getSignInAccount().
           //Toast.makeText(this,"ok",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,R.string.Error_login,Toast.LENGTH_LONG).show();

            }
        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.accion){
            login();
        }
    }

    private void login() {
        String email = em.getText().toString();
        String password = pass.getText().toString();

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("email",email);
        params.put("password",password);

        client.post(Data.HOST_USER_LOGIN, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getString("token") != null){
                        Data.TOKEN = response.getString("token");
                        Toast.makeText(LoginActivity.this, "ok", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(LoginActivity.this, menuLateral.class);
                         startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, response.getString("msn"), Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}

