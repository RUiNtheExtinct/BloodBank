package com.example.mainn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ChangepwdActivity extends AppCompatActivity
{
    EditText e6, e7, e8;
    Button b1;
    boolean b = true;
    String opwd;
    FirebaseAuth auth;
    FirebaseFirestore fdb;
    DocumentReference dref;
    String uid;
    String eid;
    AuthCredential ac;

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
        dref = fdb.collection("Users").document("uid");
        eid = auth.getCurrentUser().getEmail();

        dref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if(task.isSuccessful())
                {
                    DocumentSnapshot ds = task.getResult();
                    opwd = ds.get("Password").toString();
                }

            }
        });

        ac = EmailAuthProvider.getCredential(eid, opwd);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String s6 = e6.getText().toString();
                final String s7 = e7.getText().toString();
                final String s8 = e8.getText().toString();

                if(s6.compareTo(opwd) != 0)
                {
                    e6.setError("Entered Password doesn't match current password");
                    e6.requestFocus();
                    e6.setText("");
                    b = false;
                }
                else
                {
                    b = true;
                }
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
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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
