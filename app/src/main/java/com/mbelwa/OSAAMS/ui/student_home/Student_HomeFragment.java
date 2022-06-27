package com.mbelwa.OSAAMS.ui.student_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.StudentMainActivity;
import com.mbelwa.OSAAMS.adapters.ap_adapter;
import com.mbelwa.OSAAMS.adapters.student_home_adapter;
import com.mbelwa.OSAAMS.models.Appointment;
import com.mbelwa.OSAAMS.models.Student_dashboard;
import com.mbelwa.OSAAMS.models.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Student_HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private com.mbelwa.OSAAMS.adapters.student_home_adapter student_home_adapter;
    private Student_HomeViewModel homeViewModel;
    private List<Student_dashboard> list;
    public String registration_no;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(Student_HomeViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_home, container, false);
       // final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        recyclerView = (RecyclerView) root.findViewById(R.id.student_home_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        StudentMainActivity studentMainActivity = (StudentMainActivity) getActivity();
        registration_no = studentMainActivity.getRegno();

        getStudentDashbord();
        return root;

    }

    public void getStudentDashbord(){

        list = new ArrayList<>();

        String  ap_url = URL.STUDENT_DASHBOARD_URL;
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ap_url,
                null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray apArray = response.getJSONArray("student_dash");
                    for (int i=0; i< apArray.length();i++){
                        JSONObject apObject = apArray.getJSONObject(i);

                        Student_dashboard student_dashboard = new Student_dashboard();
                        student_dashboard.setDash_advisor_fname(apObject.getString("advisor_fname"));
                        student_dashboard.setDash_advisor_lname(apObject.getString("advisor_lname"));
                        student_dashboard.setDash_appointments_received(apObject.getString("appointments_received"));
                        student_dashboard.setDash_consultations(apObject.getString("reports"));
                        student_dashboard.setDash_student_ID(apObject.getString("registration_no"));

                        if(registration_no.equals(student_dashboard.getDash_student_ID())){
                            list.add(student_dashboard);
                        }


                    }

                    student_home_adapter = new student_home_adapter(getContext(),list);
                    recyclerView.setAdapter(student_home_adapter);
                    student_home_adapter.notifyDataSetChanged();


                    // if (null != callBack)callBack.processFinish(appointments);
                } catch (JSONException e){
                    e.printStackTrace();
                }


            }





        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue2 = Volley.newRequestQueue(getContext());
        requestQueue2.add(jsonObjectRequest);
    }
}