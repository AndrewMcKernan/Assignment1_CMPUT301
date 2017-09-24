package com.example.chrygore.countbook;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends ListActivity {
    private List<ListRecord> listItems = new ArrayList<ListRecord>();
    private ArrayAdapter<ListRecord> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        //loadFromFile() is called on every onStart() to ensure that the list of items displayed is up to date
        loadFromFile();
        adapter = new ArrayAdapter<ListRecord>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
        TextView listCount = (TextView) findViewById(R.id.listItemsCountView);
        // display the current number of records
        listCount.setText("Number of Counters: " + listItems.size());
        //listener for if the user selects one of the items in the list
        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //tell the ViewRecordActivity which list item has been selected and start it
                Intent intent = new Intent(MainActivity.this, ViewRecordActivity.class);
                intent.putExtra("position",i);
                startActivity(intent);
            }
        });

    }

    public void addNewRecord(View view) {
        Intent intent = new Intent(this, AddNewRecordActivity.class);
        startActivity(intent);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            			Gson gson = new Gson();
            			Type listType = new TypeToken<ArrayList<ListRecord>>() {}.getType();
            			listItems = gson.fromJson(in,listType);
            			//https://github.com/google/gson/blob/master/UserGuide.md

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            			listItems = new ArrayList<ListRecord>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            			throw new RuntimeException();
        }
    }
}
