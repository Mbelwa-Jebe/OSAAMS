package com.mbelwa.OSAAMS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.mbelwa.OSAAMS.R;

import java.util.HashMap;
import java.util.Map;

public class Advisor_Login extends AppCompatActivity {


    public static final String LOGIN_URL = "http://192.168.137.1:88/AcademicAdvisor/loginadvisor.php";
    public static final String KEY_ADVISOR_ID = "advisor_id";
    public static final String KEY_PASSWORD = "password";
    EditText etName, etPassword;
    Button btlogin;
    TextView advisorLogin;
    public String advisor_id;
    public String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor__login);

        etName = findViewById(R.id.studentuname);
        etPassword = findViewById(R.id.studentpass);
        btlogin = findViewById(R.id.studentlogin);

        advisorLogin = findViewById(R.id.advisorText);

        advisorLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "advisor login test", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Advisor_Login.this, MainActivity.class);
                Advisor_Login.this.startActivity(intent);

            }
        });

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                advisor_id = etName.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.trim().equals("success")) {
                                    openProfile();

                                } else {
                                    Toast.makeText(Advisor_Login.this, response, Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Advisor_Login.this, "error", Toast.LENGTH_LONG).show();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put(KEY_ADVISOR_ID, advisor_id);
                        map.put(KEY_PASSWORD, password);
                        return map;
                    }

                };
                RequestQueue requestQueue = Volley.newRequestQueue(Advisor_Login.this);
                requestQueue.add(stringRequest);

            }
        });


    }
    public void openProfile () {
        // Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_LONG).show();

         Intent intent = new Intent(Advisor_Login.this, AdvisorMainActivity.class);
        // intent.putExtra(KEY_ADVISOR_ID, advisor_id);
        Bundle bundle = new Bundle();
        bundle.putString("KEY_REGNO",advisor_id);
         intent.putExtras(bundle);
        Advisor_Login.this.startActivity(intent);
    }

}