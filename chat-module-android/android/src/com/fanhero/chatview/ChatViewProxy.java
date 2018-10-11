package com.fanhero.chatview;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;


@Kroll.proxy(creatableInModule=ChatViewModule.class)
public class ChatViewProxy extends TiViewProxy {
	// Standard Debugging variables
	private static final String LCAT = "ExampleProxy";
	private static final boolean DBG = TiConfig.LOGD;

	// Constructor
	public ChatViewProxy() {
		super();
	}

	@Override
	public TiUIView createView(Activity activity) {
		TiUIView view = new ChatViewTiUIView(this);
		view.getLayoutParams().autoFillsHeight = true;
		view.getLayoutParams().autoFillsWidth = true;

		return view;
	}

	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict options) {
		super.handleCreationDict(options);

		if (options.containsKey("message")) {
			Log.d(LCAT, "example created with message: " + options.get("message"));
		}
	}
}
