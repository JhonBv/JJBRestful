package clysmauk.jjbrestful.Networking;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by barreij on 09/08/2017.
 */

public class GetExample {

    public GetExample() {

    }

    OkHttpClient client = new OkHttpClient();


    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }
}

