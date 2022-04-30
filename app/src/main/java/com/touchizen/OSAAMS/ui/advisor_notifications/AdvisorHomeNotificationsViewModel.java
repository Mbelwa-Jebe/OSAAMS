package com.touchizen.OSAAMS.ui.advisor_notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorHomeNotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorHomeNotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}