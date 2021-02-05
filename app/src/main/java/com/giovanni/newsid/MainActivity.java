package com.giovanni.newsid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText TxtEmail,TxtPassword;
    TextView tLogin;
    FirebaseAuth fAuth;
    ProgressBar caricamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TxtEmail = (EditText) findViewById(R.id.txtEmail);
        TxtPassword = (EditText) findViewById(R.id.txtPassword);
        tLogin = (TextView) findViewById(R.id.textView_login);
        caricamento = (ProgressBar) findViewById(R.id.pbCaricamento);
        button = (Button) findViewById(R.id.btnLogin);
        fAuth = FirebaseAuth.getInstance();
        caricamento = (ProgressBar) findViewById(R.id.pbCaricamento);
        if (fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = TxtEmail.getText().toString().trim();
                String password = TxtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    TxtEmail.setError("Nome utente non inserito");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    TxtPassword.setError("Password non inserita");
                    return;
                }

                if (TxtPassword.length() < 6) {
                    TxtPassword.setError("la password deve essere lunga almeno 6 caratteri");
                    return;
                }
                caricamento.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Utente creato", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Utente non creato", Toast.LENGTH_SHORT).show();
                            caricamento.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        tLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }
}