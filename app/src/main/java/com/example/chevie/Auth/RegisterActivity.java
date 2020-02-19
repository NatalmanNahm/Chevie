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
                    mName.setError(getString(R.string.enter_name));
                    mName.requestFocus();
                }

                else if (email.isEmpty()) {
                    mEmail.setError(getString(R.string.validate_email));
                    mEmail.requestFocus();
                }

                else if (pwd.isEmpty()) {
                    mPassword.setError(getString(R.string.validate_password));
                    mPassword.requestFocus();
                }

                else if (pwdConf.isEmpty()) {
                    mConfPassword.setError(getString(R.string.please_confirm_password));
                    mConfPassword.requestFocus();
                }

                else if (name.isEmpty() && email.isEmpty() && pwd.isEmpty() && pwdConf.isEmpty()){
                    Toast.makeText(mContext, getString(R.string.field_empty), Toast.LENGTH_SHORT).show();
                    mName.requestFocus();
                }

                else if (!pwd.equals(pwdConf)){
                    mConfPassword.setError(getString(R.string.password_wrong));
                    mConfPassword.requestFocus();
                }

                else if (!(name.isEmpty() && email.isEmpty() && pwd.isEmpty() && pwdConf.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(mContext,
                                                getString(R.string.unsuccessful_registration),
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
                                                                    getString(R.string.registration),
                                                                    Toast.LENGTH_LONG).show();
                                                        } else {
                                                            Toast.makeText(RegisterActivity.this,
                                                                    getString(R.string.registration_success),
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
                    Toast.makeText(mContext, getString(R.string.error_please_try_again), Toast.LENGTH_SHORT).show();
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
