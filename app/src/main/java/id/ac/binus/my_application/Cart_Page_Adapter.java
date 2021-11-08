package id.ac.binus.my_application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

public class Cart_Page_Adapter extends RecyclerView.Adapter<Cart_Page_Adapter.ViewHolder>{


    Context ctx;
    int pictureID;
    Vector<Product_Cart> product_cart_vector;

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    Cart_Helper cart_helper = new Cart_Helper(ctx);

    public void setProduct_vector(Vector<Product_Cart> product_cart_vector) {
        this.product_cart_vector = product_cart_vector;
    }

    public Cart_Page_Adapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Cart_Page_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.cart_page_item, parent,false);
        return new Cart_Page_Adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Cart_Page_Adapter.ViewHolder holder, int position) {

        Locale locale_id = new Locale("in", "ID");
        NumberFormat formatrupiah = NumberFormat.getCurrencyInstance(locale_id);

        Cart_Helper cart_helper = new Cart_Helper(ctx);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        int user_id = sp.getInt("user_id", -1);

        //set data
        holder.prod_image_iv.setImageResource(product_cart_vector.get(position).getProduct_image());
        holder.prod_name_txt.setText(product_cart_vector.get(position).getProduct_name());
        holder.prod_rate_txt.setText("Rating: " +  String.valueOf(product_cart_vector.get(position).getProduct_rating()));
        holder.prod_price_txt.setText("Price: " + formatrupiah.format(Double.parseDouble(String.valueOf(product_cart_vector.get(position).getProduct_price()))) + ",-");
        holder.jumlah_item.setText(String.valueOf(product_cart_vector.get(position).getProduct_qty()));
        int jumlahItem = Integer.parseInt(holder.jumlah_item.getText().toString());

        //MINUS
        holder.min_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (jumlahItem == 1){
                    Toast.makeText(ctx, "Minimal Buy Item is 1", Toast.LENGTH_SHORT).show();
                }

                else{
                    int product_id_plus = product_cart_vector.get(position).getProduct_id(); // Ambil id dari produk yang dipilih (FROM HOME TO CART DIRECT)
                    Product_Cart product_cart = cart_helper.get_product_cart(user_id,product_id_plus); // GET PRODUKNYA
                    int item_qty = product_cart.getProduct_qty() - 1; //KURANG QTY NYA
                    cart_helper.updateProduct_Cart(user_id,product_id_plus,item_qty); //UPDATE KE DB

                    product_cart = cart_helper.get_product_cart(user_id,product_id_plus);
                    product_cart_vector.get(position).setProduct_qty(product_cart.getProduct_qty());
                    notifyDataSetChanged();
                    holder.jumlah_item.setText(String.valueOf(product_cart.getProduct_qty()));
                }
            }
        });
        //MINUS

        //PLUS
        holder.plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int product_id_plus = product_cart_vector.get(position).getProduct_id(); // Ambil id dari produk yang dipilih (FROM HOME TO CART DIRECT)
                Product_Cart product_cart = cart_helper.get_product_cart(user_id,product_id_plus); // GET PRODUKNYA
                int item_qty = product_cart.getProduct_qty() + 1; //KURANG QTY NYA
                cart_helper.updateProduct_Cart(user_id,product_id_plus,item_qty); //UPDATE KE DB

                product_cart = cart_helper.get_product_cart(user_id,product_id_plus);
                product_cart_vector.get(position).setProduct_qty(product_cart.getProduct_qty());
                notifyDataSetChanged();
                holder.jumlah_item.setText(String.valueOf(product_cart.getProduct_qty()));
            }
        });
        //PLUS
    }

    @Override
    public int getItemCount() {
        return product_cart_vector.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView prod_image_iv;
        TextView prod_name_txt, prod_rate_txt, prod_price_txt, product_price_txt;
        EditText jumlah_item;
        CardView cart_page_cv;
        ImageButton min_btn, plus_btn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //get component
            prod_image_iv = itemView.findViewById(R.id.prod_image_iv);
            prod_name_txt = itemView.findViewById(R.id.prod_name_txt);
            prod_rate_txt = itemView.findViewById(R.id.prod_rate_txt);
            prod_price_txt = itemView.findViewById(R.id.prod_price_txt);
            cart_page_cv = itemView.findViewById(R.id.cart_page_cv);
            min_btn = itemView.findViewById(R.id.min_btn);
            plus_btn = itemView.findViewById(R.id.plus_btn);
            jumlah_item = itemView.findViewById(R.id.jumlah_item);
        }
    }
}