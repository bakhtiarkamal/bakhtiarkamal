package com.bakhtiarkamal.reminder;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class Reminder extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        
    	DatabaseHandler db = new DatabaseHandler(this);
        
        /**
         * CRUD Operations
         * */
        // Inserting ReminderItems
        Log.d("Insert: ", "Inserting ..");
        db.addReminder(new ReminderItem("Buy eggs"));
        db.addReminder(new ReminderItem("Build more pylons"));
        db.addReminder(new ReminderItem("Play Starcraft"));
        db.addReminder(new ReminderItem("Lure Pythox to Zerg nest"));
 
        // Delete Reminders
        /*
        Log.d("Delete: ", "Deleting ..");
        db.deleteReminder(new ReminderItem(1));
        db.deleteReminder(new ReminderItem(2));
        db.deleteReminder(new ReminderItem(3));
        db.deleteReminder(new ReminderItem(4));
        */
        
        // Reading all ReminderItems
        Log.d("Reading: ", "Reading all ReminderItems..");
        List<ReminderItem> reminders = db.getAllReminders();       
 
        for (ReminderItem r : reminders) {
            String log = "Id: "+ r.getID()+" , Reminder: " + r.getTitle();
            // Writing ReminderItems to log
            Log.d("Reminders: ", log);
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reminder, menu);
        return true;
    }
}
