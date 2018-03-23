package com.cs428.app.bookapp.networking;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;
import com.cs428.app.bookapp.networking.customSerializers.BookDeserializer;
import com.cs428.app.bookapp.networking.customSerializers.UserDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.List;

/**
 * Created by mgard on 3/2/2018.
 */

public class Serializer {

    public Serializer(){}

    public String serializeUser(User user) {
        return null;
    }

    public User deserializeUser(String jsonUser) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(User.class, new UserDeserializer());
        mapper.registerModule(module);

        try {
            return mapper.readValue(jsonUser, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String serializeBook(Book book) {
        return null;
    }

    public Book deserializeBook(String jsonBook) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Book.class, new BookDeserializer());
        mapper.registerModule(module);

        try {
            return mapper.readValue(jsonBook, Book.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> deserializeListOfBooks(String jsonList) {
        return null;
    }

    public List<User> deserializeListOfUsers(String jsonList) {
        return null;
    }



}
