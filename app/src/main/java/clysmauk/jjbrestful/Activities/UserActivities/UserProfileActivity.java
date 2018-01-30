package clysmauk.jjbrestful.Activities.UserActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import clysmauk.jjbrestful.Networking.APIUserProfileClient;
import clysmauk.jjbrestful.R;

public class UserProfileActivity extends AppCompatActivity {

    TextView title;
    TextView Fname;
    TextView Mname;
    TextView Lname;
    TextView Address;
    TextView Postcode;
    TextView Phone;
    String apiUrl;
    String access_token;
    public static final String EXTRA_MESSAGE = "clysmauk.jjbrestful.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Bundle bundle = getIntent().getExtras();

        apiUrl = getString(R.string.baseUrlAddress)+"api/User/myprofile";
        access_token = bundle.getString(EXTRA_MESSAGE);

        title = (TextView) findViewById(R.id.txtTitle);
        Fname = (TextView) findViewById(R.id.txtFirstName);
        Mname = (TextView) findViewById(R.id.txtMiddleName);
        Lname = (TextView) findViewById(R.id.txtLastName);
        Address = (TextView) findViewById(R.id.txtAddress);
        Postcode = (TextView) findViewById(R.id.txtpostCode);
        Phone = (TextView) findViewById(R.id.txtPhoneNumber);

        new APIUserProfileClient(title, Fname, Mname, Lname, Address, Postcode, Phone, apiUrl, access_token).execute(access_token);






    }
}
