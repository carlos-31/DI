package com.example.bookcore.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookcore.R;
import com.example.bookcore.databinding.ActivityDashboardBinding;
import com.example.bookcore.viewModels.BookViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;
    private Context context;

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
    }
}