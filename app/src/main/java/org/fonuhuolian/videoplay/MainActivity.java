package org.fonuhuolian.videoplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.github.rubensousa.gravitysnaphelper.GravityPagerSnapHelper;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import org.fonuhuolian.xvideoplayer.XVideoPlayer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZUtils;
import cn.jzvd.Jzvd;


public class MainActivity extends AppCompatActivity {

    private int currentPosition;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 注册eventBus
        EventBus.getDefault().register(this);
        // 初始化adapter
        adapter = new Adapter(this, R.layout.rv_demo_item);
        // 设置
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 装载数据
        List<String> list = new ArrayList<>();
        list.add("http://www.ourpyw.com/upload/20181205/154399100417409844065949737291.mp4");
        list.add("http://www.ourpyw.com/upload/20181205/154399085578508075922575855313.mp4");
        list.add("http://www.ourpyw.com/upload/20181205/1543990692461030433433965425194.mp4");
        list.add("http://www.ourpyw.com/upload/20181205/154397866116907221827780702329.mp4");
        // 装载到适配器
        recyclerView.setAdapter(adapter);
        adapter.addData(list);

        // 卡片式滑动
        new GravityPagerSnapHelper(Gravity.BOTTOM, true, new GravitySnapHelper.SnapListener() {
            @Override
            public void onSnap(final int position) {

                if (currentPosition == position) {
                    return;
                }

                startPlay(currentPosition, position);
                currentPosition = position;
            }
        }).attachToRecyclerView(recyclerView);

    }


    // 自动开启播放
    protected void startPlay(int oldPosition, int newPosition) {

        if (recyclerView == null)
            return;

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null != recyclerView.findViewHolderForAdapterPosition(newPosition)) {
                    RecyclerViewHolder holder = (RecyclerViewHolder) recyclerView.findViewHolderForAdapterPosition(newPosition);
                    // 开始新视频播放
                    ((XVideoPlayer) holder.getView(R.id.videoplayer)).startVideo();
                    // 清除旧视频的播放进度
                    JZUtils.clearSavedProgress(MainActivity.this, adapter.getAttachDatas().get(oldPosition));
                }
            }
        }, 500);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(NextPosition event) {
        // 实现自动播放功能
        recyclerView.smoothScrollToPosition(event.getPostion());
    }
}
