package com.mbelwa.OSAAMS.ui.student_advisor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.StudentMainActivity;
import com.mbelwa.OSAAMS.adapters.Advisor_adapter;
import com.mbelwa.OSAAMS.models.Advisor;
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

public class Student_AdvisorFragment extends Fragment {

    private Student_AdvisorViewModel student_advisorViewModel;

    private RecyclerView recyclerView;
    private Advisor_adapter advisor_adapter;
    private List<Advisor> list;
    public String registration_no;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        student_advisorViewModel =
                ViewModelProviders.of(this).get(Student_AdvisorViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_advisor, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        student_advisorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
            //get recyclerview from layout
        recyclerView = (RecyclerView)root.findViewById(R.id.student_advisor_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

            //get registration number from main page
        StudentMainActivity studentMainActivity = (StudentMainActivity) getActivity();
        registration_no = studentMainActivity.getRegno();

           //calling function for displaying advisors
        getAdvisors();
        return root;
    }

    private void getAdvisors() {
        list = new ArrayList<>();
        String advisor_url = URL.GET_ADVISOR_URL;
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
                        advisor.setStudent_id(advObject.getString("student_id"));
                          if(registration_no.equals(advisor.getStudent_id())){
                            list.add(advisor);
                           }
                        //list.add(advisor);

                    }
                    advisor_adapter = new Advisor_adapter(getContext(),list);
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
        RequestQueue requestQueue6 = Volley.newRequestQueue(getContext());
        requestQueue6.add(jsonObjectRequest);

    }
}