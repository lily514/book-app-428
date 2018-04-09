//package com.cs428.app.bookapp.networking.mock;
//
//import com.cs428.app.bookapp.interfaces.IServerCommunicator;
//import com.cs428.app.bookapp.interfaces.OnReviewedBooksTaskComplete;
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
// *  THIS CLASS IS FOR TESTING PURPOSES ONLY!!!
// */
//
//public class Mock_ServerCommunicator implements IServerCommunicator {
//    @Override
//    public List<User> getUsers() {
//        List<User> users = new ArrayList<>();
//        for(int i = 0; i < 100; i++) {
//            User u = new User("user" + i, Integer.toString(i));
//            users.add(u);
//        }
//        return users;
//    }
//
//    @Override
//    public void loadUser(String name) {
//
//    }
//
//    @Override
//    public List<User> getFriends(String id) {
//        List<User> users = new ArrayList<>();
//        for(int i = 0; i < 50; i++) {
//            User u = new User("user" + i, Integer.toString(i));
//            users.add(u);
//        }
//        return users;
//    }
//
//    @Override
//    public List<Book> getUsersFriendsReadingList(String id) {
//        List<Book> books = new ArrayList<>();
//        for(int i = 0; i < 500; i++) {
//            Book b = new Book("book" + i, "author" + i, Integer.toString(i),null);
//            books.add(b);
//        }
//        return books;
//    }
//
//    @Override
//    public void updateUser(String username) {
//
//    }
//
//    @Override
//    public boolean addRecommendation(String id, Book book) {
//        return true;
//    }
//
//    @Override
//    public boolean addToReadingList(String id, Book book) {
//        return true;
//    }
//
//    @Override
//    public boolean followUser(String myId, String otherId) {
//        return true;
//    }
//
//    @Override
//    public List<Book> searchForBook(String searchString) {
//        List<Book> books = new ArrayList<>();
//        for(int i = 0; i < 20; i++) {
//            Book b = new Book("book" + i, "author" + i, Integer.toString(i),null);
//            books.add(b);
//        }
//        return books;
//    }
//
//    @Override
//    public Book getBookById(String id, OnReviewedBooksTaskComplete listener) {
//        return new Book("book", "author", id, null);
//    }
//
//    @Override
//    public Book searchBookByTitle(String title, OnSearchTaskComplete listener) {
//        return null;
//    }
//
//    @Override
//    public boolean rateBook(String userId, String bookId, int rating) {
//        return true;
//    }
//
//    @Override
//    public void setUserToken(String token) {
//
//    }
//
//    @Override
//    public String getUserToken() {
//        return null;
//    }
//}
