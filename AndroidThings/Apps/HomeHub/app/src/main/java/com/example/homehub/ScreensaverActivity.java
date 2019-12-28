package com.example.homehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextClock;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class ScreensaverActivity extends AppCompatActivity {
    static int screenSaverImages[] = { R.drawable.kp01, R.drawable.kp02, R.drawable.kp03, R.drawable.kp04, R.drawable.kp05, R.drawable.kp06 };
    ImageView screenSaverView;
    static int imageNum = 0;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screensaver);

        Log.i("screensaver", "Inside CREATE");

        screenSaverView = new ImageView(this);
        screenSaverView = findViewById(R.id.screensaverDisplay);

        handler = new Handler();
        runnable = new Runnable(){
            public void run(){
                Log.i("screensaver", "Inside RUNNABLE");
                if(imageNum >= screenSaverImages.length - 1){
                    imageNum = 0;
                }
                else {
                    imageNum++;
                }
                screenSaverView.setImageResource(screenSaverImages[imageNum]);
                handler.postDelayed(this, 5000); //set to go off again in 3 seconds.
            }
        };
        handler.postDelayed(runnable, 3000);

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("screensaver", "Inside START");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("screensaver", "Inside RESUME");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("screensaver", "Inside PAUSE");
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("screensaver", "Inside STOP");
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        finish();
        return super.onTouchEvent(ev);
    }
}