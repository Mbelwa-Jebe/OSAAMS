package com.mbelwa.OSAAMS.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mbelwa.OSAAMS.R;
import com.mbelwa.OSAAMS.models.Advisor_threads;
import com.mbelwa.OSAAMS.ui.advisor_chatRoom.AdvisorChatRoomFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Advisor_thread_adapter extends RecyclerView.Adapter<Advisor_thread_adapter.ViewHolder> {
    List<Advisor_threads> list;
    Context context;
    public FragmentCommunication mCommunicatior;

    public Advisor_thread_adapter(Context context, List<Advisor_threads> list,FragmentCommunication communication){
 this.list = list;
 this.context = context;
 mCommunicatior = communication;
    }

    @NonNull
    @Override
    public Advisor_thread_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View thread_row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advisor_message_threads, parent, false);
        ViewHolder holder = new ViewHolder (thread_row,mCommunicatior);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Advisor_thread_adapter.ViewHolder holder, final int position) {

        final Advisor_threads advisor_threads = list.get(position);
        holder.student_fname.setText(advisor_threads.getStudent_fname());
        holder.student_lname.setText(advisor_threads.getStudent_lname());
        holder.content.setText(advisor_threads.getContent());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"student" + advisor_threads.getStudent_id().toString(),Toast.LENGTH_SHORT).show();
//                Bundle bundle = new Bundle();
//                bundle.putString("REG_NO",advisor_threads.getStudent_id().toString());
//                AdvisorFaqsFragment advisorFaqsFragment = new AdvisorFaqsFragment();
//                advisorFaqsFragment.setArguments(bundle);
//                mCommunication.respond(position,advisor_threads.getStudent_id().toString());
////                openChatroom();
//            }
//         });
              AdvisorChatRoomFragment advisorChatRoomFragment = new AdvisorChatRoomFragment();
              Bundle bundle = new Bundle();
              bundle.putString("REG_NO",list.get(position).getStudent_id());
              advisorChatRoomFragment.setArguments(bundle);

   }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView student_fname,student_lname,content;
FragmentCommunication mCommunication;

        public ViewHolder(@NonNull View itemView,FragmentCommunication Communicator) {
            super(itemView);


            student_fname = itemView.findViewById(R.id.thread_student_fname);
            student_lname = itemView.findViewById(R.id.thread_student_lname);
            content = itemView.findViewById(R.id.thread_last_message);
            mCommunication = Communicator;

            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCommunication.respond(getAdapterPosition(),list.get(getAdapterPosition()).getStudent_id().toString());
                }
            });
        }
    }

    public void openChatroom(){


    }
}

