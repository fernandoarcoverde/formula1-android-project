package com.unibratec.ads.formula1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.unibratec.ads.formula1.GetImage;
import com.unibratec.ads.formula1.R;
import com.unibratec.ads.formula1.model.DriverPosition;

import java.util.List;

/**
 * Created by root on 08/06/17.
 */

public class DriverPositionAdapter extends ArrayAdapter<DriverPosition> {

    public static final int MAX_FLAG_BY_RATINGBAR = 10;
    public static final int TOTAL_ELEMENTS = 1;
    public static final int NO_WINS = 0;

    public DriverPositionAdapter(Context context, List<DriverPosition> driverPositions) {
        super(context, 0, driverPositions);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DriverPosition driverPosition = getItem(position);

        ViewHolder viewHolder;
        GetImage getImage = new GetImage(getContext());
        int driverWins;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_driver, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.txtPosition         = (TextView)convertView
                    .findViewById(R.id.item_driver_position);
            viewHolder.txtSecondName       = (TextView)convertView
                    .findViewById(R.id.item_driver_second_name);
            viewHolder.txtFirstName        = (TextView)convertView
                    .findViewById(R.id.item_driver_first_name);
            viewHolder.txtPoints           = (TextView)convertView
                    .findViewById(R.id.item_driver_points);
            viewHolder.imgImageDriver      = (ImageView)convertView
                    .findViewById(R.id.item_driver_image);
            viewHolder.imgImageFlag        = (ImageView)convertView
                    .findViewById(R.id.item_driver_flag);
            viewHolder.imgImageConstructor = (ImageView)convertView
                    .findViewById(R.id.item_driver_logo_constructor);
            viewHolder.ratingBar_1         = (RatingBar)convertView
                    .findViewById(R.id.ratingBarWins_1);
            viewHolder.ratingBar_2         = (RatingBar)convertView
                    .findViewById(R.id.ratingBarWins_2);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.txtPosition.setText(driverPosition.getPosition());
        viewHolder.txtSecondName.setText(driverPosition.getDriver().getFamilyName());
        viewHolder.txtFirstName.setText(driverPosition.getDriver().getGivenName());
        viewHolder.txtPoints.setText(driverPosition.getPoints());

        if (driverPosition.getUrlImageDriver() != null) {
            Picasso.with(getContext())
                    .load(driverPosition.getUrlImageDriver())
                    .into(viewHolder.imgImageDriver);
        }else {
            viewHolder.imgImageDriver.setImageResource(R.drawable.nodriver);
        }

        viewHolder.imgImageFlag.setImageResource(getImage.Flag(driverPosition.getDriver()
                .getNationality().toLowerCase()));

        viewHolder.imgImageConstructor.setImageResource(getImage.Constructor(driverPosition
                .getConstructors().get(driverPosition.getConstructors().size()-TOTAL_ELEMENTS)
                .getName()));

        driverWins = Integer.parseInt(driverPosition.getWins());
        if ( driverWins == NO_WINS) {
            viewHolder.ratingBar_1.setVisibility(View.GONE);
            viewHolder.ratingBar_2.setVisibility(View.GONE);
        } else if (driverWins <= MAX_FLAG_BY_RATINGBAR){
            viewHolder.ratingBar_2.setVisibility(View.GONE);
            viewHolder.ratingBar_1.setNumStars(driverWins);
            viewHolder.ratingBar_1.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ratingBar_1.setNumStars(MAX_FLAG_BY_RATINGBAR);
            viewHolder.ratingBar_2.setNumStars(driverWins - MAX_FLAG_BY_RATINGBAR);
            viewHolder.ratingBar_1.setVisibility(View.VISIBLE);
            viewHolder.ratingBar_2.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    private static class ViewHolder{
        TextView  txtPosition;
        TextView  txtSecondName;
        TextView  txtFirstName;
        TextView  txtPoints;
        ImageView imgImageDriver;
        ImageView imgImageFlag;
        ImageView imgImageConstructor;
        RatingBar ratingBar_1;
        RatingBar ratingBar_2;
    }



}