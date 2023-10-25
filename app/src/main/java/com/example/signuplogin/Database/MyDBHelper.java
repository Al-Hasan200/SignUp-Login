package com.example.signuplogin.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.signuplogin.UserInfo;

public class MyDBHelper extends SQLiteOpenHelper {

    //variable declaring
    private Context context;
    private static final String databaseName = "login_signup";
    private static final int databaseVersion = 1;
    private static final String databaseTableName = "login_signup_info";
    private static final String columnNameID = "_Id";
    private static final String columnNameName = "Name";
    private static final String columnNameEmail = "Email";
    private static final String columnNameUserName = "Username";
    private static final String columnNamePassword = "Password";
    private static final String createTable = "CREATE TABLE "+databaseTableName+" ("+columnNameID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+columnNameName+" VARCHAR(255) NOT NULL, "+columnNameEmail+" TEXT NOT NULL, "+columnNameUserName+" TEXT NOT NULL, "+columnNamePassword+" TEXT NOT NULL);";
    private static final String dropTable = "DROP TABLE IF EXISTS "+databaseTableName;
    private static final String select_table = "SELECT * FROM "+databaseTableName;

    public MyDBHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            Toast.makeText(context, "Table Created...", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(createTable);
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context, "Table Upgraded...", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(dropTable);
            onCreate(sqLiteDatabase);
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }
    }

    //insert userinfo in database
    public long insertData(UserInfo userInfo){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnNameName, userInfo.getName());
        contentValues.put(columnNameEmail, userInfo.getEmail());
        contentValues.put(columnNameUserName, userInfo.getUsername());
        contentValues.put(columnNamePassword, userInfo.getPassword());

        long rowId = sqLiteDatabase.insert(databaseTableName, null, contentValues);
        return rowId;
    }

    //login user
    public boolean login(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(select_table, null);
        boolean result = false;

        if (cursor.getCount() == 0){
            Toast.makeText(context, "No data is found...", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                String uname = cursor.getString(3);
                String pass = cursor.getString(4);

                if (username.equals(uname) && password.equals(pass)){
                    result = true;
                }
            }
        }

        return result;
    }
}
