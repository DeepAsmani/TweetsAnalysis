package com.tweets.tweetsanalysis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    EditText txtemail,txtpass,txtcnfpass;
    Button btnsignup;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtemail = findViewById(R.id.editTextEmail);
        txtpass = findViewById(R.id.editTextPassword);
        txtcnfpass = findViewById(R.id.editTextCon_Password);
        btnsignup = findViewById(R.id.cirRegisterButton);
        progressDialog=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btnsignup.setOnClickListener(v -> {
            if(txtemail.getText().toString().trim().isEmpty()){
                txtemail.setError("This filed is required");
            } else if (!isValidEmail(txtemail.getText().toString().trim())) {
                txtemail.setError("Not a valid email");
            }else if(txtpass.getText().toString().trim().isEmpty()){
                txtpass.setError("This filed is required");
            }else if(txtpass.getText().toString().trim().length() < 8) {
                txtpass.setError("Password must include 8 characters!");
            }else if(txtcnfpass.getText().toString().trim().isEmpty()){
                txtcnfpass.setError("This filed is required");
            }else if (txtpass.getText().toString().trim().compareTo(txtcnfpass.getText().toString().trim()) != 0) {
                txtcnfpass.setError("Not matched with the Password");
            }else {
                PerforAuth();
            }
        });
    }

    public void onLoginClick(View view){
        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public static boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void PerforAuth(){
        String email=txtemail.getText().toString();
        String password=txtpass.getText().toString();

        progressDialog.setMessage("Please Wait While Registration...");
        progressDialog.setTitle("Registeration");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    sendUserTOUserNextActivity();
                    Toast.makeText(RegisterActivity.this,"Registaration Successful",Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this,"Email Id Already Used....",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendUserTOUserNextActivity() {
        Intent intent=new Intent(RegisterActivity.this,Topic.class);
        startActivity(intent);
    }

}
