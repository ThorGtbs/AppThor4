package com.example.a1422130071.appthor;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    EditText edtNome;
    EditText edtSexo;
    EditText edtData;
    Button   btAdd,btnFoto;

    FirebaseDatabase database;
    DatabaseReference alunos;
    FirebaseAuth auth = FirebaseAuth.getInstance();
            ; // autenticação de usuario
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtNome = findViewById(R.id.edtNome);
        edtSexo = findViewById(R.id.edtSexo);
        edtData = findViewById(R.id.edtData);
        btAdd = findViewById(R.id.btAdd);
        btnFoto= findViewById(R.id.btnFoto);

        btAdd = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gravar();
            }
        });

        /*btOff = findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.sigOut();
            }
        });
        */
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                int REQUEST_IMAGE_CAPTURE=587444;
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

    }

    private  void gravar(){
        String nome = edtNome.getText().toString();
        String sexo = edtSexo.getText().toString();
        String idade = edtData.getText().toString();

        // retorna o usuario logado
        FirebaseUser user = auth.getCurrentUser();
        // pegando o uid do usuário logado
        String uid = user.getUid();

        // referencia do /aluno nome do banco
        alunos = database.getReference("Alunos");
        // pegando o uid do usuario
        alunos.child(uid).child("nome").setValue(nome);
        alunos.child(uid).child("sexo").setValue(sexo);
        alunos.child(uid).child("idade").setValue(idade);
    }
}
