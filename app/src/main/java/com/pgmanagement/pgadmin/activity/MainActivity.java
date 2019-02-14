package com.pgmanagement.pgadmin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.transactional.User;

/**
 * Created by ADMIN on 1/19/2019.
 */

public class MainActivity extends AppCompatActivity {


    TextView tvWelcome;
    Button bAddPg,bAddUser,bLogout,viewUsers,viewPgs;
    User  loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bAddPg = (Button) findViewById(R.id.addPg);
        bAddUser = (Button) findViewById(R.id.addUser);
        viewPgs = (Button) findViewById(R.id.viewPgs);
        viewUsers = (Button) findViewById(R.id.go_to_user);
        bLogout = (Button) findViewById(R.id.btn_logout);
        tvWelcome = (TextView) findViewById(R.id.welcome);

        Intent intent = getIntent();
        loggedUser = (User) intent.getSerializableExtra("loggedUser");

        if(loggedUser.getFirstName()!=null){
            tvWelcome.setText("Welcome "+loggedUser.getFirstName());
        }

        bAddPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(MainActivity.this, AddPgActivty.class);
                i.putExtra("user",loggedUser);
                startActivity(i);
            }
        });
        bAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(MainActivity.this, UserAddActivity.class);
                i.putExtra("loggedUser",loggedUser);
                startActivity(i);
            }
        });

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                finish();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        viewPgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

               // finish();
                Intent i = new Intent(MainActivity.this, PgListActivity.class);
                i.putExtra("user", loggedUser);
                startActivity(i);
            }
        });

        viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                // finish();
                Intent i = new Intent(MainActivity.this, UserListActivity.class);
                i.putExtra("loggedUser", loggedUser);
                startActivity(i);
            }
        });




    }

}
