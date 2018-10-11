package com.fanhero.chatview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by MarkHouston on 6/9/17.
 */

public class FHListView extends ListView {
    public FHListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        return 0;
    }
}
