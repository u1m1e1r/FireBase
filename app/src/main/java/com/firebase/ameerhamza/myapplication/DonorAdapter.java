package com.firebase.ameerhamza.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ameer Hamza on 11/20/2017.
 */

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.ViewHolder> {

    List<DonorList> lists;
    Context context;

    public DonorAdapter(List<DonorList> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donor_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DonorList donorList = lists.get(position);
        holder.name.setText(donorList.getName().toString());
        holder.desc.setText(donorList.getDescription().toString());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name ;
        TextView desc ;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
