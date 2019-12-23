package com.example.mainn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
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
        dref = fdb.collection("Users").document(uid);


        String s = e1.getText().toString() + "\n" +uid;
        e1.setText(s);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dref.update("Name", FieldValue.delete());
                dref.update("Age", FieldValue.delete());
                dref.update("Blood Group", FieldValue.delete());
                dref.update("Location", FieldValue.delete());
                dref.update("Phone No", FieldValue.delete());
                dref.delete().addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(DeleteActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(DeleteActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    }
                });


                auth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(DeleteActivity.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(DeleteActivity.this, "Account NOT Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
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
