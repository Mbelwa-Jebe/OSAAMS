package com.mbelwa.OSAAMS.ui.advisor_chatRoom;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.mbelwa.OSAAMS.AdvisorMainActivity;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.adapters.Message_adapter_advisor;
import com.mbelwa.OSAAMS.models.Message;
import com.mbelwa.OSAAMS.models.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdvisorChatRoomFragment extends Fragment {

    private AdvisorChatRoomViewModel advisorChatRoomViewModel;
    private RecyclerView recyclerView;

    public String registration_no,message,studentID;
    private EditText newmessage;
    private Button send;
    public static  final String KEY_MESSAGE="message";
    public static  final String KEY_REGNO="registration_no";
    public static  final String KEY_STDNTID="student_ID";
    private List<Message> list;
    public boolean x = true;
    private com.mbelwa.OSAAMS.adapters.Message_adapter_advisor message_adapter_advisor;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorChatRoomViewModel =
                ViewModelProviders.of(this).get(AdvisorChatRoomViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_chat_room, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        advisorChatRoomViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        recyclerView = (RecyclerView)root.findViewById(R.id.recycler_message_advisor);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        AdvisorMainActivity advisorMainActivity = (AdvisorMainActivity) getActivity();
        registration_no = advisorMainActivity.getRegno();
    studentID = getArguments().getString("REG_NO");
        newmessage = root.findViewById(R.id.message_sent_ad);
        send = root.findViewById(R.id.btn_send_ad);
        getMessages();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }

            private void sendMessage() {
                message = newmessage.getText().toString().trim();

                if (message.length() == 0) {

                }

                else {

                    String  post_url1 = URL.INSERT_MESSAGES_ADVISOR_URL;
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, post_url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("success")) {


                            } else {
                                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
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
                            map.put(KEY_MESSAGE, message);
                            map.put(KEY_REGNO, registration_no);
                            map.put(KEY_STDNTID, studentID);
                            return map;
                        }

                    };
                    RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
                    requestQueue1.add(stringRequest1);



                    getMessages();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setReorderingAllowed(false);
                    transaction.detach(AdvisorChatRoomFragment.this).attach(AdvisorChatRoomFragment.this).commitAllowingStateLoss();
                    message_adapter_advisor.notifyDataSetChanged();

                    newmessage.setText("");
                    recyclerView.getLayoutManager().scrollToPosition(list.size());
                    x = true;

                }
            }
        });


        final Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (x = true){
                    try {
                        Thread.sleep(7000);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (getView() != null ){
                                    getMessages();
                                }

                            }
                        });
                    }
                    catch (Exception e){

                    }
                }
            }
        }).start();

        newmessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = false;
                mHandler.removeCallbacksAndMessages(null);
                return x = false;
            }
        });
        Bundle bundle = this.getArguments();


        return root;
    }

    public void getMessages(){

        list = new ArrayList<>();

        String  ap_url = URL.GET_MESSAGES_URL;
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ap_url,
                null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray apArray = response.getJSONArray("messages");
                    for (int i=0; i< apArray.length();i++){
                        JSONObject apObject = apArray.getJSONObject(i);

                        Message message = new Message();
                        message.setMessage_id(apObject.getString("message_id"));
                        message.setStudent_id(apObject.getString("student_id"));
                        message.setAdvisor_id(apObject.getString("advisor_id"));
                        message.setContent(apObject.getString("content"));
                        message.setTimestamp(apObject.getString("time"));
                        message.setMessage_from(apObject.getString("message_from"));

                        if(registration_no.equals(message.getAdvisor_id()) && studentID.equals(message.getStudent_id())){
                            list.add(message);
                        }


                    }

                    message_adapter_advisor = new Message_adapter_advisor(getContext(),list);
                    recyclerView.setAdapter(message_adapter_advisor);
                    message_adapter_advisor.notifyDataSetChanged();
                    recyclerView.getLayoutManager().scrollToPosition(list.size()-1);


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