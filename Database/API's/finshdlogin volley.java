package com.example.fyptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.fyptest.URLs.*;

public class MainActivity extends AppCompatActivity {
    public static final String LOGIN_URL = "http://192.168.137.1:88/AcademicAdvisor/loginapi.php";
    public static  final String KEY_REGNO="registration_no";
    public static final String KEY_PASSWORD="password";
    EditText etName, etPassword;
    Button btlogin;
    private String registration_no;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.studentuname);
        etPassword = findViewById(R.id.studentpass);
        btlogin = findViewById(R.id.studentlogin);
        btlogin.setOnClickListener((View.OnClickListener) this);

    }

    public void login(View view) {
        registration_no = etName.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success"))
                        {
                           openProfile();
                        }else{
                            Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        }

                    }
                },
            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
                }

    }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> map = new HashMap<>();
                map.put(KEY_REGNO,registration_no);
                map.put(KEY_PASSWORD,password);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void openProfile(){
        Intent intent = new Intent(this,StudentDashboard.class);
        intent.putExtra(KEY_REGNO,registration_no);
    }


}