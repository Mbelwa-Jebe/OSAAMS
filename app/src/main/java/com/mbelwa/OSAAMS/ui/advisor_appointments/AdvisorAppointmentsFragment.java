package com.mbelwa.OSAAMS.ui.advisor_appointments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.mbelwa.OSAAMS.adapters.ap_adapter;
import com.mbelwa.OSAAMS.models.Appointment;
import com.mbelwa.OSAAMS.models.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdvisorAppointmentsFragment extends Fragment {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText requstinfo,studentid,req_date,req_time;
    private Button submitap;
    private RecyclerView recyclerView;
    private com.mbelwa.OSAAMS.adapters.ap_adapter ap_adapter;
    //public TextView regnoap;
    public String registration_no, request_info,student_id, post_url1,ap_date,ap_time;
    public static final String KEY_REGNO = "registration_no";
    public static final String KEY_REQUESTINFO = "request_info";
    public static final String KEY_STUDENTID = "student_id";
    public static  final String KEY_AP_DATE="appointment_date";
    public static final String KEY_AP_TIME="appointment_time";
    public DatePickerDialog datePickerDialog;
    public TimePickerDialog timePickerDialog;



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
        View view = getLayoutInflater().inflate(R.layout.advisor_popup_appointment,null);
        requstinfo = (EditText) view.findViewById(R.id.requstinfo_id_ap);
        studentid = (EditText) view.findViewById(R.id.student_id_ap);
        submitap = (Button) view.findViewById(R.id.ad_submit_ap);
        req_date = (EditText) view.findViewById(R.id.ad_date_ap);
        req_time = (EditText) view.findViewById(R.id.ad_time_ap);

        req_date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        req_date.setText(mYear+"-"+mMonth+"-"+mDay);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();

            }
        });

        req_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int mHour = c.get(Calendar.HOUR_OF_DAY); // current year
                final int mMinutes = c.get(Calendar.MINUTE); // current month

                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        req_time.setText(mHour+":"+minute);
                    }
                },mHour,mMinutes,true);
                timePickerDialog.show();

            }
        });



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
                student_id = studentid.getText().toString().trim();
                ap_date = req_date.getText().toString().trim();
                ap_time = req_date.getText().toString().trim();
                if (request_info.length() == 0) {
                    requstinfo.setError("Fill Request info first");

                } else if (student_id.length() == 0) {
                    studentid.setError("Fill Student ID first");
                }

                else{

                        post_url1 = URL.ADD_AP_ADVISOR_URL;
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, post_url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        }

                        else if (ap_date.length() == 0){
                            req_date.setError("fill date first");
                        }
                        else if (ap_time.length() == 0){
                            req_time.setError("fill time first");
                        }
                        else {
                            Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put(KEY_REGNO, registration_no);
                        map.put(KEY_REQUESTINFO, request_info);
                        map.put(KEY_STUDENTID, student_id);
                        map.put(KEY_AP_DATE, request_info);
                        map.put(KEY_AP_TIME, student_id);
                        return map;
                    }


                };
                RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
                requestQueue1.add(stringRequest1);

                ap_adapter.notifyItemInserted(list.size());
                ap_adapter.notifyDataSetChanged();
                // Fragment frg = null;
                //frg = getFragmentManager().findFragmentByTag("AdvisorAppointmentsFragment.java");
                //final FragmentTransaction ft = getFragmentManager().beginTransaction();
                //ft.detach(frg);
                //ft.attach(frg);
                //ft.commit();

                // FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
                // t.setAllowOptimization(false);
                // t.detach(Context.th).attach(AdvisorAppointmentsFragment.this).commitAllowingStateLoss();

getAppointment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(false);
                transaction.detach(AdvisorAppointmentsFragment.this).attach(AdvisorAppointmentsFragment.this).commitAllowingStateLoss();
            }
            }
        });
    }


    public void getAppointment(){

        list = new ArrayList<>();


        String  ap_url = URL.GET_AP_URL;
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
                        appointment.setAp_from(apObject.getString("ap_from"));
                        appointment.setAp_date(apObject.getString("ap_date"));
                        appointment.setAp_time(apObject.getString("ap_time"));

                        if(registration_no.equals(appointment.getAp_from())){
                            list.add(appointment);
                        }


                    }

                    ap_adapter = new ap_adapter(getContext(),list);

                    recyclerView.setAdapter(ap_adapter);



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
