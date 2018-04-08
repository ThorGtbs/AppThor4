package com.example.a1422130071.appthor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateUser extends AppCompatActivity {

    EditText edtEmail, edtSenha;
    Button button;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        edtEmail= findViewById(R.id.edtEmail);
        edtSenha= findViewById(R.id.edtSenha);
        button= findViewById(R.id.btnInserir);
        mAuth = FirebaseAuth.getInstance();
    }



    public  void registro(){
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();

        if(email.isEmpty()){
            edtEmail.setError("Digite o Email");
            edtEmail.requestFocus();
            return;
        }

        if(senha.isEmpty()){
            edtSenha.setError("Digite a Senha");
            edtSenha.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(CreateUser.this, "Sucesso no registro", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CreateUser.this, "Algo deu errado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
