package clysmauk.jjbrestful.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import clysmauk.jjbrestful.R;

import static clysmauk.jjbrestful.Activities.LoginActivity.MY_PREFS_NAME;

public class DisplayMessageActivity extends AppCompatActivity {

    private String myName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("MY_TOKEN");

        //JB Shared Preferences.
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);

            myName = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            String idToken = prefs.getString("idToken", "nothing man!"); //0 is the default value.

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.txtMyMessage);
        textView.setText(idToken);

    }//end of onCreate


    /**
     * Called when the user taps the Send button
     */
    //Go to view Items
    public void viewItems(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        startActivity(intent);
    }//end of viewItems()
    //###########################

    //Go to view Items
    public void viewRestItems(View view) {
        Intent intent = new Intent(this, RestActivity.class);
        startActivity(intent);
    }//end of viewItems()
    //###########################


    public void LogmeIn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }//end LogmeIn()
// ###########################

}//end of Class
