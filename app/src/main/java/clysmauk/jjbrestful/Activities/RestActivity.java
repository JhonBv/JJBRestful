
package clysmauk.jjbrestful.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.loopj.android.http.*;

import clysmauk.jjbrestful.R;
import cz.msebera.android.httpclient.Header;


public class RestActivity extends AppCompatActivity {
    private String BASE_URL = getString(R.string.baseUrlAddress1)+getString(R.string.token);

    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        //Twitter.initialize(this);


        client = new AsyncHttpClient();

        client.get(BASE_URL, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                client.addHeader("Accept","application/json");
                client.addHeader("Content-Type","application/json");
                client.addHeader("grant_type","password");

                client.setBasicAuth("jhon.barreiro+13@gmail.com","MyN!c3P@ss");


                String _content;
                //_content = o["access_token"].ToString();

                TextView textView = (TextView) findViewById(R.id.txtMyMessage);
                textView.setText(bytes.toString());
                /* client.get("https://example.com"); */

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });//end of client.get({})
    }

}