package com.fanhero.chatview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.TiRHelper;
import org.appcelerator.titanium.util.TiRHelper.ResourceNotFoundException;
import org.appcelerator.titanium.view.TiUIView;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

public class ChatViewTiUIView extends TiUIView implements Tiltable {
	private static final String TAG = "ChatViewTiUIView";
	
	private ChatView mChatView;
	private Bundle mState;
	
	public ChatViewTiUIView(TiViewProxy proxy) {
		super(proxy);
		this.mState = new Bundle();
		loadChatView();
	}

	@Override
	public void processProperties(KrollDict d) {
		super.processProperties(d);
	}
	
	protected void loadChatView() {
		try {
			int chatViewLayout = TiRHelper.getApplicationResource("layout.view_chat");
					
			LayoutInflater inflater = (LayoutInflater) proxy.getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			this.mChatView = null;
			this.mChatView = (ChatView) inflater.inflate(chatViewLayout, null);
			this.mChatView.setTiltable(this);
			setNativeView(mChatView);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void didTilt(int orientation) {
		Log.i(TAG, "Tilted to orientation " + orientation);
		forceLayoutNativeView(true);
	}
	
}