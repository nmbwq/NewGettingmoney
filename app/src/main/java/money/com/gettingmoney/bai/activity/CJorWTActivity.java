package money.com.gettingmoney.bai.activity;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseActivity;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.MoniCheModel;
import money.com.gettingmoney.bai.view.ListViewForScrollView;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.simulated_trading.SimulatMethondes;
import money.com.gettingmoney.weiget.LoadingDialog;


public class CJorWTActivity extends BaseActivity /*implements OnActionListener */ {
    public static final String DISTINGUISH = "DISTINGUISH";
    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.tv_qubie)
    TextView tvQubie;
    @InjectView(R.id.mLvShopMore2)
    ListViewForScrollView mLvShopMore;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;


    //
//适配器
    private CommonAdapter<MoniCheModel.ResultBean> mAdapter;
    private boolean isHasData = false;//是否有数据
    private boolean isLoading;//是否刷新中
    private LinearLayout mLlFooter;
    private TextView mTxtFooter;
    private LoadingDialog dialog;
    /**
     * 加载中的脚
     */
    private View footer;

    /**
     * 页数角标，从0开始。
     */
    private int page = 0;
    /**
     * 每页显示数量
     */
    private int num = 10;
    private boolean isFirst = true;//是否是第一次请求，控制footer只创建一次。
    int type;

    String findDealresult;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //模拟交易当日成交
                    initEvent(findDealresult);

                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra(DISTINGUISH, 1);
        if (type == 1) {
            toolBar = new MyToolBar(this, R.mipmap.bai_back, "模拟交易当日成交", "");
        } else if (type == 2) {
            toolBar = new MyToolBar(this, R.mipmap.bai_back, "模拟交易当日委托", "");
        } else if (type == 3) {
            toolBar = new MyToolBar(this, R.mipmap.bai_back, "模拟交易历史成交", "");
        } else if (type == 4) {
            toolBar = new MyToolBar(this, R.mipmap.bai_back, "模拟交易历史委托", "");
        }
        setContentView(requestView(R.layout.bai_moni_che));
        ButterKnife.inject(this);
        dialog = new LoadingDialog(CJorWTActivity.this, "正在加载中");
        dialog.show();
        if (Login()) {
            new Thread(runnable1).start();
        }
        initListview();
    }

    //刷新
    private void initListview() {
        progressLayout.setFocusable(true);
        progressLayout.setFocusableInTouchMode(true);
        progressLayout.requestFocus();
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
//                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                        page++;
//                        xiala = 1;
                        new DataTask().execute();
                    }
                });

    }

    private class DataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
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

    private void initEvent(String fDresult) {

        if (type == 1) {
            tvQubie.setText("方向/成交额");
        } else {
            tvQubie.setText("交易状态");
        }

        //列表
        footer = LayoutInflater.from(this).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);
        //解析
        final MoniCheModel Bean = new Gson().fromJson(fDresult, new TypeToken<MoniCheModel>() {
        }.getType());

        mAdapter = new CommonAdapter<MoniCheModel.ResultBean>(CJorWTActivity.this, Bean.getResult(), R.layout.bai_moni_che_item) {
            @Override
            public void convert(ViewHolder helper, MoniCheModel.ResultBean item) {
                TextView stock_name, stockId, amount, now, countVol, type, createTime;
                stock_name = (TextView) helper.getView(R.id.stock_name);
                stockId = (TextView) helper.getView(R.id.stockId);
                amount = (TextView) helper.getView(R.id.amount);
                now = (TextView) helper.getView(R.id.now);
                countVol = (TextView) helper.getView(R.id.countVol);
                type = (TextView) helper.getView(R.id.type);
                createTime = (TextView) helper.getView(R.id.createTime);

                if (item.getStockName() != null) {
                    stock_name.setText(item.getStockName());
                }
                if (item.getStockId() != null) {
                    stockId.setText(item.getStockId());
                }
                if (item.getAmount() != null) {
                    amount.setText(item.getAmount());
                }
                if (item.getNow() != null) {
                    now.setText(item.getNow());
                }
                if (item.getCountVol() != null) {
                    countVol.setText(item.getCountVol());
                }
                if (item.getCreateTime() != null) {
                    createTime.setText(item.getCreateTime());
                }
                if (item.getType() == 0) {
                    type.setText("买入");
                } else if (item.getType() == 1) {
                    type.setText("卖出");
                }


            }
        };


        mLvShopMore.setAdapter(mAdapter);

        dialog.close();
    }


    /**
     * 加载中和加载结束界面切换
     *
     * @param isLoading 是否显示加载中的布局
     */
    private void setOnLoading(boolean isLoading) {
        if (isLoading) {
            mLlFooter.setVisibility(View.VISIBLE);
            mTxtFooter.setVisibility(View.GONE);
        } else {
            mLlFooter.setVisibility(View.GONE);
            mTxtFooter.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void requestInit() {
        requestData();
    }

    /**
     * 加载出错
     */
    private void loadError() {
        progressLayout.showErrorText("加载出错，点击重试");
        progressLayout.setOnerrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressLayout.showProgress();
                page = 0;
                requestData();
            }
        });
    }


    //模拟交易当日成交的接口
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            SimulatMethondes util = new SimulatMethondes();
            util.findDeal(dialog, ShareUtil.getInstance().getUserNumber(CJorWTActivity.this), null, null, 10, 1, 0, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {

                    Log.i("模拟交易当日成交的接口", result);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    findDealresult = result;

                }
            });
        }
    };

    //判断是否有登录
    boolean Login() {
        if (ShareUtil.getInstance().getUserNumber(CJorWTActivity.this).length() < 1 || ("").equals(ShareUtil.getInstance().getUserNumber(CJorWTActivity.this))) {
            Toast.makeText(CJorWTActivity.this, "你还没有登录", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
