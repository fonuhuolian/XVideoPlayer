package org.fonuhuolian.videoplay;

import android.content.Context;

import org.fonuhuolian.xvideoplayer.XVideoCallBack;
import org.fonuhuolian.xvideoplayer.XVideoPlayer;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class Adapter extends SimpleSingleLayoutAdapter<String> {

    public Adapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public Adapter(Context context, int layoutResId, List<String> list) {
        super(context, layoutResId, list);
    }

    @Override
    public void bind(RecyclerViewHolder var1, String var2, int var3) {

        // 查找控件
        XVideoPlayer xVideoPlayer = (XVideoPlayer) var1.getView(R.id.videoplayer);
        // 装载视频地址
        xVideoPlayer.setUp(var2, "", JzvdStd.SCREEN_WINDOW_NORMAL);
        // 裁剪视频
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);
        // 设置监听
        xVideoPlayer.setVideoAllCallBack(new XVideoCallBack() {

            @Override
            public void onStateNormal() {

            }

            @Override
            public void onStatePreparing() {

            }

            @Override
            public void onStatePlaying() {

            }

            @Override
            public void onStatePause() {

            }

            @Override
            public void onStateError() {

            }

            @Override
            public void onStateAutoComplete() {

                if (getAttachDatas().size() - 1 > var3) {
                    EventBus.getDefault().post(new NextPosition(var3 + 1));
                }
            }
        });
    }
}
