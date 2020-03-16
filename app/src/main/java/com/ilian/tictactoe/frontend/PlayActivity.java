package com.ilian.tictactoe.frontend;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilian.tictactoe.R;
import com.ilian.tictactoe.backend.guiconnectors.Figure;
import com.ilian.tictactoe.backend.GameManager;
import com.ilian.tictactoe.backend.WinnerInfo;
import com.ilian.tictactoe.frontend.customviews.PathView;

import java.util.HashMap;
import java.util.Objects;

/**
 * Play screen activity.
 */
public class PlayActivity extends AppCompatActivity {
    private ConstraintLayout backgroundLayout;
    private GameManager gameManager;
    private ImageView[][] gridImageView;
    private HashMap<ImageView, Pair<Integer, Integer>> imageToPos;
    private boolean isGameOver;
    private LinearLayout gridLayout;

    /**
     * Hide all system UI and locate dynamically created elements on the activity on create.
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);
        InterfaceManager.hideSystemUI(this);

        int gridSize = getIntent().getIntExtra("GridSize", 3);
        backgroundLayout = findViewById(R.id.play_background_layout);
        gridLayout = findViewById(R.id.grid);
        gameManager = new GameManager(gridSize);

        setGrid();
        setFieldsActionsOnClick();
    }

    /**
     * Dynamically create grid with size given from the start activity.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setGrid() {
        gridImageView = new ImageView[gameManager.getGridSize()][gameManager.getGridSize()];
        imageToPos = new HashMap<>();

        for (int i = 0; i < 2 * gameManager.getGridSize() - 1; i++) {
            if (i % 2 == 0) {
                LinearLayout horizontalLL = getNewHorizontalLinearLayout();
                gridLayout.addView(horizontalLL);

                for (int j = 0; j < 2 * gameManager.getGridSize() - 1; j++) {
                    if (j % 2 == 0) {
                        ImageView field = getNewField();
                        imageToPos.put(field, new Pair<>(i / 2, j / 2));
                        gridImageView[i / 2][j / 2] = field;
                        horizontalLL.addView(field);
                    } else {
                        horizontalLL.addView(getNewGridSide(0, LinearLayout.LayoutParams.MATCH_PARENT));
                    }
                }
            } else {
                gridLayout.addView(getNewGridSide(LinearLayout.LayoutParams.MATCH_PARENT, 0));
            }
        }
    }

    /**
     * Get new horizontal linear layout for line of figure on the play grid.
     * @return horizontalLL - horizontal linear layout
     */
    private LinearLayout getNewHorizontalLinearLayout() {
        LinearLayout horizontalLL = new LinearLayout(this);
        horizontalLL.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLL.setLayoutParams(new LinearLayout.LayoutParams(
                                     LinearLayout.LayoutParams.MATCH_PARENT,
                                     0,
                                     1f));

        return horizontalLL;
    }

    /**
     * Get new play field.
     * @return field - image view
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private ImageView getNewField() {
        ImageView field = new ImageView(this);
        field.setLayoutParams(new LinearLayout.LayoutParams(
                              0,
                              LinearLayout.LayoutParams.MATCH_PARENT,
                              1f));

        return field;
    }

    /**
     * Get new grid side.
     * @param width - width of grid side
     * @param height - heigth of grid side
     * @return gridSide - view that compatible either with horizontal ll or vertical ll
     */
    @SuppressLint("ResourceAsColor")
    private View getNewGridSide(int width, int height) {
        View gridSide = new View(this);
        gridSide.setBackgroundResource(R.color.gridSideColor);
        gridSide.setLayoutParams(new LinearLayout.LayoutParams(
                                 width,
                                 height,
                                 0.1f));

        return gridSide;
    }

    /**
     * Set action on click for all fields.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setFieldsActionsOnClick() {
        for (ImageView[] fields : gridImageView) {
            for (ImageView field : fields) {
                setFieldActionOnClick(field);
            }
        }
    }


    /**
     * Set action on click for specified field.
     * @param field - image view
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setFieldActionOnClick(ImageView field) {
        field.setOnClickListener(v -> gameEventsHandler(field));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void gameEventsHandler(ImageView field) {
        if (isGameOver) {
            return;
        }

        int i = Objects.requireNonNull(imageToPos.get(field)).first;
        int j = Objects.requireNonNull(imageToPos.get(field)).second;
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
            ImageView leftField = gridImageView[i][j];
            leftField.getLocationOnScreen(leftFieldLocation);

            int[] rightFieldLocation = new int[2];
            i = winnerInfo.getRightFieldPos().getY();
            j = winnerInfo.getRightFieldPos().getX();
            ImageView rightField = gridImageView[i][j];
            rightField.getLocationOnScreen(rightFieldLocation);

            PathView pathView = new PathView(this);
            backgroundLayout.addView(pathView);

            int startX = leftFieldLocation[0] + winnerInfo.getRow().getOffsetX(field.getWidth());
            int startY = leftFieldLocation[1] + winnerInfo.getRow().getOffsetY(field.getHeight());

            int endX = rightFieldLocation[0] + field.getWidth() - winnerInfo.getRow().getOffsetX(field.getWidth());
            int endY = rightFieldLocation[1] + field.getHeight() - winnerInfo.getRow().getOffsetY(field.getHeight());

            int strokeWidth = ((LinearLayout) gridLayout.getChildAt(0)).getChildAt(1).getWidth();

            pathView.init(this, getResources().getColor(winnerInfo.getFigure().getColorID()),
                          startX, startY, endX, endY, strokeWidth);
            setInfo(winnerInfo.getFigure().getDrawableID(), R.string.winLabel);
        } else if (gameManager.isDraw()) {
            isGameOver = true;
            setInfo(R.string.drawLabel);
        } else {
            setInfo(gameManager.getTurn().getDrawableID(), R.string.turnLabel);
        }
    }

    /**
     * Set info without image view.
     * @param textInfo - id of string info
     */
    @SuppressLint("ResourceType")
    private void setInfo(int textInfo) {
        ImageView imageView = findViewById(R.id.imageInfo);
        imageView.setVisibility(View.GONE);

        TextView textView = findViewById(R.id.textInfo);
        textView.setGravity(Gravity.CENTER);
        textView.setText(textInfo);
    }

    /**
     * Set info with image view.
     * @param drawableID - id of drawable for image view
     * @param textInfo - id of string info
     */
    @SuppressLint("ResourceType")
    private void setInfo(int drawableID, int textInfo) {
        ImageView imageView = findViewById(R.id.imageInfo);
        imageView.setImageResource(drawableID);
        imageView.setVisibility(View.VISIBLE);

        TextView textView = findViewById(R.id.textInfo);
        textView.setText(textInfo);
        textView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        textView.setVisibility(View.VISIBLE);
    }

    /**
     * Stat figure animation.
     * @param imageView - figure field to be animated
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startAnimation(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) drawable;
            avd.start();
        }
    }

    /**
     * Move to start activity on restart button click.
     * @param v
     */
    public void onRestartButtonClick(View v) {
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra("GridSize", gameManager.getGridSize());
        startActivity(intent);
    }

    /**
     * Hide all system UI on resume.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        InterfaceManager.hideSystemUI(this);
    }

    /**
     * Deactivate actions when back button pressed
     */
    @Override
    public void onBackPressed() {}
}
