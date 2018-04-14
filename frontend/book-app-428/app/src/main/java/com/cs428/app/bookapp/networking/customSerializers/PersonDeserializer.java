package com.cs428.app.bookapp.networking.customSerializers;

import android.util.Log;

import com.cs428.app.bookapp.model.Person;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mgard on 4/11/2018.
 */

public class PersonDeserializer extends StdDeserializer<Person> {

    public PersonDeserializer() {
        this(null);
    }

    public PersonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Person deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("username").asText();
        String bio = node.get("bio").asText();
        String url = node.get("image_url").asText();
        String TAG = "PERSON DESERIALIZING";
        Log.d(TAG, "deserialize: name" + name);
        Log.d(TAG, "deserialize: bio" + bio);
        Log.d(TAG, "deserialize: url" + url);

        // TODO: Check node.path for the correct node name in the json
        List<String> readingList = new ArrayList<>();
        JsonNode readingNode = node.path("reading_list");
        Iterator<JsonNode> els = readingNode.elements();
        while(els.hasNext()) {
            JsonNode val = els.next();
            readingList.add(val.asText());
        }
        Log.d(TAG, "deserialize: readinglist" + readingList.size());

        // TODO: Check node.path for the correct node name in the json
        List<String> reviewedBooks = new ArrayList<>();
        JsonNode reviewNode = node.path("recommendations");
        els = reviewNode.elements();
        while(els.hasNext()) {
            JsonNode val = els.next();
            reviewedBooks.add(val.asText());
        }
        Log.d(TAG, "deserialize: reviewList" + reviewedBooks.size());

        Person person = new Person(name, name);
        person.setURL(url);
        person.setBio(bio);
        person.setReadingList(readingList);
        person.setReviewedBooks(readingList);

        return person;
    }
}
