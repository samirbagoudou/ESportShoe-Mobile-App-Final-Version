package gl52.utbm.esportshoe;

/**
 * Created by root on 08/06/17.
 */

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";
    public static final String DATABASE_NAME = "esportshoe.db";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating tables");
        db.execSQL(Constants.ADMIN_CREATE_TABLE);
        db.execSQL(Constants.SHOE_CREATE_TABLE);
        db.execSQL(Constants.SENSOR1_CREATE_TABLE);
        db.execSQL(Constants.SENSOR2_CREATE_TABLE);
        db.execSQL(Constants.SENSOR3_CREATE_TABLE);
        db.execSQL(Constants.SENSOR4_CREATE_TABLE);
        Log.i(TAG, "Tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrade sequence launched");
        //Before recreate new tables, deleting existing
        db.execSQL(Constants.ADMIN_TABLE_DROP);
        db.execSQL(Constants.SHOE_TABLE_DROP);
        db.execSQL(Constants.SENSOR1_TABLE_DROP);
        db.execSQL(Constants.SENSOR2_TABLE_DROP);
        db.execSQL(Constants.SENSOR3_TABLE_DROP);
        db.execSQL(Constants.SENSOR4_TABLE_DROP);
        //now creating tables
        onCreate(db);
    }

    //INSERTION
    public boolean insertAdmin(String fName, String lName, String login, String pwd) {
        Log.i(TAG, "Inserting admin value");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.ADMIN_FIRSTNAME, fName);
        contentValues.put(Constants.ADMIN_LASTNAME, lName);
        contentValues.put(Constants.ADMIN_LOGIN, login);
        contentValues.put(Constants.ADMIN_PASSWORD, pwd);
        db.insert(Constants.ADMIN_TABLE_NAME, null, contentValues);
        Log.i(TAG, "Value inserted");
        return true;
    }

    public boolean insertShoe(String name) {
        Log.i(TAG, "Inserting shoe value");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.SHOE_NAME, name);
        db.insert(Constants.SHOE_TABLE_NAME, null, contentValues);
        Log.i(TAG, "Value inserted");
        return true;
    }

    public boolean insertSensor1Value(String value) {
        Log.i(TAG, "Inserting sensor1 value");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.SENSOR1_VALUE, value);
        db.insert(Constants.SENSOR1_TABLE_NAME, null, contentValues);
        Log.i(TAG, "Value inserted");
        return true;
    }

    public boolean insertSensor2Value(String value) {
        Log.i(TAG, "Inserting sensor2 value");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.SENSOR2_VALUE, value);
        db.insert(Constants.SENSOR2_TABLE_NAME, null, contentValues);
        Log.i(TAG, "Value inserted");
        return true;
    }

    public boolean insertSensor3Value(String value) {
        Log.i(TAG, "Inserting sensor3 value");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.SENSOR3_VALUE, value);
        db.insert(Constants.SENSOR3_TABLE_NAME, null, contentValues);
        Log.i(TAG, "Value inserted");
        return true;
    }

    public boolean insertSensor4Value(String value) {
        Log.i(TAG, "Inserting sensor4 value");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.SENSOR4_VALUE, value);
        db.insert(Constants.SENSOR4_TABLE_NAME, null, contentValues);
        Log.i(TAG, "Value inserted");
        return true;
    }


    //GETTING VALUES
    public Cursor getAdminFLNames(String log, String pass) {
        Log.i(TAG, "Getting admin name");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select "+Constants.ADMIN_FIRSTNAME+", "+Constants.ADMIN_LASTNAME+" from "+Constants.ADMIN_TABLE_NAME+" where "+Constants.ADMIN_LOGIN+" = '"+log+"' and "+Constants.ADMIN_PASSWORD+" = '"+pass+"'", null );
        Log.i(TAG, "Admin name got");
        return res;
    }

    public int numberOfRowsSensor1(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Constants.SENSOR1_TABLE_NAME);
        return numRows;
    }


    //UPDATE
    public boolean updateAdmin (String fName, String lName, String login, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.ADMIN_FIRSTNAME, fName);
        contentValues.put(Constants.ADMIN_LASTNAME, lName);
        contentValues.put(Constants.ADMIN_LOGIN, login);
        contentValues.put(Constants.ADMIN_PASSWORD, pwd);
        db.update(Constants.ADMIN_TABLE_NAME, contentValues, Constants.ADMIN_LOGIN+" = ? ", new String[] {login} );
        return true;
    }


    //DELETE
    public Integer deleteShoe (String sName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Constants.SHOE_TABLE_NAME,
                Constants.SHOE_NAME+" = ? ",
                new String[] {sName});
    }

    public void truncateSensorTables () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.SENSOR1_TABLE_NAME, null, null);
        db.delete(Constants.SENSOR2_TABLE_NAME, null, null);
        db.delete(Constants.SENSOR3_TABLE_NAME, null, null);
        db.delete(Constants.SENSOR4_TABLE_NAME, null, null);
    }


    //RETRIEVE ALL VALUES
    public ArrayList<String> getAllShoes() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+Constants.SHOE_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Constants.SHOE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllSensor1Values() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+Constants.SENSOR1_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Constants.SENSOR1_VALUE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllSensor2Values() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+Constants.SENSOR2_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Constants.SENSOR2_VALUE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllSensor3Values() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+Constants.SENSOR3_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Constants.SENSOR3_VALUE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllSensor4Values() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+Constants.SENSOR4_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Constants.SENSOR4_VALUE)));
            res.moveToNext();
        }
        return array_list;
    }

}
