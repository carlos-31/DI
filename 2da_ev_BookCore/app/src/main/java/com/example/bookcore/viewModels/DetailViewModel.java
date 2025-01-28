package com.example.bookcore.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookcore.models.Book;
import com.example.bookcore.repositories.BookRepository;

public class DetailViewModel extends ViewModel {
    private final MutableLiveData<Book> detailLiveData = new MutableLiveData<>();
    private final BookRepository bookRepository;

    public DetailViewModel() {
        bookRepository = new BookRepository();
    }

    public LiveData<Book> getDetailLiveData() {
        return detailLiveData;
    }

    public void loadBook(String id) {
        bookRepository.getBookById(id, detailLiveData);
    }
}