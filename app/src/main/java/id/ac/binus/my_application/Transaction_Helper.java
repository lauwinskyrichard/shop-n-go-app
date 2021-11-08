package id.ac.binus.my_application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class Transaction_Helper {
    Database_Helper helper;

    public Transaction_Helper(Context context){
        helper = new Database_Helper(context);
    }

    public void insert_transaction (int user_id, int transaction_nominal, String transaction_date){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO transactions (user_id, transaction_nominal, transaction_date) " +
                "VALUES ('"+user_id+"', '"+transaction_nominal+"', '"+transaction_date+"')");
        db.close();
        helper.close();
    }

    public Vector<Product_Transaction> getAllTransaction(int user_id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM transactions  " +
                "WHERE user_id =? ", new String[] {String.valueOf(user_id)});

        cursor.moveToFirst();
        Product_Transaction product_transaction = null;

        Vector<Product_Transaction> all_transaction = null;
        if (cursor.getCount() > 0){
            all_transaction = new Vector<>();
            while (!cursor.isAfterLast()){
                //GET SEMUA DATA DARI DATABASE
                int transaction_idResult = cursor.getInt(cursor.getColumnIndex("transaction_id"));
                int user_idResult = cursor.getInt(cursor.getColumnIndex("user_id"));
                int  transaction_nominalResult = cursor.getInt(cursor.getColumnIndex("transaction_nominal"));
                String transaction_dateResult = cursor.getString(cursor.getColumnIndex("transaction_date"));
                //GET SEMUA DATA DARI DATABASE

                all_transaction.add(new Product_Transaction(transaction_idResult, user_idResult,transaction_nominalResult,transaction_dateResult));
                cursor.moveToNext();
            }

        }
        cursor.close();
        db.close();
        helper.close();
        return all_transaction;
    }
}