package com.cs428.app.bookapp.networking;

import com.cs428.app.bookapp.interfaces.IServerCommunicator;
import com.cs428.app.bookapp.interfaces.IServerProxy;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;
import com.cs428.app.bookapp.networking.mock.Mock_ServerCommunicator;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mgard on 3/10/2018.
 */
public class ServerProxyTest {

    private IServerProxy proxy;

    @Before
    public void setup() {
        IServerCommunicator com = new Mock_ServerCommunicator();
        proxy = new ServerProxy(com);
    }

    @Test
    public void login() throws Exception {
        User test = proxy.login("test", "test");
        assertEquals("test", test.getName());

        User test1 = proxy.login("test1", "test1");
        assertEquals("test1", test1.getName());
    }

    @Test
    public void searchBook() throws Exception {
        List<Book> results = proxy.searchBook("testTerm", listener);

        for(int i = 0; i < 20; i++) {
            Book b = results.get(i);
            assertEquals("book" + i, b.getTitle());
            assertEquals("author" + i, b.getAuthor());
            assertEquals(Integer.toString(i), b.getMeta());
        }

    }

    /*@Test
    public void getRecommendationFor() throws Exception {

    }
    */

}