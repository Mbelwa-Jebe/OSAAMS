package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Student_adapter extends RecyclerView.Adapter<Student_adapter.ViewHolder>{

    List<Student> list;
    Context context;

    public Student_adapter(Context context, List<Student> list ) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Student_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View student_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_row,parent,false);
        return new ViewHolder(student_row);
    }

    @Override
    public void onBindViewHolder(@NonNull Student_adapter.ViewHolder holder, int position) {
        Student student = list.get(position);
        holder.registration_no.setText(student.getRegistration_no());
        holder.student_fname.setText(student.getStudent_fname());
        holder.student_lname.setText(student.getStudent_lname());
        holder.programme.setText(student.getProgramme());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView registration_no,student_fname,student_lname,programme;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            registration_no = itemView.findViewById(R.id.registration_no);
            student_fname = itemView.findViewById(R.id.student_fname);
            student_lname = itemView.findViewById(R.id.student_lname);
            programme = itemView.findViewById(R.id.programme);
        }
    }
}
