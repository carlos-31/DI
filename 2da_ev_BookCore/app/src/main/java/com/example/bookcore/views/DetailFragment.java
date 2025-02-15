package com.example.bookcore.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookcore.R;
import com.example.bookcore.databinding.FragmentDetailBinding;
import com.example.bookcore.models.Book;
import com.example.bookcore.viewModels.DetailViewModel;
import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {

    private DetailViewModel detailViewModel;
    private FragmentDetailBinding binding;

    public static DetailFragment newInstance(String bookId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("bookId", bookId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        String bookId = getArguments() != null ? getArguments().getString("bookId") : null;

        binding.setViewModel(detailViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        detailViewModel.getFavStatus().observe(getViewLifecycleOwner(), isFavorite -> {
            if (isFavorite != null && isFavorite) {
                binding.favButton.setImageResource(R.drawable.ic_favorite);
            } else {
                binding.favButton.setImageResource(R.drawable.ic_favorite_border);
            }
        });

        binding.favButton.setOnClickListener(v -> {
            if (bookId != null) {
                detailViewModel.addFavourite(bookId);
                String message = detailViewModel.getFavStatus().getValue() != null && detailViewModel.getFavStatus().getValue() ?
                        "Added to Favorites" : "Removed from Favorites";
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        if (bookId != null) {
            detailViewModel.loadBook(bookId);
            detailViewModel.getDetailLiveData().observe(getViewLifecycleOwner(), new Observer<Book>() {
                @Override
                public void onChanged(Book book) {
                    if (book != null) {
                        Picasso.get().load(book.getCover_url()).resize(670, (int) (670 * 1.6))
                                .into(binding.coverImageView);
                    }
                }
            });
        }

        //binding.backButton.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        return binding.getRoot();
    }
}
