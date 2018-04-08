package com.example.a1422130071.appthor;

        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnLogin ;
    TextView txtCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCadastrar = findViewById(R.id.txtCadastrar);
        btnLogin= findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CreateUser.class);
                startActivity(it);
            }
        });


        if (user!= null){
            Intent it = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(it);
            finish();
        }
    }

    public void login(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtSenha = findViewById(R.id.edtSenha);


        Task<AuthResult> processo = auth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtSenha.getText().toString());
        processo.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent it = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(it);
                }else {
                    Toast.makeText(MainActivity.this,"Email ou senha invalidos !",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}