package com.mbelwa.OSAAMS.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mbelwa.OSAAMS.MainActivity;
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Appointment;
import com.mbelwa.OSAAMS.models.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ap_adapter extends RecyclerView.Adapter<ap_adapter.ViewHolder>{
    List<Appointment> list;
    Context context;
    public static  final String KEY_AP_ID="appointment_id";
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    public int appoint_id;

    public ap_adapter(Context context, List<Appointment> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ap_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ap_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_row, parent, false);
        return new ViewHolder(ap_row);

    }

    @Override
    public void onBindViewHolder(@NonNull final ap_adapter.ViewHolder holder, final int position) {
        final Appointment appointment = list.get(position);
        holder.ap_time.setText(appointment.getTimestamp());
        holder.ap_studentid.setText(appointment.getStudent_id());
        holder.ap_info.setText(appointment.getRequest_info());
        holder.ap_response.setText(appointment.getResponse());



        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 appoint_id = holder.getAdapterPosition();
                deleteFromServer(list.get(appoint_id).getAppointment_id());


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ap_info,ap_response,ap_studentid,ap_time;
        public ImageButton editBtn,deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ap_info = itemView.findViewById(R.id.ap_requestinfo);
            ap_response = itemView.findViewById(R.id.ap_status);
            ap_studentid = itemView.findViewById(R.id.ap_studentid);
            ap_time = itemView.findViewById(R.id.ap_date);

            deleteBtn = itemView.findViewById(R.id.delete_ap);
        }
    }

    public void deleteFromServer(final String ap_id){
       // public String final String KEY_AP_ID = "ap_id";
        alertDialogBuilder = new AlertDialog.Builder(context);

        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.confirmation_dialog,null);



        Button no_delete = (Button) view.findViewById(R.id.no_delete);
        Button yes_delete =(Button) view.findViewById(R.id.yes_delete);

        alertDialogBuilder.setView(view);
        dialog = alertDialogBuilder.create();
        dialog.show();

        no_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        yes_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String delete_ap = URL.DELETE_AP_URL;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, delete_ap,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.trim().equals("success")) {
                                    Toast.makeText(context, "successful deleted", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(context, "not successful", Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
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
                dialog.dismiss();
                list.remove(appoint_id);
                notifyItemRemoved(appoint_id);
                notifyItemRangeChanged(appoint_id,list.size());



            }

        });



    }

}
