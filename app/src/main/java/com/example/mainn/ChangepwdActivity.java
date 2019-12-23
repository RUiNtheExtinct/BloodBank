package com.example.mainn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ChangepwdActivity extends AppCompatActivity
{
    EditText e6, e7, e8;
    Button b1;
    boolean b = true;
    Object opwd;
    FirebaseAuth auth;
    FirebaseFirestore fdb;
    DocumentReference dref;
    String uid, op;
    String eid;
    AuthCredential ac;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd);

        e6 = findViewById(R.id.e6);
        e7 = findViewById(R.id.e7);
        e8 = findViewById(R.id.e8);
        b1 = findViewById(R.id.b1);

        auth = FirebaseAuth.getInstance();
        fdb = FirebaseFirestore.getInstance();

        uid = auth.getCurrentUser().getUid();
        dref = fdb.collection("Users").document(uid);
        eid = auth.getCurrentUser().getEmail();


        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String s6 = e6.getText().toString();
                final String s7 = e7.getText().toString();
                final String s8 = e8.getText().toString();

                ac = EmailAuthProvider.getCredential(eid, s6);

                if(s7.length() <= 8)
                {
                    e7.setError("Password should contain atleast 8 characters...");
                    e7.setText("");
                    b = false;
                }
                else
                {
                    b = true;
                }
                if(s7.compareTo(s8) != 0)
                {
                    e8.setError("Password don't match!!!");
                    e8.setText("");
                    b = false;
                }
                else
                {
                    b = true;
                }
                if(b)
                {
                    auth.getCurrentUser().reauthenticate(ac).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful()) {
                                auth.getCurrentUser().updatePassword(s7).addOnCompleteListener(new OnCompleteListener<Void>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(ChangepwdActivity.this, "Password Changed", Toast.LENGTH_SHORT).show();
                                            dref.update("Password", s7);
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                            finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(ChangepwdActivity.this, "Password Changed", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(ChangepwdActivity.this, "Error!!!\nAuthentication Failed", Toast.LENGTH_SHORT).show();
                                e6.setError("Enter Correct Password...");
                                e6.setText("");
                                e6.requestFocus();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(ChangepwdActivity.this, "Password Change Failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
