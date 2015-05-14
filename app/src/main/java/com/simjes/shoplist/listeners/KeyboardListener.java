package com.simjes.shoplist.listeners;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.simjes.shoplist.activities.MainActivity;

public class KeyboardListener implements OnEditorActionListener {

    private MainActivity activity;

    public KeyboardListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            activity.addItem(v);
        }
        return true;
    }
}
