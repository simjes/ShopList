package com.simjes.shoplist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private ArrayList<String> dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public View divider;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.itemTextView);
            divider = v.findViewById(R.id.dividerLine);
        }
    }

    public ShopAdapter(ArrayList<String> dataset) {
        this.dataset = dataset;
    }

    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customtextview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(dataset.get(i));
        viewHolder.divider.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
