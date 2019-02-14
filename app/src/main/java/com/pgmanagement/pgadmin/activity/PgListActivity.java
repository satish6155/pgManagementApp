package com.pgmanagement.pgadmin.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.transactional.Pg;
import com.pgmanagement.pgadmin.model.transactional.User;
import com.pgmanagement.pgadmin.volly.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 2/3/2019.
 */



public class PgListActivity extends AppCompatActivity {

    String baseURL;


    Button addPg;
    ListView listViewPgs;

    List<Pg> pgs;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_list);

        baseURL=getString(R.string.base_URL);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        listViewPgs = (ListView) findViewById(R.id.listView);
        addPg = (Button) findViewById(R.id.addPg);

        pgs = new ArrayList<>();

        addPg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PgListActivity.this, AddPgActivty.class);
                i.putExtra("user",user);
                startActivity(i);
            }
        });

        listViewPgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Pg pg = pgs.get(i);

               // showMessage("pg", pg.toString());
                callPgDetailsActivity(pg);



            }
        });
        listViewPgs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                showMessage("Message","Long clicked");
                return true;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        pgs.clear();
        getAllPgRequest();
    }

    public void getAllPgRequest(){
        String  tag_string_req = "string_req";
        String validateURL = baseURL+"/getPgByUser/"+user.getId();

        try {
           // showMessage("user.role",user.getRole().getCode());
            if(user.getRole().getCode().equals("Admin")){
                validateURL=baseURL+"/getAllPg";
            }
        }catch (Exception e){
            showMessage("getAllPgRequest exception_0",e.getMessage());
        }

        StringRequest strReq = new StringRequest(Request.Method.GET,
                validateURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Pg pg = new Pg();


                        Long id = jsonObject.getLong("id");
                        String code = jsonObject.getString("code");
                        String name = jsonObject.getString("name");

                        pg.setId(id);
                        pg.setCode(code);
                        pg.setName(name);
                        pgs.add(pg);

                    }
                    PgListLayout pgAdapter = new PgListLayout(PgListActivity.this, pgs);
                    //attaching adapter to the listview
                    listViewPgs.setAdapter(pgAdapter);


                }catch(Exception e){
                    showMessage("In getAllPgRequest_exception_!",e.getMessage());

                }
                //  Log.d(TAG, response.toString());
                //   pDialog.hide();

            }
            }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.getMessage()==null){
                    showMessage("In getAllPgRequest_exception_2",error.getMessage());
                }
                else{
                    showMessage("In getAllPgRequest_exception_3 ",error.getMessage());
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

    public void callPgDetailsActivity(Pg param_pg){
        String  tag_string_req = "string_req";
        String absoluteURL=baseURL+"/getPgById/"+param_pg.getId();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                absoluteURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try{
                    Pg pg = new Pg();
                    Gson gson = new Gson();
                    pg = gson.fromJson(response, Pg.class);
                    if(pg!=null){
                        Intent intent = new Intent(PgListActivity.this, PgDetail.class);
                        intent.putExtra("user",user);
                        intent.putExtra("pg",pg);
                        startActivity(intent);
                    }

                }catch(Exception e){
                    showMessage("In callPgDetailsActivity_exception_1",e.getMessage());

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.getMessage()==null){
                    showMessage("callPgDetailsActivity_exception_2","Wrong PG ID!");
                }
                else{
                    showMessage("callPgDetailsActivity_exception_3",error.getMessage());
                }
                ;
            }
        });

        try{
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

        }catch(Exception e){
            showMessage("In callPgDetailsActivity_exception_4",e.getMessage());

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

