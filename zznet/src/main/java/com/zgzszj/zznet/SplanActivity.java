package com.zgzszj.zznet;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class SplanActivity extends AppCompatActivity {

    @butterknife.InjectView(R.id.splan_vpguid)
    ViewPager splanVpguid;
    List<ImageView> pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splan);
        splanVpguid.setAdapter(adapter);

    }

    PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    };
}
