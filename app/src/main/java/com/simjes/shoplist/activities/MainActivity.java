package com.simjes.shoplist.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.simjes.shoplist.listeners.KeyboardListener;
import com.simjes.shoplist.R;
import com.simjes.shoplist.adapters.ShopAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends Activity {
    private static final String FILENAME = "ShopListItems";

    private RecyclerView.Adapter adapter;
    private ArrayList<String> dataset;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataset = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ShopAdapter(dataset, this);
        recyclerView.setAdapter(adapter);
        editText = (EditText) findViewById(R.id.inputField);
        editText.setOnEditorActionListener(new KeyboardListener(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        String allItems = "";
        if (dataset.isEmpty()) {
            try {
                FileInputStream fileInputStream = openFileInput(FILENAME);
                byte[] itemsInBytes = new byte[fileInputStream.available()];
                if (itemsInBytes.length > 0) {
                    while (fileInputStream.read(itemsInBytes) != -1) {
                    }
                    allItems += new String(itemsInBytes);
                    String[] allItemsArray = allItems.split(";");
                    dataset.addAll(Arrays.asList(allItemsArray));
                }
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        String allItems = "";
        for (String s : dataset) {
            allItems += s + ";";
        }

        try {
            FileOutputStream fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOutputStream.write(allItems.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addItem(View v) {
        if (!editText.getText().toString().equals("")) {
            dataset.add(editText.getText().toString());
            editText.setText("");
            adapter.notifyItemInserted(adapter.getItemCount());
        }
    }

    public void clearText(View v) {
        editText.setText("");
    }
}
