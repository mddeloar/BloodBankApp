package com.example.mddeloarhossain.bloodbank;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by MD. DELOAR HOSSAIN on 17-Apr-19.
 */

public class CustomAdapter1 extends ArrayAdapter<Donor> {

    private Activity context;
    private List<Donor> donorList;

    public CustomAdapter1(Activity context, List<Donor> donorList) {
        super(context, R.layout.sample_layout, donorList);
        this.context = context;
        this.donorList = donorList;
    }


    @Nullable
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_listv,  null, true);

        Donor donor=donorList.get(position);

        TextView t1=view.findViewById(R.id.donorListNameItemTextViewId);
        TextView t2=view.findViewById(R.id.donorListCityItemTextViewId);
        TextView t3=view.findViewById(R.id.donorListLocationItemTextViewId);
        TextView t4=view.findViewById(R.id.donorListContactItemTextViewId);

        t1.setText("Name : "+donor.getName());
        t2.setText("City : "+donor.getCity());
        t3.setText("Location : "+donor.getLocation());
        t4.setText("Contact No. : "+donor.getContactnumber());

        return view;
    }
}
