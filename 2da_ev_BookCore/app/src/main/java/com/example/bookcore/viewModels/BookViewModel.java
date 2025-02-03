package com.example.bookcore.viewModels;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookcore.models.Book;
import com.example.bookcore.repositories.BookRepository;
import com.example.bookcore.repositories.UserRepository;

import java.util.List;

public class BookViewModel extends ViewModel {
    private final MutableLiveData<List<Book>> bookLiveData = new MutableLiveData<>();
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final MutableLiveData<List<Integer>> favourites = new MutableLiveData<>();
    private final MutableLiveData<List<Book>> favBooks = new MutableLiveData<>();

    public BookViewModel() {
        bookRepository = new BookRepository();
        userRepository = new UserRepository();
        loadBooks();
        loadFavs();
    }

    public LiveData<List<Book>> getBookLiveData() {
        return bookLiveData;
    }

    private void loadBooks() {
        bookRepository.getBooks(bookLiveData);
    }

    public void logout(){
        new UserRepository().logoutUser();
    }

    public void getFavs(){
        userRepository.getFavorites(favourites);
    }

    public void loadFavs(){
        getFavs();
        Log.d(TAG, "loading favs en bookviemodel");
        bookRepository.getFavBooks(favourites, favBooks);
    }

    public LiveData<List<Book>> getFavBooksLiveData() {
        return favBooks;
    }

}