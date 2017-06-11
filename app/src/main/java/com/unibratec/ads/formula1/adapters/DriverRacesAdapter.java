package com.unibratec.ads.formula1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.unibratec.ads.formula1.R;
import com.unibratec.ads.formula1.model.RaceDriverRound;

import java.util.List;

/**
 * Created by root on 10/06/17.
 */

public class DriverRacesAdapter extends ArrayAdapter<RaceDriverRound> {


    public DriverRacesAdapter(Context context, List<RaceDriverRound> raceDriverRounds) {
        super(context, 0, raceDriverRounds);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RaceDriverRound raceDriverRound = getItem(position);

        ViewHolder viewHolder;


        if (convertView == null) {

            int idLayout = position % 2 == 0 ? R.layout.item_grid_set_color : R.layout.item_grid_unset_color;

            convertView = LayoutInflater.from(getContext()).inflate(idLayout, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.txtRound      = (TextView)convertView.findViewById(R.id.item_grid_round);
            viewHolder.txtRaceName   = (TextView)convertView.findViewById(R.id.item_grid_race_name);
            viewHolder.txtQualifying = (TextView)convertView.findViewById(R.id.item_grid_qualifying);
            viewHolder.txtRace       = (TextView)convertView.findViewById(R.id.item_grid_race);
            viewHolder.txtStatus     = (TextView)convertView.findViewById(R.id.item_grid_status);

            convertView.setTag(viewHolder);

        } else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.txtRound.setText(raceDriverRound.round);
        viewHolder.txtRaceName.setText(raceDriverRound.raceName.replace(" Grand Prix", ""));
        viewHolder.txtQualifying.setText(raceDriverRound.raceDriverDetails.get(0).gridPosition);
        viewHolder.txtRace.setText(raceDriverRound.raceDriverDetails.get(0).finalRacePosition);
        viewHolder.txtStatus.setText(raceDriverRound.raceDriverDetails.get(0).status);

        return convertView;
    }

    private static class ViewHolder{
        TextView txtRound;
        TextView txtRaceName;
        TextView txtQualifying;
        TextView txtRace;
        TextView txtStatus;

    }


}