package com.pgmanagement.pgadmin.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.transactional.User;
import com.pgmanagement.pgadmin.volly.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {


    String baseURL;
    TextView actualResponse,tvUsername,tvPassword,tvLink_signup,tvDemoLogin;
    User loggedUser;
    boolean responseReceived=false;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        baseURL=getString(R.string.base_URL);

        tvUsername= (TextView) findViewById(R.id.username);
        tvPassword= (TextView) findViewById(R.id.password);
        tvLink_signup= (TextView) findViewById(R.id.link_signup);
        tvDemoLogin= (TextView) findViewById(R.id.demoLogin);
        Button bLogin = (Button) findViewById(R.id.btn_login);
        actualResponse= (TextView) findViewById(R.id.actualResponse);

        tvUsername.setText("");
        tvUsername.setText("");
        actualResponse.setText("");
        loggedUser=null;


        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        gson = gsonBuilder.create();



        tvDemoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

               try {
                   User user = new User();

                   user.setId(1L);

                   user.setFirstName("Satish");
                   user.setUsername("sat");
                   user.setPassword("123");
                   Intent i = new Intent(LoginActivity.this, MainActivity.class);
                   i.putExtra("loggedUser", user);
                   startActivity(i);
               }catch (Exception e){
                   showMessage("In makeGetReq_exception",e.getMessage());
               }


            }
        });


        tvLink_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

               // finish();
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String username = tvUsername.getText().toString();
                String password = tvPassword.getText().toString();

                loggedUser =null;
                validateUser(username,password);

                /*if(user==null){
                    showMessage("2", "user is null");
                }
                else{
                    showMessage("2", "user is not null");
                }*/
                if(!responseReceived){
                    actualResponse.setText("");
                }
                else if(loggedUser==null&&responseReceived){
                    actualResponse.setText("Wrong username or password!");
                }
                else{
                    actualResponse.setText("Welcome "+loggedUser.getFirstName());
                }


                /*Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);*/
            }
        });

    }
    @Override
    protected void onResume(){

        super.onResume();
        tvUsername.setText("");
        tvPassword.setText("");
        loggedUser=null;
        if(responseReceived){
            actualResponse.setText("Logged out successfully!");
            responseReceived=false;
        }



    }

    public void validateUser(String username,String password){
        String  tag_string_req = "string_req";
        String validateURL=baseURL+"/login/"+username+"/"+password;
        //showMessage("In makeGetReq",validateURL);
        StringRequest strReq = new StringRequest(Request.Method.GET,
                validateURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //stringResponse=response;
                // showMessage("Response",response);
                try{
                    responseReceived = true;
                    loggedUser = gson.fromJson(response, User.class);
                    if(loggedUser!=null){
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("loggedUser",loggedUser);
                        startActivity(i);
                    }

                }catch(Exception e){
                    showMessage("In makeGetReq_exception",e.getMessage());

                }
                //  Log.d(TAG, response.toString());
                //   pDialog.hide();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.getMessage()==null){
                    actualResponse.setText("Wrong username or password!");
                }
                else{
                    actualResponse.setText("Error: " +error.getMessage());
                    //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                    //  pDialog.hide()

                }

                ;
            }
        });

        try{
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

        }catch(Exception e){
            showMessage("In makeGetReq_exception",e.getMessage());

        }
    }
    public String makaPostRequest(String url){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = url;
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Title", "Android Volley Demo");
            jsonBody.put("Author", "BNK");
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    actualResponse.setText("response : "+response);
                    // Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        //  VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        actualResponse.setText("Error: " +"Unsupported Encoding");
                        return null;
                    }
                }

            };

            //  requestQueue.add(stringRequest);
            AppController.getInstance().addToRequestQueue(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "hey";
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

   }
