package com.netcamp.sol;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Light extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    MediaPlayer mediaPlayer;
    Sensor s;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        mediaPlayer=MediaPlayer.create(this,R.raw.b);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        s=sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onResume() {
        sm.registerListener(this,s, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this,s);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0]>5)
        {
           mediaPlayer.start();
           /* mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {mp.start();}
            });*/
            imageView2.setImageResource(R.drawable.bon);
        }
        else
        {
          mediaPlayer.pause();
            imageView2.setImageResource(R.drawable.bof);
        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
