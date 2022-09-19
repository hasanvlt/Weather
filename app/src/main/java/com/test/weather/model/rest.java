package com.test.weather.model;


import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//class for rest api call
public class rest {
    static OkHttpClient client = new OkHttpClient();

    public static String getJSONObjectFromURL(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
     }
}
