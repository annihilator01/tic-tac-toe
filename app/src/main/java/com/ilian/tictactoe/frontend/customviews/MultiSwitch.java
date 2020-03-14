package com.ilian.tictactoe.frontend.customviews;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.ilian.tictactoe.R;

public class MultiSwitch extends View {
    private static MultiSwitch selected = null;

    public MultiSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundResource(R.color.testNot);
        setOnClickListener((l) -> select(this));

        if (selected == null) {
            select(this);
        }
    }

    private static void test(MultiSwitch multiSwitch) {
        multiSwitch.setBackgroundColor(Color.BLACK);
    }

    private static void select(MultiSwitch multiSwitch) {
        if (multiSwitch != selected) {
            if (selected != null) {
                selected.setBackgroundResource(R.color.testNot);
            }

            selected = multiSwitch;
            multiSwitch.setBackgroundResource(R.color.testSel);
        }
    }
}
