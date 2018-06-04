package com.example.tejas.eat24;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejas.eat24.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUp extends AppCompatActivity {

    EditText edituser,editpass,editrepass,editemail;
    Button btn_signUp;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        edituser = (EditText) findViewById(R.id.edituser);
        editpass = (EditText) findViewById(R.id.editpass);
        editrepass = (EditText) findViewById(R.id.editrepass);
        editemail = (EditText) findViewById(R.id.editemail);


        btn_signUp = (Button)findViewById(R.id.btn_signUp);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                String pass = editpass.getText().toString();
                String pass_ver = editrepass.getText().toString();
                String email = editemail.getText().toString();

                if(TextUtils.isEmpty(pass)){
                    editpass.setError("Enter Password");
                }
                else if(TextUtils.isEmpty(pass_ver)){
                    editrepass.setError("Enter Verify Password");
                }
                else if(TextUtils.isEmpty(email)){
                    editemail.setError("Enter Email");
                }
                else{
                    if(pass.equals(pass_ver)){
//                    Toast.makeText(SignUp.this, "Equals", Toast.LENGTH_SHORT).show();

                        mAuth.createUserWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            //Log.d(TAG, "createUserWithEmail:success");
                                            Toast.makeText(SignUp.this, "Authentication Successful",
                                                    Toast.LENGTH_SHORT).show();
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            //updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(SignUp.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
//                                            updateUI(null);
                                        }

                                        // [START_EXCLUDE]
                                        //hideProgressDialog();
                                        // [END_EXCLUDE]
                                    }
                                });

                    }else {
                        Toast.makeText(SignUp.this, "Passwords Don't Match", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


    }
}
