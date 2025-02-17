package com.example.bookcore.views;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.google.android.material.switchmaterial.SwitchMaterial;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookcore.R;
import com.example.bookcore.viewModels.ProfileViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;

public class ProfileFragment extends Fragment {

    private EditText currentPasswordEditText, newPasswordEditText;
    private SwitchMaterial darkModeSwitch;
    private ProfileViewModel profileViewModel;

    public ProfileFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        currentPasswordEditText = view.findViewById(R.id.currentPasswordEditText);
        newPasswordEditText = view.findViewById(R.id.newPasswordEditText);
        darkModeSwitch = view.findViewById(R.id.darkModeSwitch);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);


        SharedPreferences prefs = requireActivity().getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean("darkMode", false);
        darkModeSwitch.setChecked(isDarkMode);


        Button changePasswordButton = view.findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(v -> changePassword());


        darkModeSwitch.setOnCheckedChangeListener((compoundButton, checked) -> toggleDarkMode(checked));

        return view;
    }

    private void changePassword() {
        String currentPass = currentPasswordEditText.getText().toString();
        String newPass = newPasswordEditText.getText().toString();

        if (currentPass.isEmpty() || newPass.isEmpty()) {
            Toast.makeText(getContext(), "Please entre current and new passwords", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG,"aaaaaaaaaaaaaaaaaaaaaa");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG,"user info: " + user.toString());
        if (user != null) {
            Log.d(TAG,"aaaaaaaaaaaaaaaaaaaaaa parte 2");
            AuthCredential credential = EmailAuthProvider
                    .getCredential(user.getEmail(), currentPass);

            Log.d(TAG,"aaaaaaaaaaaaaaaaaaaaaa parte 3");

            user.reauthenticate(credential).addOnCompleteListener(task -> {
                Log.d(TAG,"aaaaaaaaaaaaaaaaaaaaaa parte 4");
                if (task.isSuccessful()) {
                    user.updatePassword(newPass).addOnCompleteListener(updateTask -> {
                        Log.d(TAG,"aaaaaaaaaaaaaaaaaaaaaa parte 5");
                        if (updateTask.isSuccessful()) {
                            Toast.makeText(getContext(), "Password successfully changed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error changing password", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Current password is incorrect", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void toggleDarkMode(boolean enableDarkMode) {
        SharedPreferences prefs = requireActivity().getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        prefs.edit().putBoolean("darkMode", enableDarkMode).apply();

        if (enableDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
