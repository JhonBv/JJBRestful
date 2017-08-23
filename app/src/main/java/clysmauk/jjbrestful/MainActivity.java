package clysmauk.jjbrestful;




import android.content.Intent;
//import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "clysmauk.jjbrestful.MESSAGE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=3544536a2002311973187dfabd49e876";



        //String response = "I will obtain info from Semantha Graph!";

        TextView textView = (TextView) findViewById(R.id.textView);
        new GetWeatherTask(textView).execute(url);
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


