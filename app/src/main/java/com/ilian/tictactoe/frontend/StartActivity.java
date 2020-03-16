package com.ilian.tictactoe.frontend;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.ilian.tictactoe.R;
import com.ilian.tictactoe.frontend.customviews.MultiSwitch;

/**
 * Start screen activity.
 */
public class StartActivity extends AppCompatActivity {
    /**
     * Hide all system UI on create.
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        InterfaceManager.hideSystemUI(this);
    }

    /**
     * Hide all system UI and make initial selection of multi switch on resume.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        InterfaceManager.hideSystemUI(this);
        MultiSwitch.initialSelect(findViewById(R.id.multiSwitch3x3));
    }

    /**
     * Hide all system UI on window focus changed.
     * @param hasFocus
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        InterfaceManager.hideSystemUI(this);
    }


    /**
     * Move to the play activity on button click and transmit size of play grid.
     * @param v
     */
    public void onPlayButtonClick(View v) {
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("GridSize", Integer.parseInt(MultiSwitch.getSelectedInfo()));
        startActivity(intent);
    }
}
