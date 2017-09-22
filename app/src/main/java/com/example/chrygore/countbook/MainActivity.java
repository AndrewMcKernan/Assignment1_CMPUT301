package com.example.chrygore.countbook;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends ListActivity {
    //private ListView itemlistview;
    List<ListRecord> listItems = new ArrayList<ListRecord>();
    ArrayAdapter<ListRecord> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<ListRecord>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        //itemlistview.setAdapter(adapter);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addNewRecord(View view) {
        // Do something in response to button
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

    public ArrayList<ListRecord> loadItems(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        ArrayList<ListRecord> arrayList;
        try {
            String json = sharedPrefs.getString("ListItems", null);
            Type type = new TypeToken<ArrayList<ListRecord>>() {}.getType();
            arrayList = gson.fromJson(json, type);
        }catch(Exception e){
            arrayList = new ArrayList<ListRecord>();
        }
        if (arrayList == null){
            arrayList = new ArrayList<ListRecord>();
        }
        return arrayList;
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
            					Context.MODE_PRIVATE);
            			OutputStreamWriter writer = new OutputStreamWriter(fos);
            			Gson gson = new Gson();
            			gson.toJson(listItems, writer);
            			writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            			throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            			throw new RuntimeException();
        }
    }

}
