package iics.casino.dominicmichael.healthy_foodie_mobile;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



public class loginpage extends AppCompatActivity {

    private static final String TAG = "LoginPage";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    DatabaseHelper mDatabaseHelper;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox checkBoxcheckBox;
    String username, password, checkbox;

    String message;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        Button noAccountBtn = findViewById(R.id.noaccount_btn);
        Button loginBtn = findViewById(R.id.login_btn);
        usernameEditText = findViewById(R.id.input_username);
        passwordEditText = findViewById(R.id.input_password);
        checkBoxcheckBox = findViewById(R.id.checkBox);
        mDatabaseHelper = new DatabaseHelper(this);

        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        checkbox = checkBoxcheckBox.getText().toString();



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                checkbox = checkBoxcheckBox.getText().toString();
                if(username.length() == 0 || password.length() == 0) {
                    toastMessage("Incorrect username and/or password");
                } else {
                    Cursor data = mDatabaseHelper.getData(username);
                    data.moveToFirst();
                    if (data.getCount() > 0) {
                        toastMessage("Welcome " + username);
                        Intent goToHomePage = new Intent(getApplicationContext(), homepage.class);
                        startActivity(goToHomePage);
                    }
                }

                if(checkBoxcheckBox.isChecked()){
                    mEditor.putString(getString(R.string.SPcheckbox),"True");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.SPusername), username);
                    mEditor.commit();

                    mEditor.putString(getString(R.string.SPpassword), password);
                    mEditor.commit();
                }else{
                    mEditor.putString(getString(R.string.SPcheckbox),"False");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.SPusername), "");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.SPpassword), "");
                    mEditor.commit();
                }
            }
        });

        noAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movetoRegister = new Intent(getApplicationContext(), registrationpage.class);
                startActivity(movetoRegister);
            }
        });

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        checkSharedPreferences();
    }

    private void checkSharedPreferences(){
        String checkbox =mPreferences.getString(getString(R.string.SPcheckbox),"False");
        String username = mPreferences.getString(getString(R.string.SPusername),"");
        String password = mPreferences.getString(getString(R.string.SPpassword),"");
        usernameEditText.setText(username);
        passwordEditText.setText(password);
        if(checkbox.equals("True")){
            checkBoxcheckBox.setChecked(true);
        }else{
            checkBoxcheckBox.setChecked(false);
        }
    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, duration).show();
    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
