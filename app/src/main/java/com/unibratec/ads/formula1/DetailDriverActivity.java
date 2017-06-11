package com.unibratec.ads.formula1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.unibratec.ads.formula1.fragments.DetailDriverFragment;

/**
 * Created by root on 10/06/17.
 */

public class DetailDriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_driver);


        Bundle bundle = getIntent().getExtras();
        DetailDriverFragment detailDriverFragment = DetailDriverFragment.newInstance(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_driver_detail, detailDriverFragment, "detailDriver")
                .commit();

    }




}

