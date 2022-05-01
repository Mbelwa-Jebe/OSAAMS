package com.mbelwa.OSAAMS.ui.advisor_students;

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

public class AdvisorStudentsFragment extends Fragment {

    private AdvisorStudentsViewModel advisorstudentsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorstudentsViewModel =
                ViewModelProviders.of(this).get(AdvisorStudentsViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_students, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        advisorstudentsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}