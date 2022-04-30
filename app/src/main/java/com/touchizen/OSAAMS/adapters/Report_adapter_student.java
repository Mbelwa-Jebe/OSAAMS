package com.touchizen.OSAAMS.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.touchizen.OSAAMS.R;
import com.touchizen.OSAAMS.models.Report;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Report_adapter_student extends RecyclerView.Adapter<Report_adapter_student.ViewHolder> {

    Context context;
    List<Report> list;

    public Report_adapter_student(Context context, List<Report> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Report_adapter_student.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View student_report_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_report_row,parent,false);
        return new ViewHolder(student_report_row);
    }

    @Override
    public void onBindViewHolder(@NonNull Report_adapter_student.ViewHolder holder, int position) {
        Report report = list.get(position);
        holder.report_date.setText(report.getReport_date());
        holder.report_studentfname.setText(report.getStudent_fname());
        holder.report_studentlname.setText(report.getStudent_lname());
        holder.report_studentid.setText(report.getStudent_id());
        holder.report.setText(report.getReport());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView report_date,report_studentfname,report_studentlname,report_studentid,report;
        public Button edit_report,delete_report;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            report_date = itemView.findViewById(R.id.report_date);
            report_studentfname = itemView.findViewById(R.id.report_studentfname);
            report_studentlname = itemView.findViewById(R.id.report_studentlname);
            report_studentid = itemView.findViewById(R.id.report_studentid);
            report_studentid = itemView.findViewById(R.id.report_studentid);
            report = itemView.findViewById(R.id.report);

            edit_report = itemView.findViewById(R.id.edit_report);
            delete_report = itemView.findViewById(R.id.delete_report);
        }
    }

}
