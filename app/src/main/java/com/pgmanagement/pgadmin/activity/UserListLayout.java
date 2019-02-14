package com.pgmanagement.pgadmin.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.transactional.User;

import java.util.List;


public class UserListLayout extends ArrayAdapter<User> {
    private Activity context;
    List<User> users;

    public UserListLayout(Activity context, List<User> users) {
        super(context, R.layout.layout_user_list, users);
        this.context = context;
        this.users = users;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_user_list, null, true);

        try {
            TextView name = (TextView) listViewItem.findViewById(R.id.name);
            TextView role = (TextView) listViewItem.findViewById(R.id.role);
            TextView mobile = (TextView) listViewItem.findViewById(R.id.mobile);
            TextView status = (TextView) listViewItem.findViewById(R.id.activeFlag);


            User user = users.get(position);
            name.setText(user.getFirstName()+" "+user.getLastName());
            if(user.getRole()!=null){
                role.setText(user.getRole().getDescription());
            }

            mobile.setText(Long.toString(user.getMobileNumber()));
            if(user.getStatus()==1){
                status.setText("Active");
            }else{
                status.setText("Inactive");
            }

            return listViewItem;

        } catch (Exception e) {
            showMessage("In UserListLayout exception : ", e.getMessage());
        }
        return listViewItem;

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}