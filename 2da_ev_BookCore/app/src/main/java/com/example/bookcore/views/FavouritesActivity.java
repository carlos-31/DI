package com.example.bookcore.views;

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
import com.example.bookcore.databinding.ActivityFavouritesBinding;
import com.example.bookcore.viewModels.BookViewModel;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {
    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;
    private ActivityFavouritesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourites);

        bookAdapter = new BookAdapter(new ArrayList<>());
        binding.recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewFavorites.setAdapter(bookAdapter);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        loadBooks();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardIntent = new Intent(FavouritesActivity.this, DashboardActivity.class);
                startActivity(dashboardIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBooks();
    }

    private void loadBooks(){
        bookViewModel.loadFavs();
        bookViewModel.getFavBooksLiveData().observe(this, books -> {
            if (books != null) {
                binding.noFavoritesTextView.setVisibility(View.GONE);
                bookAdapter.setBooks(books);
            } else {
                binding.noFavoritesTextView.setVisibility(View.VISIBLE);
            }
        });
    }
}