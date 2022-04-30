package com.touchizen.OSAAMS.ui.advisor_chats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorChatsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorChatsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}