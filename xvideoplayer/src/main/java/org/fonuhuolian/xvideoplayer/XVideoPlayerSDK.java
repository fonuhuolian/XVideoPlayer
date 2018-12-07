package org.fonuhuolian.xvideoplayer;

import android.app.Application;

import com.danikula.videocache.HttpProxyCacheServer;

public class XVideoPlayerSDK {

    private static XVideoPlayerSDK instance;

    // 缓存
    private HttpProxyCacheServer cacheServer = null;
    // Application.context
    private static Application mContext;


    private XVideoPlayerSDK() {
        if (mContext == null)
            throw new RuntimeException("Please invoke XVideoPlayerSDK.init(Application) on Application#onCreate()");

        cacheServer = new HttpProxyCacheServer.Builder(mContext)
                .cacheDirectory(CacheUtils.getVideoCacheDir(mContext))
                .build();
    }

    public static XVideoPlayerSDK getInstance() {
        if (instance == null)
            synchronized (XVideoPlayerSDK.class) {
                if (instance == null)
                    instance = new XVideoPlayerSDK();
            }
        return instance;
    }

    // 需要在Application进行全局初始化
    public static void init(Application context) {
        mContext = context;
    }

    public HttpProxyCacheServer getCacheServer() {
        return cacheServer;
    }
}
