package com.simjes.shoplist.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.simjes.shoplist.listeners.KeyboardListener;
import com.simjes.shoplist.R;
import com.simjes.shoplist.adapters.ShopAdapter;

import java.util.ArrayList;


public class MainActivity extends Activity {
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

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
