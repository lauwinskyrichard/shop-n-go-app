package id.ac.binus.my_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Vector;


public class HomePage extends AppCompatActivity {

    RecyclerView home_page_cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        home_page_cv = findViewById(R.id.home_page_cv);

        int temp_1 = getResources().getIdentifier("log001", "drawable", this.getPackageName());
        int temp_2 = getResources().getIdentifier("phil001", "drawable", this.getPackageName());
        int temp_3 = getResources().getIdentifier("sony001", "drawable", this.getPackageName());
        int temp_4 = getResources().getIdentifier("lg002", "drawable", this.getPackageName());
        int temp_5 = getResources().getIdentifier("phil002", "drawable", this.getPackageName());

        Product_Helper product_helper = new Product_Helper(getApplicationContext());
        Vector<Product> product_list = product_helper.getallproduct();

        if (product_list == null){
            product_helper.insertProduct("Web Cam C922 Pro", 2569000, 0.0, "Terhubung dengan kejernihan superior setiap kali Anda aktif di saluran seperti Twitch dan YouTube. Streaming apa pun pilihan Anda dalam Full 1080p pada 30 fps atau HD 720p yang super cepat pada 60 fps. Menayangkan dengan mantap menggunakan audio no-drop yang handal, autofocus, dan bidang pandang 78 derajat. Disertai dengan lisensi XSplit premium 3 bulan.", 1.0, "Logitech", "C922 Pro", temp_1);
            product_helper.insertProduct("ELECTRIC SHAVER AQUATOUCH", 619000, 0.0, "Designed to protect against nicks and cuts.", 1.0, "Philips", "S1030/04",temp_2);
            product_helper.insertProduct("OLED Smart TV 85 INCH", 51000000, 0.0, "Vivid, colourful pictures with sound that surrounds you. See how real entertainment becomes when you combine the colour, contrast and clarity of 4K1 with immersive multidimensional sound. All framed in a luxurious design.", 10.0, "Sony", "KD85X8000H", temp_3);
            product_helper.insertProduct("Washing Machine Front Loading", 18000000, 0.0, "Mesin Cuci LG 15kg dengan pengering 8kg, AI DD dan TurboWash, ThinQ dengan WiFi.", 30.0, "LG", "F2515RTGV", temp_4);
            product_helper.insertProduct("Air Fryer Essential", 2669000, 0.0, "Menggoreng sehat dengan teknologi Rapid Air.", 6.0, "Philips", "HD9270/90", temp_5);
        }

        product_list = product_helper.getallproduct();

        //Setting Recylcer View
        ProductAdapter productAdapter = new ProductAdapter(this);
        productAdapter.notifyDataSetChanged();
        productAdapter.setProduct_vector(product_list);
        productAdapter.notifyDataSetChanged();

        home_page_cv.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
        home_page_cv.setLayoutManager(new LinearLayoutManager(this));
        //Setting Recycler View

        BottomNavigationView bottomNav = findViewById(R.id.bNav);
        bottomNav.setSelectedItemId(R.id.navHome);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navCart:
                        Intent intent = new Intent(HomePage.this, Cart_Page.class);
                        startActivity(intent);
                        finish();
                        HomePage.this.overridePendingTransition(0, 0);
                        return true;

                    case R.id.navHome:
                        return true;

                    case R.id.navAcc:
                        Intent intent2 = new Intent(HomePage.this, Profile_Page.class);
                        startActivity(intent2);
                        finish();
                        HomePage.this.overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}