package com.ilian.tictactoe.frontend;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.ilian.tictactoe.R;

public class StartActivity extends AppCompatActivity {
    ConstraintLayout backgroundLayout;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        InterfaceManager.hideSystemUI(this);

        backgroundLayout = findViewById(R.id.start_background_layout);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        InterfaceManager.hideSystemUI(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        InterfaceManager.hideSystemUI(this);

        if(!hasFocus) {
            //do anything you want here
            Toast.makeText(this,"Activity changed",Toast.LENGTH_SHORT).show();
        }
    }

    public void onPlayButtonClick(View v) {
        Button playButton = findViewById(R.id.playButton);

        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
}
