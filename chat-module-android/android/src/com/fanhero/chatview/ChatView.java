package com.fanhero.chatview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;


public class ChatView extends RelativeLayout {
	private static final String TAG = "ChatView";
	
	private Context mContext;
	private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener;
	private Tiltable mTiltable;
	
	public ChatView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		
		this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				if (mTiltable != null) {
					mTiltable.didTilt(mContext.getResources().getConfiguration().orientation);
				}
			}
		};
		
		getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);
	}
	
	public Tiltable getTiltable() {
		return this.mTiltable;
	}
	
	public void setTiltable(Tiltable tiltable) {
		this.mTiltable = tiltable;
	}
}
