package com.mbelwa.OSAAMS.ui.advisor_dashboard;

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

public class AdvisorDashboardFragment extends Fragment {

    private AdvisorDashboardViewModel advisorDashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorDashboardViewModel =
                ViewModelProviders.of(this).get(AdvisorDashboardViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        advisorDashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}