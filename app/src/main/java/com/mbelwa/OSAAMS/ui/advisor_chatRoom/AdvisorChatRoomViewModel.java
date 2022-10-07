package com.mbelwa.OSAAMS.ui.advisor_chatRoom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdvisorChatRoomViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdvisorChatRoomViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}