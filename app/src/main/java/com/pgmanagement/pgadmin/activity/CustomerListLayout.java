package com.pgmanagement.pgadmin.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.transactional.Customer;

import java.util.List;


public class CustomerListLayout extends ArrayAdapter<Customer> {
    private Activity context;
    List<Customer> customers;

    public CustomerListLayout(Activity context, List<Customer> customers) {
        super(context, R.layout.layout_customer_list, customers);
        this.context = context;
        this.customers = customers;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_customer_list, null, true);

        TextView name = (TextView) listViewItem.findViewById(R.id.name);
        TextView rooms = (TextView) listViewItem.findViewById(R.id.role);
        TextView mobile = (TextView) listViewItem.findViewById(R.id.mobile);
        TextView nextDueDate = (TextView) listViewItem.findViewById(R.id.nextDueDate);

        try {
            Customer customer = customers.get(position);
            name.setText("Dummy Name");


            return listViewItem;

        } catch (Exception e) {
            showMessage("In CustomerListLayout exception : ", e.getMessage());
        }
        return null;

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}