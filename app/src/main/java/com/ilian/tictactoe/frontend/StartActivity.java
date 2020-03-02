package com.ilian.tictactoe.frontend;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ilian.tictactoe.R;

public class StartActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        InterfaceManager.hideStatusAndActionBar(this);
        InterfaceManager.hideSystemUI(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        InterfaceManager.hideStatusAndActionBar(this);
        InterfaceManager.hideSystemUI(this);
    }

    public void onPlayButtonClick(View v) {
        Button playButton = findViewById(R.id.playButton);

        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
}
