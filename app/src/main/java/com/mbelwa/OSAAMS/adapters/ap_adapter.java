package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Appointment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ap_adapter extends RecyclerView.Adapter<ap_adapter.ViewHolder>{
    List<Appointment> list;
    Context context;

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
    public void onBindViewHolder(@NonNull ap_adapter.ViewHolder holder, int position) {
        final Appointment appointment = list.get(position);
        holder.ap_time.setText(appointment.getTimestamp());
        holder.ap_studentid.setText(appointment.getStudent_id());
        holder.ap_info.setText(appointment.getRequest_info());
        holder.ap_response.setText(appointment.getResponse());

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment.getAppointment_id();
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

            editBtn = itemView.findViewById(R.id.edit_ap);
            deleteBtn = itemView.findViewById(R.id.delete_ap);
        }
    }
}
