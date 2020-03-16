package com.ilian.tictactoe.frontend.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.ilian.tictactoe.R;

/**
 * Connected checkbox that couldn't be selected at the same time (only on of them).
 */
public class MultiSwitch extends androidx.appcompat.widget.AppCompatTextView {
    private static MultiSwitch selected = null;

    /**
     * Default constructor that make initial selection and bind action to multi switch view on click
     * @param context
     * @param attrs
     */
    @SuppressLint("ResourceType")
    public MultiSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundResource(Color.TRANSPARENT);
        setOnClickListener((l) -> select(this));
        initialSelect(this);
    }

    /**
     * Initially select specified multi switch.
     * @param multiSwitch - multi switch to be initially selected
     */
    public static void initialSelect(MultiSwitch multiSwitch) {
        select(multiSwitch);
    }

    /**
     * Select specified multi switch.
     * @param multiSwitch - multi switch to be selected
     */
    @SuppressLint("ResourceType")
    private static void select(MultiSwitch multiSwitch) {
        if (multiSwitch != selected) {
            if (selected != null) {
                selected.setBackgroundResource(Color.TRANSPARENT);
            }

            selected = multiSwitch;
            multiSwitch.setBackgroundResource(R.drawable.selected_wrapper);
        }
    }

    /**
     * Get information of selected multi switch.
     * @return String - information
     */
    public static String getSelectedInfo() {
        return selected.getContentDescription().toString();
    }
}
