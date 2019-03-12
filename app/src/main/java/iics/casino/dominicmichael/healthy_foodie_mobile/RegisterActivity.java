package iics.casino.dominicmichael.healthy_foodie_mobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import iics.casino.dominicmichael.healthy_foodie_mobile.app.AppController;

public class RegisterActivity extends AppCompatActivity {


    private EditText username, password, type, progress, cpass, status, age;
    private Button btn_register;
    private RadioButton account_hybrid, account_testonly;
    private static String URL_REGIST = AppController.baseUrl+"register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
        cpass = findViewById(R.id.input_confirm);
        age = findViewById(R.id.input_age);
        btn_register = findViewById(R.id.reg_btn);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });

    }

    private void Regist(){
        final String username = this.username.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String cpass = this.cpass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(RegisterActivity.this, "Registeration Success!", Toast.LENGTH_SHORT).show();
                            }
                        } catch(JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Registration Error!", Toast.LENGTH_SHORT).show();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(RegisterActivity.this,"Registration Error!" + error.toString(), Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", "1");
                params.put("Username", username);
                params.put("Password", password);
                params.put("Type", "Hybrid");
                params.put("Status", "pretest");
                params.put("Progress", "0");
                //params.put("type", type);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
