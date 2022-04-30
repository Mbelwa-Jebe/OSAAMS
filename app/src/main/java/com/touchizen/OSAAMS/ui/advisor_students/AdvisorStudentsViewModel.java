package com.touchizen.OSAAMS.ui.advisor_students;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorStudentsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorStudentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
