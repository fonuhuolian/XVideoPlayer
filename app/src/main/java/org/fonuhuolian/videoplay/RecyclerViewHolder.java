package org.fonuhuolian.videoplay;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mCacheViews = new SparseArray();

    public RecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public ImageView getImageView(int id) {
        return (ImageView) this.getView(id);
    }

    public TextView getTextView(int id) {
        return (TextView) this.getView(id);
    }

    public EditText getEditText(int id) {
        return (EditText) this.getView(id);
    }

    public Button getButton(int id) {
        return (Button) this.getView(id);
    }

    public ImageButton getImageButton(int id) {
        return (ImageButton) this.getView(id);
    }

    public CheckBox getCheckBox(int id) {
        return (CheckBox) this.getView(id);
    }

    public ProgressBar getProgressBar(int id) {
        return (ProgressBar) this.getView(id);
    }

    public LinearLayout getLinearLayout(int id) {
        return (LinearLayout) this.getView(id);
    }

    public RelativeLayout getRelativeLayout(int id) {
        return (RelativeLayout) this.getView(id);
    }

    public FrameLayout getFrameLayout(int id) {
        return (FrameLayout) this.getView(id);
    }

    public <T extends View> T getView(int resId) {
        T view = (T) this.mCacheViews.get(resId);
        if (view == null) {
            view = this.itemView.findViewById(resId);
            this.mCacheViews.put(resId, view);
        }

        return view;
    }
}
