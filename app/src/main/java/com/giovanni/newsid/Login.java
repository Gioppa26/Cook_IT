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

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    EditText temail,tpassword;
    Button login;
    TextView createButton;
    ProgressBar caricamentoLogin;
    FirebaseAuth fAuth1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        temail=(EditText) findViewById(R.id.txtEmailL);
        tpassword=(EditText) findViewById(R.id.txtPasswordL);
        caricamentoLogin=(ProgressBar) findViewById(R.id.pcLogin);
        fAuth1 = FirebaseAuth.getInstance();
        login = (Button) findViewById(R.id.btnLogin);
        createButton = (TextView) findViewById(R.id.txtCreate);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = temail.getText().toString().trim();
                String passwd = tpassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    temail.setError("Nome utente non inserito");
                    return;
                }

                if (TextUtils.isEmpty(passwd)) {
                    tpassword.setError("Password non inserita");
                    return;
                }

                if (tpassword.length() < 6) {
                    tpassword.setError("la password deve essere lunga almeno 6 caratteri");
                    return;
                }
                caricamentoLogin.setVisibility(View.VISIBLE);
                fAuth1.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "Login effettuato", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Alimenti.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Password non corretta", Toast.LENGTH_SHORT).show();
                            caricamentoLogin.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });

    }
}