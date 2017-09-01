package clysmauk.jjbrestful.Networking;

import android.os.AsyncTask;

import com.loopj.android.http.HttpGet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by barreij on 01/09/2017.
 */

public class APIUserProfileClient extends AsyncTask<String, Void, ArrayList<String>> {
    @Override
    protected ArrayList<String> doInBackground(String... params) {

        return null;
    }

    // HTTP POST request
    private String GetProfile() throws Exception {

        String _url = "http://p00603api.azurewebsites.net/api/User/MyProfile";
        String myresponse = "UNDEFINED";

        try {
            URL url = new URL(_url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.addRequestProperty("Accept","application/json");
            urlConnection.addRequestProperty("Content-Type","application/json");
            urlConnection.addRequestProperty("Bearer","");


            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            JSONObject topLevel = new JSONObject(builder.toString());
            JSONObject main = topLevel.getJSONObject("main");
            myresponse = String.valueOf(main.getDouble("temp"));


            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


        //JB. Return the returned Token!
        return myresponse;
    }
}
