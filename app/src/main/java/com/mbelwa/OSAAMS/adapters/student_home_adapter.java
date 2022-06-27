package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Student_dashboard;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class student_home_adapter extends RecyclerView.Adapter<student_home_adapter.ViewHolder> {
    List<Student_dashboard> list;
    Context context;

    public student_home_adapter(Context context, List<Student_dashboard> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public student_home_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dash_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_home_info, parent ,false);
        return new ViewHolder(dash_row);
    }

    @Override
    public void onBindViewHolder(@NonNull student_home_adapter.ViewHolder holder, int position) {
            final Student_dashboard student_dashboard = list.get(position);
            holder.advisor_fname.setText(student_dashboard.getDash_advisor_fname());
            holder.advisor_lname.setText(student_dashboard.dash_advisor_lname);
            holder.consultations_count.setText(student_dashboard.getDash_consultations());
            holder.appointment_count.setText(student_dashboard.dash_appointments_received);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView advisor_fname,advisor_lname,appointment_count,consultations_count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            advisor_fname = itemView.findViewById(R.id.allocated_advisor_fname);
            advisor_lname = itemView.findViewById(R.id.allocated_advisor_lname);
            appointment_count = itemView.findViewById(R.id.appointments_received_student);
            consultations_count = itemView.findViewById(R.id.consultations_student);
        }
    }
}
