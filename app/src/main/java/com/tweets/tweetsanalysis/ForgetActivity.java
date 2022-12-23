package com.tweets.tweetsanalysis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgetActivity extends AppCompatActivity {

    EditText txtemail;
    Button btnforget;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        txtemail = findViewById(R.id.editTextEmail);
        btnforget = findViewById(R.id.cirForgetButton);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        btnforget.setOnClickListener(v -> {
            if(txtemail.getText().toString().trim().isEmpty()){
                txtemail.setError("This filed is required");
            } else if (!isValidEmail(txtemail.getText().toString().trim())) {
                txtemail.setError("Not a valid email");
            }else {
                PerAuthForget();
            }
        });
    }

    private void PerAuthForget() {
        String email = txtemail.getText().toString().trim();

        progressDialog.setMessage("Please Wait While Sending Link...");
        progressDialog.setTitle("Reset Password");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                sendUserTOUserNextActivity();
                Toast.makeText(ForgetActivity.this, "Link Sent on Email...", Toast.LENGTH_SHORT).show();
            } else {
                progressDialog.dismiss();
                Toast.makeText(ForgetActivity.this, "Invaild  Email Id....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void sendUserTOUserNextActivity() {
        Intent intent=new Intent(ForgetActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}