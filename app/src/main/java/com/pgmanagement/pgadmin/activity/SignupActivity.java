package com.pgmanagement.pgadmin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pgmanagement.pgadmin.R;

/**
 * Created by ADMIN on 1/19/2019.
 */

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /*TextView tvUsername= (TextView) findViewById(R.id.username);
        TextView tvPassword= (TextView) findViewById(R.id.password);  */
        TextView tvlink_login= (TextView) findViewById(R.id.link_login);


        tvlink_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

}
