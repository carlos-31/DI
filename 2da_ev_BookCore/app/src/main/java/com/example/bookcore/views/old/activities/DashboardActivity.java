package com.example.bookcore.views.old.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import static android.content.ContentValues.TAG;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookcore.R;
import com.example.bookcore.databinding.ActivityDashboardBinding;
import com.example.bookcore.viewModels.BookViewModel;
import com.example.bookcore.views.BookAdapter;
import com.example.bookcore.views.LoginActivity;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityDashboardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        bookAdapter = new BookAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(bookAdapter);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getBookLiveData().observe(this, books -> bookAdapter.setBooks(books));

        findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookViewModel.logout();
                Intent loginIntent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        findViewById(R.id.favsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(DashboardActivity.this, FavouritesActivity.class);
                startActivity(loginIntent);
            }
        });

        SharedPreferences sharedPref = getSharedPreferences("AppConfig", Context.MODE_PRIVATE);


        findViewById(R.id.themeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"yay");
                boolean isDarkMode = sharedPref.getBoolean("darkMode", false);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("darkMode", !isDarkMode);
                editor.apply();
                boolean darkMode = sharedPref.getBoolean("darkMode", false);
                if (darkMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                recreate();
            }
        });

    }
}