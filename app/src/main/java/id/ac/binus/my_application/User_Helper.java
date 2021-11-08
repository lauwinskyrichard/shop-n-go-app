package id.ac.binus.my_application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class User_Helper {
    Database_Helper helper;

    public User_Helper(Context context){
        helper = new Database_Helper(context);
    }

    public void insertUser (String email, String username, String password, String phone, int wallet, String dob, String alamat){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO user (email, username, password, phone, wallet, dob, alamat) " +
                "VALUES('"+email+"', '"+username+"', '"+password+"', '"+phone+"', '"+wallet+"', '"+dob+"', '"+alamat+"')");
        db.close();
        helper.close();
    }

    public void updateUsername (String username, String password, String new_username){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("UPDATE user " +
                "SET username = '"+new_username+"' WHERE username = '"+username+"' AND password = '"+password+"' ");
        db.close();
        helper.close();
    }

    public void updatePassword (String username, String password, String new_password){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("UPDATE user " +
                "SET password = '"+new_password+"' WHERE username = '"+username+"' AND password = '"+password+"' ");
        db.close();
        helper.close();
    }

    public void updateEmail (String username, String password, String new_email){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("UPDATE user " +
                "SET email = '"+new_email+"' WHERE username = '"+username+"' AND password = '"+password+"' ");
        db.close();
        helper.close();
    }

    public void updatePhone_number (String username, String password, String new_phone_number){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("UPDATE user " +
                "SET phone = '"+new_phone_number+"' WHERE username = '"+username+"' AND password = '"+password+"' ");
        db.close();
        helper.close();
    }

    public void updateDoB (String username, String password, String new_DoB){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("UPDATE user " +
                "SET dob = '"+new_DoB+"' WHERE username = '"+username+"' AND password = '"+password+"' ");
        db.close();
        helper.close();
    }

    public void updateAddress (String username, String password, String new_Address){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("UPDATE user " +
                "SET dob = '"+new_Address+"' WHERE username = '"+username+"' AND password = '"+password+"' ");
        db.close();
        helper.close();
    }

    public User getUser(String username, String password){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user " +
                "WHERE username =? AND password =?", new String[] {username,password});

        cursor.moveToFirst();
        User user = null;
        if (cursor.getCount() > 0){
            //GET SEMUA DATA DARI DATABASE
            int idResult = cursor.getInt(cursor.getColumnIndex("user_id"));
            String emailResult = cursor.getString(cursor.getColumnIndex("email"));
            String usernameResult = cursor.getString(cursor.getColumnIndex("username"));
            String passwordResult = cursor.getString(cursor.getColumnIndex("password"));
            String phoneResult = cursor.getString(cursor.getColumnIndex("phone"));
            int walletResult = cursor.getInt(cursor.getColumnIndex("wallet"));
            String dobResult = cursor.getString(cursor.getColumnIndex("dob"));
            String alamatResult = cursor.getString(cursor.getColumnIndex("alamat"));
            //GET SEMUA DATA DARI DATABASE

            user = new User(idResult,emailResult,usernameResult,passwordResult,phoneResult,walletResult,dobResult,alamatResult);
        }
        cursor.close();
        db.close();
        helper.close();
        return user;
    }

    public Vector<User> getalluser(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        cursor.moveToFirst();

        Vector<User> listUser = null;
        if (cursor.getCount() > 0){
            listUser = new Vector<>();
            while (!cursor.isAfterLast()){
                //GET SEMUA DATA DARI DATABASE
                int idResult = cursor.getInt(cursor.getColumnIndex("user_id"));
                String emailResult = cursor.getString(cursor.getColumnIndex("email"));
                String usernameResult = cursor.getString(cursor.getColumnIndex("username"));
                String passwordResult = cursor.getString(cursor.getColumnIndex("password"));
                String phoneResult = cursor.getString(cursor.getColumnIndex("phone"));
                int walletResult = cursor.getInt(cursor.getColumnIndex("wallet"));
                String dobResult = cursor.getString(cursor.getColumnIndex("dob"));
                String alamatResult = cursor.getString(cursor.getColumnIndex("alamat"));
                //GET SEMUA DATA DARI DATABASE

                listUser.add(new User(idResult,emailResult,usernameResult,passwordResult,phoneResult,walletResult,dobResult,alamatResult));
                cursor.moveToNext();
            }

        }
        cursor.close();
        db.close();
        helper.close();
        return listUser;
    }
}
