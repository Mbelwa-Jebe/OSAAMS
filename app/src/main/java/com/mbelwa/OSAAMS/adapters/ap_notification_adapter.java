package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Appointment;
import com.mbelwa.OSAAMS.models.URL;
import com.mbelwa.OSAAMS.ui.student_notifications.Student_NotificationsFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import static android.widget.Toast.LENGTH_LONG;

public class ap_notification_adapter extends RecyclerView.Adapter<ap_notification_adapter.ViewHolder> {
    List<Appointment> list;
    Context context;
    public static  final String KEY_AP_ID="appointment_id";
    public int appoint_id;
    private LayoutInflater inflater;

    public ap_notification_adapter(List<Appointment> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull


    @Override
    public ap_notification_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ap_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_appointment_row, parent, false);
        return new ViewHolder(ap_row);
    }

    @Override
    public void onBindViewHolder(@NonNull final ap_notification_adapter.ViewHolder holder, int position) {

        final Appointment appointment = list.get(position);
        holder.ap_time.setText(appointment.getTimestamp());
        holder.ap_from.setText(appointment.getAp_from());
        holder.ap_info.setText(appointment.getRequest_info());
        holder.ap_response.setText(appointment.getResponse());

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appoint_id = holder.getAdapterPosition();
                acceptAppointment(list.get(appoint_id).getAppointment_id());

            }
        });

        holder.rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appoint_id = holder.getAdapterPosition();
                rejectAppointment(list.get(appoint_id).getAppointment_id());

            }
        });

    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ap_info,ap_response,ap_from,ap_time;
        public ImageButton acceptBtn,rejectBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ap_info = itemView.findViewById(R.id.ap_notification_requestinfo);
            ap_response = itemView.findViewById(R.id.ap_notification_status);
            ap_from = itemView.findViewById(R.id.ap_from);
            ap_time = itemView.findViewById(R.id.ap_notification_date);

            acceptBtn = itemView.findViewById(R.id.confirm_ap);
            rejectBtn = itemView.findViewById(R.id.reject_ap);

        }
    }

    private void acceptAppointment(final String ap_id) {
       // Toast.makeText(context,"accepted", LENGTH_LONG).show();
        String accept_ap = URL.CONFIRM_AP_URL;

          StringRequest stringRequest = new StringRequest(Request.Method.POST, accept_ap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(context, "successful accepted", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "not successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "error", LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put(KEY_AP_ID,ap_id);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        notifyItemRemoved(appoint_id);
        notifyItemRangeChanged(appoint_id,list.size());
        refresh();



    }

    private void rejectAppointment(final String ap_id) {
       // Toast.makeText(context,"accepted", LENGTH_LONG).show();
        String accept_ap = URL.REJECT_AP_URL;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, accept_ap,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(context, "successful accepted", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "not successful", LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "error", LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put(KEY_AP_ID,ap_id);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        notifyItemRemoved(appoint_id);
        notifyItemRangeChanged(appoint_id,list.size());
        refresh();



    }

    public void refresh(){
    }

}
