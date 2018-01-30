package clysmauk.jjbrestful.Networking;

import android.app.VoiceInteractor;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import clysmauk.jjbrestful.R.string;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by barreij on 30/08/2017.
 */

public class myhttpClient extends AsyncTask<String, Void, String> {

    //JB. Declare the list view to which data will be bound.
    private TextView _textView;
    HttpClient httpClient;
    HttpPost postRequest;
    HttpGet getRequest;

    String access_token;


    //JB. Declare url to RESTful API.
    private String _Url;

    //Constructor
    public myhttpClient(TextView textView, String myUrl) {
        this._textView = textView;
        this._Url = myUrl;


    }//end of Constructor

    @Override
    protected String doInBackground(String... params) {
        String daResponse = "UNDEFINED";

        try {
            //GetToken();
            access_token = GetToken();
            daResponse = GetUserProfile(access_token);
        } catch (Exception e) {

            daResponse= e.toString();
        }

        return daResponse;
    }

    // HTTP POST request
    private String GetToken() throws Exception {

        String myresponse = "AJA";

        //JB. Declare the httpClient builder and initiate it.
        httpClient = HttpClientBuilder.create().build();
        //JB. Type of Request (post in this case)
        postRequest = new HttpPost(_Url);

        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("username", "jhon.barreiro+13@gmail.com"));
        params.add(new BasicNameValuePair("password", "MyN!c3P@ss"));
        params.add(new BasicNameValuePair("grant_type", "password"));

        //JB. NOTE: I am passing the entity with the x-www-form-urlencoded
        postRequest.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


        HttpResponse response = httpClient.execute(postRequest);
        HttpEntity entity = response.getEntity();


        //handle response here...
        if (entity != null) {
            InputStream instream = entity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(instream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            JSONObject topLevel = new JSONObject(builder.toString());
            //JSONObject main = topLevel.getJSONObject("");

            try {
                //JB. Obtain the returned token
                myresponse = String.valueOf(topLevel.getString("access_token"));
            } finally {
                //JB. Run regardless..
                instream.close();
            }
        }
        //JB. Return the returned Token!
        return myresponse;
    }

    // HTTP POST request
    private String GetUserProfile(String myToken) throws Exception {

        String token = GetToken();
        String profileEndPoint = "http://p00603clientapi.azurewebsites.net/api/User/myprofile";


        httpClient = HttpClientBuilder.create().build();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://p00603clientapi.azurewebsites.net/api/User/myprofile")
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", myToken)
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();




        return response.message();
    }

    //JB.
    @Override
    protected void onPostExecute(String temp) {
        _textView.setText("Token is: " + temp);
    }//end onPostExecute()
}