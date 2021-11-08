package id.ac.binus.my_application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context ctx;

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    int pictureID;

    public void setProduct_vector(Vector<Product> product_vector) {
        this.product_vector = product_vector;
    }

    Vector<Product> product_vector;

    public ProductAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.home_page_item, parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        Locale locale_id = new Locale("in", "ID");
        NumberFormat formatrupiah = NumberFormat.getCurrencyInstance(locale_id);

        //set data
        holder.prod_image_iv.setImageResource(product_vector.get(position).getProduct_image());
        holder.prod_name_txt.setText(product_vector.get(position).getProduct_name());
        holder.prod_rate_txt.setText("Rating: " +  String.valueOf(product_vector.get(position).getProduct_rating()));
        holder.prod_price_txt.setText("Price: " + formatrupiah.format(Double.parseDouble(String.valueOf(product_vector.get(position).getProduct_price()))) + ",-");

        //CART
        holder.cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Cart_Page.class);
                intent.putExtra("prod_image_iv", product_vector.get(position).getProduct_image());
                intent.putExtra("prod_name_txt", product_vector.get(position).getProduct_name());
                intent.putExtra("prod_rate_txt", product_vector.get(position).getProduct_rating());
                intent.putExtra("prod_price_txt", product_vector.get(position).getProduct_price());
                ctx.startActivity(intent);
            }
        });

        holder.cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
                SharedPreferences.Editor speditor = PreferenceManager.getDefaultSharedPreferences(ctx).edit();

                Cart_Helper cart_helper = new Cart_Helper(ctx); // CART HELPER
                Vector <Product_Cart> listproduct_cart = cart_helper.getAllProductCart(sp.getInt("user_id", -1)); //VECTOR PRODUCT CART

                int product_id = product_vector.get(position).getProduct_id();
                int user_id = sp.getInt("user_id", -1);

                //Product Pertama Kali Sekali DIINSERT
                if (listproduct_cart == null){
                    cart_helper.insertProduct_to_Cart(user_id,product_id,1);
                }

                else{
                    int sign = 0;
                    listproduct_cart = cart_helper.getAllProductCart(sp.getInt("user_id", -1)); //VECTOR PRODUCT CART

                    //Cek apakah user baru pertama kali menambahakan item ke cart
                    for (int i = 0; i < listproduct_cart.size(); i++){
                        //UPDATE QTY
                        if (product_id == listproduct_cart.get(i).getProduct_id()){
                            Product_Cart product_cart = cart_helper.get_product_cart(user_id, product_id);
                            int item_qty = product_cart.getProduct_qty() + 1;
                            cart_helper.updateProduct_Cart(user_id,product_id,item_qty);
                            sign = 1;
                            break;
                        }

                    }

                    //INSERT FIRST TIME (QTY PASTI 1)
                    if (sign == 0){
                        cart_helper.insertProduct_to_Cart(user_id,product_id,1);
                    }
                }

                Intent intent = new Intent(ctx, Cart_Page.class);
                intent.putExtra("prod_id", product_vector.get(position).getProduct_id());
                intent.putExtra("prod_image_iv", product_vector.get(position).getProduct_image());
                intent.putExtra("prod_name_txt", product_vector.get(position).getProduct_name());
                intent.putExtra("prod_rate_txt", product_vector.get(position).getProduct_rating());
                intent.putExtra("prod_price_txt", product_vector.get(position).getProduct_price());
                intent.putExtra("user_id", sp.getInt("user_id", -1));
                speditor.putInt("product_id", product_vector.get(position).getProduct_id());
                listproduct_cart = cart_helper.getAllProductCart(sp.getInt("user_id", -1)); //VECTOR PRODUCT CART
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_vector.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView prod_image_iv;
        TextView prod_name_txt, prod_rate_txt, prod_price_txt, product_price_txt;
        CardView homepage_cv;
        Button cart_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //get component
            prod_image_iv = itemView.findViewById(R.id.prod_image_iv);
            prod_name_txt = itemView.findViewById(R.id.prod_name_txt);
            prod_rate_txt = itemView.findViewById(R.id.prod_rate_txt);
            prod_price_txt = itemView.findViewById(R.id.prod_price_txt);
            homepage_cv = itemView.findViewById(R.id.homepage_cv);
            cart_btn = itemView.findViewById(R.id.cart_btn);
        }
    }
}
