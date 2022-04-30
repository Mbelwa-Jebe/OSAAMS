package com.touchizen.OSAAMS.ui.advisor_home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorHomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}