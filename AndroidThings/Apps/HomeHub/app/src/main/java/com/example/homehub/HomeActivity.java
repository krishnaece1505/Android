package com.example.homehub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.view.View;
import android.widget.FrameLayout;


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
public class HomeActivity extends AppCompatActivity {
    
    CountDownTimer homeActvTimer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Open Weather activity on weatherButton press
        Button weatherButton = findViewById(R.id.weather_button);
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelHomeActvTimer();
                openWeatherActivity();
            }
        });

        // Open Photo activity on photosButton press
        Button photosButton = findViewById(R.id.photos_button);
        photosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelHomeActvTimer();
                openPhotoActivity();
            }
        });

        // Open Calendar activity on weatherButton press
        Button calendarButton = findViewById(R.id.calendar_button);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelHomeActvTimer();
                openCalendarActivity();
            }
        });


    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        cancelHomeActvTimer();
        startHomeActvTimer();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        cancelHomeActvTimer();
        startHomeActvTimer();
        return super.onTouchEvent(ev);
    }

    //start timer function
    void startHomeActvTimer() {
        homeActvTimer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                startActivity(new Intent(HomeActivity.this, ScreensaverActivity.class));
            }
        };
        homeActvTimer.start();
    }


    //cancel timer
    void cancelHomeActvTimer() {
        if(homeActvTimer!=null)
            homeActvTimer.cancel();
    }

    public void openWeatherActivity() {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void openPhotoActivity() {
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void openCalendarActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}