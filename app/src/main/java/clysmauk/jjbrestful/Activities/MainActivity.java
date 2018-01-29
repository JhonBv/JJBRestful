package clysmauk.jjbrestful.Activities;




import android.content.Context;
import android.content.Intent;
//import android.support.v4.app.FragmentActivity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

import clysmauk.jjbrestful.Networking.myhttpClient;
import clysmauk.jjbrestful.R;
import clysmauk.jjbrestful.Services.GetWeatherTask;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "clysmauk.jjbrestful.MESSAGE";
    public static final String MyPREFERENCES = "MyPrefs" ;
    String apiUrl;


    SharedPreferences sharedpreferences;
    TextView textView;
    String MyToken;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        apiUrl = getString(R.string.baseUrlAddress);

        String url = apiUrl+getString(R.string.token);

        textView = (TextView) findViewById(R.id.txtMyMessage);

        //new GetWeatherTask(textView).execute(url);

        //###################################################################
        //JB. Pass the URL which has been configured in the Resources/String
        new myhttpClient(textView, url).execute(url);
        //###################################################################
        //textView.setText(response);


    }//end of onCreate()
    //###########################

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }//end of onResume()
    //###########################

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("returnedToken", textView.getText().toString());
        editor.commit();

        MyToken = sharedpreferences.getString("returnedToken", "");

        //String message = editText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, MyToken);
        startActivity(intent);


    }//end of sendMessage()
    //###########################

}//end of Class


