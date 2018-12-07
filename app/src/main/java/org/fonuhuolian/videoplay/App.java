package org.fonuhuolian.videoplay;

import android.app.Application;

import org.fonuhuolian.xvideoplayer.XVideoPlayerSDK;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XVideoPlayerSDK.init(this);
    }
}
