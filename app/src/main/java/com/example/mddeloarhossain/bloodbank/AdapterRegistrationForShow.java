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
        import android.widget.Filter;
        import android.widget.Filterable;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;
        import java.util.List;

        import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by MD. DELOAR HOSSAIN on 01-May-19.
 */

public class AdapterRegistrationForShow extends RecyclerView.Adapter<AdapterRegistrationForShow.MyViewHolder> implements Filterable {

    //****For Filtering here we use " implements Filterable " ****//
    private Context context;
    private List<AdapterRegistration> uploadList;
    private List<AdapterRegistration> uploadList1;//****For Filtering ****//
    private OnItemClickListener listener;

    public AdapterRegistrationForShow(Context context, List<AdapterRegistration> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
        uploadList1 = new ArrayList<>(uploadList);//****For Filtering****//
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_all_donors_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        AdapterRegistration uploadWithDetails = uploadList.get(position);
        holder.name.setText(uploadWithDetails.getName());
        holder.city.setText(uploadWithDetails.getCity());
        holder.location.setText(uploadWithDetails.getLocation());
        holder.bloodgroup.setText(uploadWithDetails.getBloodgroup());
        Picasso.with(context)
                .load(uploadWithDetails.getImageUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }







    //***********************Start For Filtering *******************************//
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<AdapterRegistration> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(uploadList1);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (AdapterRegistration item : uploadList1) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    else if (item.getCity().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    else if (item.getLocation().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    else if (item.getAge().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    else if (item.getBloodgroup().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    else if (item.getContactnumber().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    else if (item.getGender().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }

                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            uploadList.clear();
            uploadList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    //***********************Finish For Filtering *******************************//







    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        //TextView textView,nametextView;
        TextView name, location, city, bloodgroup;
        //ImageView image;
        CircleImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.donorListNameItemTextViewId);
            city = itemView.findViewById(R.id.donorListCityItemTextViewId);
            location = itemView.findViewById(R.id.donorListLocationItemTextViewId);
            bloodgroup = itemView.findViewById(R.id.donorListBloodItemTextViewId);
            imageView = itemView.findViewById(R.id.imageViewId);
            /*textView = itemView.findViewById(R.id.cardTextViewId);
            nametextView = itemView.findViewById(R.id.cardNameTextViewId);
            imageView = itemView.findViewById(R.id.cardImageViewId);*/
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


    public  void setOnItemClickListener(OnItemClickListener listener){

        this.listener = listener;

    }


}


