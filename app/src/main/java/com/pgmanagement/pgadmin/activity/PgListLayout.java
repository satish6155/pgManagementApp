package com.pgmanagement.pgadmin.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pgmanagement.pgadmin.R;
import com.pgmanagement.pgadmin.model.transactional.Pg;

import java.util.List;


public class PgListLayout extends ArrayAdapter<Pg> {
    private Activity context;
    List<Pg> pgs;

    public PgListLayout(Activity context, List<Pg> pgs) {
        super(context, R.layout.layout_pg_list, pgs);
        this.context = context;
        this.pgs = pgs;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_pg_list, null, true);

        TextView name = (TextView) listViewItem.findViewById(R.id.name);
        TextView rooms = (TextView) listViewItem.findViewById(R.id.nextDueDate);
        TextView address = (TextView) listViewItem.findViewById(R.id.address);
        TextView expiryDate = (TextView) listViewItem.findViewById(R.id.role);

        try {
            Pg pg = pgs.get(position);
            name.setText(pg.getCode()+" : "+pg.getName());
            //address.setText(pg.getAddress().getAddressLine1()+" , "+pg.getAddress().getAddressLine2());

            expiryDate.setText("Never");

            return listViewItem;

        }catch (Exception e){
            showMessage("In PgListLayout exception : ", e.getMessage());
        }
        return null;

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}