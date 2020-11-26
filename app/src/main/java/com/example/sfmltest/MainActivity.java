package com.example.sfmltest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NativeActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends NativeActivity {
    Handler stickyHandler = new Handler();

    Runnable stickRunnable = new Runnable() {
        @Override
        public void run() {
            getWindow().getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE);

            stickyHandler.postDelayed(this, 3000);
        }
    };


    private View androidOverlayView;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "onResume");
        stickyHandler.post(stickRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stickyHandler.removeCallbacks(stickRunnable);
    }



    // Used to load the 'native-lib' library on application startup.
   /* static {
        System.loadLibrary("native-lib");
        System.loadLibrary("sfml-audio");
        System.loadLibrary("sfml-system");
        System.loadLibrary("sfml-window");
        System.loadLibrary("sfml-graphics");
        System.loadLibrary("sfml-activity");
        System.loadLibrary("sfml-network");
    }*/
}