package com.mbelwa.OSAAMS.ui.student_notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelwa.OSAAMS.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Student_NotificationsFragment extends Fragment {

    private Student_NotificationsViewModel studentNotificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studentNotificationsViewModel =
                ViewModelProviders.of(this).get(Student_NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        studentNotificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}