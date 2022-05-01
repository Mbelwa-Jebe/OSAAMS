package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Report;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Report_adapter_advisor extends RecyclerView.Adapter<Report_adapter_advisor.ViewHolder> {

    Context context;
    List<Report> list;

    public Report_adapter_advisor(Context context, List<Report> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Report_adapter_advisor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View report_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advisor_report_row,parent,false);
        return new ViewHolder(report_row);
    }

    @Override
    public void onBindViewHolder(@NonNull Report_adapter_advisor.ViewHolder holder, int position) {
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
        public ImageButton edit_report,delete_report;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            report_date = itemView.findViewById(R.id.ad_report_date);
            report_studentfname = itemView.findViewById(R.id.ad_report_studentfname);
            report_studentlname = itemView.findViewById(R.id.ad_report_studentlname);
            report_studentid = itemView.findViewById(R.id.ad_report_studentid);
            report_studentid = itemView.findViewById(R.id.ad_report_studentid);
            report = itemView.findViewById(R.id.ad_report);

            edit_report = itemView.findViewById(R.id.edit_report);
            delete_report = itemView.findViewById(R.id.delete_report);
        }
    }

}
