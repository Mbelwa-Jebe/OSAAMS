package com.mbelwa.OSAAMS;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.URL;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AdvisorMainActivity extends AppCompatActivity {

    private static final float END_SCALE = 0.85f;
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavView;
    private CoordinatorLayout contentView;
    public String registration_no;

    public AlertDialog.Builder dialogBuilder;
    private AlertDialog dialogpassword,userManual;
    private EditText oldpass,newpass,confirmNewpass;
    private Button reset,cancel,done;

    public static  final String KEY_REGNO="registration_no";
    public static  final String KEY_OLD_PASSWORD="oldpass2";
    public static  final String KEY_PASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisor_activity_main);

        Bundle bundle = getIntent().getExtras();
        // String reg_no;
        registration_no= bundle.getString("KEY_REGNO");

        initToolbar();
       // initFab();
        initNavigation();
        //showBottomNavigation(false);
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.advisor_toolbar);
        setSupportActionBar(toolbar);

    }

//    private void initFab() {
//
//        FloatingActionButton fab = findViewById(R.id.advisor_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//    }

    private void initNavigation() {

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavView = findViewById(R.id.advisor_bottom_nav_view);
        contentView = findViewById(R.id.advisor_content_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.advisor_nav_home, R.id.advisor_nav_students, R.id.advisor_nav_chats,
                R.id.advisor_nav_appointments, R.id.advisor_nav_consultations, R.id.advisor_nav_faqs,
                R.id.advisor_bottom_home, R.id.advisor_bottom_dashboard, R.id.advisor_bottom_notifications)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.advisor_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavView, navController);


        animateNavigationDrawer();
    }


    private void animateNavigationDrawer() {
//        drawerLayout.setScrimColor(getResources().getColor(R.color.text_brown));
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.advisor_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Log.e("menu","menu");
        Log.v("menu","menu");
        switch (item.getItemId()){
            case R.id.ad_logout:
                Toast.makeText(AdvisorMainActivity.this, "log_out", Toast.LENGTH_LONG).show();
                Intent intent8 = new Intent(AdvisorMainActivity.this, MainActivity.class);
                AdvisorMainActivity.this.startActivity(intent8);
                finish();
                return true;

            case R.id.ad_reset_password:
                createResetPassoworddialog();

                return true;

//            case R.id.ad_user_manual:
//
//                return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.advisor_nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    public String getRegno(){
        return registration_no;
    }

    public void createResetPassoworddialog(){
        dialogBuilder = new AlertDialog.Builder(AdvisorMainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.reset_password_dialog,null);
        oldpass = (EditText) view.findViewById(R.id.old_password);
        newpass = (EditText) view.findViewById(R.id.new_password);
        confirmNewpass = (EditText) view.findViewById(R.id.confirm_password);
        reset = (Button) view.findViewById(R.id.psd_submit);
        cancel = (Button) view.findViewById(R.id.psd_cancel);
        dialogBuilder.setView(view);
        dialogpassword = dialogBuilder.create();
        dialogpassword.show();
        final List<String> errorList = new ArrayList<String>();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogpassword.dismiss();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }

            public void resetPassword() {
                final String old_pass = oldpass.getText().toString();
                final String pass = newpass.getText().toString();
                final String confirmPass = confirmNewpass.getText().toString();
                if (!isValid(pass, confirmPass, errorList)){
                    for (String error : errorList){
                        confirmNewpass.setError(error);
                    }

                }

                else {
                    // Upload to database here

                    String reset_pass = URL.RESET_PASS_ADVISOR;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, reset_pass,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.trim().equals("success")) {
                                        Toast.makeText(getApplicationContext(), "successful password changed", Toast.LENGTH_SHORT).show();
                                        dialogpassword.dismiss();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "not successful", Toast.LENGTH_SHORT).show();
                                        confirmNewpass.setError("Your old Password is incorrect");
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put(KEY_REGNO, registration_no);
                            map.put(KEY_PASSWORD, MD5(confirmPass));
                            map.put(KEY_OLD_PASSWORD, MD5(old_pass));
                            return map;
                        }

                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);


                }

            }

            public boolean isValid(String pass, String confirmPass, List<String> errorList) {
                Pattern specialCharPatten = Pattern.compile("[^a-z0-9]",Pattern.CASE_INSENSITIVE);
                Pattern upperCasePatten = Pattern.compile("[A-Z]");
                Pattern lowerCasePatten = Pattern.compile("[a-z]");
                Pattern digitCasePatten = Pattern.compile("[0-9]");
                errorList.clear();
                boolean flag = true;
                if (!pass.equals(confirmPass)){
                    errorList.add("Password and confirm password does not match");
                    flag = false;
                }

                if (!pass.equals(confirmPass)){
                    errorList.add("Password and confirm password does not match");
                    flag = false;
                }

                if (pass.length() < 8){
                    errorList.add("Password lenght must be at least 8 characters");
                    flag = false;
                }

                if (!specialCharPatten.matcher(pass).find()){
                    errorList.add("Password must at least have one special character");
                    flag = false;
                }

                if (!upperCasePatten.matcher(pass).find()){
                    errorList.add("Password must include uppercase letter");
                    flag = false;
                }

                if (!lowerCasePatten.matcher(pass).find()){
                    errorList.add("Password must include lowercase letter");
                    flag = false;
                }

                if (!digitCasePatten.matcher(pass).find()){
                    errorList.add("Password must include digit character");
                    flag = false;
                }
                return flag;
            }
        });
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
