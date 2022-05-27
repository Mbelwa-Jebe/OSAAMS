package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Faqs;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Faqs_adapter extends RecyclerView.Adapter<Faqs_adapter.ViewHolder> {
    Context context;
     List<Faqs> list;

    public Faqs_adapter(Context context, List<Faqs> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Faqs_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View faqs_row = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.student_faqs_row,parent,false);

        return new ViewHolder(faqs_row);
    }

    @Override
    public void onBindViewHolder(@NonNull Faqs_adapter.ViewHolder holder, int position) {

        final Faqs faq = list.get(position);
        holder.faqs_question.setText(faq.getFaqs_question());
        holder.faqs_answer.setText(faq.faqs_answer);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filteredList(ArrayList<Faqs> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView faqs_question,faqs_answer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            faqs_question = itemView.findViewById(R.id.student_faqs_question);
            faqs_answer = itemView.findViewById(R.id.student_faqs_answer);

        }
    }
}
