package com.cs428.app.bookapp.networking;

import android.util.Log;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.model.User;
import com.cs428.app.bookapp.networking.customSerializers.BookDeserializer;
import com.cs428.app.bookapp.networking.customSerializers.PersonDeserializer;
import com.cs428.app.bookapp.networking.customSerializers.UserDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mgard on 3/2/2018.
 */

public class Serializer {

    public Serializer(){}

    public String serializeUser(User user) {
        return null;
    }

    public Person deserializePerson(String jsonPerson) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Person.class, new PersonDeserializer());
        mapper.registerModule(module);
        Log.d("DEBUG PERSON DESERLZ", "deserializePerson: "+ jsonPerson);
        try {
            return mapper.readValue(jsonPerson, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
        List<Book> books = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        Log.d("DSZ books", "deserializeListOfBooks: " + jsonList);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        try {
            final JsonNode arrNode = mapper.readTree(jsonList).get("books");
            if (arrNode.isArray()) {
                for (final JsonNode objNode : arrNode) {
                    Log.d("DEBUG", "deserializeListOfBooks: "+ objNode);
                    books.add(deserializeBook(objNode.toString()));
                }
            }
            return books;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> deserializeListOfUsers(String jsonList) {
        List<User> users = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode root = mapper.readTree(jsonList);
            Iterator<JsonNode> els = root.elements();

            while(els.hasNext()) {
                JsonNode next = els.next();
                users.add(deserializeUser(next.asText()));
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Person> deserializeListOfPersons(String jsonList) {
        List<Person> people = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();


        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        try {

//            List<Person> personList =
//                    mapper.readValue(jsonList, new TypeReference<List<Person>>() {});


            JsonNode root = mapper.readTree(jsonList);
            JsonNode list = root.path("users");
            Log.d("DEBUG", "deserializeListOfPersons: "+ list.asText());
            people = mapper.readValue(list.asText(), new TypeReference<List<Person>>(){});
            for (Person p : people){
                Log.d("DEBUG PERSON DESERIALIZE", "name person: " + p.getName());
            }
//            Log.d("DEBUG PERSON DESERIALIZE", "deserializeListOfUsers: "+ list);
//            while(els.hasNext()) {
//                JsonNode next = els.next();
//                Log.d("DEBUG", "deserializeListOfPersons: "+ next.asText());
//                people.add(deserializePerson(next.asText()));
//            }
            return people;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
