package com.netcamp.sol;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Menu extends AppCompatActivity {
    CardView c1,c2,c3,c4,c5,c6,c7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        c1=(CardView)findViewById(R.id.c1);
        c2=(CardView)findViewById(R.id.c2);
        c3=(CardView)findViewById(R.id.c3);
        c4=(CardView)findViewById(R.id.c4);
        c5=(CardView)findViewById(R.id.c5);
        c6=(CardView)findViewById(R.id.c6);
        c7=(CardView)findViewById(R.id.c7);
       c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent n=new Intent(Menu.this,Menu.class);
                n.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(n);*/
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Menu.this,Calculator.class);
                startActivity(i);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Menu.this,Videoplayer.class);
                startActivity(j);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Menu.this,Quiz.class);
                startActivity(k);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(Menu.this,Accelerometer.class);
                startActivity(l);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(Menu.this,Camera.class);
                startActivity(m);
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o=new Intent(Menu.this,Light.class);
                startActivity(o);
            }
        });
    }
}
