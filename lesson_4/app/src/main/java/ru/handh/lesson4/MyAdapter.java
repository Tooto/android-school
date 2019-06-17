package ru.handh.lesson4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String[] mDataset;

    private OnTextClickListener onTextClickListener;


    public void setOnTextClickListener(OnTextClickListener onTextClickListener) {
        this.onTextClickListener = onTextClickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private OnTextClickListener onTextClickListener;

        public TextView textView;

        public MyViewHolder(View v, OnTextClickListener onTextClickListener) {
            super(v);
            textView = v.findViewById(R.id.textViewTitle);
            this.onTextClickListener = onTextClickListener;
        }

        public void bind(final String data) {
            textView.setText(data);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTextClickListener.onItemClick(data);
                }
            });
        }
    }

    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text, parent, false);
        MyViewHolder vh = new MyViewHolder(view, onTextClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public interface OnTextClickListener {
        void onItemClick(String text);
    }
}