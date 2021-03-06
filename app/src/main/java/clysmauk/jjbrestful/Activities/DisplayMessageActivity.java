package clysmauk.jjbrestful.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import clysmauk.jjbrestful.R;

import static clysmauk.jjbrestful.Activities.LoginActivity.MyPREFERENCES;

public class DisplayMessageActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "clysmauk.jjbrestful.MESSAGE";
    private String myName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString(EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.txtMyMessage);
        textView.setText(message);



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
