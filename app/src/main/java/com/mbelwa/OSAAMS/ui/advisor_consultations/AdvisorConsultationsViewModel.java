package com.mbelwa.OSAAMS.ui.advisor_consultations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorConsultationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorConsultationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}