package id.ac.binus.my_application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_Helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SOPI Database";
    public Database_Helper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //BUAT TABEL USER
        db.execSQL("CREATE TABLE user(" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "email TEXT NOT NULL,"+
                "username TEXT NOT NULL,"+
                "password TEXT NOT NULL,"+
                "phone TEXT NOT NULL,"+
                "wallet INTEGER NOT NULL,"+
                "dob TEXT NOT NULL,"+
                "alamat TEXT NOT NULL"+
                ")");
        //BUAT TABEL USER

        //BUAT TABEL PRODUK
        db.execSQL("CREATE TABLE product(" +
                "product_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "product_name TEXT NOT NULL,"+
                "product_price INTEGER NOT NULL,"+
                "product_rating REAL NOT NULL,"+
                "product_description TEXT NOT NULL,"+
                "product_weight REAL NOT NULL, "+
                "product_brand TEXT NOT NULL, "+
                "product_type TEXT NOT NULL, "+
                "product_image INTEGER NOT NULL "+
                ")");
        //BUAT TABEL PRODUK

        //BUAT TABEL TRANSAKSI
        db.execSQL("CREATE TABLE transactions (" +
                "transaction_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "user_id TEXT NOT NULL,"+
                "transaction_nominal INTEGER NOT NULL,"+
                "transaction_date TEXT NOT NULL,"+
                "FOREIGN KEY (user_id) REFERENCES user (user_id) ON UPDATE CASCADE ON DELETE CASCADE "+
                ")");
        //BUAT TABEL TRANSAKSI

        //BUAT TABEL WHISLIST
        db.execSQL("CREATE TABLE whislists (" +
                "whislist_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "user_id TEXT NOT NULL,"+
                "product_id TEXT NOT NULL,"+
                "FOREIGN KEY (user_id) REFERENCES user (user_id) ON UPDATE CASCADE ON DELETE CASCADE," +
                "FOREIGN KEY (product_id) REFERENCES product (product_id ) ON UPDATE CASCADE ON DELETE CASCADE" +
                ")");
        //BUAT TABEL WHISLIST

        //BUAT TABEL CART
        db.execSQL("CREATE TABLE carts (" +
                "cart_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "user_id TEXT NOT NULL,"+
                "product_id TEXT NOT NULL,"+
                "qty INTEGER," +
                "FOREIGN KEY (user_id) REFERENCES user (user_id) ON UPDATE CASCADE ON DELETE CASCADE," +
                "FOREIGN KEY (product_id) REFERENCES product (product_id ) ON UPDATE CASCADE ON DELETE CASCADE" +
                ")");
        //BUAT TABEL CART
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS product");
        db.execSQL("DROP TABLE IF EXISTS transactions");
        db.execSQL("DROP TABLE IF EXISTS whislists");
        db.execSQL("DROP TABLE IF EXISTS carts");
        onCreate(db);
    }
}
