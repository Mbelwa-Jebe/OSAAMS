package com.mbelwa.OSAAMS.ui.student_faqs;

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
import com.mbelwa.OSAAMS.adapters.Faqs_adapter;
import com.mbelwa.OSAAMS.adapters.Student_adapter;
import com.mbelwa.OSAAMS.models.Faqs;
import com.mbelwa.OSAAMS.models.Student;

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

public class Student_FaqsFragment extends Fragment {

    private Student_FaqsViewModel sendViewModel;

    private RecyclerView recyclerView;
    private Faqs_adapter faqs_adapter;
    private List<Faqs> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(Student_FaqsViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_faqs, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        recyclerView = (RecyclerView)root.findViewById(R.id.faq_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        getFaqs();
        return root;
    }

    private void getFaqs() {
        list = new ArrayList<>();
        String student_url = "http://192.168.137.1:88/AcademicAdvisor/get_faqs.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, student_url
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray faq_array = response.getJSONArray("faqs");
                    for (int i=0;i < faq_array.length();i++){
                        JSONObject stdObject = faq_array.getJSONObject(i);
                        Faqs faq = new Faqs();
                        faq.setFaqs_question(stdObject.getString("faqs_question"));
                        faq.setFaqs_answer(stdObject.getString("faqs_answer"));

                         list.add(faq);

                    }


                    faqs_adapter = new Faqs_adapter(getContext(),list);
                    recyclerView.setAdapter(faqs_adapter);

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
        RequestQueue requestQueue8 = Volley.newRequestQueue(getContext());
        requestQueue8.add(jsonObjectRequest);

    }
}