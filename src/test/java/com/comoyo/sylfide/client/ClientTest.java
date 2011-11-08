package com.comoyo.sylfide.client;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Unit test for simple Client.
 */
public class ClientTest {
    private static String apiBaseUrl;

    public ClientTest() {
        apiBaseUrl = "http://localhost:8080/id/";
    }

    @Test
    public void testSimpleRequest() throws IOException {
        Client c = new Client(apiBaseUrl);
        c.simpleRequest();
    }

    @Test
    public void testAuthedRequest() throws IOException {
        Client c = new Client(apiBaseUrl);
        c.authedRequest();
    }

}
