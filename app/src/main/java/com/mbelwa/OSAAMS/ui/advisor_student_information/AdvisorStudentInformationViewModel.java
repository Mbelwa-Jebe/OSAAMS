package com.mbelwa.OSAAMS.ui.advisor_student_information;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorStudentInformationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorStudentInformationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}