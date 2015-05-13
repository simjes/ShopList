package com.simjes.shoplist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopAdapter extends Adapter<ShopAdapter.ViewHolder> {

    private ArrayList<String> dataset;
    private int lastPosition = -1;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public View divider;
        public LinearLayout itemLayout;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.itemTextView);
            divider = v.findViewById(R.id.dividerLine);
            itemLayout = (LinearLayout) v.findViewById(R.id.itemLayout);
        }
    }

    public ShopAdapter(ArrayList<String> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customtextview, parent, false);
        v.setOnTouchListener(new SwipeListener(parent.getContext(), v) {
            @Override
            public void onSwipeRight() {
                TextView tv = (TextView) view.findViewById(R.id.itemTextView);
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
                view.startAnimation(animation);

                int pos = dataset.indexOf(tv.getText().toString());
                dataset.remove(tv.getText().toString());
                notifyItemRemoved(pos);
            }
        });
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(dataset.get(i));
        viewHolder.divider.setVisibility(View.VISIBLE);
        setAnimation(viewHolder.itemLayout, i);
    }

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
