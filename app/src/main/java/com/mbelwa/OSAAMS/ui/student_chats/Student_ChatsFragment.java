package com.mbelwa.OSAAMS.ui.student_chats;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.StudentMainActivity;
import com.mbelwa.OSAAMS.adapters.Message_adapter_student;
import com.mbelwa.OSAAMS.adapters.ap_adapter;
import com.mbelwa.OSAAMS.models.Appointment;
import com.mbelwa.OSAAMS.models.Message;
import com.mbelwa.OSAAMS.models.URL;
import com.mbelwa.OSAAMS.ui.student_appoitments.Student_AppointmentsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Student_ChatsFragment extends Fragment {
    public String registration_no,message,timestamp,my_advisor_id;
    private EditText newmessage;
    private Button send;
    private RecyclerView recyclerView;
    private com.mbelwa.OSAAMS.adapters.Message_adapter_student message_adapter_student;
    private Student_ChatsViewModel student_chatsViewModel;
    public static  final String KEY_MESSAGE="message";
    public static  final String KEY_REGNO="registration_no";
    private List<Message> list;
    public boolean x = true;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        student_chatsViewModel =
                ViewModelProviders.of(this).get(Student_ChatsViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_chats, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        student_chatsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                //textView.setText(s);
//            }
//        });
getLifecycle().addObserver(student_chatsViewModel);

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_message_student);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
      // manager.setReverseLayout(true);

        // arrange message in chat order
        manager.setStackFromEnd(true);

        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        StudentMainActivity studentMainActivity = (StudentMainActivity) getActivity();
        registration_no = studentMainActivity.getRegno();

        newmessage = root.findViewById(R.id.message_sent);
        send = root.findViewById(R.id.btn_send);
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

                    String  post_url1 = URL.INSERT_MESSAGES_STUDENT_URL;
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
                            return map;
                        }

                    };
                    RequestQueue requestQueue1 = Volley.newRequestQueue(getContext());
                    requestQueue1.add(stringRequest1);



getMessages();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setReorderingAllowed(false);
                    transaction.detach(Student_ChatsFragment.this).attach(Student_ChatsFragment.this).commitAllowingStateLoss();
                     message_adapter_student.notifyDataSetChanged();

                     newmessage.setText("");
                     recyclerView.getLayoutManager().scrollToPosition(list.size());
                    x = true;

                }
            }
        });

// fetch message after every second
//new Timer().scheduleAtFixedRate(new TimerTask() {
//    @Override
//    public void run() {
//        getMessages();
//
//    }
//},0,2000);

        //crash on change fragment

        final Handler mHandler = new Handler();
//        final int delay = 2000;
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getMessages();
//                handler.postDelayed(this, delay);
//            }
//        },delay);


        //method ok

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

                        if(registration_no.equals(message.getStudent_id())){
                            list.add(message);
                        }


                    }

                    message_adapter_student = new Message_adapter_student(getContext(),list);
                    recyclerView.setAdapter(message_adapter_student);
                    message_adapter_student.notifyDataSetChanged();
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