package com.example.mainn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity
{

    EditText e1, e2, e3, e5, e6, e7, e8;
    Spinner e4;
    Button b1;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    boolean b = true;
    FirebaseAuth auth;
    FirebaseFirestore fdb;
    DocumentReference dref;
    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        e5 = findViewById(R.id.e5);
        e6 = findViewById(R.id.e6);
        e7 = findViewById(R.id.e7);
        e8 = findViewById(R.id.e8);
        b1 = findViewById(R.id.b1);
        auth = FirebaseAuth.getInstance();
        fdb = FirebaseFirestore.getInstance();




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        e4.setAdapter(adapter);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String s1 = e1.getText().toString();
                final String s2 = e2.getText().toString();
                final String s3 = e3.getText().toString();
                final String s4 = e4.getSelectedItem().toString();
                final String s5 = e5.getText().toString();
                final String s6 = e6.getText().toString();
                final String s7 = e7.getText().toString();
                final String s8 = e8.getText().toString();

                if(s1.matches(emailPattern))
                {
                    e1.setError("Enter Valid Email-ID.");
                    e1.setText("");
                    b = false;
                }
                else
                {
                    b = true;
                }
                if(s2.length()<=3)
                {
                    e2.setError("Enter Valid Name.");
                    b = false;
                    e2.setText("");
                }
                else
                {
                    b = true;
                }
                if(s3.length()==0 || Integer.parseInt(s3)<=0 || Integer.parseInt(s3)>120)
                {
                    e3.setError("Enter Valid Age.");
                    b = false;
                    e3.setText("");
                }
                else
                {
                    b = true;
                }
                if(s4.compareTo("Blood Group") == 0)
                {
                    Toast.makeText(RegistrationActivity.this, "Select a blood group!", Toast.LENGTH_SHORT).show();
                    e4.requestFocus();
                    b = false;
                    //e2.setText("");
                }
                else
                {
                    b = true;
                }
                if(s5.length()<10)
                {
                    e5.setError("Enter Valid Phone No.");
                    b = false;
                    e2.setText("");
                }
                else
                {
                    b = true;
                }
                if(s6.length() <= 0)
                {
                    e6.setError("Enter Valid City Name");
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
                    auth.createUserWithEmailAndPassword(s1, s7).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                uid = auth.getCurrentUser().getUid();
                                Toast.makeText(RegistrationActivity.this, "User Created...", Toast.LENGTH_SHORT).show();
                                dref = fdb.collection("Users").document(uid);
                                Map<String, Object> uu = new HashMap<>();
                                uu.put("Email-ID", s1);
                                uu.put("Name", s2);
                                uu.put("Age", s3);
                                uu.put("Blood Group", s4);
                                uu.put("Phone No", s5);
                                uu.put("Location", s6);
                                uu.put("Password", s7);
                                uu.put("Status", false);
                                dref.set(uu).addOnCompleteListener(new OnCompleteListener<Void>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(RegistrationActivity.this, "New Account Created", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                            finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(RegistrationActivity.this, "New Account NOT Created", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else
                            {
                                e1.setError("Enter diffrent Email-ID");
                                e1.setText("");
                                Toast.makeText(RegistrationActivity.this, "Email-ID exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this, "Registration Failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
