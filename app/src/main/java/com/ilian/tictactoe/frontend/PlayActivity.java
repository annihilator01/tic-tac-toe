package com.ilian.tictactoe.frontend;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.KeyguardManager;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilian.tictactoe.R;
import com.ilian.tictactoe.backend.guiconnectors.Figure;
import com.ilian.tictactoe.backend.GameManager;
import com.ilian.tictactoe.backend.WinnerInfo;

public class PlayActivity extends AppCompatActivity {
    private GameManager gameManager;
    private int[][] posToID;
    private boolean isGameOver;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);

        InterfaceManager.hideStatusAndActionBar(this);
        InterfaceManager.hideSystemUI(this);

        gameManager = new GameManager();
        setFieldsActionsOnClick();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setFieldsActionsOnClick() {
        posToID = new int[gameManager.getGridSize()][gameManager.getGridSize()];
        LinearLayout gridLayout = findViewById(R.id.grid);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View element = gridLayout.getChildAt(i);

            if (element instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) element;

                for (int j = 0; j < linearLayout.getChildCount(); j++) {
                    View innerElement = linearLayout.getChildAt(j);

                    if (innerElement instanceof ImageView) {
                        ImageView field = (ImageView) innerElement;
                        posToID[getI(field)][getJ(field)] = field.getId();
                        setFieldActionOnClick(field);
                    }
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setFieldActionOnClick(ImageView field) {
        field.setOnClickListener(v -> {
            gameEventsHandler(field);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void gameEventsHandler(ImageView field) {
        if (isGameOver) {
            return;
        }

        int i = getI(field);
        int j = getJ(field);
        Figure turn = gameManager.getTurn();

        if (!gameManager.markSpace(turn, i, j)) {
            return;
        }

        field.setImageResource(turn.getDrawableID());
        startAnimation(field);

        // drawing win-line
        WinnerInfo winnerInfo = gameManager.getWinnerInfo();
        if (winnerInfo != null) {
            isGameOver = true;

            int[] leftFieldLocation = new int[2];
            i = winnerInfo.getLeftFieldPos().getY();
            j = winnerInfo.getLeftFieldPos().getX();
            ImageView leftField = findViewById(posToID[i][j]);
            leftField.getLocationOnScreen(leftFieldLocation);

            int[] rightFieldLocation = new int[2];
            i = winnerInfo.getRightFieldPos().getY();
            j = winnerInfo.getRightFieldPos().getX();
            ImageView rightField = findViewById(posToID[i][j]);
            rightField.getLocationOnScreen(rightFieldLocation);

            PathView pathView = new PathView(this);
            ((ConstraintLayout) findViewById(R.id.main)).addView(pathView);

            int startX = leftFieldLocation[0] + winnerInfo.getRow().getOffsetX(field.getWidth());
            int startY = leftFieldLocation[1] + winnerInfo.getRow().getOffsetY(field.getHeight());

            int endX = rightFieldLocation[0] + field.getWidth() - winnerInfo.getRow().getOffsetX(field.getWidth());
            int endY = rightFieldLocation[1] + field.getHeight() - winnerInfo.getRow().getOffsetY(field.getHeight());

            pathView.init(this, getResources().getColor(winnerInfo.getFigure().getColorID()), startX, startY, endX, endY);

            TextView infoTextView = findViewById(R.id.infoTextView);
            infoTextView.setText(R.string.win_name);
        } else {
            ImageView nextTurnImage = findViewById(R.id.turnImage);
            nextTurnImage.setImageResource(gameManager.getTurn().getDrawableID());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startAnimation(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) drawable;
            avd.start();
        }
    }

    private int getI(View view) {
        return Integer.parseInt(view.getContentDescription().toString().split("x")[0]);
    }

    private int getJ(View view) {
        return Integer.parseInt(view.getContentDescription().toString().split("x")[1]);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        InterfaceManager.hideStatusAndActionBar(this);
        InterfaceManager.hideSystemUI(this);
    }

    @Override
    public void onBackPressed() {

    }
}
