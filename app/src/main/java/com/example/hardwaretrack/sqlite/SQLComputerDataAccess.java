package com.example.hardwaretrack.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hardwaretrack.models.CPU;
import com.example.hardwaretrack.models.Computer;
import com.example.hardwaretrack.models.Drive;
import com.example.hardwaretrack.models.GPU;
import com.example.hardwaretrack.models.RAM;

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
        public static final String COLUMN_COMPUTER_ID = "_idComputer";
        public static final String COLUMN_COMPUTER_TYPE = "computerType";
        public static final String COLUMN_COMPUTER_MANUFACTURER = "computerManufacturer";
        public static final String COLUMN_COMPUTER_MODEL = "computerModel";
        public static final String COLUMN_COMPUTER_CUSTOM_BUILD = "customBuild";
        public static final String COLUMN_COMPUTER_PROCESSOR = "_idCpu";
        public static final String COLUMN_COMPUTER_GRAPHICS_PROCESSOR = "_idGpu";
        public static final String COLUMN_COMPUTER_RAM = "_idRam";
        public static final String COLUMN_COMPUTER_DRIVE = "_idDrive";


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
        public static final String COLUMN_CPU_ID = "_idCpu";
        public static final String COLUMN_CPU_MANUFACTURER = "cpuManufacturer";
        public static final String COLUMN_CPU_MODEL = "cpuModel";
        public static final String COLUMN_CPU_CORE_COUNT = "cpuCoreCount";
        public static final String COLUMN_CPU_THREAD_COUNT = "cpuThreadCount";
        public static final String COLUMN_CPU_BASE_CLOCK = "cpuBaseClock";
        public static final String COLUMN_CPU_BOOST_CLOCK = "cpuBoostClock";

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
        public static final String COLUMN_DRIVE_ID = "_idDrive";
        public static final String COLUMN_DRIVE_MANUFACTURER = "driveManufacturer";
        public static final String COLUMN_DRIVE_MODEL = "driveModel";
        public static final String COLUMN_DRIVE_TYPE = "driveType";
        public static final String COLUMN_DRIVE_FORM_FACTOR = "driveFormFactor";
        public static final String COLUMN_DRIVE_TRANSFER_PROTOCOL = "transferProtocol";
        public static final String COLUMN_DRIVE_CAPACITY = "driveCapacity";
        public static final String COLUMN_DRIVE_MAX_TRANSFER_RATE = "driveMaxTransferRate";

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
        public static final String COLUMN_GPU_ID = "_idGpu";
        public static final String COLUMN_GPU_MANUFACTURER = "gpuManufacturer";
        public static final String COLUMN_GPU_MODEL = "gpuModel";
        public static final String COLUMN_GPU_CORE_COUNT = "gpuCoreCount";
        public static final String COLUMN_GPU_BASE_CLOCK = "gpuBaseClock";
        public static final String COLUMN_GPU_BOOST_CLOCK = "gpuBoostClock";
        public static final String COLUMN_GPU_VRAM = "gpuVRam";

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
        public static final String COLUMN_RAM_ID = "_idRam";
        public static final String COLUMN_RAM_MANUFACTURER = "ramManufacturer";
        public static final String COLUMN_RAM_MODEL = "ramModel";
        public static final String COLUMN_RAM_TYPE = "ramType";
        public static final String COLUMN_RAM_CAPACITY = "ramCapacity";
        public static final String COLUMN_RAM_SPEED = "ramSpeed";
        public static final String COLUMN_RAM_FORM_FACTOR = "ramFormFactor";

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
        //COMPUTER DATA ACCESS METHODS
        public ArrayList<Computer> getAllComputers() {

            ArrayList<Computer> allComputers = new ArrayList<>();
            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s FROM %s", COLUMN_COMPUTER_ID, COLUMN_COMPUTER_TYPE, COLUMN_COMPUTER_MANUFACTURER, COLUMN_COMPUTER_MODEL, COLUMN_COMPUTER_CUSTOM_BUILD,
                           COLUMN_COMPUTER_PROCESSOR, COLUMN_COMPUTER_GRAPHICS_PROCESSOR, COLUMN_COMPUTER_DRIVE, COLUMN_COMPUTER_RAM, TABLE_COMPUTER_NAME);

            Cursor c = database.rawQuery(query, null); //cursor is query result

            // make sure we got some results from the db before processing them
            if((c != null) && (c.getCount() > 0)) { //check if cursor is not null and has rows

                c.moveToFirst(); //move cursor to first row

                while (!c.isAfterLast()) { //while cursor is not after last row
                    //Assign data to appropriate instance variables
                    long computerId = c.getLong(0);
                    String computerType  = c.getString(1);
                    String computerManufacturer = c.getString(2);
                    String computerModel = c.getString(3);
                    boolean computerCustomBuild = (c.getLong(4) == 1 ? true : false);//conditional operator -- if c.getlong is equal to 1 return true, else return false
                    long computerProcessorId = c.getLong(5);
                    long computerGpuId = c.getLong(6);
                    long computerDriveId = c.getLong(7);
                    long computerRamId = c.getLong(8);

                    //TODO - Change this query to properly assign components to computer object
                    Computer pc = new Computer(computerId, computerType, computerManufacturer, computerModel, computerCustomBuild, getCPUById(computerProcessorId), getGPUById(computerGpuId), getDriveById(computerDriveId), getRamById(computerRamId)); //assign variables to computer object
                    allComputers.add(pc);//add computer to array list
                    c.moveToNext(); //move to next row -- DON'T FORGET THIS LINE!!!!!!
                }
                c.close(); //close cursor object to save resources
            }
            Log.d(TAG, query);
            return allComputers;

        }


        public Computer getComputerById(long id) {

            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s = %d", COLUMN_COMPUTER_ID, COLUMN_COMPUTER_TYPE, COLUMN_COMPUTER_MANUFACTURER, COLUMN_COMPUTER_MODEL, COLUMN_COMPUTER_CUSTOM_BUILD,
            COLUMN_COMPUTER_PROCESSOR, COLUMN_COMPUTER_GRAPHICS_PROCESSOR, COLUMN_COMPUTER_DRIVE, COLUMN_COMPUTER_RAM, TABLE_COMPUTER_NAME, COLUMN_COMPUTER_ID, id);

            Cursor c = database.rawQuery(query, null);
            c.moveToFirst();

            long computerId = c.getLong(0);
            String computerType  = c.getString(1);
            String computerManufacturer = c.getString(2);
            String computerModel = c.getString(3);
            boolean computerCustomBuild = (c.getLong(4) == 1 ? true : false);//conditional operator -- if c.getlong is equal to 1 return true, else return false
            long computerProcessorId = c.getLong(5);
            long computerGpuId = c.getLong(6);
            long computerDriveId = c.getLong(7);
            long computerRamId = c.getLong(8);
            Date dueDate = null;


            c.close();

            Computer pc = new Computer(computerId, computerType, computerManufacturer, computerModel, computerCustomBuild, getCPUById(computerProcessorId), getGPUById(computerGpuId), getDriveById(computerDriveId), getRamById(computerRamId));
            return pc;
        }

        //@Override
        public Computer insertComputer(Computer pc) {

            ContentValues values = new ContentValues();
            values.put(COLUMN_COMPUTER_TYPE, pc.getType());
            values.put(COLUMN_COMPUTER_MANUFACTURER, pc.getManufacturer());
            values.put(COLUMN_COMPUTER_MODEL, pc.getModel());
            values.put(COLUMN_COMPUTER_CUSTOM_BUILD, (pc.isCustomBuild() ? 1 : 0));
            values.put(COLUMN_COMPUTER_PROCESSOR, pc.getProcessor().getId());
            values.put(COLUMN_COMPUTER_GRAPHICS_PROCESSOR, pc.getGraphicsProcessor().getId());
            values.put(COLUMN_COMPUTER_DRIVE, pc.getDrive().getId());
            values.put(COLUMN_COMPUTER_RAM, pc.getRam().getId());
            long insertId = database.insert(TABLE_COMPUTER_NAME, null, values);
            // note: insertId will be -1 if the insert failed

            pc.setId(insertId); //set object ID with auto incremented ID
            return pc;
        }



        //@Override
        public Computer updateComputer(Computer pc) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_COMPUTER_TYPE, pc.getType());
            values.put(COLUMN_COMPUTER_MANUFACTURER, pc.getManufacturer());
            values.put(COLUMN_COMPUTER_MODEL, pc.getModel());
            values.put(COLUMN_COMPUTER_CUSTOM_BUILD, (pc.isCustomBuild() ? 1 : 0));
            values.put(COLUMN_COMPUTER_PROCESSOR, pc.getProcessor().getId());
            values.put(COLUMN_COMPUTER_GRAPHICS_PROCESSOR, pc.getGraphicsProcessor().getId());
            values.put(COLUMN_COMPUTER_DRIVE, pc.getDrive().getId());
            values.put(COLUMN_COMPUTER_RAM, pc.getRam().getId());
            int rowsUpdated = database.update(TABLE_COMPUTER_NAME, values, COLUMN_COMPUTER_ID + " = " + pc.getId(), null);
            // this method returns the number of rows that were updated in the db
            // so that you could use it to confirm that your update worked
            return pc;
        }


        //@Override
        public int deleteComputer(Computer pc) {
            int rowsDeleted = database.delete(TABLE_COMPUTER_NAME, COLUMN_COMPUTER_ID + " = " + pc.getId(), null);
            // the above method returns the number of row that were deleted
            return rowsDeleted;
        }


        //CPU DATA ACCESS METHODS
        public ArrayList<CPU> getAllCPUs(){

            ArrayList<CPU> allCPUs = new ArrayList<>();
            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s FROM %s", COLUMN_CPU_ID, COLUMN_CPU_MANUFACTURER, COLUMN_CPU_MODEL, COLUMN_CPU_CORE_COUNT, COLUMN_CPU_THREAD_COUNT, COLUMN_CPU_BASE_CLOCK,
                           COLUMN_CPU_BOOST_CLOCK, TABLE_CPU_NAME);

            Cursor c = database.rawQuery(query, null); //cursor is query result

            // make sure we got some results from the db before processing them
            if((c != null) && (c.getCount() > 0)) { //check if cursor is not null and has rows

                c.moveToFirst(); //move cursor to first row

                while (!c.isAfterLast()) { //while cursor is not after last row
                    //Assign data to appropriate instance variables
                    long cpuId = c.getLong(0);
                    String cpuManufacturer = c.getString(1);
                    String cpuModel = c.getString(2);
                    int cpuCoreCount = c.getInt(3);
                    int cpuThreadCount = c.getInt(4);
                    float cpuBaseClock = c.getFloat(5);
                    float cpuBoostClock = c.getFloat(6);


                    CPU cpu = new CPU(cpuId, cpuManufacturer, cpuModel, cpuCoreCount, cpuThreadCount, cpuBaseClock, cpuBoostClock); //assign variables to cpu object
                    allCPUs.add(cpu);//add cpu to array list
                    c.moveToNext(); //move to next row -- DON'T FORGET THIS LINE!!!!!!
                }
                c.close(); //close cursor object to save resources
            }
            Log.d(TAG, query);
            return allCPUs;

        }

        public CPU getCPUById(long id) {

            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s = %d", COLUMN_CPU_ID, COLUMN_CPU_MANUFACTURER, COLUMN_CPU_MODEL, COLUMN_CPU_CORE_COUNT, COLUMN_CPU_THREAD_COUNT,
                           COLUMN_CPU_BASE_CLOCK, COLUMN_CPU_BOOST_CLOCK, TABLE_CPU_NAME, COLUMN_CPU_ID, id);

            Cursor c = database.rawQuery(query, null);
            c.moveToFirst();

            long cpuId = c.getLong(0);
            String cpuManufacturer = c.getString(1);
            String cpuModel = c.getString(2);
            int cpuCoreCount = c.getInt(3);
            int cpuThreadCount = c.getInt(4);
            float cpuBaseClock = c.getFloat(5);
            float cpuBoostClock = c.getFloat(6);


            c.close();

            CPU cpu = new CPU(cpuId, cpuManufacturer, cpuModel, cpuCoreCount, cpuThreadCount, cpuBaseClock, cpuBoostClock);
            return  cpu;

        }

        public CPU insertTask(CPU cpu) {

            ContentValues values = new ContentValues();
            values.put(COLUMN_CPU_MANUFACTURER, cpu.getManufacturer());
            values.put(COLUMN_CPU_MODEL, cpu.getModel());
            values.put(COLUMN_CPU_CORE_COUNT, cpu.getCoreCount());
            values.put(COLUMN_CPU_THREAD_COUNT, cpu.getThreadCount());
            values.put(COLUMN_CPU_BASE_CLOCK, cpu.getBaseClock());
            values.put(COLUMN_CPU_BOOST_CLOCK, cpu.getBoostClock());
            long insertId = database.insert(TABLE_CPU_NAME, null, values);
            // note: insertId will be -1 if the insert failed

            cpu.setId(insertId); //set task object ID with auto incremented ID
            return cpu;
        }

        public CPU updateCPU(CPU cpu) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CPU_MANUFACTURER, cpu.getManufacturer());
            values.put(COLUMN_CPU_MODEL, cpu.getModel());
            values.put(COLUMN_CPU_CORE_COUNT, cpu.getCoreCount());
            values.put(COLUMN_CPU_THREAD_COUNT, cpu.getThreadCount());
            values.put(COLUMN_CPU_BASE_CLOCK, cpu.getBaseClock());
            values.put(COLUMN_CPU_BOOST_CLOCK, cpu.getBoostClock());
            int rowsUpdated = database.update(TABLE_CPU_NAME, values, COLUMN_CPU_ID + " = " + cpu.getId(), null);
            // this method returns the number of rows that were updated in the db
            // so that you could use it to confirm that your update worked
            return cpu;
        }

        public int deleteCPU(CPU cpu) {
            int rowsDeleted = database.delete(TABLE_CPU_NAME, COLUMN_CPU_ID + " = " + cpu.getId(), null);
            // the above method returns the number of row that were deleted
            return rowsDeleted;
        }

        //DRIVE DATA ACCESS METHODS
        public ArrayList<Drive> getAllDrives() {

            ArrayList<Drive> allDrives = new ArrayList<>();
            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s FROM %s", COLUMN_DRIVE_ID, COLUMN_DRIVE_MANUFACTURER, COLUMN_DRIVE_MODEL, COLUMN_DRIVE_TYPE, COLUMN_DRIVE_FORM_FACTOR,
                    COLUMN_DRIVE_TRANSFER_PROTOCOL, COLUMN_DRIVE_CAPACITY, COLUMN_DRIVE_MAX_TRANSFER_RATE, TABLE_DRIVE_NAME);

            Cursor c = database.rawQuery(query, null); //cursor is query result

            // make sure we got some results from the db before processing them
            if ((c != null) && (c.getCount() > 0)) { //check if cursor is not null and has rows

                c.moveToFirst(); //move cursor to first row

                while (!c.isAfterLast()) { //while cursor is not after last row
                    //Assign data to appropriate instance variables
                    long driveId = c.getLong(0);
                    String driveManufacturer = c.getString(1);
                    String driveModel = c.getString(2);
                    String driveType = c.getString(3);
                    String driveFormFactor = c.getString(4);
                    String driveTransferProtocol = c.getString(5);
                    long driveCapacity = c.getLong(6);
                    long driveMaxTransferRate = c.getLong(7);


                    Drive drive = new Drive(driveId, driveManufacturer, driveModel, driveType, driveFormFactor, driveTransferProtocol, driveCapacity, driveMaxTransferRate); //assign variables to drive object
                    allDrives.add(drive);//add drive to array list
                    c.moveToNext(); //move to next row -- DON'T FORGET THIS LINE!!!!!!
                }
                c.close(); //close cursor object to save resources
            }
            Log.d(TAG, query);
            return allDrives;
        }

        public Drive getDriveById(long id) {

            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s = %d", COLUMN_DRIVE_ID, COLUMN_DRIVE_MANUFACTURER, COLUMN_DRIVE_MODEL, COLUMN_DRIVE_TYPE, COLUMN_DRIVE_FORM_FACTOR,
                    COLUMN_DRIVE_TRANSFER_PROTOCOL, COLUMN_DRIVE_CAPACITY, COLUMN_DRIVE_MAX_TRANSFER_RATE, TABLE_DRIVE_NAME, COLUMN_DRIVE_ID, id);

            Cursor c = database.rawQuery(query, null);
            c.moveToFirst();

            long driveId = c.getLong(0);
            String driveManufacturer = c.getString(1);
            String driveModel = c.getString(2);
            String driveType = c.getString(3);
            String driveFormFactor = c.getString(4);
            String driveTransferProtocol = c.getString(5);
            long driveCapacity = c.getLong(6);
            long driveMaxTransferRate = c.getLong(7);



            c.close();

            Drive drive = new Drive(driveId, driveManufacturer, driveModel, driveType, driveFormFactor, driveTransferProtocol, driveCapacity, driveMaxTransferRate);
            return drive;

        }

        public Drive insertDrive(Drive d) {

            ContentValues values = new ContentValues();
            values.put(COLUMN_DRIVE_MANUFACTURER, d.getManufacturer());
            values.put(COLUMN_DRIVE_MODEL, d.getModel());
            values.put(COLUMN_DRIVE_TYPE, d.getType());
            values.put(COLUMN_DRIVE_FORM_FACTOR, d.getFormFactor());
            values.put(COLUMN_DRIVE_TRANSFER_PROTOCOL, d.getTransferProtocol());
            values.put(COLUMN_DRIVE_CAPACITY, d.getCapacity());
            values.put(COLUMN_DRIVE_MAX_TRANSFER_RATE, d.getMaxTransferRate());
            long insertId = database.insert(TABLE_DRIVE_NAME, null, values);
            // note: insertId will be -1 if the insert failed

            d.setId(insertId); //set task object ID with auto incremented ID
            return d;
        }

        public Drive updateDrive(Drive d) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_DRIVE_MANUFACTURER, d.getManufacturer());
            values.put(COLUMN_DRIVE_MODEL, d.getModel());
            values.put(COLUMN_DRIVE_TYPE, d.getType());
            values.put(COLUMN_DRIVE_FORM_FACTOR, d.getFormFactor());
            values.put(COLUMN_DRIVE_TRANSFER_PROTOCOL, d.getTransferProtocol());
            values.put(COLUMN_DRIVE_CAPACITY, d.getCapacity());
            values.put(COLUMN_DRIVE_MAX_TRANSFER_RATE, d.getMaxTransferRate());
            int rowsUpdated = database.update(TABLE_DRIVE_NAME, values, COLUMN_DRIVE_ID + " = " + d.getId(), null);
            // this method returns the number of rows that were updated in the db
            // so that you could use it to confirm that your update worked
            return d;
        }

        public int deleteDrive(Drive d) {
            int rowsDeleted = database.delete(TABLE_DRIVE_NAME, COLUMN_DRIVE_ID + " = " + d.getId(), null);
            // the above method returns the number of row that were deleted
            return rowsDeleted;
        }

        //GPU DATA ACCESS METHODS
        public ArrayList<GPU> getAllGPUs() {

            ArrayList<GPU> allGPUs = new ArrayList<>();
            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s FROM %s", COLUMN_GPU_ID, COLUMN_GPU_MANUFACTURER, COLUMN_GPU_MODEL, COLUMN_GPU_CORE_COUNT, COLUMN_GPU_BASE_CLOCK,
                           COLUMN_GPU_BOOST_CLOCK, COLUMN_GPU_VRAM, TABLE_GPU_NAME);

            Cursor c = database.rawQuery(query, null); //cursor is query result

            // make sure we got some results from the db before processing them
            if ((c != null) && (c.getCount() > 0)) { //check if cursor is not null and has rows

                c.moveToFirst(); //move cursor to first row

                while (!c.isAfterLast()) { //while cursor is not after last row
                    //Assign data to appropriate instance variables
                    long gpuId = c.getLong(0);
                    String gpuManufacturer = c.getString(1);
                    String gpuModel = c.getString(2);
                    long gpuCoreCount = c.getLong(3);
                    long gpuBaseBlock = c.getLong(4);
                    long gpuBoostClock = c.getLong(5);
                    String gpuVram = c.getString(6);


                    GPU gpu = new GPU(gpuId, gpuManufacturer, gpuModel, gpuCoreCount, gpuBaseBlock, gpuBoostClock, gpuVram); //assign variables to gpu object
                    allGPUs.add(gpu);//add gpu to array list
                    c.moveToNext(); //move to next row -- DON'T FORGET THIS LINE!!!!!!
                }
                c.close(); //close cursor object to save resources
            }
            Log.d(TAG, query);
            return allGPUs;
        }

        public GPU getGPUById(long id){
            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s = %d", COLUMN_GPU_ID, COLUMN_GPU_MANUFACTURER, COLUMN_GPU_MODEL, COLUMN_GPU_CORE_COUNT, COLUMN_GPU_BASE_CLOCK,
                    COLUMN_GPU_BOOST_CLOCK, COLUMN_GPU_VRAM, TABLE_GPU_NAME, COLUMN_GPU_ID, id);

            Cursor c = database.rawQuery(query, null);
            c.moveToFirst();

            long gpuId = c.getLong(0);
            String gpuManufacturer = c.getString(1);
            String gpuModel = c.getString(2);
            long gpuCoreCount = c.getLong(3);
            long gpuBaseBlock = c.getLong(4);
            long gpuBoostClock = c.getLong(5);
            String gpuVram = c.getString(6);



            c.close();

            GPU gpu = new GPU(gpuId, gpuManufacturer, gpuModel, gpuCoreCount, gpuBaseBlock, gpuBoostClock, gpuVram);
            return gpu;
        }

        public GPU insertGPU(GPU gpu) {

            ContentValues values = new ContentValues();
            values.put(COLUMN_GPU_MANUFACTURER, gpu.getManufacturer());
            values.put(COLUMN_GPU_MODEL, gpu.getModel());
            values.put(COLUMN_GPU_CORE_COUNT, gpu.getCoreCount());
            values.put(COLUMN_GPU_BASE_CLOCK, gpu.getCoreCount());
            values.put(COLUMN_GPU_BOOST_CLOCK, gpu.getBoostClock());
            values.put(COLUMN_GPU_VRAM, gpu.getvRam());
            long insertId = database.insert(TABLE_GPU_NAME, null, values);
            // note: insertId will be -1 if the insert failed

            gpu.setId(insertId); //set task object ID with auto incremented ID
            return gpu;
        }

        public GPU updateGPU(GPU gpu){

            ContentValues values = new ContentValues();
            values.put(COLUMN_GPU_MANUFACTURER, gpu.getManufacturer());
            values.put(COLUMN_GPU_MODEL, gpu.getModel());
            values.put(COLUMN_GPU_CORE_COUNT, gpu.getCoreCount());
            values.put(COLUMN_GPU_BASE_CLOCK, gpu.getCoreCount());
            values.put(COLUMN_GPU_BOOST_CLOCK, gpu.getBoostClock());
            values.put(COLUMN_GPU_VRAM, gpu.getvRam());
            int rowsUpdated = database.update(TABLE_GPU_NAME, values, COLUMN_GPU_ID + " = " + gpu.getId(), null);
            // this method returns the number of rows that were updated in the db
            // so that you could use it to confirm that your update worked
            return gpu;
        }

        public int deleteGPU(GPU gpu) {
            int rowsDeleted = database.delete(TABLE_GPU_NAME, COLUMN_GPU_ID + " = " + gpu.getId(), null);
            // the above method returns the number of row that were deleted
            return rowsDeleted;
        }

        //RAM DATA ACCESS METHODS
        public ArrayList<RAM> getAllRAM() {

            ArrayList<RAM> allRAM = new ArrayList<>();
            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s FROM %s", COLUMN_RAM_ID, COLUMN_RAM_MANUFACTURER, COLUMN_RAM_MODEL, COLUMN_RAM_TYPE, COLUMN_RAM_CAPACITY,
                           COLUMN_RAM_SPEED, COLUMN_RAM_FORM_FACTOR, TABLE_RAM_NAME);

            Cursor c = database.rawQuery(query, null); //cursor is query result

            // make sure we got some results from the db before processing them
            if ((c != null) && (c.getCount() > 0)) { //check if cursor is not null and has rows

                c.moveToFirst(); //move cursor to first row

                while (!c.isAfterLast()) { //while cursor is not after last row
                    //Assign data to appropriate instance variables
                    long ramId = c.getLong(0);
                    String ramManufacturer = c.getString(1);
                    String ramModel = c.getString(2);
                    String ramType = c.getString(3);
                    long ramCapacity = c.getLong(4);
                    long ramSpeed = c.getLong(5);
                    String ramFormFactor = c.getString(6);


                    RAM ram = new RAM(ramId, ramManufacturer, ramModel, ramType, ramCapacity, ramSpeed, ramFormFactor); //assign variables to RAM object
                    allRAM.add(ram);//add RAM to array list
                    c.moveToNext(); //move to next row -- DON'T FORGET THIS LINE!!!!!!
                }
                c.close(); //close cursor object to save resources
            }
            Log.d(TAG, query);
            return allRAM;
        }

        public RAM getRamById(long id){
            String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s = %d", COLUMN_RAM_ID, COLUMN_RAM_MANUFACTURER, COLUMN_RAM_MODEL, COLUMN_RAM_TYPE, COLUMN_RAM_CAPACITY,
                           COLUMN_RAM_SPEED, COLUMN_RAM_FORM_FACTOR, TABLE_RAM_NAME, COLUMN_RAM_ID, id);

            Cursor c = database.rawQuery(query, null);
            c.moveToFirst();

            long ramId = c.getLong(0);
            String ramManufacturer = c.getString(1);
            String ramModel = c.getString(2);
            String ramType = c.getString(3);
            long ramCapacity = c.getLong(4);
            long ramSpeed = c.getLong(5);
            String ramFormFactor = c.getString(6);



            c.close();

            RAM ram = new RAM(ramId, ramManufacturer, ramModel, ramType, ramCapacity, ramSpeed, ramFormFactor);
            return ram;
        }

        public RAM insertRAM(RAM ram) {

            ContentValues values = new ContentValues();
            values.put(COLUMN_RAM_MANUFACTURER, ram.getManufacturer());
            values.put(COLUMN_RAM_MODEL, ram.getModel());
            values.put(COLUMN_RAM_TYPE, ram.getType());
            values.put(COLUMN_RAM_CAPACITY, ram.getCapacity());
            values.put(COLUMN_RAM_SPEED, ram.getSpeed());
            values.put(COLUMN_RAM_FORM_FACTOR, ram.getFormFactor());
            long insertId = database.insert(TABLE_RAM_NAME, null, values);
            // note: insertId will be -1 if the insert failed

            ram.setId(insertId); //set task object ID with auto incremented ID
            return ram;
        }


        public RAM updateRAM(RAM ram){

            ContentValues values = new ContentValues();
            values.put(COLUMN_RAM_MANUFACTURER, ram.getManufacturer());
            values.put(COLUMN_RAM_MODEL, ram.getModel());
            values.put(COLUMN_RAM_TYPE, ram.getType());
            values.put(COLUMN_RAM_CAPACITY, ram.getCapacity());
            values.put(COLUMN_RAM_SPEED, ram.getSpeed());
            values.put(COLUMN_RAM_FORM_FACTOR, ram.getFormFactor());
            int rowsUpdated = database.update(TABLE_RAM_NAME, values, COLUMN_RAM_ID + " = " + ram.getId(), null);
            // this method returns the number of rows that were updated in the db
            // so that you could use it to confirm that your update worked
            return ram;
        }

        public int deleteRAM(RAM ram){
            int rowsDeleted = database.delete(TABLE_RAM_NAME, COLUMN_RAM_ID + " = " + ram.getId(), null);
            // the above method returns the number of row that were deleted
            return rowsDeleted;
        }

}