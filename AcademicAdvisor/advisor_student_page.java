package com.example.fyptest;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.Student_adapter;
import Model.Student;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static controller.ap_controller.getInstance;

public class Advisor_students_Page extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter.Student_adapter student_adapter;
    private List<Student> list;
    public String registration_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_students__page);

        recyclerView = (RecyclerView)findViewById(R.id.st_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(Advisor_students_Page.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        Bundle bundle = getIntent().getExtras();
        // String reg_no;
        registration_no= bundle.getString("KEY_REGNO");

        getStudents();
    }

    private void getStudents() {
        list = new ArrayList<>();
        String student_url = "http://192.168.137.1:88/AcademicAdvisor/get_students.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, student_url
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray std_array = response.getJSONArray("students");
                    for (int i=0;i < std_array.length();i++){
                        JSONObject stdObject = std_array.getJSONObject(i);
                        Student student = new Student();
                        student.setRegistration_no(stdObject.getString("registration_no"));
                        student.setStudent_fname(stdObject.getString("student_fname"));
                        student.setStudent_lname(stdObject.getString("student_lname"));
                        student.setProgramme(stdObject.getString("programme"));
                        student.setAdvisor_id(stdObject.getString("advisor_id"));
                       // if(registration_no.equals(student.getAdvisor_id())){
                          //      list.add(student);
                           // }
                        list.add(student);

                    }


                    student_adapter = new Student_adapter(Advisor_students_Page.this,list);
                    recyclerView.setAdapter(student_adapter);

                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        getInstance().addToRequestQueue(jsonObjectRequest);


    }
}