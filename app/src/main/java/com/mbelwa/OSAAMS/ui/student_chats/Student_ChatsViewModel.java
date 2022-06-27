package com.mbelwa.OSAAMS.ui.student_chats;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

public class Student_ChatsViewModel extends ViewModel implements LifecycleObserver {

    private MutableLiveData<String> mText;

    public Student_ChatsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }
@OnLifecycleEvent(Lifecycle.Event.ON_STOP)
void fun_stop(){


}
    public LiveData<String> getText() {
        return mText;
    }
}