package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Advisor;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Advisor_adapter extends RecyclerView.Adapter<Advisor_adapter.ViewHolder> {

    Context context;
    List<Advisor> list;

    public Advisor_adapter(Context context, List<Advisor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Advisor_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View advisor_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advisor_row,parent,false);
        return new ViewHolder(advisor_row);
    }

    @Override
    public void onBindViewHolder(@NonNull Advisor_adapter.ViewHolder holder, int position) {

        Advisor advisor = list.get(position);
        holder.advisor_fname.setText(advisor.getAdvisor_fname());
        holder.advisor_lname.setText(advisor.getAdvisor_lname());
        holder.advisor_id.setText(advisor.getContacts());
        holder.advisor_office.setText(advisor.getAdvisor_office());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView advisor_fname,advisor_lname,advisor_id,advisor_office;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            advisor_fname = itemView.findViewById(R.id.advisor_fname);
            advisor_lname = itemView.findViewById(R.id.advisor_lname);
            advisor_id = itemView.findViewById(R.id.advisor_id);
            advisor_office = itemView.findViewById(R.id.advisor_office);

        }
    }
}
