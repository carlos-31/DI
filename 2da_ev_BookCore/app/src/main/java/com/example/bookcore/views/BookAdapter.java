package com.example.bookcore.views;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.bookcore.R;
import com.example.bookcore.databinding.ItemBookBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookcore.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_book,
                parent,
                false
        );
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        int width = 200;
        String coverUrl = book.getCover_url();
        Log.d(TAG, "onBindViewHolder: "+ book.getTitle());
        Log.d(TAG, "link: " + book.getCover_url());
        Picasso.get()
                .load(coverUrl)
                .resize(width, (int) (width * 1.6))
                .into(holder.binding.bookCover);

        holder.itemView.setOnClickListener(v -> {
            Log.d(TAG, "link: " + book.getTitle() + book.getCover_url());
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            Log.d(TAG, " ~~~~~~~~~~~~~~id: " + position);
            intent.putExtra("bookId", book.getId());
            intent.putExtra("url", book.getCover_url());
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("synopsis", book.getSynopsis());
            v.getContext().startActivity(intent);
        });

        holder.bind(book);

    }

    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        private final ItemBookBinding binding;

        public BookViewHolder(@NonNull ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Book book) {
            binding.setBook(book);
            binding.executePendingBindings();
        }
    }
}
