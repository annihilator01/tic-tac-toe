package com.ilian.tictactoe.frontend;

import android.app.ActionBar;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class InterfaceManager {
    public static void hideStatusAndActionBar(AppCompatActivity activity) {
        View decorView = activity.getWindow().getDecorView();
        // Hide the status bar
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Hide the action bar
        ActionBar actionBar = activity.getActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void hideSystemUI(AppCompatActivity activity) {
        View decorView = activity.getWindow().getDecorView();
        hideSystemUI(decorView);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void hideSystemUI(View decorView) {
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
