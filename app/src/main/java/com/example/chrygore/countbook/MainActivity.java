package com.example.chrygore.countbook;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    int clickCounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
    }

    public void addNewRecord(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AddNewRecordActivity.class);
        startActivity(intent);
    }

    public void addItemsTest(View view){
        listItems.add("Clicked: "+clickCounter++);
        adapter.notifyDataSetChanged();
    }

}
