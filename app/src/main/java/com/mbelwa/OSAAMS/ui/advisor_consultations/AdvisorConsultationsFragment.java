package com.mbelwa.OSAAMS.ui.advisor_consultations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mbelwa.OSAAMS.AdvisorMainActivity;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.adapters.Report_adapter_advisor;
import com.mbelwa.OSAAMS.models.Report;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdvisorConsultationsFragment extends Fragment {

    private AdvisorConsultationsViewModel advisorConsultationsViewModel;

    private EditText stdnt_id,report_info;
    private Button submit_report;
    private RecyclerView recyclerView;
    private Report_adapter_advisor report_adapter_advisor;
    //public TextView regnoap;
    public String registration_no,report,report_stdntid;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    public static  final String KEY_STDNT_ID="report_stdntid";
    public static final String KEY_REPORT_INFO="report";
    public static final String KEY_ADV_REGNO="registration_no";

    private List<Report> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorConsultationsViewModel =
                ViewModelProviders.of(this).get(AdvisorConsultationsViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_consultations, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        advisorConsultationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        recyclerView = (RecyclerView) root.findViewById(R.id.advisor_report_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        AdvisorMainActivity advisorMainActivity = (AdvisorMainActivity) getActivity();
        registration_no = advisorMainActivity.getRegno();

        getReports();

        FloatingActionButton fab = root.findViewById(R.id.advisor_consultation_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
            }


        });

        return root;
    }


    public void getReports() {
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

                        if(registration_no.equals(report.getAdvisor_id())){
                            list.add(report);
                        }
                    }

                    report_adapter_advisor = new Report_adapter_advisor(getContext(),list);
                    recyclerView.setAdapter(report_adapter_advisor);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue3 = Volley.newRequestQueue(getContext());
        requestQueue3.add(jsonObjectRequest);

    }

    private void createPopupDialog() {
        dialogBuilder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.pop_up_add_report,null);
        stdnt_id = (EditText) view.findViewById(R.id.report_stdntid);
        report_info = (EditText) view.findViewById(R.id.report_info);
        submit_report = (Button) view.findViewById(R.id.report_submit);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        submit_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReport(v);
            }

            private void submitReport(View v) {
                report = report_info.getText().toString().trim();
                report_stdntid = stdnt_id.getText().toString().trim();

                String  post_report_url = "http://192.168.137.1:88/AcademicAdvisor/insert_reports.php";
                StringRequest stringRequest5 = new StringRequest(Request.Method.POST, post_report_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.trim().equals("success")) {
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                        new Response.ErrorListener(){

                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams()throws AuthFailureError {
                        Map<String,String> map = new HashMap<>();
                        map.put(KEY_STDNT_ID,report_stdntid);
                        map.put(KEY_REPORT_INFO,report);
                        map.put(KEY_ADV_REGNO,registration_no);
                        return map;
                    }


                };

                RequestQueue requestQueue5 = Volley.newRequestQueue(getContext());
                requestQueue5.add(stringRequest5);

            }

        });


    }

}