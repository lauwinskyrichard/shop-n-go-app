package id.ac.binus.my_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

public class Checkout_Page extends AppCompatActivity {

    RecyclerView checkout_page_cv;
    TextView subtotal_price;
    Button checkout_btn;
    ImageButton back_btn, home_btn;
    int subtotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout__page);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        checkout_page_cv = findViewById(R.id.checkout_page_cv);
        back_btn = findViewById(R.id.back_btn);
        home_btn = findViewById(R.id.home_btn);
        checkout_btn = findViewById(R.id.checkout_btn);
        subtotal_price = findViewById(R.id.subtotal_price);

        int user_id = sp.getInt("user_id", -1);
        Cart_Helper cart_helper = new Cart_Helper(Checkout_Page.this);

        Vector<Product_Cart> all_product_cart = cart_helper.getAllProductCart(user_id);

        //Setting Recylcer View
        Checkout_Page_Adapter checkout_page_adapter = new Checkout_Page_Adapter(Checkout_Page.this);
        checkout_page_adapter.setProduct_vector(all_product_cart);

        checkout_page_cv.setAdapter(checkout_page_adapter);
        checkout_page_cv.setLayoutManager(new LinearLayoutManager(Checkout_Page.this));
        //Setting Recycler View

        Locale locale_id = new Locale("in", "ID");
        NumberFormat formatrupiah = NumberFormat.getCurrencyInstance(locale_id);

        //SUBTOTAL
        if (all_product_cart != null) {
            for (int i = 0; i < all_product_cart.size(); i++){
                subtotal = subtotal + (all_product_cart.get(i).getProduct_qty() * all_product_cart.get(i).getProduct_price());
            }
            subtotal_price.setText(formatrupiah.format(Double.parseDouble(String.valueOf(subtotal))) + ",-");
            SharedPreferences.Editor speditor = PreferenceManager.getDefaultSharedPreferences(Checkout_Page.this).edit();
            speditor.putInt("subtotal", subtotal);
        }
        else {
            Toast.makeText(this, "Haven't Buy Anything", Toast.LENGTH_SHORT).show();
        }

        //SUBTOTAL

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Checkout_Page.this, Cart_Page.class);
                startActivity(intent);
                finish();
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Checkout_Page.this, HomePage.class);
                startActivityForResult(intent, 1);
                finish();
            }
        });

        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton copy_btn;
                Button ok;
                TextView virtual_number;
                User_Helper user_helper = new User_Helper(getApplicationContext());
                String username = sp.getString("username", null);
                String password = sp.getString("password", null);
                User user = user_helper.getUser(username,password);
                AlertDialog.Builder builder = new AlertDialog.Builder(Checkout_Page.this);
                View view = getLayoutInflater().inflate(R.layout.pop_up_virtualacc, null);

                copy_btn = view.findViewById(R.id. copy_btn);
                ok = view.findViewById(R.id. ok_btn);
                virtual_number = view.findViewById(R.id. virtual_number);

                builder.setView(view);
                builder.create();
                builder.show();

                copy_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) Checkout_Page.this.getSystemService(Checkout_Page.this.CLIPBOARD_SERVICE);
                            clipboard.setText(virtual_number.getText().toString());
                        } else {
                            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) Checkout_Page.this.getSystemService(Checkout_Page.this.CLIPBOARD_SERVICE);
                            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", virtual_number.getText().toString());
                            clipboard.setPrimaryClip(clip);
                        }
                        Toast.makeText(Checkout_Page.this, "Copied", Toast.LENGTH_SHORT).show();
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Checkout_Page.this, Transaction_Proof_Page.class);
                        intent.putExtra("subtotal", subtotal);
                        startActivity(intent);
                        finish();
                    }
                });


            }
        });
    }
}