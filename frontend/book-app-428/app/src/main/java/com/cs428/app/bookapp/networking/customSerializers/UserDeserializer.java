package com.cs428.app.bookapp.networking.customSerializers;

import com.cs428.app.bookapp.model.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mgard on 3/19/2018.
 */

public class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
        this(null);
    }

    public UserDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String username = node.get("username").textValue();
        String email = node.get("email").textValue();

        User user = new User(username, username);
        user.setEmail(email);

        List<String> follows = new ArrayList<>();
        JsonNode followsNode = node.path("users_following");
        Iterator<JsonNode> els = followsNode.elements();
        while(els.hasNext()) {
            JsonNode val = els.next();
            follows.add(val.asText());
        }

        List<String> recommends = new ArrayList<>();
        JsonNode recNode = node.path("recommendations");
        els = recNode.elements();
        while(els.hasNext()) {
            JsonNode val = els.next();
            recommends.add(val.asText());
        }
        user.setReviewedBooks(recommends);

        List<String> readingList = new ArrayList<>();
        JsonNode readNode = node.path("reading_list");
        els = readNode.elements();
        while(els.hasNext()) {
            JsonNode val = els.next();
            readingList.add(val.asText());
        }
        user.setReadingList(readingList);

        return user;
    }
}
