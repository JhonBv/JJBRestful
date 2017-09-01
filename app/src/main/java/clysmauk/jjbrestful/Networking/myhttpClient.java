package clysmauk.jjbrestful.Networking;

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.message.BasicNameValuePair;


/**
 * Created by barreij on 30/08/2017.
 */

public class myhttpClient extends AsyncTask<String, Void, String> {

    //JB. Declare the list view to which data will be bound.
    private TextView _textView;

    //JB. Declare url to RESTful API.
    private String _Url;

    //Constructor
    public myhttpClient(TextView textView) {
        this._textView = textView;
        //this._Url = url;

    }//end of Constructor


    @Override
    protected String doInBackground(String... params) {
        String daResponse = "UNDEFINED";
        try {
            daResponse = sendPost();
        } catch (Exception e) {

            daResponse= e.toString();
        }

        return daResponse;
    }

    // HTTP POST request
    private String sendPost() throws Exception {

        String url = "http://p00603api.azurewebsites.net/token";
        String myresponse = "AJA";

        //JB. Declare the httpClient builder and initiate it.
        HttpClient httpClient = HttpClientBuilder.create().build();
        //JB. Type of Request (post in this case)
        HttpPost postRequest = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("username", "jhon.barreiro+13@gmail.com"));
        params.add(new BasicNameValuePair("password", "MyN!c3P@ss"));
        params.add(new BasicNameValuePair("grant_type", "password"));

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

    //JB.
    @Override
    protected void onPostExecute(String temp) {
        _textView.setText("Token is: " + temp);
    }//end onPostExecute()
}
