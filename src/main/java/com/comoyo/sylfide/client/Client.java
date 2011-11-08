package com.comoyo.sylfide.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 *
 */
public class Client
{
    HttpHost targetHost = new HttpHost("localhost", 8080, "http");
    final private String apiEndPoint;

    public Client(String endpoint) {
        apiEndPoint = endpoint;
    }

    public void findURItemplates() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(apiEndPoint);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String body = httpClient.execute(httpGet, responseHandler);
        JSONObject templates = new JSONObject(body);
    }

    public void simpleRequest() throws IOException {

        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(apiEndPoint);

            System.out.println("executing request " + httpget.getRequestLine());

            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            System.out.println("----------------------------------------");
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

    }

    public void authedRequest() throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                    new UsernamePasswordCredentials("sdk", "sdk123"));

            // Create AuthCache instance
            AuthCache authCache = new BasicAuthCache();
            // Generate BASIC scheme object and add it to the local auth cache
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(targetHost, basicAuth);

            // Add AuthCache to the execution context
            BasicHttpContext localcontext = new BasicHttpContext();
            localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);


            HttpGet httpget = new HttpGet("/id/users");

            System.out.println("executing request " + httpget.getRequestLine());
            System.out.println("to target: " + targetHost);

            HttpResponse response = httpclient.execute(targetHost, httpget, localcontext);

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("Response content length: " + entity.getContentLength());
                System.out.println(EntityUtils.toString(entity));
            }
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }
}
