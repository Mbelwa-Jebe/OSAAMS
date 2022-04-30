package com.touchizen.OSAAMS.ui.student_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Student_HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Student_HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}