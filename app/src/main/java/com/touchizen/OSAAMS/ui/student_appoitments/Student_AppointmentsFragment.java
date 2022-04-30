package com.touchizen.OSAAMS.ui.student_appoitments;

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

public class Student_AppointmentsFragment extends Fragment {

    private student_AppointmentsViewModel student_appointmentsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        student_appointmentsViewModel =
                ViewModelProviders.of(this).get(student_AppointmentsViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_appointments, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        student_appointmentsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}