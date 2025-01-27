package com.example.bookcore.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookcore.repositories.UserRepository;

public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;
    private MutableLiveData<Boolean> wasSuccessful = new MutableLiveData<>();

    public LoginViewModel(){
        userRepository = new UserRepository();
    }

    public MutableLiveData<Boolean> getWasSuccess(){
        return wasSuccessful;
    }

    public void login(String email, String password){
        userRepository.loginUser(email, password).observeForever(isSuccess -> {
            wasSuccessful.setValue(isSuccess);
        });

    }



}
