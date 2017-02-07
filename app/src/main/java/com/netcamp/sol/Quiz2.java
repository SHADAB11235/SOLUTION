package com.netcamp.sol;

import android.content.Intent;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.Locale;

public class Quiz2 extends AppCompatActivity {

    Button b1;
    RadioButton r1,r2,r3,r4;
    TextToSpeech t;
    static int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton)findViewById(R.id.r2);
        r3=(RadioButton)findViewById(R.id.r3);
        r4=(RadioButton)findViewById(R.id.r4);
        b1=(Button)findViewById(R.id.b1);
        t=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                t.setLanguage(Locale.ENGLISH);
                t.setPitch(10f);
                t.setSpeechRate(0.1f);

                //  t1.setSpeechRate(5);

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r2.isChecked())
                {
                    ++(Quiz.score);
                    t.speak("answer is correct",TextToSpeech.QUEUE_FLUSH,null);
                    r2.setBackgroundColor(Color.BLUE);

                }
                else if(r1.isChecked())
                {
                    --(Quiz.score);
                    t.speak("your answer is wrong",TextToSpeech.QUEUE_FLUSH,null);
                    r1.setBackgroundColor(Color.RED);
                }

                else if(r3.isChecked())
                {
                    --(Quiz.score);
                    t.speak("your answer is wrong",TextToSpeech.QUEUE_FLUSH,null);
                    r3.setBackgroundColor(Color.RED);
                }
                else if(r4.isChecked()) {
                    --(Quiz.score);
                    t.speak("your answer is wrong",TextToSpeech.QUEUE_FLUSH,null);
                    r4.setBackgroundColor(Color.RED);
                }
                Intent i=new Intent(Quiz2.this,Quiz3.class);
                startActivity(i);

            }
        });

    }
}
