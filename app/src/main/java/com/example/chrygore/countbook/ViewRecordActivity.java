package com.example.chrygore.countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
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
import java.util.Date;
import static android.provider.Telephony.Mms.Part.FILENAME;

/**
 * Created by chrygore on 22/09/17.
 */

public class ViewRecordActivity extends AppCompatActivity{
    private int listPosition;
    private ArrayList<ListRecord> listItems;
    private ListRecord viewingRecord;
    @Override
    public void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.view_record);
        // get the record that the user selected
        listPosition = getIntent().getIntExtra("position",0);
        loadFromFile();
        viewingRecord = listItems.get(listPosition);
        populatePage();
    }

    private void populatePage(){
        //fill all the fields on the page with the contents of the viewingRecord
        TextView date = (TextView) findViewById(R.id.dateContainer);
        date.setText(viewingRecord.getDateOfCreation());
        TextView name = (TextView) findViewById(R.id.nameContainer);
        name.setText(viewingRecord.getTitle());
        TextView initVal = (TextView) findViewById(R.id.initValContainer);
        initVal.setText(Integer.toString(viewingRecord.getInitialValue()));
        TextView currVal = (TextView) findViewById(R.id.currentValContainer);
        currVal.setText(Integer.toString(viewingRecord.getCurrentValue()));
        TextView comments = (TextView) findViewById(R.id.commentsContainer);
        comments.setText(viewingRecord.getComments());
    }

    public void incrementCurrentVal(View view){
        viewingRecord.setCurrentValue(viewingRecord.getCurrentValue() + 1);
        viewingRecord.setDate(new Date());
        populatePage();
        saveChanges();
    }

    public void decrementCurrentVal(View view){
        viewingRecord.setCurrentValue(viewingRecord.getCurrentValue() - 1);
        viewingRecord.setDate(new Date());
        populatePage();
        saveChanges();
    }

    private void saveChanges(){
        listItems.set(listPosition,viewingRecord);
        saveInFile();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<ListRecord>>() {}.getType();
            listItems = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            listItems = new ArrayList<ListRecord>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
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

    public void editRecordButton(View v){
        //switch to editing the record, making sure to pass the position of the current record to the next activity
        Intent intent = new Intent(ViewRecordActivity.this, EditRecord.class);
        intent.putExtra("position",listPosition);
        startActivity(intent);
        finish();
    }
}
