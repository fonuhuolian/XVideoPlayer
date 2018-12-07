package org.fonuhuolian.videoplay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleSingleLayoutAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private List<T> mDatas;
    private LayoutInflater mInflater;
    private int mLayoutResId;
    private RecyclerView mRecyclerView;
    private OnItemClickListener mListener;
    private OnItemLongClickListener mLongListener;
    private Context mContext;

    public SimpleSingleLayoutAdapter(Context context, int layoutResId) {
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mLayoutResId = layoutResId;
        this.mDatas = new ArrayList();
        this.mContext = context;
    }

    public SimpleSingleLayoutAdapter(Context context, int layoutResId, List<T> list) {
        this(context, layoutResId);
        this.addData(list);
    }

    public void addData(List<T> data) {
        if (data != null) {
            this.mDatas.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    public int getItemCount() {
        return this.mDatas.size();
    }

    @NonNull
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.mInflater.inflate(this.mLayoutResId, parent, false);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        return new RecyclerViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        this.bind(holder, this.mDatas.get(position), position);
    }

    public void clearAll() {
        this.mDatas.clear();
        this.notifyDataSetChanged();
    }

    public abstract void bind(RecyclerViewHolder var1, T var2, int var3);

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public void onClick(View v) {
        int position = this.mRecyclerView.getChildAdapterPosition(v);
        T t = this.mDatas.get(position);
        if (this.mListener != null) {
            this.mListener.onClick(t, position, v);
        }

    }

    public boolean onLongClick(View view) {
        int position = this.mRecyclerView.getChildAdapterPosition(view);
        T t = this.mDatas.get(position);
        if (this.mLongListener != null) {
            this.mLongListener.onLongClick(t, position, view);
        }

        return this.mLongListener != null;
    }

    public List<T> getAttachDatas() {
        return this.mDatas;
    }

    public Context getAttachContext() {
        return this.mContext;
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        this.mLongListener = listener;
    }
}
