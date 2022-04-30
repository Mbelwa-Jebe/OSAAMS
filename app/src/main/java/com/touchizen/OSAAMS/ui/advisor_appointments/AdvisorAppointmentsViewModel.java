package com.touchizen.OSAAMS.ui.advisor_appointments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorAppointmentsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorAppointmentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}