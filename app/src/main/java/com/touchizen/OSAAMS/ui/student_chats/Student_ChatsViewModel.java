package com.touchizen.OSAAMS.ui.student_chats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Student_ChatsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Student_ChatsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}