package com.bakhtiarkamal.reminder;

public class ReminderItem {

    //private variables
    int _id;
    String _title;
 
    // Empty constructor
    public ReminderItem(){
 
    }
    // constructor
    public ReminderItem(int id)
    {
    	this._id = id;
    }
    
    public ReminderItem(int id, String title){
        this._id = id;
        this._title = title;
    }
 
    // constructor
    public ReminderItem(String title){
        this._title = title;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int id){
        this._id = id;
    }
 
    // getting name
    public String getTitle(){
        return this._title;
    }
 
    // setting name
    public void setTitle(String title){
        this._title = title;
    }
}