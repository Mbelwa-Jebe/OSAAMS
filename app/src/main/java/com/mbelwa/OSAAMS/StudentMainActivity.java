package com.mbelwa.OSAAMS;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class StudentMainActivity extends AppCompatActivity {

    private static final float END_SCALE = 0.85f;
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavView;
    private CoordinatorLayout contentView;
    public String registration_no;
    private TextView student_logged_in;
    private MenuView.ItemView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_main);

        Bundle bundle = getIntent().getExtras();
        // String reg_no;
        registration_no= bundle.getString("KEY_REGNO");
        String user = "1234";
       // student_logged_in = (TextView) findViewById(R.id.student_logged_in);
      //  student_logged_in.setText("hello");
      //  View header = navigationView.getHeaderView(0);
      //  TextView student_logged_in = (TextView) header.findViewById(R.id.student_logged_in);
       // student_logged_in.setText(registration_no);
      //NavigationView navigationView = findViewById(R.id.student_nav_header_main_layout);



        initToolbar();
       // initFab();
        initNavigation();
    }

    public String getRegno(){
        return registration_no;
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.student_toolbar);
        setSupportActionBar(toolbar);

    }

    private void initFab() {

        FloatingActionButton fab = findViewById(R.id.student_ap_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void initNavigation() {

        drawer = findViewById(R.id.student_drawer_layout);
        navigationView = findViewById(R.id.student_nav_view);
        bottomNavView = findViewById(R.id.student_bottom_nav_view);
        contentView = findViewById(R.id.student_content_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.student_nav_home, R.id.student_nav_advisor, R.id.student_nav_chats,
                R.id.student_nav_appointments, R.id.student_nav_consultations, R.id.student_nav_faqs,
                R.id.student_bottom_home, R.id.student_bottom_dashboard, R.id.student_bottom_notifications)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.student_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavView, navController);

       // logout = (MenuView.ItemView) findViewById(R.id.student_nav_logout);



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
       // getMenuInflater().inflate(R.menu.student_main, menu);
        MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.student_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Log.e("menu","menu");
        Log.v("menu","menu");
        switch (item.getItemId()){
            case R.id.student_logout:
                Toast.makeText(StudentMainActivity.this, "log_out", Toast.LENGTH_LONG).show();
                Intent intent8 = new Intent(StudentMainActivity.this, MainActivity.class);
                StudentMainActivity.this.startActivity(intent8);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.student_nav_host_fragment);
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


}