package id.ac.binus.my_application;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.text.InputType.TYPE_CLASS_PHONE;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
import static android.text.InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD;

public class Profile_Page extends AppCompatActivity {

    TextView username_tv, email_tv, phone_number_tv, DoB_tv, address_tv, password_tv;
    ImageView edit_email_iv, edit_phone_number_iv, edit_DoB_iv, edit_address_iv, edit_username_iv, edit_password_iv;
    Button logout_btn;
    EditText edit_username, edit_email, edit_phone_number, edit_DoB, edit_address, edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__page);
        username_tv = findViewById(R.id.username_tv);
        email_tv = findViewById(R.id.email_tv);
        phone_number_tv = findViewById(R.id.phone_number_tv);
        DoB_tv = findViewById(R.id.DoB_tv);
        address_tv = findViewById(R.id.address_tv);
        edit_email_iv = findViewById(R.id.edit_email_iv);
        edit_phone_number_iv = findViewById(R.id.edit_phone_number_iv);
        edit_DoB_iv = findViewById(R.id.edit_DoB_iv);
        edit_address_iv = findViewById(R.id.edit_address_iv);
        logout_btn = findViewById(R.id.logout_btn);
        edit_username_iv = findViewById(R.id.edit_username_iv);
        password_tv = findViewById(R.id.password_tv);
        edit_password_iv = findViewById(R.id.edit_password_iv);

        User_Helper user_helper = new User_Helper(getApplicationContext());
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sp.getString("username", null);
        String password = sp.getString("password", null);
        User user = user_helper.getUser(username,password);

        username_tv.setText(user.getUsername());
        email_tv.setText(user.getEmail());
        phone_number_tv.setText(user.getPhone());
        DoB_tv.setText(user.getDob());
        address_tv.setText(user.getAlamat());
        password_tv.setText(user.getPassword());

        edit_username_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Page.this);
                View view = getLayoutInflater().inflate(R.layout.dialoque_edit_username,null);
                edit_username = view.findViewById(R.id.dialoque_edit_username);
                edit_username.setHint("Enter New Username");
                edit_username.setInputType(TYPE_CLASS_TEXT);
                builder.setView(view);
                builder.setTitle("Edit Username");
                builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String new_username = edit_username.getText().toString();
                        user_helper.updateUsername(username,password,new_username);
                        username_tv.setText(new_username);
                        Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        });

        edit_email_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Page.this);
                View view = getLayoutInflater().inflate(R.layout.dialoque_edit_username,null);
                edit_email = view.findViewById(R.id.dialoque_edit_username);
                edit_email.setHint("Enter New Email");
                edit_email.setInputType(TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                builder.setView(view);
                builder.setTitle("Edit Email");
                builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String new_email = edit_email.getText().toString();
                        user_helper.updateEmail(username,password,new_email);
                        email_tv.setText(new_email);
                        Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        });

        edit_phone_number_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Page.this);
                View view = getLayoutInflater().inflate(R.layout.dialoque_edit_username,null);
                edit_phone_number = view.findViewById(R.id.dialoque_edit_username);
                edit_phone_number.setHint("Enter New Phone Number");
                edit_phone_number.setInputType(TYPE_CLASS_PHONE);
                builder.setView(view);
                builder.setTitle("Edit Phone Number");
                builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String new_phone_number = edit_phone_number.getText().toString();
                        user_helper.updatePhone_number(username,password,new_phone_number);
                        phone_number_tv.setText(new_phone_number);
                        Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        });

        edit_DoB_iv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog;
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int  year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(Profile_Page.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String new_DoB = String.valueOf(dayOfMonth) + "/" + (String.valueOf(month + 1)) + "/" + String.valueOf(year);
                        user_helper.updateDoB(username,password,new_DoB);
                        DoB_tv.setText(new_DoB);
                        Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
                    }
                }, year, month,day);
                datePickerDialog.show();

            }
        });

        edit_address_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Page.this);
                View view = getLayoutInflater().inflate(R.layout.dialoque_edit_username,null);
                edit_address = view.findViewById(R.id.dialoque_edit_username);
                edit_address.setHint("Enter New Address");
                builder.setView(view);
                builder.setTitle("Edit Address");
                builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String new_address = edit_address.getText().toString();
                        user_helper.updateAddress(username,password,new_address);
                        address_tv.setText(new_address);
                        Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        });

        edit_password_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Page.this);
                View view = getLayoutInflater().inflate(R.layout.dialoque_edit_username,null);
                edit_password = view.findViewById(R.id.dialoque_edit_username);
                edit_password.setHint("Enter New Password");
                edit_password.setInputType(TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(view);
                builder.setTitle("Edit Password");
                builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String new_password = edit_password.getText().toString();
                        user_helper.updatePassword(username,password,new_password);
                        password_tv.setText(new_password);
                        Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bNav);
        bottomNav.setSelectedItemId(R.id.navAcc);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navHome:
                        Intent intent = new Intent(Profile_Page.this, HomePage.class);
                        startActivity(intent);
                        finish();
                        Profile_Page.this.overridePendingTransition(0, 0);
                        return true;

                    case R.id.navAcc:
                        return true;

                    case R.id.navCart:
                        Intent intent2 = new Intent(Profile_Page.this, Cart_Page.class);
                        startActivity(intent2);
                        finish();
                        Profile_Page.this.overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}