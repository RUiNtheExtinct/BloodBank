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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity
{

    EditText etForgotEmailId;
    Button btnResetPassword;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        etForgotEmailId=findViewById(R.id.etForgotEmailId);
        btnResetPassword=findViewById(R.id.btnLogin);
        firebaseAuth=FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String un=etForgotEmailId.getText().toString();
                firebaseAuth.sendPasswordResetEmail(firebaseAuth.getCurrentUser().getEmail()).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ForgotActivity.this, "Check Email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(ForgotActivity.this, "Password Reset Failed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
