package com.capgemini.sandwichclub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> sandwichName;
    ArrayList<String> description;
    ArrayList<String> fileName;
    Context context;
    private AdapterCallback adapterCallback;


    public CustomAdapter(Context context, ArrayList<String> personNames, ArrayList<String> emailIds, ArrayList<String> fileName) {
        this.context = context;
        adapterCallback = ((AdapterCallback) context);
        this.sandwichName = personNames;
        this.description = emailIds;
        this.fileName = fileName;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.name.setText(sandwichName.get(position));
        holder.email.setText(description.get(position));
        holder.path.setText(fileName.get(position));
        holder.path.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterCallback.onMethodCallback(sandwichName.get(position), description.get(position), fileName.get(position));
            }
        });

    }


    @Override
    public int getItemCount() {
        return sandwichName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, path;

        public MyViewHolder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.description);
            path = (TextView) itemView.findViewById(R.id.path);

        }
    }

    public static interface AdapterCallback {
        void onMethodCallback(String name, String description, String imageName);
    }
}
