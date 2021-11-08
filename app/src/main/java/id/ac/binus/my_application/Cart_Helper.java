package id.ac.binus.my_application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class Cart_Helper {
    Database_Helper helper;

    public Cart_Helper(Context context){
        helper = new Database_Helper(context);
    }

    public void insertProduct_to_Cart (int user_id, int product_id, int qty){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO carts (user_id, product_id, qty) " +
                "VALUES ('"+user_id+"', '"+product_id+"', '"+qty+"')");
        db.close();
        helper.close();
    }

    public void updateProduct_Cart (int user_id, int product_id, int qty){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("UPDATE carts " +
                "SET qty = '"+qty+"' WHERE user_id = '"+user_id+"' AND product_id = '"+product_id+"'");
        db.close();
        helper.close();
    }


    public Product_Cart get_product_cart (int user_id, int product_id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM carts JOIN product on carts.product_id = product.product_id " +
                "WHERE user_id =? AND product.product_id =?", new String[] {String.valueOf(user_id), String.valueOf(product_id)});

        cursor.moveToFirst();
        Product_Cart product_cart = null;
        if (cursor.getCount() > 0){

            //GET SEMUA DATA DARI DATABASE
            int cart_idResult = cursor.getInt(cursor.getColumnIndex("cart_id"));
            int product_idResult = cursor.getInt(cursor.getColumnIndex("product_id"));
            String product_nameResult = cursor.getString(cursor.getColumnIndex("product_name"));
            int  product_priceResult = cursor.getInt(cursor.getColumnIndex("product_price"));
            double product_ratingResult = cursor.getDouble(cursor.getColumnIndex("product_rating"));
            String product_descriptionResult = cursor.getString(cursor.getColumnIndex("product_description"));
            double product_weightResult = cursor.getDouble(cursor.getColumnIndex("product_weight"));
            String product_brandResult = cursor.getString(cursor.getColumnIndex("product_brand"));
            String product_typeResult = cursor.getString(cursor.getColumnIndex("product_type"));
            int product_imageResult = cursor.getInt(cursor.getColumnIndex("product_image"));
            int product_qtyResult = cursor.getInt(cursor.getColumnIndex("qty"));
            //GET SEMUA DATA DARI DATABASE

            product_cart = new Product_Cart(cart_idResult, product_idResult,product_nameResult,product_priceResult,product_ratingResult,product_descriptionResult,product_weightResult,product_brandResult,product_typeResult, product_imageResult, product_qtyResult);
        }
        cursor.close();
        db.close();
        helper.close();
        return product_cart;
    }

    public Vector<Product_Cart> getAllProductCart(int user_id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM carts JOIN product on carts.product_id = product.product_id " +
                "WHERE user_id = ? ", new String[] {String.valueOf(user_id)});
        cursor.moveToFirst();

        Vector<Product_Cart> listAllProductCart= null;
        if (cursor.getCount() > 0){
            listAllProductCart = new Vector<>();
            while (!cursor.isAfterLast()){
                //GET SEMUA DATA DARI DATABASE
                int cart_idResult = cursor.getInt(cursor.getColumnIndex("cart_id"));
                int product_idResult = cursor.getInt(cursor.getColumnIndex("product_id"));
                String product_nameResult = cursor.getString(cursor.getColumnIndex("product_name"));
                int  product_priceResult = cursor.getInt(cursor.getColumnIndex("product_price"));
                double product_ratingResult = cursor.getDouble(cursor.getColumnIndex("product_rating"));
                String product_descriptionResult = cursor.getString(cursor.getColumnIndex("product_description"));
                double product_weightResult = cursor.getDouble(cursor.getColumnIndex("product_weight"));
                String product_brandResult = cursor.getString(cursor.getColumnIndex("product_brand"));
                String product_typeResult = cursor.getString(cursor.getColumnIndex("product_type"));
                int product_imageResult = cursor.getInt(cursor.getColumnIndex("product_image"));
                int product_qtyResult = cursor.getInt(cursor.getColumnIndex("qty"));
                //GET SEMUA DATA DARI DATABASE

                listAllProductCart.add(new Product_Cart(cart_idResult, product_idResult,product_nameResult,product_priceResult,product_ratingResult,product_descriptionResult,product_weightResult,product_brandResult,product_typeResult, product_imageResult,product_qtyResult));
                cursor.moveToNext();
            }

        }
        cursor.close();
        db.close();
        helper.close();
        return listAllProductCart;
    }

    public void deleteAll (int user_id){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM carts " +
                "WHERE user_id = '"+user_id+"'");
        db.close();
        helper.close();
    }
}
