package com.touchizen.OSAAMS.ui.advisor_appointments;

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
import com.touchizen.OSAAMS.AdvisorMainActivity;
import com.touchizen.OSAAMS.R;
import com.touchizen.OSAAMS.adapters.ap_adapter;
import com.touchizen.OSAAMS.models.Appointment;

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

public class AdvisorAppointmentsFragment extends Fragment {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText requstinfo;
    private Button submitap;
    private RecyclerView recyclerView;
    private com.touchizen.OSAAMS.adapters.ap_adapter ap_adapter;
    //public TextView regnoap;
    public String registration_no, request_info, post_url1;
    public static final String KEY_REGNO = "registration_no";
    public static final String KEY_REQUESTINFO = "request_info";

    private List<Appointment> list;

    private AdvisorAppointmentsViewModel advisorAppointmentsViewModel;

    public AdvisorAppointmentsFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorAppointmentsViewModel =
                ViewModelProviders.of(this).get(AdvisorAppointmentsViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_appointments, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        advisorAppointmentsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        recyclerView = (RecyclerView) root.findViewById(R.id.advisor_ap_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        getAppointment();

        FloatingActionButton fab = root.findViewById(R.id.advisor_ap_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createPopupDialog();

            }
        });

        AdvisorMainActivity advisorMainActivity = (AdvisorMainActivity) getActivity();
        registration_no = advisorMainActivity.getRegno();


        return root;
    }


    private void createPopupDialog(){
        dialogBuilder = new AlertDialog.Builder(this.getContext());
        View view = getLayoutInflater().inflate(R.layout.popup_appointment,null);
        requstinfo = (EditText) view.findViewById(R.id.requstinfo_id);
        submitap = (Button) view.findViewById(R.id.ap_submit);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        submitap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitapAppointment(v);

            }

            private void submitapAppointment(View v) {
                request_info = requstinfo.getText().toString().trim();


                post_url1 ="http://192.168.137.1:88/AcademicAdvisor/insertap1.php";
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, post_url1, new Response.Listener<String>() {
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
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                )
                {
                    @Override
                    protected Map<String, String> getParams()throws AuthFailureError {
                        Map<String,String> map = new HashMap<>();
                        map.put(KEY_REGNO,registration_no);
                        map.put(KEY_REQUESTINFO,request_info);
                        return map;
                    }

                };
                RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
                requestQueue1.add(stringRequest1);

            }
        });
    }


    public void getAppointment(){

        list = new ArrayList<>();

        String  ap_url = "http://192.168.137.1:88/AcademicAdvisor/get_appointments.php";
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ap_url,
                null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray apArray = response.getJSONArray("appointments");
                    for (int i=0; i< apArray.length();i++){
                        JSONObject apObject = apArray.getJSONObject(i);

                        Appointment appointment = new Appointment();
                        appointment.setRequest_info(apObject.getString("request_info"));
                        appointment.setResponse(apObject.getString("response"));
                        appointment.setAppointment_id(apObject.getString("appointment_id"));
                        appointment.setAdvisor_id(apObject.getString("advisor_id"));
                        appointment.setStudent_id(apObject.getString("student_id"));
                        appointment.setAdvisor_fname(apObject.getString("advisor_fname"));
                        appointment.setAdvisor_lname(apObject.getString("advisor_lname"));
                        appointment.setStudent_fname(apObject.getString("student_fname"));
                        appointment.setStudent_lname(apObject.getString("student_lname"));
                        appointment.setTimestamp(apObject.getString("time_stamp"));

                        if(registration_no.equals(appointment.getStudent_id())){
                            list.add(appointment);
                        }
                        else if(registration_no.equals(appointment.getAdvisor_id())){
                            list.add(appointment);
                        }

                    }

                    ap_adapter = new ap_adapter(getContext(),list);
                    recyclerView.setAdapter(ap_adapter);
                    ap_adapter.notifyDataSetChanged();


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
