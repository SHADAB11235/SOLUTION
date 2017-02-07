package com.netcamp.sol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    private static final String TAG ="signupactiviy" ;
    Button b2;
    TextView t2;
    EditText editText,editText2,editText3,editText4,editText5,editText6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b2=(Button)findViewById(R.id.b2);
        t2=(TextView)findViewById(R.id.t2);
        editText=(EditText)findViewById(R.id.edittext);
        editText2=(EditText)findViewById(R.id.edittext2);
        editText3=(EditText)findViewById(R.id.edittext3);
        editText4=(EditText)findViewById(R.id.edittext4);
        editText5=(EditText)findViewById(R.id.edittext5);
        editText6=(EditText)findViewById(R.id.edittext6);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }
    public void signup() {
        Log.d(TAG,"Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        b2.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = editText.getText().toString();
        String address = editText2.getText().toString();
        String email = editText3.getText().toString();
        String mobile = editText4.getText().toString();
        String password = editText5.getText().toString();
        String reEnterPassword = editText6.getText().toString();

        // TODO: Implement your own signup logic here.
        SQLiteDatabase data =openOrCreateDatabase("netcamp1",MODE_PRIVATE,null);

        data.execSQL("Create Table if not exists student1(name Varchar,address Varchar,email Varchar,mobile Varchar,password Varchar)");

       String s="select * from student1 where email=='"+email+"'";
        Log.e("Data",s);
        Cursor cursor = data.rawQuery(s,null);
        if(cursor.getCount()>0)
            Toast.makeText(Signup.this,"email already exists",Toast.LENGTH_LONG).show();
        else
        {
            data.execSQL("Insert Into student1 values ('"+name+"','"+address+"','"+email+"','"+mobile+"','"+password+"')");
            Toast.makeText(Signup.this,"successfully registered",Toast.LENGTH_LONG).show();
            Intent i=new Intent(Signup.this,MainActivity.class);
            startActivity(i);
        }
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
        //cursor.close();
    }


    public void onSignupSuccess() {
        b2.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        b2.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = editText.getText().toString();
        String address = editText2.getText().toString();
        String email = editText3.getText().toString();
        String mobile = editText4.getText().toString();
        String password = editText5.getText().toString();
        String reEnterPassword = editText6.getText().toString();
        if (name.isEmpty() || name.length() < 3) {
            editText.setError("at least 3 characters");
            valid = false;
        } else {
            editText.setError(null);
        }

        if (address.isEmpty()) {
            editText2.setError("Enter Valid Address");
            valid = false;
        } else {
            editText2.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText3.setError("enter a valid email address");
            valid = false;
        } else {
            editText3.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            editText4.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            editText4.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editText5.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editText5.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            editText6.setError("Password Do not match");
            valid = false;
        } else {
            editText6.setError(null);
        }

        return valid;
    }
}
