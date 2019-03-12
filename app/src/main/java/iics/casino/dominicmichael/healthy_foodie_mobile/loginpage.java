package iics.casino.dominicmichael.healthy_foodie_mobile;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import iics.casino.dominicmichael.healthy_foodie_mobile.app.AppController;

public class loginpage extends AppCompatActivity {

    private String urlForJsonObject = AppController.baseUrl+"get_all_products.php";
    private String jsonResponse;
    private ListView productsList;
    private TextView noConnection;
    private TextView viewDescription;

    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
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

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait....");
        pDialog.setCancelable(false);
//
//        if (checkForConnection()){
//            viewDescription.setVisibility(View.VISIBLE);
//            makeJsonObjectRequest();
//        }
//        else{
//            viewDescription.setVisibility(View.GONE);
//            noConnection.setVisibility(View.VISIBLE);
//        }

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
//
//    private void makeJsonObjectRequest() {
//
//        showpDialog();
//
//        final List<String> productsArrayList = new ArrayList<String>();
//
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
//                urlForJsonObject,
//                null,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, response.toString()); //for android log cat??
//
//                        try {
//                            // Parsing json object response
//                            // response will be a json object
//                            int success = response.getInt("success");
//                            if (success == 1){
//                                JSONArray products = response.getJSONArray("account");
//                                for(int i =0; i<products.length(); i++){
//                                    JSONObject phone = products.getJSONObject(i);
//                                    String username = phone.get("Username").toString();
//                                    String password = phone.get("Password").toString();
//                                    String type = phone.get("Type").toString();
//                                    String progress = phone.get("Progress").toString();
//                                    String status = phone.get("Status").toString();
//                                    jsonResponse = "";
//                                    jsonResponse += "UserName: " + username + "\n\n";
//                                    jsonResponse += "Password: " + password + "\n\n";
//                                    jsonResponse += "Type: " + type + "\n\n";
//                                    jsonResponse += "Progress: " + progress + "\n\n";
//                                    jsonResponse += "Status: " + status + "\n\n";
//                                    productsArrayList.add(jsonResponse);
//
//                                }
//
//                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                                        loginpage.this,
//                                        android.R.layout.simple_list_item_1,
//                                        productsArrayList );
//
//                                productsList.setAdapter(arrayAdapter);
//                            }
//                            else {
//                                Toast.makeText(loginpage.this, "No products in the database", Toast.LENGTH_LONG).show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(),
//                                    "Error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//
//                        hidepDialog();
//
//                    }
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                VolleyLog.d(TAG,"Error: "+ volleyError.getMessage() );
//                Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
//                hidepDialog();
//            }
//        });
//        //adding request to request queue
//        AppController.getmInstance().addToRequestQueue(jsonObjReq);
//    }
//
//
//
//    private void showpDialog(){
//        if(!pDialog.isShowing()){
//            pDialog.show();
//        }
//    }
//
//    private void hidepDialog(){
//        if(pDialog.isShowing()){
//            pDialog.dismiss();
//        }
//    }
//
//    @Override
//    public void onRefresh() {
//        if (checkForConnection()){
//            viewDescription.setVisibility(View.VISIBLE);
//            noConnection.setVisibility(View.GONE);
//            makeJsonObjectRequest();
//            swipeLayout.setRefreshing(false);
//
//        }
//        else{
//            productsList.setVisibility(View.GONE);
//            viewDescription.setVisibility(View.GONE);
//            noConnection.setVisibility(View.VISIBLE);
//            swipeLayout.setRefreshing(false);
//
//        }
//    }

    @Override
    public void onDestroy() {
        // You must call this or the ad adapter may cause a memory leak
        super.onDestroy();
    }

    private Boolean checkForConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
