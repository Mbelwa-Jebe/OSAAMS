package com.mbelwa.OSAAMS.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Student_adapter extends RecyclerView.Adapter<Student_adapter.ViewHolder>{

    List<Student> list;
    Context context;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

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
        final Student student = list.get(position);
        holder.registration_no.setText(student.getRegistration_no());
        holder.student_fname.setText(student.getStudent_fname());
        holder.student_lname.setText(student.getStudent_lname());
        holder.programme.setText(student.getProgramme());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder = new AlertDialog.Builder(context);

                inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.student_transcript,null);

                Button dismis = (Button) view.findViewById(R.id.dismis_transcript);
                TextView TRanscript_id = (TextView) view.findViewById(R.id.student_transcript);

                TRanscript_id.setText(student.getRegistration_no().toString());
                alertDialogBuilder.setView(view);
                dialog = alertDialogBuilder.create();
                dialog.show();

                dismis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });




            }
        });

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
