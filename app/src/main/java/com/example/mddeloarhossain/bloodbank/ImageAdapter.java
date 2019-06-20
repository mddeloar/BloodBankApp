package com.example.mddeloarhossain.bloodbank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MD. DELOAR HOSSAIN on 01-May-19.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private Context context;
    private List<Upload> uploadList;
    private OnItemClickListener listener;

    public ImageAdapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.image_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Upload upload = uploadList.get(position);
        holder.textView.setText(upload.getImageName());
        Picasso.with(context)
                .load(upload.getImageUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView textView;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardTextViewId);
            imageView = itemView.findViewById(R.id.cardImageViewId);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {

            if(listener != null){
                int position = getAdapterPosition();

                if(position != RecyclerView.NO_POSITION){

                    listener.OnItemClick(position);
                }

            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.setHeaderTitle("Choose an action");
            MenuItem doAnyTask = contextMenu.add(Menu.NONE, 1, 1, "do any task");
            MenuItem delete = contextMenu.add(Menu.NONE, 2, 2, "delete");

            doAnyTask.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            if(listener != null){
                int position = getAdapterPosition();

                if(position != RecyclerView.NO_POSITION){

                    switch (menuItem.getItemId()){

                        case 1:
                            listener.onDoAnyTask(position);
                            return true;


                        case 2:
                            listener.onDelete(position);
                            return true;



                    }
                }

            }


            return false;
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
        void onDoAnyTask(int position);
        void onDelete(int position);

    }


    public void setOnItemClickListener(OnItemClickListener listener){

        this.listener = listener;

    }


}
