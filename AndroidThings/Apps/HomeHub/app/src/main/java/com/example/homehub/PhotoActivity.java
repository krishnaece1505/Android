package com.example.homehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
public class PhotoActivity extends AppCompatActivity {

    static int photoViewerImages[] = { R.drawable.kp01, R.drawable.kp02, R.drawable.kp03, R.drawable.kp04, R.drawable.kp05, R.drawable.kp06 };
    static int imageNum = 0;
    ImageView photosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        photosView = new ImageView(this);
        photosView = findViewById(R.id.photoDisplay);

        Button nextButton = (Button) findViewById(R.id.photos_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imageNum >= photoViewerImages.length - 1){
                    imageNum = 0;
                }
                else {
                    imageNum++;
                }
                photosView.setImageResource(photoViewerImages[imageNum]);
            }
        });

        Button previousButton = (Button) findViewById(R.id.photos_previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imageNum == 0){
                    imageNum = photoViewerImages.length - 1;
                }
                else {
                    imageNum--;
                }
                photosView.setImageResource(photoViewerImages[imageNum]);
            }
        });

        Button backButton = (Button) findViewById(R.id.photos_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAfterTransition();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("photoviewer", "Inside START");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("photoviewer", "Inside RESUME");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("photoviewer", "Inside PAUSE");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("photoviewer", "Inside STOP");
    }
}
