package com.mbelwa.OSAAMS.ui.student_advisor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Student_AdvisorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Student_AdvisorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}