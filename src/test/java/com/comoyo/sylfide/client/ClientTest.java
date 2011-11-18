package com.comoyo.sylfide.client;

import org.json.JSONException;
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
        apiBaseUrl = "http://localhost:8080/";
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

    @Test
    public void testTemplates() throws IOException, JSONException {
        Client c = new Client(apiBaseUrl);
        c.findURItemplates();
    }

}
