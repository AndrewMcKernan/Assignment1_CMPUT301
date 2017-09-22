package com.example.chrygore.countbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chrygore on 22/09/17.
 */

public class ViewRecordActivity extends AppCompatActivity{
    int listPosition;
    ArrayList<ListRecord> listItems;
    @Override
    public void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        listPosition = getIntent().getIntExtra("position",0);
        TextView date = (TextView) findViewById(R.id.dateContainer);

    }
}
