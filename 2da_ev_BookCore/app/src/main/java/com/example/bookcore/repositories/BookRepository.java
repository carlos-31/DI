package com.example.bookcore.repositories;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.bookcore.models.Book;
import com.google.firebase.database.*;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private final DatabaseReference bookRef;

    public BookRepository() {
        bookRef = FirebaseDatabase.getInstance().getReference("books");
    }

    public void getBooks(MutableLiveData<List<Book>> bookLiveData) {
        bookRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Book> books = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Book book = child.getValue(Book.class);
                    book.setId(child.getKey());
                    books.add(book);
                }
                bookLiveData.setValue(books);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void getBookById(String bookId, MutableLiveData<Book> detailLiveData) {
        bookRef.child(bookId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Book book = snapshot.getValue(Book.class);
                if (book != null) {
                    book.setId(bookId);
                    detailLiveData.setValue(book);
                } else {
                    detailLiveData.setValue(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void getFavBooks(MutableLiveData<List<Integer>> favIndices, MutableLiveData<List<Book>> favoriteBooksLiveData) {
        favIndices.observeForever(new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> favoriteIndices) {
                List<Book> favoriteBooks = new ArrayList<>();

                for (int i = 0; i < favoriteIndices.size(); i++) {
                    Integer index = favoriteIndices.get(i);

                    bookRef.child(String.valueOf(index)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            Book book = snapshot.getValue(Book.class);
                            if (book != null) {
                                book.setId(snapshot.getKey());
                                favoriteBooks.add(book);
                            }

                            if (favoriteBooks.size() == favoriteIndices.size()) {
                                favoriteBooksLiveData.setValue(favoriteBooks);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            Log.e(TAG, "error fetching book: " + error.getMessage());
                        }
                    });
                }
            }
        });
    }


}
