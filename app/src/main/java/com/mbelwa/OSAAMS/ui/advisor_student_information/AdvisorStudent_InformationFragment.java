package com.mbelwa.OSAAMS.ui.advisor_student_information;

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

public class AdvisorStudent_InformationFragment extends Fragment {

    private AdvisorStudentInformationViewModel advisorStudentInformationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        advisorStudentInformationViewModel =
                ViewModelProviders.of(this).get(AdvisorStudentInformationViewModel.class);
        View root = inflater.inflate(R.layout.advisor_fragment_student_information, container, false);

        return root;
    }
}