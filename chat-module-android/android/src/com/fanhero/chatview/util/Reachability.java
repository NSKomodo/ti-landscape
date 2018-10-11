package com.fanhero.chatview.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class Reachability {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnected();
    }
}
