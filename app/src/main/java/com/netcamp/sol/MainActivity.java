package com.netcamp.sol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    TextView t1;
    EditText e1, e2;
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        b1 = (Button) findViewById(R.id.b1);
        t1 = (TextView) findViewById(R.id.t1);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
    b1.setEnabled(false);

    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,R.style.AppTheme_Dark_Dialog);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Authenticating...");
    progressDialog.show();

    String email = e1.getText().toString();
    String password = e2.getText().toString();

    // TODO: Implement your own authentication logic here.

        SQLiteDatabase data =openOrCreateDatabase("netcamp1",MODE_PRIVATE,null);
            data.execSQL("create table if not exists student1 (email varchar,password varchar)");
            String s="select * from student1 where email='"+email+"' and password='"+password+"'";
        String s1="select password from student where email='"+email+"'";
            Cursor cursor=data.rawQuery(s,null);
            if(cursor.getCount()>0)
            {
                Toast.makeText(MainActivity.this,"login successful",Toast.LENGTH_LONG).show();
                Intent j=new Intent(MainActivity.this,Menu.class);
                startActivity(j);
            }
            else if (s1!=password) {
            Toast.makeText(MainActivity.this, "wrong password", Toast.LENGTH_LONG).show();
            Intent j = new Intent(MainActivity.this, MainActivity.class);
            startActivity(j);
            }

            else
                Toast.makeText(MainActivity.this,"please register first",Toast.LENGTH_LONG).show();

    new Handler().postDelayed(
            new Runnable() {
        public void run() {
            // On complete call either onLoginSuccess or onLoginFailed
            onLoginSuccess();
            // onLoginFailed();
            progressDialog.dismiss();
        }
    }, 3000);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        b1.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        b1.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = e1.getText().toString();
        String password = e2.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e1.setError("enter a valid email address");
            valid = false;
        } else {
            e1.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            e2.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            e2.setError(null);
        }

        return valid;
    }
}
