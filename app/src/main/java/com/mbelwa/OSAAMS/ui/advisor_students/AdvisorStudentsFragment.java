package com.mbelwa.OSAAMS.ui.advisor_students;

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
import com.mbelwa.OSAAMS.AdvisorMainActivity;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.adapters.Student_adapter;
import com.mbelwa.OSAAMS.models.Student;
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

public class AdvisorStudentsFragment extends Fragment {

    private AdvisorStudentsViewModel advisorstudentsViewModel;

    private RecyclerView recyclerView;
    private Student_adapter student_adapter;
    private List<Student> list;
    public String registration_no;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorstudentsViewModel =
                ViewModelProviders.of(this).get(AdvisorStudentsViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_students, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        advisorstudentsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        recyclerView = (RecyclerView)root.findViewById(R.id.advisor_student_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        AdvisorMainActivity advisorMainActivity = (AdvisorMainActivity) getActivity();
        registration_no = advisorMainActivity.getRegno();

        getStudents();
        return root;
    }

    private void getStudents() {
        list = new ArrayList<>();
        String student_url = URL.GET_STUDENT_URL;
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
                         if(registration_no.equals(student.getAdvisor_id())){
                              list.add(student);
                         }
                       // list.add(student);

                    }


                    student_adapter = new Student_adapter(getContext(),list);
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
        RequestQueue requestQueue7 = Volley.newRequestQueue(getContext());
        requestQueue7.add(jsonObjectRequest);

    }
}