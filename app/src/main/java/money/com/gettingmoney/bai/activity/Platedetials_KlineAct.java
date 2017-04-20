package money.com.gettingmoney.bai.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.mikephil.charting.chartss.CombinedChart;
import com.github.mikephil.charting.componentss.Legend;
import com.github.mikephil.charting.componentss.XAxis;
import com.github.mikephil.charting.componentss.YAxis;
import com.github.mikephil.charting.datas.BarEntry;
import com.github.mikephil.charting.datas.CandleData;
import com.github.mikephil.charting.datas.CandleDataSet;
import com.github.mikephil.charting.datas.CandleEntry;
import com.github.mikephil.charting.datas.CombinedData;
import com.github.mikephil.charting.datas.Entry;
import com.github.mikephil.charting.datas.LineData;
import com.github.mikephil.charting.datas.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.Test_Kline.DataParse;
import money.com.gettingmoney.Test_Kline.KLineActivity;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.model.PlateStock_bean;
import money.com.gettingmoney.bai.model.homeNewsitem;
import money.com.gettingmoney.bai.view.ListViewForScrollView;
import money.com.gettingmoney.bean.UserStocks;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.hangqing.indexuti;
import money.com.gettingmoney.webutil.news.NewsUtil;
import money.com.gettingmoney.weiget.LoadingDialog;

//板块的详情界面
public class Platedetials_KlineAct extends Activity implements View.OnClickListener {

    private ImageView img_back;
    TextView shock_name, share, option, detials_kline, buy, sell, kill_order;
    ListViewForScrollView mLvShopMore;
    private LoadingDialog dialog;
    //现价，正跌幅和涨跌额，交易时间,今开，最高，卖出1，昨收，最低，买入1
    private TextView now, differRange_differ, lastUpdateTime, open, hight, sell1Money, preClose, low, buy1Money;


    /**
     * 页数角标，从1开始。
     */
    private int page = 1;
    /**
     * 每页显示数量
     */
    private int num = 5;
    // 0是下拉刷新的  1上拉加载
    private int xiala;
    private int type;
    String stockId, shock_names;//从上一页传递过来的stockId（股票id）和股票的名称//searchview搜索，自选，沪深，界面点进去的

    //适配器
    private CommonAdapter<homeNewsitem> mAdapter;
    private List<homeNewsitem> mList = new ArrayList<>();

    private PullToRefreshScrollView pullToRefreshScrollVie;//最外一层的加载让他仿照apple那种下拉刷新的效果
    private UserStocks userStocks = new UserStocks();


    //K线图
    CombinedChart combinedchart;
    private DataParse mData;
    private List<PlateStock_bean.ListBean> stockHistoryListBeans = new ArrayList<>();
    XAxis xAxisK;
    YAxis axisLeftK;
    YAxis axisRightK;


