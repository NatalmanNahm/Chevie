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

import com.example.chevie.HomeActivity;
import com.example.chevie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //Initializing the variables
    private EditText mEmail, mPassword;
    private Button mLoginBtn;
    private TextView mBtRegister;
    private Context mContext;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Referencing each variables to their appropriate field.
        mFirebaseAuth = FirebaseAuth.getInstance();
        mContext = this;
        mEmail = (EditText) findViewById(R.id.email_login);
        mPassword = (EditText) findViewById(R.id.password_login);
        mLoginBtn = (Button) findViewById(R.id.signIn_button);
        mBtRegister = (TextView) findViewById(R.id.back_to_register);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null){
                    Toast.makeText(mContext, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(mContext, getString(R.string.login_error_message), Toast.LENGTH_SHORT).show();
                }
            }
        };

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String pwd = mPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    mEmail.setError(getString(R.string.validate_email));
                    mEmail.requestFocus();
                }

                else if (pwd.isEmpty()) {
                    mPassword.setError(getString(R.string.validate_password));
                    mPassword.requestFocus();
                }


                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(mContext, getString(R.string.info_required), Toast.LENGTH_SHORT).show();
                    mEmail.requestFocus();
                }

                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener((Activity) mContext,
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (!task.isSuccessful()){
                                                Toast.makeText(mContext, getString(R.string.wrong_password), Toast.LENGTH_SHORT).show();
                                            }

                                            else {
                                                Intent toHomeIntent = new Intent(mContext, HomeActivity.class);
                                                startActivity(toHomeIntent);
                                            }
                                        }
                                    });
                }

                else {
                    Toast.makeText(mContext, getString(R.string.error_please_try_again), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
