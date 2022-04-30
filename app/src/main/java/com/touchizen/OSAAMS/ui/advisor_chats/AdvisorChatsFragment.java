package com.touchizen.OSAAMS.ui.advisor_chats;

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

public class AdvisorChatsFragment extends Fragment {

    private AdvisorChatsViewModel advisorChatsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorChatsViewModel =
                ViewModelProviders.of(this).get(AdvisorChatsViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_chats, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        advisorChatsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}