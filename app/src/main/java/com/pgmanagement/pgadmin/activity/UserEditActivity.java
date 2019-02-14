package com.pgmanagement.pgadmin.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.master.GenericParameter;
import com.pgmanagement.pgadmin.model.master.ZipCode;
import com.pgmanagement.pgadmin.model.transactional.Address;
import com.pgmanagement.pgadmin.model.transactional.User;
import com.pgmanagement.pgadmin.volly.AppController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 2/9/2019.
 */

public class UserEditActivity  extends AppCompatActivity {

    String baseURL;
    private Gson gson = new Gson();

    TextView tvWelcome,username,etCity,etState,etCountry;
    EditText password,firstName,lastName,mobile,address_line1,address_line2,etZipcode;
    Button bVerifyZipcode,updateUser,cancel;
    User loggedUser ;
    User user;
    ZipCode zipCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        baseURL=getString(R.string.base_URL);


        tvWelcome = (TextView) findViewById(R.id.welcome);
        username = (TextView) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        mobile = (EditText) findViewById(R.id.mobile);
        address_line1 = (EditText) findViewById(R.id.address_line1);
        address_line2 = (EditText) findViewById(R.id.address_line2);
        etZipcode = (EditText) findViewById(R.id.zipCode);
        etCity = (TextView) findViewById(R.id.city);
        etState = (TextView) findViewById(R.id.state);
        etCountry = (TextView) findViewById(R.id.country);
        bVerifyZipcode = (Button) findViewById(R.id.verifyZipcode);
        updateUser = (Button) findViewById(R.id.updateUser);
        cancel = (Button) findViewById(R.id.cancel);
        Intent intent = getIntent();

        loggedUser = (User) intent.getSerializableExtra("loggedUser");
        user = (User) intent.getSerializableExtra("user");



