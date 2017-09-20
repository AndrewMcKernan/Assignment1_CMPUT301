package com.example.chrygore.countbook;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrygore on 16/09/17.
 */

public class AddNewRecordActivity extends AppCompatActivity {

    ArrayList<ListRecord> listOfItems;
    JsonSaving saveObject = new JsonSaving();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_record);
    }

    public void saveJSONRecord(View view){
        //retrieve current list of records so new record can be appended
        saveObject.loadList(listOfItems);

        EditText name = (EditText) findViewById(R.id.enterRecordNameText);
        EditText initValue = (EditText) findViewById(R.id.enterInitValueText);
        Boolean isValid = true;
        if (name.getText().toString().equals("")){
            name.setError("This field is required.");
            isValid = false;
        }
        if (initValue.getText().toString().equals("")){
            name.setError("This field is required.");
            isValid = false;
        }

        if (isValid){
            // create ListRecord and add to end
            ListRecord newEntry = new ListRecord(Integer.parseInt(initValue.getText().toString()),name.getText().toString());
            listOfItems.add(listOfItems.size(),newEntry);
            //save listOfItems to json
            saveObject.saveList(listOfItems);
        }
    }
}
