package com.touchizen.OSAAMS.ui.student_consultations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Student_consultationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Student_consultationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}