package com.touchizen.OSAAMS.ui.advisor_home;

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

public class AdvisorHomeFragment extends Fragment {

    private AdvisorHomeViewModel advisorHomeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorHomeViewModel =
                ViewModelProviders.of(this).get(AdvisorHomeViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        advisorHomeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}