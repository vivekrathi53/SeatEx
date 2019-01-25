package com.example.android.SeatEx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText username;
    Button signUp;
    Button logIn;
 //   final DatabaseReference databaseExpenses = FirebaseDatabase.getInstance().getReference("Expense");
    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signUp=findViewById(R.id.sign_up);
        logIn=findViewById(R.id.log_in);
        progressBar = findViewById(R.id.progressbar);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(MainActivity.this, Begin.class));
            finish();
        }
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String assword = password.getText().toString();
             //   String id = databaseExpenses.push().getKey();
               // String user = username.getText().toString();
              //  Profile profile = new Profile(user,mail,assword);
            //    databaseExpenses.child(id).setValue(profile);
                if(TextUtils.isEmpty(mail))
                {
                    Toast.makeText(getApplicationContext(),"Enter the email adress",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(assword))
                {
                    Toast.makeText(getApplicationContext(),"Enter the password",Toast.LENGTH_SHORT).show();
                }
                else if(assword.length()<6)
                {
                    Toast.makeText(getApplicationContext(),"Password length is too small",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(mail,assword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(MainActivity.this, Begin.class));
                            finish();
                        }
                    }
                });
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String assword = password.getText().toString();
              //  String id = databaseExpenses.push().getKey();
              //  String user = username.getText().toString();
            //    Profile profile = new Profile(user,mail,assword);
          //      databaseExpenses.child(id).setValue(profile);
                if(TextUtils.isEmpty(mail))
                {
                    Toast.makeText(getApplicationContext(),"Enter the email adress",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(assword))
                {
                    Toast.makeText(getApplicationContext(),"Enter the password",Toast.LENGTH_SHORT).show();
                }
                else if(assword.length()<6)
                {
                    Toast.makeText(getApplicationContext(),"Password length is too small",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(mail,assword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(MainActivity.this, Begin.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
