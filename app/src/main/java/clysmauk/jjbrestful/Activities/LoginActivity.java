package clysmauk.jjbrestful.Activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
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
    private EditText txtvwReturnedToken;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String EXTRA_MESSAGE = "clysmauk.jjbrestful.MESSAGE";
    SharedPreferences sharedpreferences;
    String apiUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //JB. Set the login details from EditTexts
        usernametxt = (EditText) findViewById(R.id.txtUsername);
        passwordtxt = (EditText) findViewById(R.id.txtPassword);
        txtvwReturnedToken = (EditText) findViewById(R.id.txtToken);
         apiUrl = getString(R.string.baseUrlAddress);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //JB. Declare the RememberMe checkBox to allow user to save details locally.
        //CheckBox rememberMe = (CheckBox) findViewById( R.id.chkRememberme );
        final CheckBox rememberMe = (CheckBox) findViewById(R.id.chkRememberme);
        //JB. Add the OnCheckedListener so it can act once the checkbox is checked.
                if ( rememberMe.isChecked() )
                {

                    String un  = usernametxt.getText().toString();
                    String pss  = passwordtxt.getText().toString();

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("username", un);
                    editor.putString("password", pss);

                    editor.commit();

                    Toast.makeText(LoginActivity.this,"Your login details have been saved",Toast.LENGTH_LONG).show();
                }



        Button button = (Button) findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //JB. Arraylist to store username and password
                ArrayList<String> details = new ArrayList<String>();

                //JB. Set the login details from EditTexts
                EditText ttusr = (EditText) findViewById(R.id.txtUsername);
                EditText ttpswr = (EditText) findViewById(R.id.txtPassword);

                //JB. Add data entered in the fields to the array list.
                details.add(ttusr.getText().toString());
                details.add(ttpswr.getText().toString());

                String url = apiUrl +getString(R.string.token);


                //JB. Pass on the ArrayList with set properties. the txtvwReturnedToken is of type EditText  ;)
                new ApiLoginModule(txtvwReturnedToken, url).execute(details);


                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("returnedToken", txtvwReturnedToken.getText().toString());
                editor.commit();

                /*txtvwReturnedToken.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        GotoMainActivity();
                    }
                });*/

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GotoMainActivity();
                    }
                }, 3000);
            }
        });

    }//end onCreate()

    public void GotoMainActivity() {

        Intent intent = new Intent(this, DisplayMessageActivity.class);

        intent.putExtra(EXTRA_MESSAGE, txtvwReturnedToken.getText().toString());
        startActivity(intent);
    }
}

//JB unused methods
//JB. Instantiate loginModel and assign values
        /*LoginModel model = new LoginModel();
        model.SetUserName(ttusr.getText().toString());
        model.SetPassword(ttpswr.getText().toString());*/
