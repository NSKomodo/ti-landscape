package com.fanhero.chatview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class FHEditText extends EditText {
    public FHEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.clearFocus();
            return false;
        }

        return super.onKeyPreIme(keyCode, event);
    }
}
