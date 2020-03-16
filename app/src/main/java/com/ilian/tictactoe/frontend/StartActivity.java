package com.ilian.tictactoe.frontend;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.ilian.tictactoe.R;
import com.ilian.tictactoe.frontend.customviews.MultiSwitch;

public class StartActivity extends AppCompatActivity {
    ConstraintLayout backgroundLayout;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        InterfaceManager.hideSystemUI(this);

        backgroundLayout = findViewById(R.id.startBackgroundLayout);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        InterfaceManager.hideSystemUI(this);
        MultiSwitch.initialSelect(findViewById(R.id.multiSwitch3x3));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        InterfaceManager.hideSystemUI(this);
    }

    public void onPlayButtonClick(View v) {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("GridSize", Integer.parseInt(MultiSwitch.getSelectedInfo()));
        startActivity(intent);
    }
}
