package com.example.hardwaretrack.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hardwaretrack.models.Computer;

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
        public static final String TABLE_COMPUTER_NAME = "computer";
        public static final String COLUMN_COMPUTER_ID = "_id";
        public static final String COLUMN_COMPUTER_TYPE = "type";
        public static final String COLUMN_COMPUTER_MANUFACTURER = "manufacturer";
        public static final String COLUMN_COMPUTER_MODEL = "model";
        public static final String COLUMN_COMPUTER_CUSTOM_BUILD = "customBuild";
        public static final String COLUMN_COMPUTER_PROCESSOR = "processor";
        public static final String COLUMN_COMPUTER_GRAPHICS_PROCESSOR = "graphicsProcessor";
        public static final String COLUMN_COMPUTER_RAM = "ram";
        public static final String COLUMN_COMPUTER_DRIVE = "drive";


        public static final String TABLE_COMPUTER_CREATE = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER," +
                                                    "%s INTEGER, %s INTEGER, %s INTEGER)",
                TABLE_COMPUTER_NAME,
                COLUMN_COMPUTER_ID,
                COLUMN_COMPUTER_TYPE,
                COLUMN_COMPUTER_MANUFACTURER,
                COLUMN_COMPUTER_MODEL,
                COLUMN_COMPUTER_CUSTOM_BUILD,
                COLUMN_COMPUTER_PROCESSOR,
                COLUMN_COMPUTER_GRAPHICS_PROCESSOR,
                COLUMN_COMPUTER_RAM,
                COLUMN_COMPUTER_DRIVE
        );

        public static final String TABLE_CPU_NAME = "cpu";
        public static final String COLUMN_CPU_ID = "_id";
        public static final String COLUMN_CPU_MANUFACTURER = "manufacturer";
        public static final String COLUMN_CPU_MODEL = "model";
        public static final String COLUMN_CPU_CORE_COUNT = "coreCount";
        public static final String COLUMN_CPU_THREAD_COUNT = "threadCount";
        public static final String COLUMN_CPU_BASE_CLOCK = "baseClock";
        public static final String COLUMN_CPU_BOOST_CLOCK = "boostClock";

        public static final String TABLE_CPU_CREATE = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s REAL, %s REAL, FOREIGN KEY(%s) REFERENCES %s(%s))",

                TABLE_CPU_NAME,
                COLUMN_CPU_ID,
                COLUMN_CPU_MANUFACTURER,
                COLUMN_CPU_MODEL,
                COLUMN_CPU_CORE_COUNT,
                COLUMN_CPU_THREAD_COUNT,
                COLUMN_CPU_BASE_CLOCK,
                COLUMN_CPU_BOOST_CLOCK,
                COLUMN_CPU_ID,
                TABLE_COMPUTER_NAME,
                COLUMN_COMPUTER_PROCESSOR
                );

        public static final String TABLE_DRIVE_NAME = "drive";
        public static final String COLUMN_DRIVE_ID = "_id";
        public static final String COLUMN_DRIVE_MANUFACTURER = "manufacturer";
        public static final String COLUMN_DRIVE_MODEL = "model";
        public static final String COLUMN_DRIVE_TYPE = "type";
        public static final String COLUMN_DRIVE_FORM_FACTOR = "formFactor";
        public static final String COLUMN_DRIVE_TRANSFER_PROTOCOL = "transferProtocol";
        public static final String COLUMN_DRIVE_CAPACITY = "capacity";
        public static final String COLUMN_DRIVE_MAX_TRANSFER_RATE = "maxTransferRate";

        public static final String TABLE_DRIVE_CREATE = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, FOREIGN KEY(%s) REFERENCES %s(%s))",

                TABLE_DRIVE_NAME,
                COLUMN_DRIVE_ID,
                COLUMN_DRIVE_MANUFACTURER,
                COLUMN_DRIVE_MODEL,
                COLUMN_DRIVE_TYPE,
                COLUMN_DRIVE_FORM_FACTOR,
                COLUMN_DRIVE_TRANSFER_PROTOCOL,
                COLUMN_DRIVE_CAPACITY,
                COLUMN_DRIVE_MAX_TRANSFER_RATE,
                COLUMN_DRIVE_ID,
                TABLE_COMPUTER_NAME,
                COLUMN_COMPUTER_DRIVE
                );

        public static final String TABLE_GPU_NAME = "gpu";
        public static final String COLUMN_GPU_ID = "_id";
        public static final String COLUMN_GPU_MANUFACTURER = "manufacturer";
        public static final String COLUMN_GPU_MODEL = "model";
        public static final String COLUMN_GPU_CORE_COUNT = "coreCount";
        public static final String COLUMN_GPU_BASE_CLOCK = "baseClock";
        public static final String COLUMN_GPU_BOOST_CLOCK = "boostClock";
        public static final String COLUMN_GPU_VRAM = "vRam";

        public static final String TABLE_GPU_CREATE = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT, FOREIGN KEY(%s) REFERENCES %s(%s))",

                TABLE_GPU_NAME,
                COLUMN_GPU_ID,
                COLUMN_GPU_MANUFACTURER,
                COLUMN_GPU_MODEL,
                COLUMN_GPU_CORE_COUNT,
                COLUMN_GPU_BASE_CLOCK,
                COLUMN_GPU_BOOST_CLOCK,
                COLUMN_GPU_VRAM,
                COLUMN_GPU_ID,
                TABLE_COMPUTER_NAME,
                COLUMN_COMPUTER_GRAPHICS_PROCESSOR
                );

        public static final String TABLE_RAM_NAME = "ram";
        public static final String COLUMN_RAM_ID = "_id";
        public static final String COLUMN_RAM_MANUFACTURER = "manufacturer";
        public static final String COLUMN_RAM_MODEL = "model";
        public static final String COLUMN_RAM_TYPE = "type";
        public static final String COLUMN_RAM_CAPACITY = "capacity";
        public static final String COLUMN_RAM_SPEED = "speed";
        public static final String COLUMN_RAM_FORM_FACTOR = "formFactor";

        public static final String TABLE_RAM_CREATE = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s TEXT, FOREIGN KEY(%s) REFERENCES %s(%s))",

                TABLE_RAM_NAME,
                COLUMN_RAM_ID,
                COLUMN_RAM_MANUFACTURER,
                COLUMN_RAM_MODEL,
                COLUMN_RAM_TYPE,
                COLUMN_RAM_CAPACITY,
                COLUMN_RAM_SPEED,
                COLUMN_RAM_FORM_FACTOR,
                COLUMN_RAM_ID,
                TABLE_COMPUTER_NAME,
                COLUMN_COMPUTER_RAM
                );


        //@Override TODO - IMPLEMENT INTERFACE?
        public ArrayList<Computer> getAllComputers() {

            ArrayList<Computer> computer = new ArrayList<>();
            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s FROM %s", COLUMN_COMPUTER_ID, COLUMN_COMPUTER_TYPE, COLUMN_COMPUTER_MANUFACTURER, COLUMN_COMPUTER_MODEL, COLUMN_COMPUTER_CUSTOM_BUILD, COLUMN_COMPUTER_PROCESSOR,
                                        COLUMN_COMPUTER_GRAPHICS_PROCESSOR, COLUMN_COMPUTER_DRIVE, COLUMN_COMPUTER_RAM, TABLE_COMPUTER_NAME);

            Cursor c = database.rawQuery(query, null); //cursor is query result

            // make sure we got some results from the db before processing them
            if((c != null) && (c.getCount() > 0)) { //check if cursor is not null and has rows

                c.moveToFirst(); //move cursor to first row

                while (!c.isAfterLast()) { //while cursor is not after last row
                    //Assign data to appropriate instance variables
                    long computer_id = c.getLong(0);
                    String computer_type  = c.getString(1);
                    String computer_manufacturer = c.getString(2);
                    String computer_model = c.getString(3);
                    boolean comuter_custom_build = (c.getLong(4) == 1 ? true : false);//conditional operator -- if c.getlong is equal to 1 return true, else return false
                    String computer_processor = c.getString(5);
                    String computer_gpu = c.getString(6);
                    String computer_drive = c.getString(7);
                    String computer_ram = c.getString(8);

                    /* -- TODO - INCLUDE JOINS FOR SQL QUERY - CREATE NEW CONSTRUCTOR FOR COMPUTER AND FINISH THIS METHOD!!
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

