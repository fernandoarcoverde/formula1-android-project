package com.unibratec.ads.formula1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.unibratec.ads.formula1.adapters.DriverPositionAdapter;
import com.unibratec.ads.formula1.OnDriverPositionClick;
import com.unibratec.ads.formula1.R;
import com.unibratec.ads.formula1.dao.DataBaseEvent;
import com.unibratec.ads.formula1.dao.DriverDAO;
import com.unibratec.ads.formula1.model.DriverPosition;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by root on 10/06/17.
 */

public class FavoritesDriversFragment extends Fragment {

    private ListView mListDriverPositions;
    private List<DriverPosition> driverPositions;
    private DriverDAO driverDAO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites_drivers, container, false);

        mListDriverPositions = (ListView)view.findViewById(R.id.list_driver_positions);
        mListDriverPositions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if(getActivity() instanceof OnDriverPositionClick){
                    DriverPosition driverPosition = (DriverPosition) mListDriverPositions
                            .getItemAtPosition(position);

                    ((OnDriverPositionClick)getActivity()).onDriverPositionClick(driverPosition);

                }

            }
        });

        updateList();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private  void updateList(){
        driverDAO = new DriverDAO(getActivity());
        driverPositions = driverDAO.selectDriverPositions();

        mListDriverPositions.setAdapter(new DriverPositionAdapter(getActivity(), driverPositions));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DataBaseEvent event) {
        updateList();
    }
}
