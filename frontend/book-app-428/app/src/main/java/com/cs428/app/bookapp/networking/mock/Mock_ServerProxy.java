//package com.cs428.app.bookapp.networking.mock;
//
//import com.cs428.app.bookapp.interfaces.IServerProxy;
//import com.cs428.app.bookapp.interfaces.OnHomeBooksTaskComplete;
//import com.cs428.app.bookapp.interfaces.OnReadingBooksTaskComplete;
//import com.cs428.app.bookapp.interfaces.OnSearchTaskComplete;
//import com.cs428.app.bookapp.model.Book;
//import com.cs428.app.bookapp.model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by mgard on 3/10/2018.
// *
// * THIS CLASS IS FOR TESTING PURPOSES ONLY!!!!
// */
//
//public class Mock_ServerProxy implements IServerProxy {
//    @Override
//    public boolean followFriend(User user, String followUsername) {
//        return true;
//    }
//
//    @Override
//    public void searchBook(String searchTerm, OnSearchTaskComplete listener) {
//        List<Book> testBooks = new ArrayList<>();
//        for(int i = 0; i < 100; i++) {
//            Book b = new Book("test" + i, "author" + i, Integer.toString(i), null);
//            testBooks.add(b);
//        }
//    }
//
//    @Override
//    public boolean recommendBook(User user, String bookId) {
//        return true;
//    }
//
//    @Override
//    public boolean rateBook(User user, String bookID, int rating) {
//        return true;
//    }
//
//    @Override
//    public List<Book> getRecommendationFor(User user, OnHomeBooksTaskComplete listener) {
//        List<Book> testBooks = new ArrayList<>();
//        for(int i = 100; i < 200; i++) {
//            Book b = new Book("test" + i, "author" + i, Integer.toString(i), null);
//            testBooks.add(b);
//        }
//        return testBooks;
//    }
//
//    @Override
//    public Book getBookById(String bookId, OnReadingBooksTaskComplete listener) {
//        //todo: needs to be implemented
//        return null;
//    }
//
//    @Override
//    public void initialize() {
//
//    }
//}
