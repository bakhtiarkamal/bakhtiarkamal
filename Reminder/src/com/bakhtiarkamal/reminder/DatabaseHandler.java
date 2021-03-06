package com.bakhtiarkamal.reminder;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "ReminderManager";
 
    // Contacts table name
    private static final String TABLE_REMINDERS = "reminders";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_REMINDERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDERS);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    void addReminder(ReminderItem reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, reminder.getTitle()); // Contact Name
 
        // Inserting Row
        db.insert(TABLE_REMINDERS, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    ReminderItem getReminder(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_REMINDERS, new String[] { KEY_ID,
                KEY_TITLE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        ReminderItem reminder = new ReminderItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return reminder;
    }
 
    // Getting All Contacts
    public List<ReminderItem> getAllReminders() {
        List<ReminderItem> reminderList = new ArrayList<ReminderItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REMINDERS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ReminderItem reminder = new ReminderItem();
                reminder.setID(Integer.parseInt(cursor.getString(0)));
                reminder.setTitle(cursor.getString(1));
                // Adding contact to list
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return reminderList;
    }
 
    // Updating single contact
    public int updateReminder(ReminderItem reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, reminder.getTitle());
 
        // updating row
        return db.update(TABLE_REMINDERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(reminder.getID()) });
    }
 
    // Deleting single contact
    public void deleteReminder(ReminderItem reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REMINDERS, KEY_ID + " = ?",
                new String[] { String.valueOf(reminder.getID()) });
        db.close();
    }
 
    // Getting contacts Count
    public int getReminderCount() {
        String countQuery = "SELECT  * FROM " + TABLE_REMINDERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}
