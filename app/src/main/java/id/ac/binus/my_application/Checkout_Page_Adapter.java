package id.ac.binus.my_application;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

public class Checkout_Page_Adapter extends RecyclerView.Adapter<Checkout_Page_Adapter.ViewHolder>{

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

    public Checkout_Page_Adapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Checkout_Page_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.checkout_page_item, parent,false);
        return new Checkout_Page_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Checkout_Page_Adapter.ViewHolder holder, int position) {

        Locale locale_id = new Locale("in", "ID");
        NumberFormat formatrupiah = NumberFormat.getCurrencyInstance(locale_id);

        Cart_Helper cart_helper = new Cart_Helper(ctx);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        int user_id = sp.getInt("user_id", -1);

        int total_price_per_jenis_item = product_cart_vector.get(position).getProduct_price() * product_cart_vector.get(position).getProduct_qty();

        //set data
        holder.prod_image_iv.setImageResource(product_cart_vector.get(position).getProduct_image());
        holder.prod_name_txt.setText(product_cart_vector.get(position).getProduct_name());
        holder.prod_qty_txt.setText(String.valueOf("Qty: " + product_cart_vector.get(position).getProduct_qty()));
        holder.prod_total_price_txt.setText(formatrupiah.format(Double.parseDouble(String.valueOf(total_price_per_jenis_item))) + ",-");
    }

    @Override
    public int getItemCount() {
        if (product_cart_vector != null) {
            return product_cart_vector.size();
        }
        return  0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView prod_image_iv;
        TextView prod_name_txt, prod_qty_txt, prod_total_price_txt;
        CardView checkout_page_cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //get component
            prod_image_iv = itemView.findViewById(R.id.prod_image_iv);
            prod_name_txt = itemView.findViewById(R.id.prod_name_txt);
            prod_qty_txt = itemView.findViewById(R.id.prod_qty_txt);
            prod_total_price_txt = itemView.findViewById(R.id.prod_total_price_txt);
            checkout_page_cv = itemView.findViewById(R.id.checkout_page_cv);
        }
    }
}
