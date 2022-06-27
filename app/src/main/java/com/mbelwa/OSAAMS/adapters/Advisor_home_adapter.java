package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Advisor;
import com.mbelwa.OSAAMS.models.Advisor_dashboard;
import com.mbelwa.OSAAMS.models.Student_dashboard;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Advisor_home_adapter extends RecyclerView.Adapter<Advisor_home_adapter.ViewHolder> {
    List<Advisor_dashboard> list;
    Context context;

    public Advisor_home_adapter(Context context, List<Advisor_dashboard> list){
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public Advisor_home_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dash_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advisor_home_info, parent ,false);
        return new ViewHolder(dash_row);
    }

    @Override
    public void onBindViewHolder(@NonNull Advisor_home_adapter.ViewHolder holder, int position) {
final Advisor_dashboard advisor_dashboard = list.get(position);
holder.student_count.setText(advisor_dashboard.getStudent_count());
holder.consultations_count.setText(advisor_dashboard.report_count);
holder.appointment_count.setText(advisor_dashboard.getAppointment_count());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView appointment_count,consultations_count,student_count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appointment_count = itemView.findViewById(R.id.appointments_received_ad);
            consultations_count = itemView.findViewById(R.id.consultations_ad);
            student_count = itemView.findViewById(R.id.allocated_students);
        }
    }
}
