package com.cs428.app.bookapp.networking.customSerializers;

import android.util.Log;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.BookReview;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgard on 3/19/2018.
 */

public class BookDeserializer extends StdDeserializer<Book> {

    public BookDeserializer() {
        this(null);
    }

    public BookDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Book deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Log.d("DESERIALIZE", "deserialize: " + node.toString());
        int id = (Integer) node.get("id").asInt();
        String title = node.get("title").asText();
        String author = node.get("author").asText();
        String isbn = node.get("ISBN").asText();
        String description = node.get("description").asText();
        String coverUrl = node.get("cover_url").asText();
        /*int upvotes = (Integer) node.get("Upvotes").asInt();

        ObjectMapper mapper = new ObjectMapper();
        List<String> reviews = mapper.readValue(node.get("reviews").textValue(), new TypeReference<List<String>>(){});
        List<BookReview> bookReviews = new ArrayList<>();

        for (int i = 0; i < reviews.size(); ++i) {
            bookReviews.add(new BookReview(reviews.get(i)));
        }
        */
        Book book = new Book(title, author, coverUrl);
        //book.setRating(upvotes);
        book.setSummary(description);
        book.setId(id);
        //book.setReviews(bookReviews);

        return book;
    }
}
