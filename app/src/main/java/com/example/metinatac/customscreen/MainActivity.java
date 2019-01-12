package com.example.metinatac.customscreen;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int OVERLAY_PERMISSION_CODE =2002 ; // can be anything

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkPremission();
    }



    public void checkPremission() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {

                // Open the permission page
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_CODE);
                return;
            }
        }


        goToReactActivity();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        // If the permission has been checked
        if (requestCode == OVERLAY_PERMISSION_CODE) {
            if (Settings.canDrawOverlays(this)) {
                goToReactActivity();
            }
        }
    }

    private void goToReactActivity() {
        startService(new Intent(this, CornerService.class));
        finish();
    }
}
