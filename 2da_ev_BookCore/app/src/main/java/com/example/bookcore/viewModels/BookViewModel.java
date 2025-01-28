package com.example.bookcore.viewModels;

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

    public BookViewModel() {
        bookRepository = new BookRepository();
        loadBooks();
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

}