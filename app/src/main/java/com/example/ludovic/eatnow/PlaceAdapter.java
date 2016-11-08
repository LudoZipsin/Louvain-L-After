package com.example.ludovic.eatnow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ludovic Zipsin on 03/11/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private ArrayList<Place> placeList;
    private Activity activity;

    public PlaceAdapter(ArrayList<Place> placeList, Activity activity){
        this.placeList = placeList;
        this.activity = activity;
    }

    @Override
    public int getItemCount(){
        return placeList.size();
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder placeViewHolder, int i){
        final Place place = placeList.get(i);
        placeViewHolder.vName.setText(place.getName());
        placeViewHolder.vDescription.setText(place.getDesc());
        placeViewHolder.vDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO launch detailed activity with map and button for navigation
                Intent intent = new Intent(activity, DetailedPlaceActivity.class);
                intent.putExtra("name",place.getName());
                intent.putExtra("lat",place.getLat());
                intent.putExtra("lon",place.getLon());
                intent.putExtra("phone",place.getPhone());
                intent.putExtra("address",place.getAddress());
                intent.putExtra("desc",place.getDesc());
                intent.putExtra("openmonday", place.getOpenMonday());
                intent.putExtra("opentuesday", place.getOpenTuesday());
                intent.putExtra("openwednesday", place.getOpenWednesday());
                intent.putExtra("openthursday", place.getOpenThursday());
                intent.putExtra("openfriday", place.getOpenFriday());
                intent.putExtra("opensaturday", place.getOpenSaturday());
                intent.putExtra("opensunday", place.getOpenSunday());
                intent.putExtra("closemonday", place.getCloseMonday());
                intent.putExtra("closetuesday", place.getCloseTuesday());
                intent.putExtra("closewednesday", place.getCloseWednesday());
                intent.putExtra("closethursday", place.getCloseThursday());
                intent.putExtra("closefriday", place.getCloseFriday());
                intent.putExtra("closesaturday", place.getCloseSaturday());
                intent.putExtra("closesunday", place.getCloseSunday());
                activity.startActivity(intent);
            }
        });
        placeViewHolder.btnNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO launch activity for navigation
            }
        });
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.place_card, viewGroup, false);
        return new PlaceViewHolder(itemView);
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected TextView vDescription;
        protected Button btnNavigation;

        public PlaceViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.place_name);
            vDescription = (TextView) v.findViewById(R.id.place_description);
            btnNavigation = (Button) v.findViewById(R.id.place_navigation_button);
        }
    }

}
