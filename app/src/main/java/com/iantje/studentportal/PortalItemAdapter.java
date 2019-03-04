package com.iantje.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PortalItemAdapter extends RecyclerView.Adapter<PortalItemAdapter.ViewHolder > {

    private List<PortalItem> portalItems;

    public PortalItemAdapter (List<PortalItem> portalItems) {
        this.portalItems = portalItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);

        return new PortalItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PortalItem portalItem = portalItems.get(i);
        viewHolder.textView.setText(portalItem.name);
    }

    @Override
    public int getItemCount() {
        return portalItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
