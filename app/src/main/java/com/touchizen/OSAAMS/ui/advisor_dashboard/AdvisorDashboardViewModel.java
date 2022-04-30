package com.touchizen.OSAAMS.ui.advisor_dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorDashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorDashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}