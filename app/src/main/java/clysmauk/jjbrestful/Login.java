package clysmauk.jjbrestful;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by barreij on 18/08/2017.
 */

public class Login {
    private static final String BASE_URL = "http://p00603api.azurewebsites.net/";
    AsyncHttpClient myClient = new AsyncHttpClient();


    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        myClient.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
