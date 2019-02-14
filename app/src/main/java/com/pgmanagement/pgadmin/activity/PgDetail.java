package com.pgmanagement.pgadmin.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.master.ZipCode;
import com.pgmanagement.pgadmin.model.transactional.Pg;
import com.pgmanagement.pgadmin.model.transactional.User;

public class PgDetail extends AppCompatActivity {

    String baseURL;
    private Gson gson = new Gson();

    /*  TextView tvWelcome;
      EditText etPgName,etPgCode,address_line1,address_line2,etZipcode,etCity,etState,etCountry;
      Button bVerifyZipcode,btn_addPg;*/
    User user;
    Pg pg;

    TextView etPgName,etPgCode,address_line1,address_line2,etZipcode,etCity,etState,etCountry;
    Button editPg,back;

    boolean demoSetup = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_detail);

        baseURL=getString(R.string.base_URL);

      /*  GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        gson = gsonBuilder.create();*/

        etPgName = (TextView) findViewById(R.id.etPgName);
        etPgCode = (TextView) findViewById(R.id.etPgCode);
        address_line1 = (TextView) findViewById(R.id.address_line1);
        address_line2 = (TextView) findViewById(R.id.address_line2);
        etZipcode = (TextView) findViewById(R.id.zipCode);
        etCity = (TextView) findViewById(R.id.city);
        etState = (TextView) findViewById(R.id.state);
        etCountry = (TextView) findViewById(R.id.country);

        editPg = (Button) findViewById(R.id.editPg);
        back = (Button) findViewById(R.id.back);

        //showMessage("Here","1");

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        pg = (Pg) intent.getSerializableExtra("pg");

        //showMessage("PG : ",pg.toString());

        //showMessage("Here","2");

        etPgName.setText(pg.getName());
        etPgCode.setText(pg.getCode());


        if (demoSetup) {
            showMessage("Hi", "It is a demo setup.");

            etZipcode.setText("272001");
            etCity.setText("Basti");
            etState.setText("Uttar Pradesh");
            etCountry.setText("INDIA");
        }
        else{
            try {
                address_line1.setText(pg.getAddress().getAddressLine1());
                address_line2.setText(pg.getAddress().getAddressLine2());
                etZipcode.setText(pg.getAddress().getZipCode().getZipCode().toString());
                etCity.setText(pg.getAddress().getZipCode().getCity().getCityName());
                etState.setText(pg.getAddress().getZipCode().getCity().getState().getStateName());
                etCountry.setText(pg.getAddress().getZipCode().getCity().getState().getCountry().getCountryName());
            }catch(Exception e){
                showMessage("Exception :", e.getMessage());
            }

        }

        editPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                finish();
                Intent i = new Intent(PgDetail.this, EditPgActivity.class);
                i.putExtra("user",user);
                i.putExtra("pg",pg);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                finish();
                Intent i = new Intent(PgDetail.this, PgListActivity.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });


    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
