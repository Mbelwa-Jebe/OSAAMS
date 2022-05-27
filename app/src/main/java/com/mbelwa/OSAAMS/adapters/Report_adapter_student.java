package com.mbelwa.OSAAMS.adapters;

import android.app.AlertDialog;
import android.content.Context;
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
import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Report_adapter_student extends RecyclerView.Adapter<Report_adapter_student.ViewHolder> {

    Context context;
    List<Report> list;
    public static  final String KEY_AP_ID="consultation_id";
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    public int report_id;

    public Report_adapter_student(Context context, List<Report> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Report_adapter_student.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View student_report_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advisor_report_row,parent,false);
        return new ViewHolder(student_report_row);
    }

    @Override
    public void onBindViewHolder(@NonNull final Report_adapter_student.ViewHolder holder, int position) {
        Report report = list.get(position);
        holder.report_date.setText(report.getReport_date());
        holder.report_studentfname.setText(report.getStudent_fname());
        holder.report_studentlname.setText(report.getStudent_lname());
        holder.report_studentid.setText(report.getStudent_id());
        holder.report.setText(report.getReport());

      holder.delete_report.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Toast.makeText(context,"deleted",Toast.LENGTH_SHORT).show();

               report_id = holder.getAdapterPosition();
               deleteFromServer(list.get(report_id).getConsultation_id());

            }
        });

      holder.edit_report.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(context,"edited",Toast.LENGTH_SHORT).show();

          }
      });

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

    public void deleteFromServer(final String reprt_id){
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


                String delete_ap = "http://192.168.137.1:88/AcademicAdvisor/delete_report.php";
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
                        map.put(KEY_AP_ID,reprt_id);
                        return map;
                    }

                };
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);
                dialog.dismiss();
                list.remove(report_id);
                notifyItemRemoved(report_id);
                notifyItemRangeChanged(report_id,list.size());



            }

        });



    }

}
