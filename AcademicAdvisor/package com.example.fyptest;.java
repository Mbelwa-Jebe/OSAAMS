package com.example.fyptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.fyptest.URLs.*;

public class MainActivity extends AppCompatActivity{
    EditText etName, etPassword;
    Button btlogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
        finish();
        startActivity(new Intent(this,StudentDashboard.class));
        }

        etName=findViewById(R.id.studentuname);
        etPassword=findViewById(R.id.studentpass);
        progressBar=findViewById(R.id.progressBar);

        //calling login method
        btlogin.setOnClickListener(new View.OnClickListener(){

@Override
public void onClick(View v){
        userLogin();
        }
        });
        }
    private void userLogin() {
        //getting the data
        final String username = etName.getText().toString();
        final String password = etPassword.getText().toString();
        //validate inputs

        if (TextUtils.isEmpty(username)) {
            etName.setError("Enter your username first");
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter your password first");
            etPassword.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
        progressBar.setVisibility(View.GONE);

        try{

        //convert response to json object
        JSONObject obj=new JSONObject(response);

        //if response is ok
        if(!obj.getBoolean("error")){
        Toast.makeText(getApplicationContext(),obj.getString("registration_no"),Toast.LENGTH_SHORT).show();

        //getting student from response
        JSONObject userjson = obj.getJSONObject("registration_no");

        //create new user object

        User registration_no = new User(
        userjson.getString("registration_no"),
        userjson.getString("student_fname"),
        userjson.getString("student_lname"),
        userjson.getString("programme"),
        userjson.getString("advisor_id")
        );



        //store user in shared preferences

        SharedPrefManager.getInstance(getApplicationContext()).userLogin(registration_no);
        finish();
        startActivity(new Intent(getApplicationContext(),StudentDashboard.class));
        } else {
        Toast.makeText(getApplicationContext(), obj.getString("registration_no"), Toast.LENGTH_SHORT).show();

            }
         }
            catch(JSONException e){
            e.printStackTrace();
                }
             }
        },

                error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show())

        {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("registration_no", username);
                params.put("password", password);
                return params;
            }
            };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}