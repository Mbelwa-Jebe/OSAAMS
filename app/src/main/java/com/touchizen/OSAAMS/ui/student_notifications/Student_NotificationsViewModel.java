package com.touchizen.OSAAMS.ui.student_notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Student_NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Student_NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}