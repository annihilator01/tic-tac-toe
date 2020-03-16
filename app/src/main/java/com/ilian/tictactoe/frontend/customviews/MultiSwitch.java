package com.ilian.tictactoe.frontend.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.ilian.tictactoe.R;

public class MultiSwitch extends androidx.appcompat.widget.AppCompatTextView {
    private static MultiSwitch selected = null;

    @SuppressLint("ResourceType")
    public MultiSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundResource(Color.TRANSPARENT);
        setOnClickListener((l) -> select(this));
        initialSelect(this);
    }

    public static void initialSelect(MultiSwitch multiSwitch) {
        select(multiSwitch);
    }

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

    public static String getSelectedInfo() {
        return selected.getContentDescription().toString();
    }
}
