package money.com.gettingmoney.bai.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import money.com.gettingmoney.bai.Butils.BDialog;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseActivity;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.MoniCheModel;
import money.com.gettingmoney.bai.model.MoniStockHomeModel;
import money.com.gettingmoney.bai.view.ListViewForScrollView;
import money.com.gettingmoney.bean.FindUserBean;
import money.com.gettingmoney.bean.OrderBeans;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.simulated_trading.SimulatMethondes;
import money.com.gettingmoney.weiget.LoadingDialog;

/*
* 模拟股票资产
* */
public class MoniStockHomeActivity extends BaseActivity /*implements OnActionListener */ {

    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.ll_zhang)
    LinearLayout llZhang;
    @InjectView(R.id.ll_die)
    LinearLayout llDie;
    @InjectView(R.id.ll_chi)
    LinearLayout llChi;
    @InjectView(R.id.ll_che)
    LinearLayout llChe;
    @InjectView(R.id.ll_select)
    LinearLayout llSelect;
    @InjectView(R.id.mLvShopMore)
    ListViewForScrollView mLvShopMore;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;
    @InjectView(R.id.totleMoney)
    TextView totleMoney;
    @InjectView(R.id.supMoney)
    TextView supMoney;


    //
//适配器
    private CommonAdapter<MoniCheModel.ResultBean> mAdapter;
    private List<MoniStockHomeModel> mList = new ArrayList<>();

    private boolean isHasData = false;//是否有数据
    private boolean isLoading;//是否刷新中

    private LinearLayout mLlFooter;
    private TextView mTxtFooter;

    /**
     * 加载中的脚
     */
    private View footer;

    /**
     * 页数角标，从0开始。
     */
    // 0是下拉刷新的  1上拉加载
    private int xiala;
    private int page = 0;
    /**
     * 每页显示数量
     */
    private int num = 10;
    private boolean isFirst = true;//是否是第一次请求，控制footer只创建一次。
    private AlertDialog alertDialog;

    private String findDealresult = "";
    private OrderBeans beans = new OrderBeans(); //添加股票交易的上传的参数
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //持仓列表
                    initEvent();
                    break;

                case 2:
                    dialog1.close();
                    alertDialog.dismiss();
                    Toast.makeText(MoniStockHomeActivity.this, "添加股票成功", Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    };
    private LoadingDialog dialog, dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolBar = new MyToolBar(this, R.mipmap.bai_back, "模拟股票资产", "");
        setContentView(requestView(R.layout.bai_moni_stock_home));
        ButterKnife.inject(this);
        dialog = new LoadingDialog(MoniStockHomeActivity.this, "正在加载中");
        dialog.show();
        if (Login()) {
            findDeal();
            findUserWallet(ShareUtil.getInstance().getUserNumber(MoniStockHomeActivity.this));
        }
        initListview();
    }


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
            // Simulates a background job.
            try {
                Thread.sleep(2000);
                //查询股票交易记录
                findDeal();

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

        //解析
        final MoniCheModel Bean = new Gson().fromJson(findDealresult, new TypeToken<MoniCheModel>() {
        }.getType());

        //列表
        footer = LayoutInflater.from(this).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);

        mAdapter = new CommonAdapter<MoniCheModel.ResultBean>(MoniStockHomeActivity.this, Bean.getResult(), R.layout.bai_moni_stock_home_items) {
            @Override
            public void convert(ViewHolder baseViewHolder, final MoniCheModel.ResultBean item) {
                final LinearLayout linearLayout = (LinearLayout) baseViewHolder.getView(R.id.ll_ishiden);
                final ImageView imageView = (ImageView) baseViewHolder.getView(R.id.im_image);
                final TextView textView = (TextView) baseViewHolder.getView(R.id.tv_xianshi);
                TextView detials = (TextView) baseViewHolder.getView(R.id.detials);
                TextView pingcang = (TextView) baseViewHolder.getView(R.id.pingcang);
                TextView stockName_stockid, riseAndFallValue_riseAndFall, now_riseAndFall, amount, countVol, countVol_now;
                stockName_stockid = (TextView) baseViewHolder.getView(R.id.stockName_stockid);
                riseAndFallValue_riseAndFall = (TextView) baseViewHolder.getView(R.id.riseAndFallValue_riseAndFall);
                now_riseAndFall = (TextView) baseViewHolder.getView(R.id.now_riseAndFall);
                amount = (TextView) baseViewHolder.getView(R.id.amount);
                countVol = (TextView) baseViewHolder.getView(R.id.countVol);
                countVol_now = (TextView) baseViewHolder.getView(R.id.countVol_now);

                if (item.getStockName() != null && item.getStockId() != null) {
                    stockName_stockid.setText(item.getStockName() + "  " + item.getStockId());
                }
                if (item.getRiseAndFallValue() != null && item.getRiseAndFall() != null) {
                    riseAndFallValue_riseAndFall.setText(item.getRiseAndFallValue() + "  " + item.getRiseAndFall());
                }
                if (item.getNow() != null && item.getRiseAndFall() != null) {
                    now_riseAndFall.setText(item.getNow() + "  " + item.getRiseAndFall());
                }
                if (item.getAmount() != null) {
                    amount.setText(item.getAmount());
                }
                if (item.getCountVol() != null) {
                    countVol.setText(item.getCountVol());
                }

                // 形状不变  改变背景颜色
                final GradientDrawable p = (GradientDrawable) textView.getBackground();

                //详情
                detials.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MoniStockHomeActivity.this, MoniDetailActivity.class));

                    }
                });
                //平仓
                pingcang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog = BDialog.showDialog(MoniStockHomeActivity.this, R.layout.bai_dialog_pingcang, "", "", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditText editTextnumber = (EditText) alertDialog.findViewById(R.id.tv_number);

                                int i = (int) Float.parseFloat(item.getAmount().toString());
                                beans.setStockId(item.getStockId());
                                beans.setAmount(i);
                                beans.setType(0);
                                if (editTextnumber.getText().length() > 0) {
                                    beans.setCountVol(Integer.parseInt(editTextnumber.getText().toString()));

                                }

                                if (Login()) {

                                    if (editTextnumber.getText().length() > 0) {

                                        if (Integer.parseInt(editTextnumber.getText().toString()) % 100 == 0) {

                                            dialog1 = new LoadingDialog(MoniStockHomeActivity.this, "正在加载中");
                                            dialog1.show();
                                            addDeal(ShareUtil.getInstance().getUserNumber(MoniStockHomeActivity.this));
                                        } else {
                                            Toast.makeText(MoniStockHomeActivity.this, "只能整倍的买，例如100,200,300...等等", Toast.LENGTH_SHORT).show();

                                        }

                                    } else {
                                        Toast.makeText(MoniStockHomeActivity.this, "购买股票数量不能为空", Toast.LENGTH_SHORT).show();
                                    }


                                }

                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });

                        TextView stockName, stockId, riseAndFallValue_riseAndFall;

                        stockName = (TextView) alertDialog.findViewById(R.id.stockName);

                        stockId = (TextView) alertDialog.findViewById(R.id.stockId);

                        riseAndFallValue_riseAndFall = (TextView) alertDialog.findViewById(R.id.riseAndFallValue_riseAndFall);

                        stockName.setText(item.getStockName());
                        stockId.setText(item.getStockId());
                        riseAndFallValue_riseAndFall.setText(item.getRiseAndFallValue() + "(" + item.getRiseAndFall() + ")");
                    }

                });


                if (item.isFlag()) {
                    linearLayout.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                    p.setColor(getResources().getColor(R.color._069043));
                } else {
                    linearLayout.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    p.setColor(getResources().getColor(R.color.text_red));
                }
                baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.isFlag()) {
                            item.setFlag(false);
                            linearLayout.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
//                            p.setColor(getResources().getColor(R.color.text_red));
                        } else {
                            item.setFlag(true);
                            linearLayout.setVisibility(View.VISIBLE);
                            imageView.setVisibility(View.GONE);
//                            p.setColor(getResources().getColor(R.color._069043));
                        }
                    }
                });

            }


        };
        mLvShopMore.setAdapter(mAdapter);
        dialog.close();


    }


    //添加股票交易记录
    void addDeal(final String userNumber) {
        Runnable runnable4 = new Runnable() {
            @Override
            public void run() {
                SimulatMethondes util = new SimulatMethondes();
                //参数：第一个dilaog，第二个token，第三个股票id，第四个成交金额，第五个交易数量，必须是100的整数，第6个；买入是0，卖出是1；第七个回调方法
                util.addDeal(dialog, userNumber, beans, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {

                        Log.i("买入持仓添加股票返回值", result);
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);


                    }
                });
            }
        };
        new Thread(runnable4).start();
    }


    //查询用户资产的访问访问网络
    void findUserWallet(final String userNumber) {
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                SimulatMethondes util = new SimulatMethondes();
                util.findUserWallet(userNumber, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {

                        FindUserBean Bean = new Gson().fromJson(result, new TypeToken<FindUserBean>() {
                        }.getType());
                        if (String.valueOf(Bean.getResult().getTotleMoney()).length() > 1) {
                            totleMoney.setText(String.valueOf(Bean.getResult().getTotleMoney()));
                        }
                        if (String.valueOf(Bean.getResult().getSupMoney()).length() > 1) {
                            supMoney.setText(String.valueOf(Bean.getResult().getSupMoney()));
                        }


                    }
                });
            }
        };
        new Thread(runnable3).start();
    }
    //查询股票交易的接口

    void findDeal() {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                SimulatMethondes util = new SimulatMethondes();
                util.findDeal(dialog, ShareUtil.getInstance().getUserNumber(MoniStockHomeActivity.this), null, null, 10, 1, 1, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {

                        Log.i("买入的股票的值", result.toString());
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        findDealresult = result;
                    }
                });
            }
        };
        new Thread(runnable1).start();
    }

    //判断是否有登录
    boolean Login() {
        if (ShareUtil.getInstance().getUserNumber(MoniStockHomeActivity.this).length() < 1 || ("").equals(ShareUtil.getInstance().getUserNumber(MoniStockHomeActivity.this))) {
            Toast.makeText(MoniStockHomeActivity.this, "你还没有登录", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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


    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }


    @OnClick({R.id.ll_zhang, R.id.ll_die, R.id.ll_chi, R.id.ll_che, R.id.ll_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_zhang:

                Intent intent1 = new Intent(MoniStockHomeActivity.this, MoniAllActivity.class);
                intent1.putExtra("type", 0);
                startActivity(intent1);
                break;
            case R.id.ll_die:

                Intent intent2 = new Intent(MoniStockHomeActivity.this, MoniAllActivity.class);
                intent2.putExtra("type", 1);
                startActivity(intent2);
                break;
            case R.id.ll_chi:

                Intent intent3 = new Intent(MoniStockHomeActivity.this, MoniAllActivity.class);
                intent3.putExtra("type", 2);
                startActivity(intent3);


                break;
            case R.id.ll_che:
                Intent intent4 = new Intent(MoniStockHomeActivity.this, MoniAllActivity.class);
                intent4.putExtra("type", 3);
                startActivity(intent4);
                break;
            case R.id.ll_select:
                Intent intent5 = new Intent(MoniStockHomeActivity.this, MoniAllActivity.class);
                intent5.putExtra("type", 4);
                startActivity(intent5);
                break;
        }
    }


}
