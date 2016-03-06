package com.simjes.shoplist.helper;


import android.support.v7.widget.RecyclerView;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPos, int toPos);

    void onItemDismiss(int pos);
}
