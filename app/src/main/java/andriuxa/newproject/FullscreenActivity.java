package andriuxa.newproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class FullscreenActivity extends AppCompatActivity {

    private Button button2;
    private static final int CAMERA_REQUEST = 50;
    private boolean flashLightStatus = false;
    private int strobe = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final MediaPlayer alarm = MediaPlayer.create (this, R.raw.alarm);

        ActivityCompat.requestPermissions(FullscreenActivity.this, new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
        setContentView(R.layout.activity_fullscreen);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                    if (strobe == 1) {
                        button2.setBackgroundResource(R.drawable.buttonshape);
                        strobe = 0;
                        alarm.pause();
                    } else
                        try {
                            button2.setBackgroundResource(android.R.drawable.btn_default);
                            alarm.start();
                            flash_effect();
                            strobe = 1;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            flashLightStatus = true;
        } catch (CameraAccessException e) {

        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            flashLightStatus = false;
        } catch (CameraAccessException e) {

        }
    }

    public void onButtonClick(View v){

        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(this, PlacePickerActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, FoodMultiPanelActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, FireGuide.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, AnimalsActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, VideoActivity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(this, MapsActivity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(this, WeatherActivity.class));
                break;
        }

    }

    public void flash_effect() throws InterruptedException
    {

        Thread a = new Thread()
        {
            public void run()
            {
                while(strobe == 1)
                {

                    flashLightOn();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flashLightOff();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                }
            }
        };

        a.start();
    }

}