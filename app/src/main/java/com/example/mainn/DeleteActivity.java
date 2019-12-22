package com.example.mainn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity
{
    Button b1, b2;
    FirebaseAuth auth;
    FirebaseFirestore fdb;
    DocumentReference dref;
    String uid;
    TextView e1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        e1 = findViewById(R.id.e1);
        auth = FirebaseAuth.getInstance();
        auth = FirebaseAuth.getInstance();
        fdb = FirebaseFirestore.getInstance();
        uid = auth.getCurrentUser().getUid();


        String s = e1.getText().toString() + "\n" +uid;
        e1.setText(s);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*DocumentReference docRef = fdb.collection("Users").document(uid);
                Map<String,Object> updates = new HashMap<>();
                updates.put("Age", FieldValue.delete());
                updates.put("Blood Group", FieldValue.delete());
                updates.put("Email-ID", FieldValue.delete());
                updates.put("Location", FieldValue.delete());
                updates.put("Name", FieldValue.delete());
                updates.put("Password", FieldValue.delete());
                updates.put("Phone No.", FieldValue.delete());
                updates.put("Status", FieldValue.delete());
                docRef.update(updates);*/
                fdb.collection("Users").document(uid).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(DeleteActivity.this, "Details Deleted", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(DeleteActivity.this, "Details NOT Deleted", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                auth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(DeleteActivity.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                        }
                        else
                        {
                            Toast.makeText(DeleteActivity.this, "Account NOT Deleted", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangepwdActivity.class));
            }
        });

    }
}
