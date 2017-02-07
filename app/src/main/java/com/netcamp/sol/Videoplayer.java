package com.netcamp.sol;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Videoplayer extends AppCompatActivity {
    private static final String TAG = "VideoPlayerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);

        final VideoView videoView =
                (VideoView) findViewById(R.id.videoView1);

        //  videoView.setVideoPath(
        //        "http://www.ebookfrenzy.com/android_book/movie.mp4");

        Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a);
        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
       /* videoView.setOnPreparedListener(new
                                                MediaPlayer.OnPreparedListener()  {
                                                    @Override
                                                    public void onPrepared(MediaPlayer mp) {
                                                        Log.i(TAG, "Duration = " +
                                                                videoView.getDuration());
                                                    }
                                                });*/

        videoView.start();
    }
}
