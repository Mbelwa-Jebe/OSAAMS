package com.mbelwa.OSAAMS.ui.advisor_home;

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
import com.mbelwa.OSAAMS.AdvisorMainActivity;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.StudentMainActivity;
import com.mbelwa.OSAAMS.adapters.Advisor_home_adapter;
import com.mbelwa.OSAAMS.adapters.student_home_adapter;
import com.mbelwa.OSAAMS.models.Advisor_dashboard;
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

public class AdvisorHomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private com.mbelwa.OSAAMS.adapters.Advisor_home_adapter advisor_home_adapter;
    private AdvisorHomeViewModel advisorHomeViewModel;
    public String registration_no;
    private List<Advisor_dashboard> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorHomeViewModel =
                ViewModelProviders.of(this).get(AdvisorHomeViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        advisorHomeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });
        recyclerView = (RecyclerView) root.findViewById(R.id.advisor_home_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        AdvisorMainActivity advisorMainActivity = (AdvisorMainActivity) getActivity();
        registration_no = advisorMainActivity.getRegno();

        getAdvisorDashbord();
        return root;
    }

    public void getAdvisorDashbord(){

        list = new ArrayList<>();

        String  ap_url = URL.ADVISOR_DASHBOARD_URL;
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ap_url,
                null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray apArray = response.getJSONArray("advisor_dash");
                    for (int i=0; i< apArray.length();i++){
                        JSONObject apObject = apArray.getJSONObject(i);

                        Advisor_dashboard advisor_dashboard = new Advisor_dashboard();
                        advisor_dashboard.setAdvisor_id(apObject.getString("advisor_id"));
                        advisor_dashboard.setAppointment_count(apObject.getString("appointments_received"));
                        advisor_dashboard.setReport_count(apObject.getString("reports"));
                        advisor_dashboard.setStudent_count(apObject.getString("students_allocated"));

                        if(registration_no.equals(advisor_dashboard.getAdvisor_id())){
                            list.add(advisor_dashboard);
                        }


                    }

                    advisor_home_adapter = new Advisor_home_adapter(getContext(),list);
                    recyclerView.setAdapter(advisor_home_adapter);
                    advisor_home_adapter.notifyDataSetChanged();


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