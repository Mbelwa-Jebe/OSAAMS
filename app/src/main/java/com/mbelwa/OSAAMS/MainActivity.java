package com.mbelwa.OSAAMS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mbelwa.OSAAMS.models.URL;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOGIN_URL = URL.LOGIN_URL;
    public static  final String KEY_REGNO="registration_no";
    public static final String KEY_PASSWORD="password";
    EditText etName, etPassword;
    Button btlogin;
    TextView advisorLogin;
    private String registration_no;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("j","j");
        Log.v("j","j");

        etName = findViewById(R.id.studentuname);
        etPassword = findViewById(R.id.studentpass);
        btlogin = findViewById(R.id.studentLogin);
        btlogin.setOnClickListener(this);
//        advisorLogin = findViewById(R.id.advisorText);

        // user online_user = new user(etName.getText());

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration_no = etName.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if (registration_no.length() == 0  ) {
                    etName.setError("Fill your Credentials first");
                }
                else if (password.length() == 0){
                    etPassword.setError("Enter your password first");
                }

                else {


                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.trim().equals("student")) {
                                    openProfile();

                                } else if (response.trim().equals("advisor")) {
                                    openProfile2();
                                } else {
                                    etName.setError("Enter correct Username");
                                    etPassword.setError("Enter correct Password");
                                   // Toast.makeText(MainActivity.this, "check your userneme or password", Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put(KEY_REGNO, registration_no);
                        map.put(KEY_PASSWORD, password);
                        return map;
                    }

                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);
            }
            }
        });

//        advisorLogin.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "advisor login test", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(MainActivity.this, Advisor_Login.class);
//                MainActivity.this.startActivity(intent);
//
//            }
//        });



    }


    public void openProfile(){
        // Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_LONG).show();


        Intent intent = new Intent(MainActivity.this, StudentMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_REGNO",registration_no);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    public void openProfile2(){
        // Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_LONG).show();


        Intent intent = new Intent(MainActivity.this, AdvisorMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_REGNO",registration_no);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }


    @Override
    public void onClick(View v) {

    }

    public String MD5(String md5){
        try{

            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i< array.length; ++i){
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        }
        catch (java.security.NoSuchAlgorithmException e){
        }
        return null;
    }
}