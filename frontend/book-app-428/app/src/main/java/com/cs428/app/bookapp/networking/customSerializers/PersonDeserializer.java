package com.cs428.app.bookapp.networking.customSerializers;

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
        String name = node.get("name").asText();
        String id = node.get("id").asText();

        // TODO: Check node.path for the correct node name in the json
        List<String> readingList = new ArrayList<>();
        JsonNode readingNode = node.path("reading");
        Iterator<JsonNode> els = readingNode.elements();
        while(els.hasNext()) {
            JsonNode val = els.next();
            readingList.add(val.asText());
        }

        // TODO: Check node.path for the correct node name in the json
        List<String> reviewedBooks = new ArrayList<>();
        JsonNode reviewNode = node.path("reviewed");
        els = reviewNode.elements();
        while(els.hasNext()) {
            JsonNode val = els.next();
            reviewedBooks.add(val.asText());
        }

        Person person = new Person(name, id);
        person.setReadingList(readingList);
        person.setReviewedBooks(reviewedBooks);

        return person;
    }
}
