package com.mbelwa.OSAAMS.ui.student_faqs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Student_FaqsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Student_FaqsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}