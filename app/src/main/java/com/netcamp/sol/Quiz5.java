package com.netcamp.sol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class Quiz5 extends AppCompatActivity {
WebView wb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);
        Intent j=getIntent();
        String s=j.getStringExtra("key");
        Toast.makeText(Quiz5.this,s,Toast.LENGTH_LONG).show();
    }
}
