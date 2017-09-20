package com.example.chrygore.countbook;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by chrygore on 19/09/17.
 */

public class JsonSaving extends AppCompatActivity {
    private SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

    public void loadList(ArrayList<ListRecord> outputList){
        Gson gson = new Gson();
        try{
            String json = mPrefs.getString("ListItems", null);
            Type type = new TypeToken<ArrayList<ListRecord>>() {}.getType();
            outputList = gson.fromJson(json, type);
        }catch(Exception e){
            outputList = new ArrayList<ListRecord>();
        }
    }

    public void saveList(ArrayList<ListRecord> listToSave){
        Gson gson = new Gson();
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        //save listOfItems to json
        String json = gson.toJson(listToSave);
        prefsEditor.putString("ListItems",json);
        prefsEditor.commit();
    }
}