    float sum = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            combinedchart.setAutoScaleMinMaxEnabled(true);
            combinedchart.notifyDataSetChanged();
            combinedchart.invalidate();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platedetials__kline);
        Event();

        stockId = getIntent().getStringExtra("stockId");
        shock_names = getIntent().getStringExtra("shock_name");

        dialog = new LoadingDialog(Platedetials_KlineAct.this, "正在加载");
        dialog.show();
        //k线图
        if (!("").equals(String.valueOf(stockId))) {
            K_shocked();
            initChart();

        }
        if (!("").equals(shock_names)) {
            shock_name.setText(shock_names);
        }

        //新闻列表
        if (Login()) {
            newsList();
        }
        initEvent();
        initListview();


    }


    //监听事件
    void Event() {
        img_back = (ImageView) findViewById(R.id.img_back);
        shock_name = (TextView) findViewById(R.id.shock_name);
        share = (TextView) findViewById(R.id.share);
        option = (TextView) findViewById(R.id.option);
        mLvShopMore = (ListViewForScrollView) findViewById(R.id.mLvShopMore);
        pullToRefreshScrollVie = (PullToRefreshScrollView) findViewById(R.id.pullToRefreshScrollVie);
        combinedchart = (CombinedChart) findViewById(R.id.combinedchart);
        detials_kline = (TextView) findViewById(R.id.detials_kline);
        buy = (TextView) findViewById(R.id.buy);
        sell = (TextView) findViewById(R.id.sell);
        kill_order = (TextView) findViewById(R.id.kill_order);

        now = (TextView) findViewById(R.id.now);
        differRange_differ = (TextView) findViewById(R.id.differRange_differ);
        lastUpdateTime = (TextView) findViewById(R.id.lastUpdateTime);
        open = (TextView) findViewById(R.id.open);
        hight = (TextView) findViewById(R.id.hight);
        sell1Money = (TextView) findViewById(R.id.sell1Money);
        preClose = (TextView) findViewById(R.id.preClose);
        low = (TextView) findViewById(R.id.low);
        buy1Money = (TextView) findViewById(R.id.buy1Money);

        img_back.setOnClickListener(this);
        share.setOnClickListener(this);
        option.setOnClickListener(this);
        detials_kline.setOnClickListener(this);
        buy.setOnClickListener(this);
        sell.setOnClickListener(this);
        kill_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.buy:
                Intent intent1 = new Intent(Platedetials_KlineAct.this, MoniAllActivity.class);
                intent1.putExtra("type", 0);
                startActivity(intent1);
                break;
            case R.id.sell:

                Intent intent2 = new Intent(Platedetials_KlineAct.this, MoniAllActivity.class);
                intent2.putExtra("type", 1);
                startActivity(intent2);
                break;
            case R.id.kill_order:
                Intent intent4 = new Intent(Platedetials_KlineAct.this, MoniAllActivity.class);
                intent4.putExtra("type", 3);
                startActivity(intent4);
                break;

            case R.id.share:
                Toast.makeText(Platedetials_KlineAct.this, "你选择了分享", Toast.LENGTH_SHORT).show();

                break;
            case R.id.option:
                if (Login()) {
                    dialog = new LoadingDialog(Platedetials_KlineAct.this, "正在添加股票中");
                    dialog.show();
                    createStock();
                }
                break;
            case R.id.detials_kline:
                //传递股票id过去点击进入到股票K线图的页面
                Intent intent = new Intent(Platedetials_KlineAct.this, KLineActivity.class);
                intent.putExtra("stockId", stockId);
                intent.putExtra("type", "板块");//在板块的详情页面传递type过去 在下一个页面接受这个字段并且判断是否是从指数列表里点进来的
                startActivity(intent);

                break;

        }
    }


    private void initListview() {

/*        // 上拉、下拉设定
        mLvShopMore.setMode(PullToRefreshBase.Mode.BOTH);
        // 下拉刷新 业务代码
        mLvShopMore.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        mLvShopMore.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        mLvShopMore
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                        page = 1;
                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                        page++;
                        xiala = 1;
                        new DataTask().execute();
                    }
                });*/


        // 上拉、下拉设定
        pullToRefreshScrollVie.setMode(PullToRefreshBase.Mode.BOTH);
        // 下拉刷新 业务代码
        pullToRefreshScrollVie.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        pullToRefreshScrollVie.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");

        pullToRefreshScrollVie
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {

                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                        page = 1;
                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                        page++;
                        xiala = 1;
                        new DataTask().execute();
                    }
                });


    }

    private class DataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {

            try {
                Thread.sleep(2000);
                Log.d("Debug", "开始请求数据");
                if (Login()) {
                    newsList();

                }
            } catch (InterruptedException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {

            pullToRefreshScrollVie.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

    //新闻列表访问网络
    void newsList() {
        Runnable newsList = new Runnable() {
            @Override
            public void run() {
                NewsUtil util = new NewsUtil();
                util.newslist(dialog, type, page, num, ShareUtil.getInstance().getUserNumber(Platedetials_KlineAct.this), new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {

                        try {
                            JSONObject object = new JSONObject(result);
                            JSONArray newsList = object.getJSONArray("newsList");
                            mList = JSON.parseArray(newsList.toString(), homeNewsitem.class);
                            Log.i("新闻列表的点进去返回的值", mList.toString());

                            //0 下拉刷新 1下拉加载
                            if (xiala == 0) {
                                mAdapter.setmDatas(mList);
                                Log.d("Debug", "下拉刷新  只有首页的数据");
                            } else {
                                mAdapter.addmDatas(mList);
                                Log.d("Debug", "上拉加载  多页的数据");
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        new Thread(newsList).run();
    }


    //新闻列表适配器
    private void initEvent() {
        mAdapter = new CommonAdapter<homeNewsitem>(Platedetials_KlineAct.this, null, R.layout.bai_homenews_list_items) {
            @Override
            public void convert(ViewHolder helper, final homeNewsitem item) {
                TextView info;
                info = (TextView) helper.getView(R.id.info);
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) helper.getView(R.id.image);
                simpleDraweeView.setImageURI(item.getPic() + "");
                helper.setText(R.id.title, item.getTitle());
                helper.setText(R.id.time, item.getTime());
                info.setText(Html.fromHtml(item.getContent()));
                helper.setText(R.id.tv_comment_number, item.getCommentCount() + "条数据");
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Platedetials_KlineAct.this, NewsDetailActivity.class).putExtra(NewsDetailActivity.KEY_ID, item.getId()));

                    }
                });
            }
        };

        mLvShopMore.setAdapter(mAdapter);

    }


    //添加用户关注的股票
    void createStock() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                indexuti indexuti = new indexuti();

                indexuti.createStock(dialog, ShareUtil.getInstance().getUserNumber(Platedetials_KlineAct.this), userStocks, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Log.i("股票详情里的添加股票接口", result);

                        dialog.close();
                        Toast.makeText(Platedetials_KlineAct.this, "添加股票成功", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        };
        new Thread(runnable).start();
    }

    //板块的日k线图股票网络
    void K_shocked() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                indexuti indexuti = new indexuti();

                indexuti.domainStockgetByDay(dialog, stockId, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Log.i("板块的日k线图股票接口111111", result);

                        //解析
                        PlateStock_bean Bean = new Gson().fromJson(result, new TypeToken<PlateStock_bean>() {
                        }.getType());


                        stockHistoryListBeans = Bean.getList();
                        //把值传递到K线图里
                        setData(stockHistoryListBeans);
                        dialog.close();

                        Log.i("股票详情访问接口返回的值111122", Bean.getDetail().toString());
                        if (Bean.getDetail().getNow().length() > 0) {
                            now.setText(Bean.getDetail().getNow());
                        }
                        if (Bean.getDetail().getRiseAndFall().length() > 0 && Bean.getDetail().getRiseAndFallValue().length() > 0) {
                            differRange_differ.setText(Bean.getDetail().getRiseAndFall() + "  " + Bean.getDetail().getRiseAndFallValue());
                        }
                        if (Bean.getList().get(0).getUpdateTime().length() > 0) {
                            lastUpdateTime.setText(Bean.getList().get(0).getUpdateTime());
                        }
                        if (String.valueOf(Bean.getDetail().getOpen()).length() > 0) {
                            open.setText(String.valueOf(Bean.getDetail().getOpen()));
                        }
                        if (String.valueOf(Bean.getDetail().getLow()).length() > 0) {
                            low.setText(String.valueOf(Bean.getDetail().getLow()));
                        }
                        if (String.valueOf(Bean.getDetail().getHigh()).length() > 0) {

                            hight.setText(String.valueOf(Bean.getDetail().getHigh()));
                        }
                        if (String.valueOf(Bean.getDetail().getClose()).length() > 0) {
                            preClose.setText(String.valueOf(Bean.getDetail().getClose()));
                        }
                        if (Bean.getDetail().getTurnover().length() > 0) {
                            sell1Money.setText(Bean.getDetail().getTurnover());
                        }
                        if (Bean.getDetail().getVol().length() > 0) {
                            buy1Money.setText(Bean.getDetail().getVol());
                        }
                        userStocks.setSharesCode(Bean.getDetail().getDomainCode());
                        //绑定值

                    }
                });
            }
        };
        new Thread(runnable).start();

    }


    //判断是否有登录
    boolean Login() {
        if (ShareUtil.getInstance().getUserNumber(Platedetials_KlineAct.this).length() < 1 || ("").equals(ShareUtil.getInstance().getUserNumber(Platedetials_KlineAct.this))) {
            Toast.makeText(Platedetials_KlineAct.this, "你还没有登录", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    //初始表
    private void initChart() {
        /****************************************************************/
        combinedchart.setDrawBorders(true);
        combinedchart.setBorderWidth(1);
        combinedchart.setBorderColor(getResources().getColor(R.color._4087f5));
        combinedchart.setDescription("");
        combinedchart.setDragEnabled(true);
        combinedchart.setScaleYEnabled(false);

        Legend combinedchartLegend = combinedchart.getLegend();
        combinedchartLegend.setEnabled(false);
        //K线图 x y轴
        xAxisK = combinedchart.getXAxis();
        xAxisK.setDrawLabels(true);
        xAxisK.setDrawGridLines(false);
        xAxisK.setDrawAxisLine(false);
        xAxisK.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxisK.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisK.setGridColor(getResources().getColor(R.color._4087f5));
        axisLeftK = combinedchart.getAxisLeft();
        axisLeftK.setDrawGridLines(true);
        axisLeftK.setDrawAxisLine(false);
        axisLeftK.setDrawLabels(true);
        axisLeftK.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeftK.setGridColor(getResources().getColor(R.color._4087f5));
        axisLeftK.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisRightK = combinedchart.getAxisRight();
        axisRightK.setDrawLabels(false);
        //k线图里显示的x轴横线条
        axisRightK.setDrawGridLines(true);
        axisRightK.setDrawAxisLine(false);
        axisRightK.setGridColor(getResources().getColor(R.color._4087f5));
        combinedchart.setDragDecelerationEnabled(true);
        combinedchart.setDragDecelerationFrictionCoef(0.2f);
    }

    private float getSum(Integer a, Integer b) {

        for (int i = a; i <= b; i++) {
            sum += stockHistoryListBeans.get(i).getClose();
        }
        return sum;
    }

    private float culcMaxscale(float count) {
        float max = 1;
        max = count / 127 * 5;
        return max;
    }

    private void setData(List<PlateStock_bean.ListBean> mData) {

        int size = stockHistoryListBeans.size();

        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<CandleEntry> candleEntries = new ArrayList<>();
        ArrayList<Entry> line5Entries = new ArrayList<>();
        ArrayList<Entry> line10Entries = new ArrayList<>();
        ArrayList<Entry> line30Entries = new ArrayList<>();
        for (int i = 0, j = 0; i < mData.size(); i++, j++) {
            xVals.add(mData.get(i).getUpdateTime() + "");
            barEntries.add(new BarEntry(mData.get(i).getVol(), i));
            candleEntries.add(new CandleEntry(i, mData.get(i).getHigh(), mData.get(i).getLow(), mData.get(i).getOpen(), mData.get(i).getClose()));
            if (i >= 4) {
                sum = 0;
                line5Entries.add(new Entry(getSum(i - 4, i) / 5, i));
            }
            if (i >= 9) {
                sum = 0;
                line10Entries.add(new Entry(getSum(i - 9, i) / 10, i));
            }
            if (i >= 29) {
                sum = 0;
                line30Entries.add(new Entry(getSum(i - 29, i) / 30, i));
            }

        }


        CandleDataSet candleDataSet = new CandleDataSet(candleEntries, "KLine");
        candleDataSet.setDrawHorizontalHighlightIndicator(false);
        candleDataSet.setHighlightEnabled(true);
        candleDataSet.setHighLightColor(Color.WHITE);
        candleDataSet.setValueTextSize(10f);
        candleDataSet.setDrawValues(false);
        candleDataSet.setColor(Color.RED);
        candleDataSet.setShadowWidth(1f);
        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        CandleData candleData = new CandleData(xVals, candleDataSet);

        //计算均线值
        ArrayList<ILineDataSet> sets = new ArrayList<>();

        /******此处修复如果显示的点的个数达不到MA均线的位置所有的点都从0开始计算最小值的问题******************************/
        if (size >= 30) {
            sets.add(setMaLine(5, xVals, line5Entries));
            sets.add(setMaLine(10, xVals, line10Entries));
            sets.add(setMaLine(30, xVals, line30Entries));
        } else if (size >= 10 && size < 30) {
            sets.add(setMaLine(5, xVals, line5Entries));
            sets.add(setMaLine(10, xVals, line10Entries));
        } else if (size >= 5 && size < 10) {
            sets.add(setMaLine(5, xVals, line5Entries));
        }


        CombinedData combinedData = new CombinedData(xVals);
        LineData lineData = new LineData(xVals, sets);
        combinedData.setData(candleData);
        combinedData.setData(lineData);
        combinedchart.setData(combinedData);
        combinedchart.moveViewToX(mData.size() - 1);
        final ViewPortHandler viewPortHandlerCombin = combinedchart.getViewPortHandler();
        viewPortHandlerCombin.setMaximumScaleX(culcMaxscale(xVals.size()));
        Matrix matrixCombin = viewPortHandlerCombin.getMatrixTouch();
        final float xscaleCombin = 3;
        matrixCombin.postScale(xscaleCombin, 1f);

        combinedchart.moveViewToX(mData.size() - 1);

/****************************************************************************************
 此处解决方法来源于CombinedChartDemo，k线图y轴显示问题，图表滑动后才能对齐的bug，希望有人给出解决方法
 (注：此bug现已修复，感谢和chenguang79一起研究)
 ****************************************************************************************/

        handler.sendEmptyMessageDelayed(0, 300);

    }

    @NonNull
    private LineDataSet setMaLine(int ma, ArrayList<String> xVals, ArrayList<Entry> lineEntries) {
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + ma);
        if (ma == 5) {
            lineDataSetMa.setHighlightEnabled(true);
            lineDataSetMa.setDrawHorizontalHighlightIndicator(false);
            lineDataSetMa.setHighLightColor(Color.WHITE);
        } else {/*此处必须得写*/
            lineDataSetMa.setHighlightEnabled(false);
        }
        lineDataSetMa.setDrawValues(false);
        if (ma == 5) {
            lineDataSetMa.setColor(Color.GREEN);
        } else if (ma == 10) {
            lineDataSetMa.setColor(Color.GRAY);
        } else {
            lineDataSetMa.setColor(Color.YELLOW);
        }
        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);
        return lineDataSetMa;
    }


}
