package com.example.bookcore.viewModels;


import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookcore.models.Book;
import com.example.bookcore.repositories.BookRepository;
import com.example.bookcore.repositories.UserRepository;

public class DetailViewModel extends ViewModel {
    private final MutableLiveData<Book> detailLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> favStatus = new MutableLiveData<>();
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public DetailViewModel() {
        userRepository = new UserRepository();
        bookRepository = new BookRepository();
    }

    public LiveData<Book> getDetailLiveData() {
        return detailLiveData;
    }

    public void loadBook(String id) {
        bookRepository.getBookById(id, detailLiveData);
    }

    public void addFavourite(String id){
        userRepository.addFavourite(id, favStatus);
    }

    public void checkFav(){
        Book book = detailLiveData.getValue();
        if (book != null) {
            userRepository.checkFav(book.getId(), favStatus);
            Log.d(TAG, "checked book: " + book.getTitle());
            Log.d(TAG, "fav?: " + book.getTitle() + favStatus.getValue());
        }
    }

    public LiveData<Boolean> getFavStatus() {
        return favStatus;
    }
}