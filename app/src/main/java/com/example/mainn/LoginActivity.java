package com.example.mainn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity
{
    EditText etEmailid,etPassword;
    TextView tvForgotpassword,tvSignup;
    Button btnLogin;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    static SharedPreferences sp;
    static SharedPreferences.Editor ed;
    int j, autoSave;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailid=findViewById(R.id.etEmailId);
        etPassword=findViewById(R.id.etPassword);
        tvForgotpassword=findViewById(R.id.tvForgotPassword);
        tvSignup=findViewById(R.id.tvSignup);
        btnLogin=findViewById(R.id.btnLogin);

        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        sp = getSharedPreferences("autoLogin", MODE_PRIVATE);
        j = sp.getInt("key", 0);
        if(j > 0)
        {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }


        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                final String un = etEmailid.getText().toString();
                String pwd = etPassword.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(un, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            autoSave = 1;
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putInt("key", autoSave);
                            editor.apply();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("un", un);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Login Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                finish();
            }
        });

        tvForgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(LoginActivity.this,ForgotActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
