package com.example.bookcore.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookcore.models.User;
import com.example.bookcore.repositories.UserRepository;

public class RegisterViewModel extends ViewModel {
    private UserRepository userRepository;
    private MutableLiveData<String> validationMessage = new MutableLiveData<>();
    private MutableLiveData<Character> isRegistered = new MutableLiveData<>();

    public RegisterViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<String> getValidationMessage() {
        return validationMessage;
    }

    public LiveData<Character> getRegistrationStatus() {
        return isRegistered;
    }

    public void validateAndRegister(String name, String email, String password, String password2, String phone, String address) {
        isRegistered.setValue('p');

        if (name.isEmpty()) {
            validationMessage.setValue("Error: Name is missing");
            return;
        }
        if (!email.contains("@")) {
            validationMessage.setValue("Error: Email not valid");
            return;
        }
        if (!password.equals(password2) || password.isEmpty()) {
            validationMessage.setValue("Error: Password don't match");
            return;
        }
        if (phone.length() != 9) {
            validationMessage.setValue("Error: Phone number not valid");
            return;
        }
        if (address.isEmpty()) {
            validationMessage.setValue("Error: Address is missing");
            return;
        }

        User user = new User(name, phone, address);

        LiveData<Boolean> registrationResult = userRepository.registerUser(user, email, password);
        registrationResult.observeForever(success -> {
            if (success) {
                isRegistered.setValue('s');
            } else {
                isRegistered.setValue('f');
            }
        });
    }

}
