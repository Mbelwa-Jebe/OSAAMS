package com.mbelwa.OSAAMS.ui.advisor_chats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mbelwa.OSAAMS.AdvisorMainActivity;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.adapters.Advisor_thread_adapter;
import com.mbelwa.OSAAMS.adapters.FragmentCommunication;
import com.mbelwa.OSAAMS.models.Advisor_threads;
import com.mbelwa.OSAAMS.models.URL;
import com.mbelwa.OSAAMS.ui.advisor_chatRoom.AdvisorChatRoomFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdvisorChatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private com.mbelwa.OSAAMS.adapters.Advisor_thread_adapter advisor_thread_adapter;
    private List<Advisor_threads> list;
    public String registration_no,student_ID;

    private AdvisorChatsViewModel advisorChatsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorChatsViewModel =
                ViewModelProviders.of(this).get(AdvisorChatsViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_chats, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.advisor_threads);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        AdvisorMainActivity advisorMainActivity = (AdvisorMainActivity) getActivity();
        registration_no = advisorMainActivity.getRegno();

        getThhreads();

        return root;
    }

    public void getThhreads(){

        list = new ArrayList<>();

        String  ap_url = URL.GET_ADVISOR_THREADS;
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ap_url,
                null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray apArray = response.getJSONArray("threads");
                    for (int i=0; i< apArray.length();i++){
                        JSONObject apObject = apArray.getJSONObject(i);

                        Advisor_threads advisor_threads = new Advisor_threads();
                        advisor_threads.setStudent_fname(apObject.getString("student_fname"));
                        advisor_threads.setStudent_lname(apObject.getString("student_lname"));
                        advisor_threads.setContent(apObject.getString("content"));
                        advisor_threads.setStudent_id(apObject.getString("student_id"));
                        advisor_threads.setAdvisor_id(apObject.getString("advisor_id"));

                        if(registration_no.equals(advisor_threads.getAdvisor_id())){
                            list.add(advisor_threads);
                        }


                    }

                    advisor_thread_adapter = new Advisor_thread_adapter(getContext(),list,communication);
                    recyclerView.setAdapter(advisor_thread_adapter);
                    advisor_thread_adapter.notifyDataSetChanged();


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


    // transit to chatroom for specific student
//    public void changeFragment(){
//
//       Fragment fragment = new AdvisorFaqsFragment();
//       getFragmentManager().beginTransaction()
//               .replace(R.id.advisor_nav_host_fragment,fragment)
//               .commit();
//
////        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.replace(R.id.advisor_nav_host_fragment,fragment);
////        fragmentTransaction.addToBackStack(null);
////        fragmentTransaction.commit();
//
//    }




    //implenent Interface for data sharing

    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void respond(int position, String student_ID) {
           AdvisorChatRoomFragment advisorChatRoomFragment = new AdvisorChatRoomFragment();
           Bundle bundle = new Bundle();
           bundle.putString("REG_NO",student_ID);
            advisorChatRoomFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .replace(R.id.advisor_nav_host_fragment, advisorChatRoomFragment)
                    .commit();
        }
    };

}