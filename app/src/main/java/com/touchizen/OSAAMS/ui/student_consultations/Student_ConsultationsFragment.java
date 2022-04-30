package com.touchizen.OSAAMS.ui.student_consultations;

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

public class Student_ConsultationsFragment extends Fragment {

    private Student_consultationsViewModel consultationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consultationsViewModel =
                ViewModelProviders.of(this).get(Student_consultationsViewModel.class);
        View root = inflater.inflate(R.layout.student_fragment_consultations, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        consultationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}