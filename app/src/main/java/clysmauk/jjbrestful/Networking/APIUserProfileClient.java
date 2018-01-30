package clysmauk.jjbrestful.Networking;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import com.loopj.android.http.HttpGet;

import org.json.JSONArray;
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
import java.util.Date;
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


    private TextView title;
    private TextView _Fname;
    private TextView _Mname;
    private TextView _Lname;
    private TextView _Address;
    private TextView _Postcode;
    private TextView _Phone;

    String daUrl;
    String Access_token;
    //Constructor
    public APIUserProfileClient() {

    }//end of Constructor


    //Constructor
    public APIUserProfileClient(TextView title, TextView fname, TextView mname, TextView lname, TextView address, TextView pcode, TextView phone, String myUrl, String token) {
        this.title = title;
        this._Fname = fname;
        this._Mname = mname;
        this._Lname = lname;
        this._Address = address;
        this._Postcode = pcode;
        this._Phone = phone;
        this.daUrl = myUrl;
        this.Access_token = token;

    }//end of Constructor

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> daResponse = new ArrayList<String>();

        //Access_token = params[0];

        //JB. Attempt to obtain today's date.
        Date todays = new Date();

        try {
            daResponse = GetProfile();

        } catch (Exception e) {

            daResponse.add("Semantha detected an Error: " +"/n happened on" + todays.toString() +"/n"  + e.toString());
        }

        return daResponse;
    }

    // HTTP POST request
    private ArrayList<String> GetProfile() throws Exception {

        String _url = daUrl;
        //String myresponse = "UNDEFINED";

        ArrayList<String> returnedProfile = null;
        try {
            URL url = new URL(_url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("Authorization", "Bearer:" + Access_token);

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            returnedProfile = new ArrayList<>();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            JSONObject topLevel = new JSONObject(builder.toString());
            JSONObject main = topLevel.getJSONObject("userDetails");
            JSONArray arr = topLevel.getJSONArray("userDetails");

            returnedProfile.add(String.valueOf(arr.getString(0)));
//            returnedProfile.add(String.valueOf(main.getString("UserFirstName")));
//            returnedProfile.add(String.valueOf(main.getString("UserMiddleName")));
//            returnedProfile.add(String.valueOf(main.getString("UserLastName")));
//            returnedProfile.add(String.valueOf(main.getString("Address")));
//            returnedProfile.add(String.valueOf(main.getString("PostCode")));
//            returnedProfile.add(String.valueOf(main.getString("PhoneNumber")));

         /* myresponse = String.valueOf(main.getString("Title"));
            myresponse = String.valueOf(main.getString("UserFirstName"));
            myresponse = String.valueOf(main.getString("UserMiddleName"));
            myresponse = String.valueOf(main.getString("UserLastName"));

            myresponse = String.valueOf(main.getString("Address"));
            myresponse = String.valueOf(main.getString("PostCode"));
            myresponse = String.valueOf(main.getString("PhoneNumber"));*/


            urlConnection.disconnect();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        //JB. Return the returned Token!
        return returnedProfile;
    }

//    ProgressDialog dialog;
//
//    @Override
//    protected void onPreExecute() {
//        dialog = new ProgressDialog(null);
//        dialog.setTitle("Calculating...");
//        dialog.setMessage("Please wait...");
//        dialog.setIndeterminate(true);
//        dialog.show();
//    }

    //JB.
    @Override
    protected void onPostExecute(ArrayList<String> temp) {
        this.title.setText("Mr");
        this._Fname.setText("Jhon");
        this._Mname.setText("J");
        this._Lname.setText("Barreiro");
        this._Address.setText("AddressCollection");

    }
}
