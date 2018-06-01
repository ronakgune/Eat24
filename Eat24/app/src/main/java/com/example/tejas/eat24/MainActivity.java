package com.example.tejas.eat24;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button)findViewById(R.id.button_signin);
        btnSignUp = (Button)findViewById(R.id.button_signup);


        //Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
          @Override
        public void onClick(View v) {
          Intent signUp = new Intent(MainActivity.this,SignUp.class);
          startActivity(signUp);
          }
        });

     //   btnSignIn.setOnClickListener(new View.OnClickListener() {
       //     @Override
         //   public void onClick(View view) {
           //     Intent signIn = new Intent(MainActivity.this,SignIn.class);
             //   startActivity(signIn);
           // }
       // });
    }
}
