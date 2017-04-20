package money.com.gettingmoney.Test_Kline;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.BaseActivity;
import money.com.gettingmoney.fragment.Day_KlineFragment;
import money.com.gettingmoney.fragment.Month_KlineFragment;
import money.com.gettingmoney.fragment.Time_KlineFragment;
import money.com.gettingmoney.fragment.Week_KlineFragment;


/*
* k线图最外面的界面
* */

public class KLineActivity extends BaseActivity implements View.OnClickListener {


    private ArrayList<Fragment> fragments;


    Button time_kline, day_kline, week_kline, month_kline;//时分，日线，周线，月线
    String stockId, type;//从板块的详情页面传递过来的股票id


    private FragmentTransaction transaction;

    Day_KlineFragment daykline;
    Week_KlineFragment weeline;
    Time_KlineFragment timekline;
    Month_KlineFragment monthkline;
    FrameLayout container;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kline);

        container = (FrameLayout) findViewById(R.id.container);

        time_kline = (Button) findViewById(R.id.time_kline);
        day_kline = (Button) findViewById(R.id.day_kline);
        week_kline = (Button) findViewById(R.id.week_kline);
        month_kline = (Button) findViewById(R.id.month_kline);

        time_kline.setBackgroundColor(getResources().getColor(R.color._666666));

        time_kline.setOnClickListener(this);
        day_kline.setOnClickListener(this);
        week_kline.setOnClickListener(this);
        month_kline.setOnClickListener(this);


        stockId = getIntent().getStringExtra("stockId");
        type=getIntent().getStringExtra("type");

        bundle = new Bundle();
        bundle.putString("stockId", stockId);
        bundle.putString("type", type);



        fragments = new ArrayList<>();
        if (savedInstanceState == null) {
            fragments.add(timekline);
            fragments.add(daykline);
            fragments.add(weeline);
            fragments.add(monthkline);

        }
        setSelect(0);

    }

    int position;

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        hideFragment(transaction);

        position = i;
        switch (i) {
            case 0:
                if (timekline == null) {
                    timekline = new Time_KlineFragment();
                    timekline.setArguments(bundle);

                    transaction.add(R.id.container, timekline);
                } else {
                    transaction.hide(timekline);
                }
                transaction.show(timekline);
                break;
            case 1:
                if (daykline == null) {
                    daykline = new Day_KlineFragment();

                    daykline.setArguments(bundle);

                    transaction.add(R.id.container, daykline);
                } else {
                    transaction.hide(daykline);
                }
                transaction.show(daykline);
                break;
            case 2:
                if (weeline == null) {
                    weeline = new Week_KlineFragment();

                    weeline.setArguments(bundle);

                    transaction.add(R.id.container, weeline);
                } else {
                    transaction.hide(weeline);
                }
                transaction.show(weeline);
                break;

            case 3:
                if (monthkline == null) {
                    monthkline = new Month_KlineFragment();
                    monthkline.setArguments(bundle);

                    transaction.add(R.id.container, monthkline);
                } else {
                    transaction.hide(monthkline);
                }
                transaction.show(monthkline);
                break;
        }
        transaction.commit();
    }


    private void hideFragment(FragmentTransaction transaction) {
        if (daykline != null) {
            transaction.hide(daykline);
        }
        if (weeline != null) {
            transaction.hide(weeline);
        }

        if (timekline != null) {
            transaction.hide(timekline);
        }
        if (monthkline != null) {
            transaction.hide(monthkline);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_kline:

                setSelect(0);

                time_kline.setBackgroundColor(getResources().getColor(R.color._666666));
                day_kline.setBackgroundColor(getResources().getColor(R.color._383838));
                week_kline.setBackgroundColor(getResources().getColor(R.color._383838));
                month_kline.setBackgroundColor(getResources().getColor(R.color._383838));

                break;

            case R.id.day_kline:
                setSelect(1);

                day_kline.setBackgroundColor(getResources().getColor(R.color._666666));
                time_kline.setBackgroundColor(getResources().getColor(R.color._383838));
                week_kline.setBackgroundColor(getResources().getColor(R.color._383838));
                month_kline.setBackgroundColor(getResources().getColor(R.color._383838));

                break;

            case R.id.week_kline:

                week_kline.setBackgroundColor(getResources().getColor(R.color._666666));
                time_kline.setBackgroundColor(getResources().getColor(R.color._383838));
                day_kline.setBackgroundColor(getResources().getColor(R.color._383838));
                month_kline.setBackgroundColor(getResources().getColor(R.color._383838));


                setSelect(2);
                break;

            case R.id.month_kline:
                setSelect(3);
                month_kline.setBackgroundColor(getResources().getColor(R.color._666666));
                time_kline.setBackgroundColor(getResources().getColor(R.color._383838));
                week_kline.setBackgroundColor(getResources().getColor(R.color._383838));
                day_kline.setBackgroundColor(getResources().getColor(R.color._383838));

                break;

        }
    }

}
