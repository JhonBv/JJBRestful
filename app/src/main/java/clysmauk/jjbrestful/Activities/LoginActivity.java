package clysmauk.jjbrestful.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import clysmauk.jjbrestful.Networking.ApiLoginModule;

import clysmauk.jjbrestful.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernametxt;
    private EditText passwordtxt;
    private String aja;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private String DA_RESPONSE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //JB. Set the login details from EditTexts
        usernametxt = (EditText) findViewById(R.id.txtUsername);
        passwordtxt = (EditText) findViewById(R.id.txtPassword);

        //JB. Declare the RememberMe checkBox to allow user to save details locally.
        CheckBox rememberMe = (CheckBox) findViewById( R.id.chkRememberme );

        //JB. Add the OnCheckedListener so it can act once the checkbox is checked.
        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    /*
                    String un  = usernametxt.getText().toString();
                    String pss  = passwordtxt.getText().toString();

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(UserName, un);
                    editor.putString(UserPassword, pss);

                    editor.commit();
                    */
                    Toast.makeText(LoginActivity.this,"Your login details have been saved",Toast.LENGTH_LONG).show();
                }
            }
        });

        Button button = (Button) findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtvwReturnedToken = (EditText) findViewById(R.id.txtToken);

                //JB. Arraylist to store username and password
                ArrayList<String> details = new ArrayList<String>();

                //JB. Set the login details from EditTexts
                EditText ttusr = (EditText) findViewById(R.id.txtUsername);
                EditText ttpswr = (EditText) findViewById(R.id.txtPassword);

                //JB. Add data entered in the fields to the array list.
                details.add(ttusr.getText().toString());
                details.add(ttpswr.getText().toString());

                //JB. Pass on the ArrayList with set properties. the txtvwReturnedToken is of type EditText  ;)
                new ApiLoginModule(txtvwReturnedToken).execute(details);


                aja = txtvwReturnedToken.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", "Elena");
                editor.putString("idToken", txtvwReturnedToken.getText().toString());
                editor.apply();

                //Loog(null);
            }
        });



    }//end onCreate()

    public void Loog(View view) {

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("MY_TOKEN", aja);
        startActivity(intent);
    }
}

//JB unused methods
//JB. Instantiate loginModel and assign values
        /*LoginModel model = new LoginModel();
        model.SetUserName(ttusr.getText().toString());
        model.SetPassword(ttpswr.getText().toString());*/
