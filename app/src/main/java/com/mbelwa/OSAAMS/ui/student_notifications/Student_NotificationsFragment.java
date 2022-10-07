package com.mbelwa.OSAAMS.ui.student_notifications;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.StudentMainActivity;
import com.mbelwa.OSAAMS.adapters.ap_adapter;
import com.mbelwa.OSAAMS.adapters.ap_notification_adapter;
import com.mbelwa.OSAAMS.models.Appointment;
import com.mbelwa.OSAAMS.models.URL;
import com.mbelwa.OSAAMS.ui.student_appoitments.Student_AppointmentsFragment;
import com.mbelwa.OSAAMS.ui.student_home.Student_HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Student_NotificationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ap_notification_adapter adapter;
    private List<Appointment> list;
    public String registration_no;
   // private com.mbelwa.OSAAMS.adapters.ap_notification_adapter ap_adapter;


    private Student_NotificationsViewModel studentNotificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studentNotificationsViewModel =
                ViewModelProviders.of(this).get(Student_NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_notifications, container, false);
       // final TextView textView = root.findViewById(R.id.text_notifications);
       // studentNotificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //    @Override
         //   public void onChanged(@Nullable String s) {
         //       textView.setText(s);
         //   }
       // });

        recyclerView = (RecyclerView) root.findViewById(R.id.student_notification_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        StudentMainActivity studentMainActivity = (StudentMainActivity) getActivity();
        registration_no = studentMainActivity.getRegno();





        getAppointments();

        return root;
    }

    private void getAppointments() {

        list = new ArrayList<>();

        String  ap_url = URL.GET_AP_URL;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ap_url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray apArray = response.getJSONArray("appointments");
                    for (int i = 0; i < apArray.length(); i++) {
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
                        appointment.setAp_to(apObject.getString("ap_to"));
                        appointment.setAp_from(apObject.getString("ap_from"));
                        appointment.setAp_date(apObject.getString("ap_date"));
                        appointment.setAp_time(apObject.getString("ap_time"));

                        if (registration_no.equals(appointment.getAp_to())) {
                            list.add(appointment);
                        }


                    }

                    adapter = new ap_notification_adapter(list, getContext()){
                        @Override
                        public void refresh(){
                            refreshFragment();
                        }
                    };
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                    // if (null != callBack)callBack.processFinish(appointments);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue9 = Volley.newRequestQueue(getContext());
        requestQueue9.add(jsonObjectRequest);


    }
    //refresh the changes
    public void refreshFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setReorderingAllowed(false);
        transaction.detach(this).attach(this).commitAllowingStateLoss();

    }



}