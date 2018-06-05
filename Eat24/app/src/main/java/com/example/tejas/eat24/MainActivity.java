package com.example.tejas.eat24;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        btnSignIn = (Button)findViewById(R.id.button_signin);
        btnSignUp = (Button)findViewById(R.id.button_signup);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPass);




        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String email_val = email.getText().toString();
               String pass_val = password.getText().toString();

               //Check if email is empty
               if(TextUtils.isEmpty(email_val)){
                   email.setError("Please Enter Email");
               }
               //Check if password is empty
               else if(TextUtils.isEmpty(pass_val)){
                   password.setError("Please Enter Password");
               }else{
                   mAuth.signInWithEmailAndPassword(email_val, pass_val)
                           .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful()) {
                                       // Sign in success, update UI with the signed-in user's information
//                                       Log.d(TAG, "signInWithEmail:success");
                                       Toast.makeText(MainActivity.this, "Authentication Successful",
                                               Toast.LENGTH_SHORT).show();
                                       Intent home = new Intent(MainActivity.this, home.class);
                                       startActivity(home);
                                       FirebaseUser user = mAuth.getCurrentUser();
//                                       updateUI(user);
                                   } else {
                                       // If sign in fails, display a message to the user.
//                                       Log.w(TAG, "signInWithEmail:failure", task.getException());
                                       Toast.makeText(MainActivity.this, "Authentication failed.",
                                               Toast.LENGTH_SHORT).show();
//                                       updateUI(null);
                                   }

                                   // [START_EXCLUDE]
                                   if (!task.isSuccessful()) {
                                       Toast.makeText(MainActivity.this, "Authentication failed.",
                                               Toast.LENGTH_SHORT).show();
                                   }
                                   // [END_EXCLUDE]
                               }
                           });
               }



            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
          @Override
        public void onClick(View v) {
          Intent signUp = new Intent(MainActivity.this,home.class);
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
