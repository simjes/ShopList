package com.simjes.shoplist.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simjes.shoplist.R;
import com.simjes.shoplist.helper.ItemTouchHelperAdapter;
import com.simjes.shoplist.helper.ItemTouchHelperViewHolder;
import com.simjes.shoplist.helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;

public class ShopAdapter extends Adapter<ShopAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    private ArrayList<String> items;
    private OnStartDragListener startDragListener;
    private CoordinatorLayout snackbarLayout;

    public ShopAdapter(ArrayList<String> items, OnStartDragListener startDragListener, Activity activity) {
        this.items = items;
        this.startDragListener = startDragListener;
        this.snackbarLayout = (CoordinatorLayout) activity.findViewById(R.id.snackbarView);
    }

    @Override
    public ShopAdapter.ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customtextview, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder viewHolder, int i) {
        viewHolder.textView.setText(items.get(i));
        viewHolder.handleView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    startDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public boolean onItemMove(int fromPos, int toPos) {
        Collections.swap(items, fromPos, toPos);
        notifyItemMoved(fromPos, toPos);
        return true;
    }

    @Override
    public void onItemDismiss(int pos) {
        String removedItem = items.get(pos);
        items.remove(pos);
        notifyItemRemoved(pos);
        Snackbar.make(snackbarLayout, removedItem + " was removed.", Snackbar.LENGTH_LONG);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        public TextView textView;
        public ImageView handleView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.itemTextView);
            handleView = (ImageView) itemView.findViewById(R.id.handle);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
