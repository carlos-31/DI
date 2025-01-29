package com.example.bookcore.repositories;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bookcore.models.Book;
import com.example.bookcore.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class UserRepository {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseRef;
    private DatabaseReference userFavoritesRef;
    private MutableLiveData<Boolean> registrationStatus = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private MutableLiveData<List<Book>> favBooks = new MutableLiveData<>();

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference("users");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userFavoritesRef = FirebaseDatabase.getInstance()
                    .getReference("users/" + user.getUid() + "/favourites");
        }

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

    public LiveData<Boolean> loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "logged in");
                        loginStatus.setValue(true);
                    } else {
                        Log.e(TAG, "error login: " + task.getException());
                        loginStatus.setValue(false);
                    }
                });
        return loginStatus;
    }

    public void logoutUser(){
        mAuth.signOut();
    }

    public void addFavourite(String id) {
        //userFavoritesRef.child(id).setValue(true);
        userFavoritesRef.child(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    Boolean isFavorite = task.getResult().getValue(Boolean.class);
                    if (isFavorite != null && isFavorite) {
                        userFavoritesRef.child(id).setValue(false);
                        Log.d(TAG, "removed book id: " + id + " from favs");
                    } else {
                        userFavoritesRef.child(id).setValue(true);
                        Log.d(TAG, "added book id: " + id + " from favs");
                    }
                } else {
                    userFavoritesRef.child(id).setValue(true);
                    Log.d(TAG, "added book id: " + id + " from favs");
                }
            } else {
                Log.e(TAG, "Error: " + task.getException());
            }
        });
    }

    public boolean checkFav(String id){
        return Boolean.TRUE.equals(userFavoritesRef.child(id).get().getResult().getValue(Boolean.class));
    }


}
