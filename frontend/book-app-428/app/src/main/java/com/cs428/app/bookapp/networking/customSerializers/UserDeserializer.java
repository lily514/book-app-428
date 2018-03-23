package com.cs428.app.bookapp.networking.customSerializers;

import com.cs428.app.bookapp.model.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

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
        int id = (Integer) ((IntNode) node.get("id")).numberValue();
        String username = node.get("username").textValue();
        String email = node.get("email").textValue();
        String password = node.get("password").textValue();

        //TODO: Handle 3 arrays that are passed back

        User user = new User(username, Integer.toString(id));
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
