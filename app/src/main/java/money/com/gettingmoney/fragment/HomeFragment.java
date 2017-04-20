package money.com.gettingmoney.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.mikephil.charting.chartss.LineChart;
import com.github.mikephil.charting.datas.LineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.HotSearchview;
import money.com.gettingmoney.bai.activity.BusinessNewsActivity;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.utils.ToastUtils;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.homeNews;
import money.com.gettingmoney.bai.model.zuJianModel;
import money.com.gettingmoney.bai.view.ListViewForScrollView;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.news.NewsUtil;
import money.com.gettingmoney.weiget.LoadingDialog;

public class HomeFragment extends BaseFragment /*implements OnChartGestureListener, OnChartValueSelectedListener*/ {

    @InjectView(R.id.tv_zujian)
    TextView tvZujian;
    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.mZuJianList)
    GridView mZuJianList;
    @InjectView(R.id.mLvShopMore1)
    ListViewForScrollView mLvShopMore;
    @InjectView(R.id.mProgress)
    ProgressLayout progressLayout;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;
    @InjectView(R.id.mLineChar)
    LineChart mLineChar;


    //适配器
    private CommonAdapter<homeNews.NewsListBean> mAdapter;

    private CommonAdapter<zuJianModel> ZmAdapter;
    private List<zuJianModel> ZmList = new ArrayList<>();


    private LineDataSet set1;

    public HomeFragment() {
    }

    private LoadingDialog dialog;


    /**
     * 有财路主页面
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    String utilresutl = "";

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //添加到适配器里绑定值
                    initEvent();
                    // dialog.close();
                    break;
            }

        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fresco.initialize(getActivity());
        toolBar = new MyToolBar(getActivity(), "", "有财路", R.mipmap.bai_sousuo);
        toolBar.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HotSearchview.class));
            }
        });


        View view = requestView(inflater, R.layout.fragment_home);
        ButterKnife.inject(this, view);
        dialog = new LoadingDialog(getContext(), "正在加载中");
        dialog.show();
        zhujian();
        initListview();
        // initChart();
        init();
        return view;
    }

/*    private void initChart() {

//设置手势滑动事件
        mLineChar.setOnChartGestureListener(this);
        //设置数值选择监听
        mLineChar.setOnChartValueSelectedListener(this);
        //后台绘制
        mLineChar.setDrawGridBackground(false);
        //设置描述文本
        mLineChar.getDescription().setEnabled(false);
        //设置支持触控手势
        mLineChar.setTouchEnabled(true);
        //设置缩放
        mLineChar.setDragEnabled(true);
        //设置推动
        mLineChar.setScaleEnabled(true);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChar.setPinchZoom(true);


//        //x轴
//        LimitLine llXAxis = new LimitLine(10f, "标记");
//        //设置线宽
//        llXAxis.setLineWidth(0f);
//        //
//        llXAxis.enableDashedLine(2f, 2f, 0f);
//        //设置
//        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//        llXAxis.setTextSize(2f);

        XAxis xAxis = mLineChar.getXAxis();
        xAxis.enableGridDashedLine(5f, 5f, 0f);
        //设置x轴的最大值
        xAxis.setAxisMaximum(1000f);
        //设置x轴的最小值
        xAxis.setAxisMinimum(0f);

        LimitLine ll1 = new LimitLine(150f, "优秀");
        ll1.setLineWidth(0.5f);
        ll1.enableDashedLine(0f, 0f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(2f);

        LimitLine ll2 = new LimitLine(30f, "不及格");
        ll2.setLineWidth(2f);
        ll2.enableDashedLine(2f, 2f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(2f);

        YAxis leftAxis = mLineChar.getAxisLeft();
        //重置所有限制线,以避免重叠线
        leftAxis.removeAllLimitLines();
        //设置优秀线
        leftAxis.addLimitLine(ll1);
        //设置及格线
//        leftAxis.addLimitLine(ll2);
        //y轴最大
        leftAxis.setAxisMaximum(200f);
        //y轴最小
        leftAxis.setAxisMinimum(0f);
        //设置界面没有条数控制（没有背景的条条颜色）
        leftAxis.enableGridDashedLine(0f, 10000f, 0f);
        leftAxis.setDrawZeroLine(false);

        // 限制数据(而不是背后的线条勾勒出了上面)
        leftAxis.setDrawLimitLinesBehindData(true);

        mLineChar.getAxisRight().setEnabled(false);

        //这里我模拟一些数据
        ArrayList<Entry> values = new ArrayList<Entry>();
        values.add(new Entry(0, 50));
        values.add(new Entry(10, 66));
        values.add(new Entry(15, 120));
        values.add(new Entry(20, 30));
        values.add(new Entry(35, 10));
        values.add(new Entry(40, 110));
        values.add(new Entry(45, 30));
        values.add(new Entry(50, 160));
        values.add(new Entry(59, 190));
        values.add(new Entry(55, 140));
        values.add(new Entry(58, 158));
        values.add(new Entry(70, 170));
        values.add(new Entry(75, 165));
        values.add(new Entry(100, 30));

        //设置数据
        setData(values);

        //默认动画
        mLineChar.animateXY(2500, 2500);
        //刷新
        //mChart.invalidate();

        // 得到这个文字
        Legend l = mLineChar.getLegend();

        // 修改文字 ...
        l.setForm(Legend.LegendForm.LINE);
    }*/

