package com.touchizen.OSAAMS.ui.student_advisor;

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

public class Student_AdvisorFragment extends Fragment {

    private Student_AdvisorViewModel student_advisorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        student_advisorViewModel =
                ViewModelProviders.of(this).get(Student_AdvisorViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_advisor, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        student_advisorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}