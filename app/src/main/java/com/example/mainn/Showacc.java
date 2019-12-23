package com.example.mainn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Showacc extends MainActivity {

    Button b1;
    TextView e1, e2, e3, e4, e5, e6;
    String s1, s2, s3, s4, s5, s6;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showacc);

        b1 = findViewById(R.id.b1);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        e5 = findViewById(R.id.e5);
        e6 = findViewById(R.id.e6);

        auth = FirebaseAuth.getInstance();

        s1 = auth.getCurrentUser().getEmail();
        e1.setText(s1);

    }
}
