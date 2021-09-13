package com.hdiseguros.ofertashdiseguros.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenConnectionAPI {

    public static InputStream openConnection(String urlAPI) throws IOException {
        URL url = new URL(urlAPI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");

        InputStream responseStream = connection.getInputStream();

        return responseStream;
    }
}
