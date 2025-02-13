package com.example.bookcore.views;

import static android.content.ContentValues.TAG;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookcore.R;
import com.example.bookcore.databinding.FragmentFavouritesBinding;
import com.example.bookcore.viewModels.BookViewModel;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {

    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;
    private FragmentFavouritesBinding binding;

    public static FavouritesFragment newInstance() {
        return new FavouritesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        bookAdapter = new BookAdapter(new ArrayList<>());
        binding.recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewFavorites.setAdapter(bookAdapter);

        loadBooks();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadBooks();
    }

    private void loadBooks() {

        Log.d(TAG,"loading favs");

        bookViewModel.loadFavs();
        bookViewModel.getFavBooksLiveData().observe(getViewLifecycleOwner(), books -> {
            Log.d(TAG, books.toString());
            if (!books.isEmpty()) {
                binding.noFavoritesTextView.setVisibility(View.GONE);
                binding.recyclerViewFavorites.setVisibility(View.VISIBLE);
                bookAdapter.setBooks(books);
            } else {
                Log.d(TAG,"~~~~~~ shes empty ~~~~~~~~~~~~~~~~");
                binding.noFavoritesTextView.setVisibility(View.VISIBLE);
                binding.recyclerViewFavorites.setVisibility(View.GONE);
            }
        });
    }
}