package com.example.chrygore.countbook;

import java.util.Date;

/**
 * Created by chrygore on 16/09/17.
 */

public class ListRecord {
    private int initialValue;
    private int currentValue;
    private String recordName;
    private Date dateOfCreation;
    private String comments;

    public ListRecord(){
        this.dateOfCreation = new Date();
        this.initialValue = 0;
        this.currentValue = 0;
        this.recordName = "Unnamed record";
        this.comments = "";
    }

    public ListRecord(int initialValue){
        this.dateOfCreation = new Date();
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.recordName = "Unnamed record";
        this.comments = "";
    }

    public ListRecord(int initialValue, String title){
        this.dateOfCreation = new Date();
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

    public Date getDateOfCreation(){
        return this.dateOfCreation;
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

}
