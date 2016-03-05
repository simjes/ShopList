package com.simjes.shoplist.helper;


public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPos, int toPos);

    void onItemDismiss(int pos);
}
