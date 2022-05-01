package com.mbelwa.OSAAMS.ui.student_appoitments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class student_AppointmentsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public student_AppointmentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}