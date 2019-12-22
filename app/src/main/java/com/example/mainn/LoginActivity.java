package com.example.mainn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    EditText e1, e2;
    Button b1, b2;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String un = e1.getText().toString();
                String pwd = e2.getText().toString();
                auth.signInWithEmailAndPassword(un, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent i = new Intent(LoginActivity.this, DeleteActivity.class);
                            i.putExtra("un", un);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });


    }

}
