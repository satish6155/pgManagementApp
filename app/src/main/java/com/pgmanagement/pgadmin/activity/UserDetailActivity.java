package com.pgmanagement.pgadmin.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.master.ZipCode;
import com.pgmanagement.pgadmin.model.transactional.Address;
import com.pgmanagement.pgadmin.model.transactional.User;

/**
 * Created by ADMIN on 2/9/2019.
 */

public class UserDetailActivity extends AppCompatActivity {

    TextView tvWelcome,etCity,etState,etCountry,username,password,firstName,lastName,mobile,address_line1,address_line2,etZipcode;
    Button back,assignPg,editUser;
    User loggedUser, user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);


        tvWelcome = (TextView) findViewById(R.id.welcome);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        mobile = (TextView) findViewById(R.id.mobile);
        address_line1 = (TextView) findViewById(R.id.address_line1);
        address_line2 = (TextView) findViewById(R.id.address_line2);
        etZipcode = (TextView) findViewById(R.id.zipCode);
        etCity = (TextView) findViewById(R.id.city);
        etState = (TextView) findViewById(R.id.state);
        etCountry = (TextView) findViewById(R.id.country);
        back = (Button) findViewById(R.id.back);
        assignPg = (Button) findViewById(R.id.assignPg);
        editUser = (Button) findViewById(R.id.editUser);

        Intent intent = getIntent();

        loggedUser = (User) intent.getSerializableExtra("loggedUser");
        user = (User) intent.getSerializableExtra("user");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent i = new Intent(UserDetailActivity.this, MainActivity.class);
                i.putExtra("loggedUser", loggedUser);
                startActivity(i);
            }
        });

        assignPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*finish();
                Intent i = new Intent(UserAddActivity.this, MainActivity.class);
                i.putExtra("loggedUser", loggedUser);
                startActivity(i);*/
            }
        });
        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent i = new Intent(UserDetailActivity.this, UserEditActivity.class);
                i.putExtra("loggedUser", loggedUser);
                i.putExtra("user", user);
                startActivity(i);
            }
        });

        try{
            tvWelcome.setText(loggedUser.getFirstName());
            username.setText(user.getUsername());
            password.setText(user.getPassword());
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            mobile.setText(Long.toString(user.getMobileNumber()));

            Address address = user.getAddresses().get(0);
            ZipCode zip = address.getZipCode();
            address_line1.setText(address.getAddressLine1());
            address_line2.setText(address.getAddressLine2());
            etZipcode.setText(zip.getZipCode().toString());
            etCity.setText(zip.getCity().getCityName());
            etState.setText(zip.getCity().getState().getStateName());
            etCountry.setText(zip.getCity().getState().getCountry().getCountryName());

        }catch(Exception e){
            showMessage("Exception in UserDetailActivity_1 ",e.getMessage());
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
