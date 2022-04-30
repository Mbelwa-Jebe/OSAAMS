package com.touchizen.OSAAMS.ui.advisor_faqs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorFaqsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorFaqsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}