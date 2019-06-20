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

public class CustomAdapter extends BaseAdapter {
    Context context;
    int[] images;
    String[] gridNames;
    private LayoutInflater inflater;

    CustomAdapter(Context context,String[] gridNames, int[] images ){
        this.context=context;
        this.gridNames=gridNames;
        this.images=images;
    }


    @Override
    public int getCount() {
        return gridNames.length;
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
            view = inflater.inflate(R.layout.sample_view,viewGroup,false);

            ImageView imageView = view.findViewById(R.id.imageViewId);
            TextView textView = view.findViewById(R.id.textViewId);

            imageView.setImageResource(images[i]);
            textView.setText(gridNames[i]);
        }
        return view;
    }
}

