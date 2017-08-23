package clysmauk.jjbrestful.Services;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by barreij on 22/08/2017.
 */

public class GetWeatherTask extends AsyncTask<String, Void, String> {

    private TextView textView;

    public GetWeatherTask(TextView textView) {
        this.textView = textView;
    }//end of GetWeatherTask()

    @Override
    protected String doInBackground(String... strings) {
        String weather = "UNDEFINED";
        try {
            URL url = new URL(strings[0]);
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
            weather = String.valueOf(main.getDouble("temp"));



            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return weather;

    }//end doInBackground()

    @Override
    protected void onPostExecute(String temp) {
        textView.setText("Current Weather: " + temp);
    }//end onPostExecute()

}//end class
