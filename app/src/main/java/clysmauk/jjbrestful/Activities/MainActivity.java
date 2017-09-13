package clysmauk.jjbrestful.Activities;




import android.content.Intent;
//import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

import clysmauk.jjbrestful.Networking.myhttpClient;
import clysmauk.jjbrestful.R;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "clysmauk.jjbrestful.MESSAGE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://p00603api.azurewebsites.net/token";//"http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=3544536a2002311973187dfabd49e876";

        TextView textView = (TextView) findViewById(R.id.txtMyMessage);
        //new GetWeatherTask(textView).execute(url);
        new myhttpClient(textView).execute(url);
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
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }//end of sendMessage()
    //###########################

}//end of Class


