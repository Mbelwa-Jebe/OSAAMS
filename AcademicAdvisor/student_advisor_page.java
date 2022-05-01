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

import Adapter.Advisor_adapter;
import Model.Advisor;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static controller.ap_controller.getInstance;

public class Student_AdvisorPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter.Advisor_adapter advisor_adapter;
    private List<Advisor> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_page);

        recyclerView = (RecyclerView)findViewById(R.id.ad_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(Student_AdvisorPage.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        getAdvisors();

    }

    private void getAdvisors() {
        list = new ArrayList<>();
        String advisor_url = "http://192.168.137.1:88/AcademicAdvisor/get_advisors.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, advisor_url
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray adv_array = response.getJSONArray("advisors");
                    for (int i=0;i<adv_array.length();i++){
                        JSONObject advObject = adv_array.getJSONObject(i);
                        Advisor advisor = new Advisor();
                        advisor.setAdvisor_fname(advObject.getString("advisor_fname"));
                        advisor.setAdvisor_lname(advObject.getString("advisor_lname"));
                        advisor.setAdvisor_id(advObject.getString("advisor_id"));
                        advisor.setAdvisor_office(advObject.getString("advisor_office"));
                        advisor.setAdvisor_rank(advObject.getString("advisor_rank"));
                       // if(registration_no.equals(appoint.getStudent_id())){
                        //    list.add(advisor);
                       // }
                        list.add(advisor);

                    }
                    advisor_adapter = new Advisor_adapter(Student_AdvisorPage.this,list);
                    recyclerView.setAdapter(advisor_adapter);



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