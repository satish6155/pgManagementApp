package com.pgmanagement.pgadmin.service;

/**
 * Created by ADMIN on 1/27/2019.
 */

public class userService {

    public static String baseURL="http://192.168.225.28:8080/SpringRestHibernate";



   /* public static String validateUser(String username,String password){

        String  tag_string_req = "string_req";
        String url=baseURL+"/user/1";
        String strResponse;

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //stringResponse=response;
                strResponse = response;
                //etAction.setText(response);
                //  Log.d(TAG, response.toString());
                //   pDialog.hide();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                //  pDialog.hide();
            }
        });

        try{
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

        }catch(Exception e){
                return e.getMessage();
        }
        return "yo";


    }*/

}
