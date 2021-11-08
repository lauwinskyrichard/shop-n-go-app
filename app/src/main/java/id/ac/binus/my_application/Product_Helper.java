package id.ac.binus.my_application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class Product_Helper {
    Database_Helper helper;

    public Product_Helper(Context context){
        helper = new Database_Helper(context);
    }

    public void insertProduct (String product_name, int product_price, double product_rating, String product_description, double product_weight, String product_brand, String product_type, int product_image){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO product (product_name, product_price, product_rating, product_description, product_weight, product_brand, product_type, product_image) " +
                "VALUES ('"+product_name+"', '"+product_price+"','"+product_rating+"', '"+product_description+"', '"+product_weight+"', '"+product_brand+"', '"+product_type+"', '"+product_image+"')");
        db.close();
        helper.close();
    }

    public Product getProduct(int product_id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM product " +
                "WHERE product_id =? ", new String[] {String.valueOf(product_id)});

        cursor.moveToFirst();
        Product product = null;
        if (cursor.getCount() > 0){

            //GET SEMUA DATA DARI DATABASE
            int idResult = cursor.getInt(cursor.getColumnIndex("product_id"));
            String product_nameResult = cursor.getString(cursor.getColumnIndex("product_name"));
            int  product_priceResult = cursor.getInt(cursor.getColumnIndex("product_price"));
            double product_ratingResult = cursor.getDouble(cursor.getColumnIndex("product_rating"));
            String product_descriptionResult = cursor.getString(cursor.getColumnIndex("product_description"));
            double product_weightResult = cursor.getDouble(cursor.getColumnIndex("product_weight"));
            String product_brandResult = cursor.getString(cursor.getColumnIndex("product_brand"));
            String product_typeResult = cursor.getString(cursor.getColumnIndex("product_type"));
            int product_imageResult = cursor.getInt(cursor.getColumnIndex("product_image"));
            //GET SEMUA DATA DARI DATABASE

            product = new Product(idResult,product_nameResult,product_priceResult,product_ratingResult,product_descriptionResult,product_weightResult,product_brandResult,product_typeResult, product_imageResult);
        }
        cursor.close();
        db.close();
        helper.close();
        return product;
    }

    public Vector<Product> getallproduct(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM product", null);
        cursor.moveToFirst();

        Vector<Product> listProduct = null;
        if (cursor.getCount() > 0){
            listProduct = new Vector<>();
            while (!cursor.isAfterLast()){
                //GET SEMUA DATA DARI DATABASE
                int idResult = cursor.getInt(cursor.getColumnIndex("product_id"));
                String product_nameResult = cursor.getString(cursor.getColumnIndex("product_name"));
                int  product_priceResult = cursor.getInt(cursor.getColumnIndex("product_price"));
                double product_ratingResult = cursor.getDouble(cursor.getColumnIndex("product_rating"));
                String product_descriptionResult = cursor.getString(cursor.getColumnIndex("product_description"));
                double product_weightResult = cursor.getDouble(cursor.getColumnIndex("product_weight"));
                String product_brandResult = cursor.getString(cursor.getColumnIndex("product_brand"));
                String product_typeResult = cursor.getString(cursor.getColumnIndex("product_type"));
                int product_imageResult = cursor.getInt(cursor.getColumnIndex("product_image"));
                //GET SEMUA DATA DARI DATABASE

                listProduct.add(new Product(idResult,product_nameResult,product_priceResult,product_ratingResult,product_descriptionResult,product_weightResult,product_brandResult,product_typeResult, product_imageResult));
                cursor.moveToNext();
            }

        }
        cursor.close();
        db.close();
        helper.close();
        return listProduct;
    }

}
