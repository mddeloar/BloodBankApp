package com.example.mddeloarhossain.bloodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MD. DELOAR HOSSAIN on 22-Apr-19.
 */

public class firstAdapter extends BaseAdapter {
    Context context;
    int[] fimages;
    String[] fgridNames;
    private LayoutInflater inflater;

    firstAdapter (Context context,String[] fgridNames, int[] fimages ){
        this.context=context;
        this.fgridNames=fgridNames;
        this.fimages=fimages;
    }


    @Override
    public int getCount() {
        return fgridNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.firstsample_view,viewGroup,false);

            ImageView imageView = view.findViewById(R.id.fimageViewId);
            TextView textView = view.findViewById(R.id.ftextViewId);

            imageView.setImageResource(fimages[i]);
            textView.setText(fgridNames[i]);
        }
        return view;
    }
}
