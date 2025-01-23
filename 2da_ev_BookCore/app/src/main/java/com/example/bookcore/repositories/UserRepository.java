package com.example.bookcore.repositories;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import com.example.bookcore.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserRepository {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseRef;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference("users");
    }

    public void registerUser(String email, String password, User user) {
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
                                    } else {
                                        Log.e(TAG, "registerUser: " + databaseTask.getException() );
                                    }
                                });
                    } else {
                        Log.e(TAG, "registerUser: " + task.getException());
                    }
                });
    }
}
