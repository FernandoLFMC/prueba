package com.example.myapplication;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Login_Result extends AppCompatActivity {
 String avatar,email,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar((toolbar));
         avatar =this.getIntent().getExtras().getString("avatar");
        email =this.getIntent().getExtras().getString("email");
        name= this.getIntent().getExtras().getString("name");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            } });
        loadComponents();


        }

    private void loadComponents() {/*
        Tex0tView nameTxt=(TextView)this.findViewById(R.id.nombre);
        TextView emailTxt=(TextView)this.findViewById(R.id.email);
        ImageView img =(ImageView)this.findViewById(R.id.avatar);
        nameTxt.setText(name);
        nameTxt.setText(email);
        task loadimg =new TaskImag();
        loadimg.execute(avatar);
        loadimg.setLoadImage(img,this);

    */}

}
