package com.example.bookcore.repositories;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bookcore.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserRepository {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseRef;
    private MutableLiveData<Boolean> registrationStatus = new MutableLiveData<>();

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference("users");
    }

    public LiveData<Boolean> registerUser(User user, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("name", user.getName());
                        userMap.put("phone", user.getPhone());
                        userMap.put("address", user.getAddress());

                        databaseRef.child(userId).setValue(userMap)
                                .addOnCompleteListener(databaseTask -> {
                                    if (databaseTask.isSuccessful()) {
                                        Log.d(TAG, "registerUser: yay");
                                        registrationStatus.setValue(true);
                                    } else {
                                        Log.e(TAG, "registerUser: " + databaseTask.getException() );
                                        registrationStatus.setValue(false);
                                    }
                                });
                    } else {
                        Log.e(TAG, "registerUser: " + task.getException());
                        registrationStatus.setValue(false);
                    }
                });
        return registrationStatus;
    }


}
