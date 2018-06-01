package com.example.tejas.eat24;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejas.eat24.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    EditText edituser,editpass,editrepass,editemail;
    Button btn_signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edituser = (EditText) findViewById(R.id.edituser);
        editpass = (EditText) findViewById(R.id.editpass);
        editrepass = (EditText) findViewById(R.id.editrepass);
        editemail = (EditText) findViewById(R.id.editemail);

        //edtName = (EditText) findViewById(R.id.edtName);
        //edtPassword = (EditText) findViewById(R.id.edtPassword);
        //edtPhone = (EditText) findViewById(R.id.edtPhone);

        btn_signUp = (Button)findViewById(R.id.btn_signUp);

        //Init Firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");



        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Check if already user email registered
                        if(dataSnapshot.child(editemail.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this,"Email already registered", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mDialog.dismiss();
                            User user = new User(edituser.getText().toString(),editpass.getText().toString());
                            table_user.child(editemail.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"SignUp Successful", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }

        });
    }
}
