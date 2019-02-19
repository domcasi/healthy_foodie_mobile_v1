package iics.casino.dominicmichael.healthy_foodie_mobile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registrationpage extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    private EditText nameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmEditText;
    String name, username, password, confirmPassword;

    String message;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);

        Button cancelBtn = findViewById(R.id.cancel_btn);
        Button registerBtn = findViewById(R.id.reg_btn);
        nameEditText = findViewById(R.id.input_name);
        usernameEditText = findViewById(R.id.input_username);
        passwordEditText = findViewById(R.id.input_password);
        confirmEditText = findViewById(R.id.input_confirm);

        mDatabaseHelper = new DatabaseHelper(this);

        name = nameEditText.getText().toString();
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        confirmPassword = confirmEditText.getText().toString();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString();
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                confirmPassword = confirmEditText.getText().toString();

                if (name.length() != 0 && username.length() != 0 && password.length() != 0 && confirmPassword.length() != 0) {

                    if (password.equals(confirmPassword)) {
                        AddData(name, username, password);
                        nameEditText.setText("");
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                        confirmEditText.setText("");
                    } else {
                        toastMessage("Password and Confirm Password did not match");
                        nameEditText.setText("");
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                        confirmEditText.setText("");
                    }

                } else {
                    toastMessage("Pls fill up all the information");
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogin = new Intent(getApplicationContext(), loginpage.class);
                startActivity(goToLogin);
            }
        });
    }

    public void AddData(String name, String username, String password) {
        boolean insertData = mDatabaseHelper.addData(name, username, password);

        if (insertData) {
            toastMessage("Welcome " + name);
            Intent goToHomePage = new Intent(getApplicationContext(), homepage.class);
            startActivity(goToHomePage);
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, duration).show();
    }
}
