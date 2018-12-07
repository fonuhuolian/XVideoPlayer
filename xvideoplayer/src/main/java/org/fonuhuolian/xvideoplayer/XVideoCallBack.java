package org.fonuhuolian.xvideoplayer;

public interface XVideoCallBack {

    // 进入普通状态，通常指setUp之后
    void onStateNormal();

    // 进入准备中状态，就是loading状态
    void onStatePreparing();

    // 进入播放状态
    void onStatePlaying();

    // 进入暂停状态
    void onStatePause();

    // onStateError
    void onStateError();

    // 进入自动播放完成状态
    void onStateAutoComplete();
}
