package com.ego.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Administrator
 */
public class URLConnectUtil extends URLConnection {
    /**
     * Constructs a URL connection to the specified URL.
     * A connection to the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */
    protected URLConnectUtil(URL url) {
        super(url);
    }

    @Override
    public void connect() throws IOException {
        URL url = null;
        try {
            url = new URL("http://10.138.18.89:8080/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
