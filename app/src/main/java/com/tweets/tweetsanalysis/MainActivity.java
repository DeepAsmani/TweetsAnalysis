package com.tweets.tweetsanalysis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {

    private static final String TWITTER_KEY = "eR41ZAvOn9gpV6KGe0DgnXzgr"; // Use your twitter Consumer Key
    private static final String TWITTER_SECRET = "vVaAZhZ690t5SKYplIz3cvnqE8gkrvtq93U5HfR8bcuAA5rnRl"; // Use your twitter Secret Key
    private EditText txtemail,txtpass;
    private Button btnlogin;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        InputStream databaseInputStream1 = getResources().openRawResource(R.raw.imdb_labelled);
        try {
            File file = new File(getBaseContext().getFilesDir(), "imdb_labelled.txt");
            OutputStream out= openFileOutput("imdb_labelled.txt", Context.MODE_PRIVATE);
            IOUtils.copy(databaseInputStream1, out);
            databaseInputStream1.close();
            out.close();
        }
        catch (IOException e){}

        InputStream databaseInputStream2 = getResources().openRawResource(R.raw.positivekeywords);
        try {
            File file = new File(getBaseContext().getFilesDir(), "positivekeywords.txt");
            OutputStream out= openFileOutput("positivekeywords.txt", Context.MODE_PRIVATE);
            IOUtils.copy(databaseInputStream2, out);
            databaseInputStream1.close();
            out.close();
        }
        catch (IOException e){}

        InputStream databaseInputStream3 = getResources().openRawResource(R.raw.politics);
        try {
            File file = new File(getBaseContext().getFilesDir(), "politics.txt");
            OutputStream out= openFileOutput("politics.txt", Context.MODE_PRIVATE);
            IOUtils.copy(databaseInputStream3, out);
            databaseInputStream1.close();
            out.close();
        }
        catch (IOException e){}


        InputStream databaseInputStream7 = getResources().openRawResource(R.raw.negetivekeywords);
        try {
            File file = new File(getBaseContext().getFilesDir(), "negetivekeywords.txt");
            OutputStream out= openFileOutput("negetivekeywords.txt", Context.MODE_PRIVATE);
            IOUtils.copy(databaseInputStream7, out);
            databaseInputStream7.close();
            out.close();
        }
        catch (IOException e){}
        /*Intent intent = new Intent(MainActivity.this,Topic.class);
        startActivity(intent);*/
        txtemail = findViewById(R.id.editTextEmail);
        txtpass = findViewById(R.id.editTextPassword);
        btnlogin = findViewById(R.id.cirLoginButton);
        progressDialog=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btnlogin.setOnClickListener(v -> {
            if(txtemail.getText().toString().trim().isEmpty()){
                txtemail.setError("This filed is required");
            } else if (!isValidEmail(txtemail.getText().toString().trim())) {
                txtemail.setError("Not a valid email");
            }else if(txtpass.getText().toString().trim().isEmpty()){
                txtpass.setError("This filed is required");
            }else if(txtpass.getText().toString().trim().length() < 8) {
                txtpass.setError("Password must include 8 characters!");
            }else{
                PerAuth();
            }
        });
    }

    private void PerAuth() {
        String email=txtemail.getText().toString();
        String password=txtpass.getText().toString();

        progressDialog.setMessage("Please Wait While Login...");
        progressDialog.setTitle("Log in");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                progressDialog.dismiss();
                Intent intent = new Intent(MainActivity.this,Topic.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Log in Successful",Toast.LENGTH_SHORT).show();
            }else {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Invaild  Email Id Or Wrong Password....",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onForgetClick(View view){
        Intent intent=new Intent(MainActivity.this,ForgetActivity.class);
        startActivity(intent);
    }

    public void onLoginClick(View View){
        Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    public static boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
