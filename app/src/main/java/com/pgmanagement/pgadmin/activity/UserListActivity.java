package com.pgmanagement.pgadmin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.master.GenericParameter;
import com.pgmanagement.pgadmin.model.transactional.User;
import com.pgmanagement.pgadmin.volly.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    String baseURL;

    Button addUser;
    ListView listViewUsers;

    List<User> users;
    User loggedUser;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        baseURL = getString(R.string.base_URL);

        Intent intent = getIntent();
        loggedUser = (User) intent.getSerializableExtra("loggedUser");
        listViewUsers = (ListView) findViewById(R.id.listView);
        addUser = (Button) findViewById(R.id.addUser);

        users = new ArrayList<>();


        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(UserListActivity.this,
                        AddUserActivty.class);
                i.putExtra("user", user);
                startActivity(i);*/
            }
        });

        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int i, long l) {

                        User user = users.get(i);

                        // showMessage("user", user.toString());
                        callUserDetailsActivity(user);

                    }
                });
        listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView,
                                                   View view, int i, long l) {

                        showMessage("Message", "Long clicked");
                        return true;
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        users.clear();
        getAllUserRequest();
    }

    public void getAllUserRequest() {
        String tag_string_req = "string_req";
        String URL = baseURL + "/getUserByCreatedBy/" + loggedUser.getId();

        try {
            // showMessage("user.role",user.getRole().getCode());
            if ((loggedUser.getRole()!=null) && (loggedUser.getRole().getCode().equals("Admin"))) {
                URL = baseURL + "/getAllUsers";
            }
        } catch (Exception e) {
            showMessage("getAllUsersRequest exception_0", e.getMessage());
        }

        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray
                                .getJSONObject(i);
                        User user = new User();

                        user.setId(jsonObject.getLong("id"));
                        user.setFirstName(jsonObject.getString("firstName"));
                        user.setLastName(jsonObject.getString("lastName"));
                        user.setMobileNumber(jsonObject.getLong("mobileNumber"));
                        user.setStatus(new Integer(jsonObject.getInt("status")));
                      //  showMessage(Integer.toString(jsonObject.getInt("activeFlag")));

                        String role = jsonObject.getString("role");
                        GenericParameter gpRole =  gson.fromJson(role, GenericParameter.class) ;
                        user.setRole(gpRole);



                        users.add(user);

                    }
                    UserListLayout userAdapter = new UserListLayout(
                            UserListActivity.this, users);
                    // attaching adapter to the listview
                    listViewUsers.setAdapter(userAdapter);

                } catch (Exception e) {
                    showMessage("In getAllUsersRequest_exception_1",
                            e.getMessage());

                }
                // Log.d(TAG, response.toString());
                // pDialog.hide();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error.getMessage() == null) {
                    showMessage("In getAllUsersRequest_exception_2",
                            error.getMessage());
                } else {
                    showMessage("In getAllUsersRequest_exception_3 ",
                            error.getMessage());
                }
                ;
            }
        });

        try {
            AppController.getInstance().addToRequestQueue(strReq,
                    tag_string_req);

        } catch (Exception e) {
            showMessage("In makeGetReq_exception", e.getMessage());

        }
    }

    public void callUserDetailsActivity(User param_user) {
        String tag_string_req = "string_req";
        String absoluteURL = baseURL + "/getUserById/" + param_user.getId();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                absoluteURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    User user = new User();
                    Gson gson = new Gson();
                    user = gson.fromJson(response, User.class);
                   // showMessage("In callUserDetailsActivity_2, User : ",user.toString());
                    if (user != null) {
                        Intent intent = new Intent(
                                UserListActivity.this, UserDetailActivity.class);
                        intent.putExtra("loggedUser", loggedUser);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    showMessage(
                            "In callUserDetailsActivity_exception_1",
                            e.getMessage());

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error.getMessage() == null) {
                    showMessage("callUserDetailsActivity_exception_2",
                            "Wrong User ID!");
                } else {
                    showMessage("callUserDetailsActivity_exception_3",
                            error.getMessage());
                }
                ;
            }
        });

        try {
            AppController.getInstance().addToRequestQueue(strReq,
                    tag_string_req);

        } catch (Exception e) {
            showMessage("In callUserDetailsActivity_exception_4",
                    e.getMessage());
        }
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}