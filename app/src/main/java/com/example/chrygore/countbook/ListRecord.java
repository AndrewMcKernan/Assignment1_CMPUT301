package com.example.chrygore.countbook;

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
    // Calendar was used here over Date and SimpleDateFormat since it is easier to retrieve the desired date format with this than with
    // Date, and SimpleDateFormat was causing issues with saving with Json
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

    public void setDate(Date date){
        calendar.setTime(date);
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

    public void setCurrentValue(int currentValue){
        if (currentValue >= 0)
            this.currentValue = currentValue;
    }

    public int getCurrentValue(){
        return this.currentValue;
    }

    public int getInitialValue(){
        return this.initialValue;
    }

    public void setInitialValue(int value){
        this.initialValue = value;
    }

    @Override
    public String toString(){
        // this toString() format is what is showed in the list in the main screen
        return recordName + "          " + Integer.toString(currentValue) + "          " + getDateOfCreation();
    }

}
