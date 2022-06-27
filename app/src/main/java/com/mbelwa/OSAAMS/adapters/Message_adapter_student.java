package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Message;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Message_adapter_student extends RecyclerView.Adapter<Message_adapter_student.ViewHolder> {
    List<Message> list;
    Context context;
    private int SELF = 786;



    public Message_adapter_student(Context context, List<Message> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        Message message = list.get(position);

        if (message.getMessage_from().equals("student") ) {
            return SELF;
        }

        return position;
    }
    @NonNull
    @Override
    public Message_adapter_student.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView;
       //checking if it is self message

       if (viewType == SELF){
           itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_self, parent, false);
       }
       else {
           itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_other, parent, false);

       }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Message_adapter_student.ViewHolder holder, int position) {

        Message message = list.get(position);
        holder.textViewMessage.setText(message.getContent());
        holder.textViewTime.setText(message.getTimestamp());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewMessage;
        public TextView textViewTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMessage = (TextView) itemView.findViewById(R.id.message);
            textViewTime = (TextView) itemView.findViewById(R.id.timestamp);
        }
    }
}
