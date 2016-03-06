package com.simjes.shoplist.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;

import com.simjes.shoplist.helper.OnStartDragListener;
import com.simjes.shoplist.helper.SimpleTouchHelperCallback;
import com.simjes.shoplist.listeners.KeyboardListener;
import com.simjes.shoplist.R;
import com.simjes.shoplist.adapters.ShopAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// For RecyclerView touch events and animations: https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-6a6f0c422efd#.2rnoufxac

public class MainActivity extends AppCompatActivity implements OnStartDragListener {
    private static final String FILENAME = "ShopListItems";

    private ShopAdapter adapter;
    private ItemTouchHelper itemTouchHelper;
    private ArrayList<String> items;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ShopAdapter(items, this, this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SimpleTouchHelperCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        editText = (EditText) findViewById(R.id.inputField);
        editText.setOnEditorActionListener(new KeyboardListener(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        String allItems = "";
        if (items.isEmpty()) {
            try {
                FileInputStream fileInputStream = openFileInput(FILENAME);
                byte[] itemsInBytes = new byte[fileInputStream.available()];
                if (itemsInBytes.length > 0) {
                    while (fileInputStream.read(itemsInBytes) != -1) {
                        //reading file
                    }
                    allItems += new String(itemsInBytes);
                    String[] allItemsArray = allItems.split(";");
                    items.addAll(Arrays.asList(allItemsArray));
                }
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        String allItems = "";
        for (String s : items) {
            allItems += s + ";";
        }

        try {
            FileOutputStream fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOutputStream.write(allItems.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }

    public void addItem(View v) {
        if (!editText.getText().toString().equals("")) {
            items.add(editText.getText().toString());
            editText.setText("");
            adapter.notifyItemInserted(adapter.getItemCount());
        }
    }
}
