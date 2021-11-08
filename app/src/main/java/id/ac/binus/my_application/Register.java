package id.ac.binus.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class Register extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    EditText confirm_password;
    EditText phone_number, alamat;
    EditText dob;
    DatePickerDialog picker;
    Button btn_register;
    int huruf = 0;//gak ada huruf
    int angka = 0;//gak ada angka
    String DoB = "";
    String hari = "";
    String bulan = "";
    String tahun = "";
    static ArrayList<User> list_user = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        phone_number = findViewById(R.id.phone_number);
        dob = findViewById(R.id.date_of_birth);
        btn_register = findViewById(R.id.btn_register);
        alamat = findViewById(R.id.alamat);
        dob.setInputType(InputType.TYPE_NULL);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                picker = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameee = "";
                usernameee = username.getText().toString();
                String emailll = email.getText().toString();
                String pasworddd = password.getText().toString();
                String confirm_passworddd = confirm_password.getText().toString();
                String phone_numberrrr = phone_number.getText().toString();
                String alamattt = alamat.getText().toString();
                DoB = dob.getText().toString();

                for (int i = 0; i < pasworddd.length(); i++){
                    if ((pasworddd.charAt(i) >= 65 && pasworddd.charAt(i) <= 90) || (pasworddd.charAt(i) >= 97 && pasworddd.charAt(i) <= 122)){
                        huruf = 1;
                    }
                    if (pasworddd.charAt(i) >= 48 && pasworddd.charAt(i) <= 57){
                        angka = 1;
                    }
                }

                //Validasi Username
                if (usernameee.isEmpty()){
                    Toast.makeText(Register.this,"Username must be Filled", Toast.LENGTH_SHORT).show();
                }
                //Validasi Username

                //Validasi Email
                else if (emailll.isEmpty()){
                    Toast.makeText(Register.this,"Email must be Filled", Toast.LENGTH_SHORT).show();
                }

                else if(!emailll.contains("@") && emailll.contains(".")){
                    Toast.makeText(Register.this,"Email must contains '@' and '.'", Toast.LENGTH_SHORT).show();
                }
                //Validasi Email

                //Validasi Password
                else if (pasworddd.isEmpty()){
                    Toast.makeText(Register.this,"Password must be Filled", Toast.LENGTH_SHORT).show();
                }

                else if (angka == 0 && huruf == 0){
                    Toast.makeText(Register.this,"Password must contains alphabet and number", Toast.LENGTH_SHORT).show();
                }

                else if(pasworddd.length() < 8 || pasworddd.length() > 12){
                    Toast.makeText(Register.this,"Password must be 8 - 12 characters", Toast.LENGTH_SHORT).show();
                }
                //Validasi Password

                //Validasi Confirm Password
                else if(confirm_passworddd.isEmpty()){
                    Toast.makeText(Register.this,"Confirm Password must be Filled", Toast.LENGTH_SHORT).show();
                }

                else if (!confirm_passworddd.equals(pasworddd)){
                    Toast.makeText(Register.this,"Password doesn't Match", Toast.LENGTH_SHORT).show();
                }
                //Validasi Confirm Password

                //Validasi Alamat
                else if (alamattt.isEmpty()){
                    Toast.makeText(Register.this,"Address must be Filled", Toast.LENGTH_SHORT).show();
                }
                //Validasi Alamat

                //Validasi Phone Number
                else if(phone_numberrrr.isEmpty()){
                    Toast.makeText(Register.this,"Phone Number must be Filled", Toast.LENGTH_SHORT).show();
                }
                //Validasi Phone Number

                //Validasi DOB
                else if(DoB.isEmpty()){
                    Toast.makeText(Register.this,"Date of Birth must be Filled", Toast.LENGTH_SHORT).show();
                }
                //Validasi DOB

                //Validation Complete
                else {
                    User_Helper user_helper = new User_Helper(getApplicationContext());
                    user_helper.insertUser(emailll,usernameee,pasworddd,phone_numberrrr,0, DoB, alamattt);

//                    SharedPreferences.Editor sp = PreferenceManager.getDefaultSharedPreferences(Register.this).edit();
//                    sp.apply();

                    Toast.makeText(Register.this,"Register Success", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Register.this,Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}