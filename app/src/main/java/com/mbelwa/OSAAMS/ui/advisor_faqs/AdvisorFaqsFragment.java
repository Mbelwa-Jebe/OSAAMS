package com.mbelwa.OSAAMS.ui.advisor_faqs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.adapters.Faqs_adapter;
import com.mbelwa.OSAAMS.models.Faqs;
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

public class AdvisorFaqsFragment extends Fragment {

    private AdvisorFaqsViewModel advisorFaqsViewModel;
    private RecyclerView recyclerView;
    private Faqs_adapter faqs_adapter;
    private List<Faqs> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorFaqsViewModel =
                ViewModelProviders.of(this).get(AdvisorFaqsViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_faqs, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        advisorFaqsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        recyclerView = (RecyclerView)root.findViewById(R.id.faq_recyclerview_ad);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        getFaqs();

        EditText editText = (EditText) root.findViewById(R.id.editSearch_ad);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Action after detecting text change

                filter(s.toString());

            }
        });
        return root;
    }

    private void filter(String text){

        //Filtered search for each word in user input
        String[] input1 = text.split(" ");
        for(String ch: input1){
            ArrayList<Faqs> filteredList = new ArrayList<>();
            for (Faqs item : list){
                if (item.getFaqs_answer().toLowerCase().contains(ch.toLowerCase())  || item.getFaqs_question().toLowerCase().contains(ch.toLowerCase()) ){
                    filteredList.add(item);
                }

            }
            faqs_adapter.filteredList(filteredList);
        }




        // NormalSearch
//             if (item.getFaqs_answer().toLowerCase().contains(text.toLowerCase().replaceAll(" ",""))){
//                 filteredList.add(item);
//             }


        //  }

        // faqs_adapter.filteredList(filteredList);
    }

    private void getFaqs() {
        list = new ArrayList<>();

        //Pulling Faqs from database
        String student_url = URL.GET_FAQS_URL;
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

                    //connect adapter
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