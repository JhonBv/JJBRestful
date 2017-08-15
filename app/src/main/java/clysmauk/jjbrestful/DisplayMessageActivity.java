package clysmauk.jjbrestful;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
    }//end of onCreate


    /** Called when the user taps the Send button */
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


}//end of Class
