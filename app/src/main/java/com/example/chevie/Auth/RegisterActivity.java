package com.example.chevie.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chevie.Auth.LoginActivity;
import com.example.chevie.HomeActivity;
import com.example.chevie.Models.User;
import com.example.chevie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    //Initializing the variables
    private EditText mEmail, mPassword, mConfPassword, mName;
    private Button mRegisterBtn;
    private TextView mBtSignUp;
    private Context mContext;
    private User mUser;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Referencing each variables to their appropriate field.
        mFirebaseAuth = FirebaseAuth.getInstance();
        mContext = this;
        mName = (EditText) findViewById(R.id.name_user);
        mEmail = (EditText) findViewById(R.id.email_register);
        mPassword = (EditText) findViewById(R.id.password_register);
        mConfPassword = (EditText) findViewById(R.id.password_register_conf);
        mRegisterBtn = (Button) findViewById(R.id.register_btn);
        mBtSignUp = (TextView) findViewById(R.id.back_to_login);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = mName.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                String pwd = mPassword.getText().toString().trim();
                String pwdConf = mConfPassword.getText().toString().trim();

                if (name.isEmpty()){
                    mName.setError("Please enter Your Name");
                    mName.requestFocus();
                }

                else if (email.isEmpty()) {
                    mEmail.setError("Please enter a Valid Email");
                    mEmail.requestFocus();
                }

                else if (pwd.isEmpty()) {
                    mPassword.setError("Please Enter Password");
                    mPassword.requestFocus();
                }

                else if (pwdConf.isEmpty()) {
                    mConfPassword.setError("Please confirm your password");
                    mConfPassword.requestFocus();
                }

                else if (name.isEmpty() && email.isEmpty() && pwd.isEmpty() && pwdConf.isEmpty()){
                    Toast.makeText(mContext, "Fields are Empty! Please Enter Info!", Toast.LENGTH_SHORT).show();
                    mName.requestFocus();
                }

                else if (!pwd.equals(pwdConf)){
                    mConfPassword.setError("Passwords don't match");
                    mConfPassword.requestFocus();
                }

                else if (!(name.isEmpty() && email.isEmpty() && pwd.isEmpty() && pwdConf.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(mContext,
                                                "Unsuccessful Registration, Please try again",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    else {

                                        //Addind user to the firebase database
                                        mUser = new User(name,email);
                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser()
                                                .getUid()).setValue(mUser).addOnCompleteListener(
                                                new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            Toast.makeText(RegisterActivity.this,
                                                                    "User registration Complition",
                                                                    Toast.LENGTH_LONG).show();
                                                        } else {
                                                            Toast.makeText(RegisterActivity.this,
                                                                    "User registration failed",
                                                                    Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                }
                                        );
                                        startActivity(new Intent(mContext, HomeActivity.class));
                                    }
                                }
                            });
                }

                else {
                    Toast.makeText(mContext, "Error Occurred, Please again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToLoginIntent = new Intent(mContext, LoginActivity.class);
                startActivity(backToLoginIntent);
            }
        });
    }
}
