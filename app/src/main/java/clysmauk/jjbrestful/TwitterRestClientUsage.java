package clysmauk.jjbrestful;

/**
 * Created by barreij on 14/08/2017.
 */

import org.json.*;
import com.loopj.android.http.*;

import okhttp3.internal.http2.Header;

public class TwitterRestClientUsage {

    public void getPublicTimeline() throws JSONException {

        TwitterRestClient.get("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
            //@Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            //@Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) throws JSONException {
                // Pull out the first event on the public timeline
                JSONObject firstEvent = (JSONObject) timeline.get(0);
                String tweetText = firstEvent.getString("text");

                // Do something with the response
                System.out.println(tweetText);
            }
        });
    }

}
