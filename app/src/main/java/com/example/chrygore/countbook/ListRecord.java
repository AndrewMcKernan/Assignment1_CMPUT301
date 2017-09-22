package com.example.chrygore.countbook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chrygore on 16/09/17.
 */

public class ListRecord {
    private int initialValue;
    private int currentValue;
    private String recordName;
    private String comments;
    private Calendar calendar;

    public ListRecord(int initialValue, String title, String comments){
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.recordName = title;
        this.comments = comments;
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
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

    public String getDateOfCreation(){

        return calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
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
        return recordName + " " + getDateOfCreation() + " " + Integer.toString(currentValue);
    }

}
