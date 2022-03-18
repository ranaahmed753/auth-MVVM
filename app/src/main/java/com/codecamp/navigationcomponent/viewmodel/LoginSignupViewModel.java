package com.codecamp.navigationcomponent.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.codecamp.navigationcomponent.model.FirebaseRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginSignupViewModel extends AndroidViewModel {
    private FirebaseRepository firebaseRepository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> loggedOutMutableLiveData;

    public LoginSignupViewModel(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository(application);
        userMutableLiveData = firebaseRepository.getUserMutableLiveData();
        loggedOutMutableLiveData = firebaseRepository.getLoggedOutMutableLiveData();
    }
    public void signup(String email,String password){
        firebaseRepository.signup(email,password);
    }
    public void login(String email,String password){
        firebaseRepository.login(email,password);
    }
    public void logout(){
        firebaseRepository.logout();
    }
    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }
}