/*    //传递数据集
    private void setData(ArrayList<Entry> values) {
        if (mLineChar.getData() != null && mLineChar.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChar.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mLineChar.getData().notifyDataChanged();
            mLineChar.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = new LineDataSet(values, "走势图");

            // 在这里设置线
            set1.enableDashedLine(1f, 0f, 0f);
            set1.enableDashedHighlightLine(5f, 0f, 0f);
            set1.setColor(Color.RED);
            set1.setCircleColor(Color.BLUE);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // 填充背景只支持18以上
                //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
                //set1.setFillDrawable(drawable);
                set1.setFillColor(getResources().getColor(R.color.text_red));
            } else {
                set1.setFillColor(Color.BLACK);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            //添加数据集
            dataSets.add(set1);

            //创建一个数据集的数据对象
            LineData data = new LineData(dataSets);

            //谁知数据
            mLineChar.setData(data);
            mLineChar.getData().setHighlightEnabled(!mLineChar.getData().isHighlightEnabled());

            List<ILineDataSet> setsCubic = mLineChar.getData().getDataSets();
            for (ILineDataSet iSet : setsCubic) {
                LineDataSet set = (LineDataSet) iSet;
                //立体曲线
                set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                        ? LineDataSet.Mode.LINEAR
                        : LineDataSet.Mode.CUBIC_BEZIER);
                //消除圆点
                if (set.isDrawCirclesEnabled())
                    set.setDrawCircles(false);
                else
                    set.setDrawCircles(true);

                //切换显示/隐藏
                set.setDrawValues(!set.isDrawValuesEnabled());
            }
            mLineChar.invalidate();

        }
    }*/

    private void initListview() {
        progressLayout.setFocusable(true);
        progressLayout.setFocusableInTouchMode(true);
        progressLayout.requestFocus();
        // 上拉、下拉设定
        pullToRefreshScrollVie.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        // 下拉刷新 业务代码
        pullToRefreshScrollVie.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        pullToRefreshScrollVie.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        pullToRefreshScrollVie
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {

                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                        page = 1;
//                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                        page++;
//                        xiala = 1;
                        new DataTask().execute();
                    }
                });

    }

/*    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        // 完成之后停止晃动
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mLineChar.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }*/

    private class DataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
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


    private void initEvent() {

        if (utilresutl.length() > 1) {

            homeNews homeNews = new Gson().fromJson(utilresutl, new TypeToken<homeNews>() {
            }.getType());
            mAdapter = new CommonAdapter<money.com.gettingmoney.bai.model.homeNews.NewsListBean>(getActivity(),
                    homeNews.getNewsList(), R.layout.bai_homenews_items) {
                @Override
                public void convert(final ViewHolder helper, final homeNews.NewsListBean item) {
                    ImageView pic;
                    TextView title, time, content;
                    pic = (ImageView) helper.getView(R.id.pic);
                    title = (TextView) helper.getView(R.id.title1);
                    time = (TextView) helper.getView(R.id.time);
                    content = (TextView) helper.getView(R.id.content);
                    if (item.getPic() != null) {
                        // pic.setImageURI(item.getPic());
                    }
                    if (item.getContent() != null) {
                        title.setText(item.getTitle());
                    }
                    if (item.getTime() != null) {
                        time.setText(item.getTime());
                    }
                    if (item.getContent() != null) {
                        content.setText(Html.fromHtml(item.getContent()));
                    }

                    helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), BusinessNewsActivity.class);
                            intent.putExtra("type", item.getType());
                            startActivity(intent);
                        }
                    });


                }
            };
            mLvShopMore.setAdapter(mAdapter);
            dialog.close();
        } else {
            Toast.makeText(getContext(), "首页返回的新闻四大列表为空！", Toast.LENGTH_SHORT).show();
        }
    }


    private void zhujian() {
        requestdate2();
        ZmList.clear();
        zuJianModel zuJianModel = new zuJianModel("添加组件", " http://b.hiphotos.baidu.com/image/pic/item/d009b3de9c82d15825ffd75c840a19d8bd3e42da.jpg");
        ZmList.add(zuJianModel);

        //添加组件的gildview适配器
        ZmAdapter = new CommonAdapter<zuJianModel>(getActivity(), ZmList, R.layout.bai_zujian_items) {
            @Override
            public void convert(ViewHolder baseViewHolder, zuJianModel item) {
                SimpleDraweeView view = (SimpleDraweeView) baseViewHolder.getView(R.id.im_zujian_photo);
                view.setImageURI(item.getPhotoUrl());
                baseViewHolder.setText(R.id.im_zujian_name, item.getName());

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (ZmList.size() < 4) {
//                            Log.i("点击+号图片可以添加组件", "size已经小于4了");
//                        } else {
//                            Log.i("点击+号图片可以添加组件", "size已经大于4了");
//
//                        }
                        ToastUtils.MyToast(getActivity(),"此功能暂未开放...");
                    }
                });
            }
        };
        mZuJianList.setAdapter(ZmAdapter);
    }


    private void requestdate2() {
//        for (int i = 0; i < 4; i++) {
//            zuJianModel zuJianModel = new zuJianModel("添加组件", "http://b.hiphotos.baidu.com/image/pic/item/d009b3de9c82d15825ffd75c840a19d8bd3e42da.jpg");
//            ZmList.add(zuJianModel);
//        }
    }

    //首页新闻访问4大列表
    private void init() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                NewsUtil util = new NewsUtil();
                util.newslist(null, 0, 1, 10, null, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Log.i("首页返回值", result);
                        //  dialog = new LoadingDialog(getContext(), "正在加载");
                        utilresutl = result;
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                });

            }
        };

        new Thread(runnable).start();


    }

    @Override
    public void requestInit() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.tv_zujian)
    public void onClick() {
        ToastUtils.MyToast(getActivity(), "点击组件的操作");
    }
}
