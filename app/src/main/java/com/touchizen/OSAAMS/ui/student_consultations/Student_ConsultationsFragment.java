package com.touchizen.OSAAMS.ui.student_consultations;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.touchizen.OSAAMS.R;
import com.touchizen.OSAAMS.StudentMainActivity;
import com.touchizen.OSAAMS.adapters.Report_adapter_student;
import com.touchizen.OSAAMS.models.Report;

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

public class Student_ConsultationsFragment extends Fragment {

    private Student_consultationsViewModel consultationsViewModel;

    private RecyclerView recyclerView;
    private Report_adapter_student student_report_adapter;
    private List<Report> list;
    public String registration_no;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consultationsViewModel =
                ViewModelProviders.of(this).get(Student_consultationsViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_consultations, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        consultationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        recyclerView = (RecyclerView) root.findViewById(R.id.student_report_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        StudentMainActivity studentMainActivity = (StudentMainActivity) getActivity();
        registration_no = studentMainActivity.getRegno();


        getReports();

//        FloatingActionButton fab = root.findViewById(R.id.student_ap_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//               // createPopupDialog();
//
//            }
//        });


        return root;
    }

    private void getReports() {
        list = new ArrayList<>();

        String  get_report_url = "http://192.168.137.1:88/AcademicAdvisor/get_reports.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, get_report_url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray report_arr = response.getJSONArray("reports");
                    for (int i =0;i < report_arr.length();i++){
                        JSONObject reportObj = report_arr.getJSONObject(i);

                        Report report = new Report();
                        report.setReport_date(reportObj.getString("report_date"));
                        report.setReport(reportObj.getString("report"));
                        report.setAdvisor_lname(reportObj.getString("advisor_lname"));
                        report.setAdvisor_fname(reportObj.getString("advisor_fname"));
                        report.setStudent_lname(reportObj.getString("student_lname"));
                        report.setStudent_fname(reportObj.getString("student_fname"));
                        report.setStudent_id(reportObj.getString("student_id"));
                        report.setAdvisor_id(reportObj.getString("advisor_id"));
                        report.setConsultation_id(reportObj.getString("consultation_id"));

                        if(registration_no.equals(report.getStudent_id())){
                            list.add(report);
                        }
                    }

                    student_report_adapter = new Report_adapter_student(getContext(),list);
                    recyclerView.setAdapter(student_report_adapter);

                } catch (JSONException e) {
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