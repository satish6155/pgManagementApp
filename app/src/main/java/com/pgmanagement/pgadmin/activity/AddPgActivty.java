package com.pgmanagement.pgadmin.activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.pgmanagement.pgadmin.R;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pgmanagement.pgadmin.model.master.GenericParameter;
import com.pgmanagement.pgadmin.model.master.ZipCode;
import com.pgmanagement.pgadmin.model.transactional.Address;
import com.pgmanagement.pgadmin.model.transactional.Pg;
import com.pgmanagement.pgadmin.model.transactional.User;
import com.pgmanagement.pgadmin.volly.AppController;

import java.io.UnsupportedEncodingException;


public class AddPgActivty extends AppCompatActivity {

    String baseURL;
    private Gson gson = new Gson();

    TextView tvWelcome,etCity,etState,etCountry;
    EditText etPgName,etPgCode,address_line1,address_line2,etZipcode;
    Button bVerifyZipcode,btn_addPg,cancel;
    User user ;
    ZipCode zipCode;

    boolean demoSetup = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pg);

        baseURL=getString(R.string.base_URL);

      /*  GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        gson = gsonBuilder.create();*/

        tvWelcome = (TextView) findViewById(R.id.welcome);
        etPgName = (EditText) findViewById(R.id.etPgName);
        etPgCode = (EditText) findViewById(R.id.etPgCode);
        address_line1 = (EditText) findViewById(R.id.address_line1);
        address_line2 = (EditText) findViewById(R.id.address_line2);
        etZipcode = (EditText) findViewById(R.id.zipCode);
        etCity = (TextView) findViewById(R.id.city);
        etState = (TextView) findViewById(R.id.state);
        etCountry = (TextView) findViewById(R.id.country);
        bVerifyZipcode = (Button) findViewById(R.id.verifyZipcode);
        btn_addPg = (Button) findViewById(R.id.btn_addPg);
        cancel = (Button) findViewById(R.id.cancel);
        Intent intent = getIntent();

        user = (User) intent.getSerializableExtra("user");

        if(demoSetup){showMessage("Hi", "It is a demo setup.");}

        if (user.getFirstName() != null) {
            tvWelcome.setText(user.getFirstName());
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                 finish();
                Intent i = new Intent(AddPgActivty.this, PgListActivity.class);
                i.putExtra("user", user);
                startActivity(i);
            }
        });

        bVerifyZipcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String code= etZipcode.getText().toString();

                if(!demoSetup){
                    validateZipCode(code);
                }
                else{

                    if(etZipcode.getText().toString().equals("272001")){
                        etCity.setText("Basti");
                        etState.setText("Uttar Pradesh");
                        etCountry.setText("INDIA");

                    }
                    else{
                        showMessage("ZipCode "+etZipcode.getText().toString()+" Not found!","Enter Valid ZipCode.");

                    }
                }

            }
        });
        btn_addPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(etPgName.getText().toString().equals("")){
                    showMessage("Error!","Please Enter PG Name.");
                }
                else if(etPgCode.getText().toString().equals("")){
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

                    Address address = new Address();
                    address.setAddressLine1(address_line1.getText().toString());
                    address.setAddressLine2(address_line2.getText().toString());
                    address.setCreatedBy(user.getId());
                    address.setActiveAddress(1);
                    address.setAddresstype(addressType);
                    address.setZipCode(zipCode);

                    Pg pg = new Pg();
                    pg.setCode(etPgCode.getText().toString());
                    pg.setName(etPgName.getText().toString());
                    pg.setCreatedBy(user.getId());
                    pg.setActiveFlag(1);
                    pg.setAddress(address);

                  /*  before calling saveNewPg check whether code already exists or not.*/
                    saveNewPg(pg)  ;

                }

            }
        });


    }
    public void validateZipCode(String code){

        String  tag_string_req = "string_req";
        String validateURL=baseURL+"/getZipCodeByCode/"+code;

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

    public void saveNewPg(Pg pg){

        if(demoSetup){
           // finish();
            Intent i = new Intent(AddPgActivty.this, PgDetail.class);
            i.putExtra("user",user);
            i.putExtra("pg",pg);
            startActivity(i);
        }

        String  tag_string_req = "string_req";
        String URL=baseURL+"/saveNewPg";

        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            Gson gson = new Gson();
            final String requestBody = gson.toJson(pg);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{
                        Gson gson = new Gson();
                        Pg pg = gson.fromJson(response, Pg.class);
                        finish();
                        Intent i = new Intent(AddPgActivty.this, PgDetail.class);
                        i.putExtra("user",user);
                        i.putExtra("pg",pg);
                        startActivity(i);


                       // showMessage("In saveNewPg ", "Response : "+pg.toString());
                    }catch(Exception e){
                        showMessage("In saveNewPg ", "Exception : "+e.getMessage());
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    showMessage("Error in saveNewPg1 :",error.toString());
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
            showMessage("Error in saveNewPg2 :",e.toString());
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