        try{
            tvWelcome.setText(loggedUser.getFirstName());
            username.setText(user.getUsername());
            password.setText(user.getPassword());
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            mobile.setText(Long.toString(user.getMobileNumber()));

            Address address = user.getAddresses().get(0);
            zipCode = address.getZipCode();
            address_line1.setText(address.getAddressLine1());
            address_line2.setText(address.getAddressLine2());
            etZipcode.setText(zipCode.getZipCode().toString());
            etCity.setText(zipCode.getCity().getCityName());
            etState.setText(zipCode.getCity().getState().getStateName());
            etCountry.setText(zipCode.getCity().getState().getCountry().getCountryName());

        }catch(Exception e){
            showMessage("Exception in UserDetailActivity_1 ",e.getMessage());
        }


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                finish();
                Intent i = new Intent(UserEditActivity.this, UserDetailActivity.class);
                i.putExtra("loggedUser", loggedUser);
                i.putExtra("user", user);
                startActivity(i);
            }
        });

        bVerifyZipcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String code= etZipcode.getText().toString();

                validateZipCode(code);

            }
        });
        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(username.getText().toString().equals("")){
                    showMessage("Error!","Please Enter PG Name.");
                }
                else if(username.getText().toString().contains(" ")){
                    showMessage("Error!","Username cannot have spaces.");
                }
                else if(username.getText().toString().contains(" ")){
                    showMessage("Error!","Password cannot have spaces.");
                }
                else if(password.getText().toString().equals("")){
                    showMessage("Error!","Please Enter PG Code.");
                }
                else if(password.getText().toString().contains(" ")){
                    showMessage("Error!","Password cannot have spaces.");
                }
                else if(firstName.getText().toString().equals("")){
                    showMessage("Error!","Please Enter PG Code.");
                }
                else if(etZipcode.getText().toString().equals("")){
                    showMessage("Error!","Please Enter ZipCode.");
                }
                else if(etCity.getText().toString().equals("")){
                    showMessage("Error!","Please verify ZipCode.");
                }
                else if(etState.getText().toString().equals("")){
                    showMessage("Error!","Please verify ZipCode.");
                }
                else if(etCountry.getText().toString().equals("")){
                    showMessage("Error!","Please verify ZipCode.");
                }
                else {

                    //showMessage("Zipcode",zipCode.toString());

                    GenericParameter addressType = new GenericParameter();
                    addressType.setId(1L);

                    GenericParameter gender = new GenericParameter();
                    gender.setId(4L);

                    Address address = new Address();
                    address.setAddressLine1(address_line1.getText().toString());
                    address.setAddressLine2(address_line2.getText().toString());
                    address.setLastUpdatedBy(loggedUser.getId());
                    address.setActiveAddress(1);
                    address.setAddresstype(addressType);
                    address.setZipCode(zipCode);

                    List<Address> addresses = new ArrayList<Address>();
                    addresses.add(address);

                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setFirstName(firstName.getText().toString());
                    user.setLastName(lastName.getText().toString());
                    user.setMobileNumber(Long.valueOf(mobile.getText().toString()));

                    user.setLastUpdatedBy(loggedUser.getId());
                    user.setStatus(1);
                    user.setGender(gender);
                    user.setAddresses(addresses);

                  /*  before calling saveNewPg check whether code already exists or not.*/
                    updateUser(user)  ;

                }

            }
        });


    }
    public void validateZipCode(String code){

        String  tag_string_req = "string_req";
        String validateURL=baseURL+"/getZipCodeByCode/"+code;
        zipCode = new ZipCode();
        etCity.setText("");
        etState.setText("");
        etCountry.setText("");

        //zipCode.setZipCode(Integer.parseInt(code));

        StringRequest strReq = new StringRequest(Request.Method.GET,
                validateURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //stringResponse=response;
                //  showMessage("Response",response);
                try{
                    zipCode = gson.fromJson(response, ZipCode.class);
                    if(zipCode==null){
                        showMessage("ZipCode "+etZipcode.getText().toString()+" Not found!","Enter Valid ZipCode.");
                    }
                    else{
                        if(zipCode.getCity()!=null){
                            etCity.setText(zipCode.getCity().getCityName());
                        }
                        else{
                            showMessage("Error!","City is not maintained.");
                        }
                        if(zipCode.getCity().getState()!=null){
                            etState.setText(zipCode.getCity().getState().getStateName());
                        }
                        else{
                            showMessage("Error!","State is not maintained.");
                        }
                        if(zipCode.getCity().getState().getCountry()!=null){
                            etCountry.setText(zipCode.getCity().getState().getCountry().getCountryName());
                        }
                        else{
                            showMessage("Error!","Country is not maintained.");
                        }
                    }

                }catch(Exception e){
                    showMessage("In validateZipCodeReq_exception",e.getMessage());
                }
                //  Log.d(TAG, response.toString());
                //   pDialog.hide();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.getMessage()==null){
                    showMessage("ZipCode "+etZipcode.getText().toString()+" Not found!","Enter Valid ZipCode.");
                }
                else{
                    showMessage("Error: ",error.getMessage());
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
    public void updateUser(User user){

        String  tag_string_req = "string_req";
        String URL=baseURL+"/updateUser";

        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            Gson gson = new Gson();
            final String requestBody = gson.toJson(user);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        Gson gson = new Gson();
                        User user = gson.fromJson(response, User.class);
                        finish();
                        // showMessage("Success","User Created Successfully!");
                        Intent i = new Intent(UserEditActivity.this, UserDetailActivity.class);
                        i.putExtra("loggedUser",loggedUser);
                        i.putExtra("user",user);
                        startActivity(i);


                        // showMessage("In saveNewPg ", "Response : "+pg.toString());
                    }catch(Exception e){
                        showMessage("In saveNewUser_1 ", "Exception : "+e.getMessage());
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    showMessage("Error in saveNewUser_2 :",error.toString());
                    //Log.e("VOLLEY", error.toString());
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
                        return null;
                    }
                }

            };

            AppController.getInstance().addToRequestQueue(stringRequest);
        } catch (Exception e) {
            showMessage("Error in saveNewUser_3 :",e.toString());
            // e.printStackTrace();

        }

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
