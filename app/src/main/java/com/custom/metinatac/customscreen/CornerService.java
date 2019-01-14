package com.custom.metinatac.customscreen;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class CornerService extends Service {
    public CornerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        Toast.makeText(this, "CustomDisplay activated, Have Fun!", Toast.LENGTH_SHORT).show();
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);


        ImageView ivTopLeft = new ImageView(this);
        ImageView ivTopRight = new ImageView(this);
        ImageView ivBottomLeft = new ImageView(this);
        ImageView ivBottomRight = new ImageView(this);

        ImageView ivNotch = new ImageView(this);

        ivTopLeft.setImageResource(R.drawable.ic_corner);
        ivTopRight.setImageResource(R.drawable.ic_corner);
        ivBottomLeft.setImageResource(R.drawable.ic_corner);
        ivBottomRight.setImageResource(R.drawable.ic_corner);
        ivNotch.setImageResource(R.drawable.iphonenotch);

        ivTopRight.setRotation(90);
        ivBottomLeft.setRotation(-90);
        ivBottomRight.setRotation(180);

        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }





        //Creating Params
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(

                50,50,      //Width and height
               LAYOUT_FLAG,
               // WindowManager.LayoutParams.TYPE_WALLPAPER// Type

                WindowManager.LayoutParams.FLAG_FULLSCREEN
                |WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION

                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_ATTACHED_IN_DECOR,

                PixelFormat.TRANSLUCENT     //Format

        );
//hi
        //Add Views onto Screen:
        params.gravity = Gravity.TOP | Gravity.LEFT;
        manager.addView(ivTopLeft, params);

        params.gravity = Gravity.TOP | Gravity.RIGHT;
        manager.addView(ivTopRight, params);

        params.gravity = Gravity.BOTTOM | Gravity.LEFT;
        manager.addView(ivBottomLeft, params);

        params.gravity = Gravity.BOTTOM| Gravity.RIGHT;
        manager.addView(ivBottomRight, params);

        params.gravity = Gravity.CENTER | Gravity.TOP;
        ivNotch.setScaleType(ImageView.ScaleType.FIT_XY);
        params.width = 450;
        params.height = 75;
        manager.addView(ivNotch, params);

    }
int a;


}
