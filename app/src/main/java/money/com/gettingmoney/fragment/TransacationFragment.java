package money.com.gettingmoney.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.chartss.PieChart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.LoginActivity;
import money.com.gettingmoney.bai.activity.MoniStockHomeActivity;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bean.TransBean;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.user.UserUtil;

public class TransacationFragment extends BaseFragment {


    @InjectView(R.id.mPieChart)
    PieChart mPieChart;
    @InjectView(R.id.tv_total)
    TextView tvTotal;
    @InjectView(R.id.tv_gupiao)
    TextView tvGupiao;
    @InjectView(R.id.tv_keyong)
    TextView tvKeyong;

    /**
     * 交易界面
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toolBar = new MyToolBar(getActivity(), "", "交易", "");
        View view = requestView(inflater, R.layout.fragment_transacation);
        ButterKnife.inject(this, view);
        initEvent();
        return view;
    }

    private void initEvent() {
//        //饼状图
//        PieData mPieData = getPieData(5, 100);
//        showChart(mPieChart, mPieData);

        if (ShareUtil.getInstance().getUserNumber(getActivity()).length() == 0) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return;
        }
        new Thread(findUserWallet).run();
    }


    @Override
    public void requestInit() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    Runnable findUserWallet = new Runnable() {
        @Override
        public void run() {
            UserUtil util = new UserUtil();
            util.findUserWallet(null, ShareUtil.getInstance().getUserNumber(getActivity()), new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    Log.i("新闻列表的点进去返回的值", result);

                    //解析
                    TransBean Bean = new Gson().fromJson(result, new TypeToken<TransBean>() {
                    }.getType());

                    tvKeyong.setText(String.valueOf(Bean.getResult().getSupMoney()));
                    tvTotal.setText(String.valueOf(Bean.getResult().getTotleMoney()));
                    tvGupiao.setText(String.valueOf(Bean.getResult().getShareMoney()));


                }
            });
        }
    };


/*    private void showChart(PieChart pieChart, PieData pieData) {
//        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(45f);  //半径
        pieChart.setTransparentCircleRadius(47f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        //使用百分比
//        pieChart.setUsePercentValues(true);
        pieChart.setDrawSliceText(true);

        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比
//        pieChart.setUnit("€");
//        pieChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

//      mChart.setOnAnimationListener(this);

//        pieChart.setCenterText("我爱中国");  //饼状图中间的文字

        //设置数据
        pieChart.setData(pieData);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();
//
        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.LEFT_OF_CHART_INSIDE);  //最右边显示
        mLegend.setForm(Legend.LegendForm.EMPTY);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360);
    }

    *//**
     * @param count 分成几部分
     * @param range
     *//*
    private PieData getPieData(int count, float range) {
//
//        List<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
//
//        for (int i = 0; i < count; i++) {
//            xValues.add("Quarterly" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
//        }

        List<PieEntry> yValues = new ArrayList<PieEntry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        */

    /**
     * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
     * 所以 14代表的百分比就是14%
     *//*
        float quarterly1 = 130;
        float quarterly2 = 24;
        float quarterly3 = 360;
        float quarterly4 = 200;
        float quarterly5 = 200;

        //这里显示的是百分比
        yValues.add(new PieEntry(quarterly1, 0));
        yValues.add(new PieEntry(quarterly2, 0));
        yValues.add(new PieEntry(quarterly3,0));
        yValues.add(new PieEntry(quarterly4, 0));
        yValues.add(new PieEntry(quarterly5, 0));




        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "");

        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));
        colors.add(Color.rgb(25, 120, 100));
        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        return pieData;
    }*/
    @OnClick(R.id.rl_moni_stock)
    public void onClick() {
        startActivity(new Intent(getActivity(), MoniStockHomeActivity.class
        ));
    }
}
