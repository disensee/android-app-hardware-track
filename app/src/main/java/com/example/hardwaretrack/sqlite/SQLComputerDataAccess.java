package com.example.hardwaretrack.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLComputerDataAccess {


        public static final String TAG = "SQLComputerDataAccess";

        // Instance variables
        //private ArrayList<Task> allTasks;
        private Context context;
        private MySQLiteHelper dbHelper;
        private SQLiteDatabase database;

        // Constructors
        public SQLComputerDataAccess(Context context) {
            Log.d(TAG, "Instantiating SQLDataAccess");
            this.context = context;
            this.dbHelper = new MySQLiteHelper(context);
            this.database = this.dbHelper.getWritableDatabase();
        }


        // We should create static constants for the table name, and all column names
        public static final String TABLE_NAME = "computer";
        public static final String COLUMN_COMPUTER_ID = "_id";
        public static final String COLUMN_COMPUTER_TYPE = "type";
        public static final String COLUMN_COMPUTER_MANUFACTURER = "manufacturer";
        public static final String COLUMN_COMPUTER_MODEL = "model";
        public static final String COLUMN_COMPUTER_CUSTOM_BUILD = "customBuild";
        public static final String COLUMN_COMPUTER_PROCESSOR = "processor";
        public static final String COLUMN_COMPUTER_GRAPHICS_PROCESSOR = "graphicsProcessor";
        public static final String COLUMN_COMPUTER_RAM = "ram";
        public static final String COLUMN_COMPUTER_DRIVE1 = "drive1";
        public static final String COLUMN_COMPUTER_DRIVE2 = "drive2";
        public static final String COLUMN_COMPUTER_DRIVE3 = "drive3";

        public static final String TABLE_CREATE = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER," +
                                                    "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)",
                TABLE_NAME,
                COLUMN_COMPUTER_ID,
                COLUMN_COMPUTER_TYPE,
                COLUMN_COMPUTER_MANUFACTURER,
                COLUMN_COMPUTER_MODEL,
                COLUMN_COMPUTER_CUSTOM_BUILD,
                COLUMN_COMPUTER_PROCESSOR,
                COLUMN_COMPUTER_GRAPHICS_PROCESSOR,
                COLUMN_COMPUTER_RAM,
                COLUMN_COMPUTER_DRIVE1,
                COLUMN_COMPUTER_DRIVE2,
                COLUMN_COMPUTER_DRIVE3
        );

    /*
        @Override
        public ArrayList<Task> getAllTasks() {

            ArrayList<Task> tasks = new ArrayList<>();
            String query = String.format("SELECT %s, %s, %s, %s FROM %s", COLUMN_TASK_ID, COLUMN_DESCRIPTION, COLUMN_DUE, COLUMN_DONE, TABLE_NAME);

            Cursor c = database.rawQuery(query, null); //cursor is query result

            // make sure we got some results from the db before processing them
            if((c != null) && (c.getCount() > 0)) { //check if cursor is not null and has rows

                c.moveToFirst(); //move cursor to first row

                while (!c.isAfterLast()) { //while cursor is not after last row
                    //Assign data to appropriate instance variables
                    long id = c.getLong(0);
                    String desc = c.getString(1);
                    String due = c.getString(2);
                    boolean done = (c.getLong(3) == 1 ? true : false);//conditional operator -- if c.getlong is equal to 1 return true, else return false

                    Date dueDate = null;
                    //convert date string from DB to Date. SQLite cannot store dates
                    try {
                        dueDate = dateFormat.parse(due);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Task t = new Task(id, desc, dueDate, done); //assign variables to Task object
                    tasks.add(t);//add task object to array list
                    c.moveToNext(); //move to next row -- DON'T FORGET THIS LINE!!!!!!
                }
                c.close(); //close cursor object to save resources
            }
            return tasks;
        }

        @Override
        public Task getTaskById(long id) {

            String query = String.format("SELECT %s, %s, %s, %s FROM %s WHERE %s = %d", COLUMN_TASK_ID, COLUMN_DESCRIPTION, COLUMN_DUE, COLUMN_DONE, TABLE_NAME, COLUMN_TASK_ID, id);

            Cursor c = database.rawQuery(query, null);
            c.moveToFirst();

            //id = c.getLong(0);
            String desc = c.getString(1);
            String due = c.getString(2);
            boolean done = (c.getLong(3) == 1 ? true : false);

            Date dueDate = null;

            try {
                dueDate = dateFormat.parse(due);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            c.close();

            Task t = new Task(id,desc,dueDate,done);
            return  t;
        }

        @Override
        public Task insertTask(Task t) {

            ContentValues values = new ContentValues();
            values.put(COLUMN_DESCRIPTION, t.getDescription());
            values.put(COLUMN_DUE, dateFormat.format(t.getDue())); //date format to convert to Date object to string to store in DB
            values.put(COLUMN_DONE, (t.isDone() ? 1 : 0)); //convert boolean to 1 or 0 to store in DB
            long insertId = database.insert(TABLE_NAME, null, values);
            // note: insertId will be -1 if the insert failed

            t.setId(insertId); //set task object ID with auto incremented ID
            return t;
        }

        @Override
        public Task updateTask(Task t) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_DESCRIPTION, t.getDescription());
            values.put(COLUMN_DUE, dateFormat.format(t.getDue()));
            values.put(COLUMN_DONE, (t.isDone() ? 1 : 0));
            int rowsUpdated = database.update(TABLE_NAME, values, COLUMN_TASK_ID + " = " + t.getId(), null);
            // this method returns the number of rows that were updated in the db
            // so that you could use it to confirm that your update worked
            return t;
        }

        @Override
        public int deleteTask(Task t) {
            int rowsDeleted = database.delete(TABLE_NAME, COLUMN_TASK_ID + " = " + t.getId(), null);
            // the above method returns the number of row that were deleted
            return rowsDeleted;
        }

     */
}

