//package com.example.bookcore.views;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.example.bookcore.R;
//import com.example.bookcore.databinding.FragmentDashboardBinding;
//import com.example.bookcore.viewModels.BookViewModel;
//import com.example.bookcore.models.Book;
//import com.google.firebase.FirebaseApp;
//
//import java.util.ArrayList;
//
//import androidx.appcompat.app.AppCompatDelegate;
//import androidx.core.view.ViewCompat;
//import androidx.core.graphics.Insets;
//import androidx.core.view.WindowInsetsCompat;
//
//public class DashboardFragment extends Fragment {
//
//    private BookViewModel bookViewModel;
//    private BookAdapter bookAdapter;
//    private FragmentDashboardBinding binding;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        binding = FragmentDashboardBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//
//        bookAdapter = new BookAdapter(new ArrayList<>());
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.recyclerView.setAdapter(bookAdapter);
//
//        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
//        bookViewModel.getBookLiveData().observe(getViewLifecycleOwner(), books -> bookAdapter.setBooks(books));
//
//        FirebaseApp.initializeApp(requireContext());
//        bookViewModel.initializeFavourites();
//    }
//}

package com.example.bookcore.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookcore.databinding.FragmentDashboardBinding;
import com.example.bookcore.viewModels.BookViewModel;
import com.example.bookcore.viewModels.DashboardViewModel;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;
    private FragmentDashboardBinding binding;
    private DashboardViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookAdapter = new BookAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(bookAdapter);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getBookLiveData().observe(getViewLifecycleOwner(), books -> bookAdapter.setBooks(books));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}