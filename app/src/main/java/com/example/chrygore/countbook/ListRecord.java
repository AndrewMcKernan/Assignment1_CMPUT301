package com.example.chrygore.countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chrygore on 16/09/17.
 */

public class ListRecord {
    private int initialValue;
    private int currentValue;
    private String recordName;
    private String comments;
    SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");

    public ListRecord(int initialValue, String title){
        date.format(new Date());
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.recordName = title;
        this.comments = "";
    }

    public String getTitle(){
        return this.recordName;
    }

    public void setTitle(String recordName){
        this.recordName = recordName;
    }

    public String getComments(){
        return this.comments;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public SimpleDateFormat getDateOfCreation(){
        return this.date;
    }

    public void incrementCurrentValue(){
        this.currentValue++;
    }

    public void decrementCurrentValue(){
        this.currentValue--;
    }

    public void setCurrentValue(int currentValue){
        this.currentValue = currentValue;
    }

    public int getCurrentValue(){
        return this.currentValue;
    }

    public int getInitialValue(){
        return this.initialValue;
    }

    @Override
    public String toString(){
        return recordName + " " + date.toPattern() + " " + Integer.toString(currentValue);
    }

}
