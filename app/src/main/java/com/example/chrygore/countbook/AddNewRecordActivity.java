package com.example.chrygore.countbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

/**
 * Created by chrygore on 16/09/17.
 */

public class AddNewRecordActivity extends AppCompatActivity {

    private List<ListRecord> listItems = new ArrayList<ListRecord>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_record);
    }

    public void saveButton(View view){
        loadFromFile();
        //validate info
        EditText name = (EditText) findViewById(R.id.enterRecordNameText);
        EditText initialVal = (EditText) findViewById(R.id.enterInitValueText);
        EditText comments = (EditText) findViewById(R.id.enterCommentsText);

        boolean isValid = true;

        if (name.getText().toString().equals("")){
            name.setError("This field is required.");
            isValid = false;
        }
        if (initialVal.getText().toString().equals("")){
            initialVal.setError("This field is required.");
            isValid = false;
        }

        if (isValid){
            ListRecord itemToAdd = new ListRecord(Integer.parseInt(initialVal.getText().toString()),name.getText().toString(),comments.getText().toString());
            listItems.add(itemToAdd);
            saveInFile();
            finish();
        }
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
        if (listItems == null) {
            listItems = new ArrayList<ListRecord>();
        }
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
