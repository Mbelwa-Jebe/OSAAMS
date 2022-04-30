package com.touchizen.OSAAMS.ui.student_chats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.touchizen.OSAAMS.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Student_ChatsFragment extends Fragment {

    private Student_ChatsViewModel student_chatsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        student_chatsViewModel =
                ViewModelProviders.of(this).get(Student_ChatsViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_chats, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        student_chatsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}