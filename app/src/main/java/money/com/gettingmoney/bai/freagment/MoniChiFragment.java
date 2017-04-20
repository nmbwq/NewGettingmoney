package money.com.gettingmoney.bai.freagment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.Butils.BDialog;
import money.com.gettingmoney.bai.activity.MoniDetailActivity;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.MoniCheModel;
import money.com.gettingmoney.bean.FindUserBean;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.simulated_trading.SimulatMethondes;
import money.com.gettingmoney.weiget.LoadingDialog;


/**
 * Created by Administrator on 2016/8/16.
 * 模拟股票交易里的持仓
 */
public class MoniChiFragment extends BaseFragment /*implements OnActionListener*/ {


    int type;
    @InjectView(R.id.mLvShopMore)
    ListView mLvShopMore;
    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;
    @InjectView(R.id.totleMoney)
    TextView totleMoney;
    @InjectView(R.id.supMoney)
    TextView supMoney;


    //适配器
    private CommonAdapter<MoniCheModel.ResultBean> mAdapter;
    private List<MoniCheModel.ResultBean> mList = new ArrayList<>();

    private boolean isHasData = false;//是否有数据
    private boolean isLoading;//是否刷新中

    private LinearLayout mLlFooter;
    private TextView mTxtFooter;
    private AlertDialog alertDialog;
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
    private Map<TextView, CountDownTimer> leftTimeMap = new HashMap<>();
    private int pos;

    public static MoniChiFragment getInstance() {
        MoniChiFragment fragment = new MoniChiFragment();
        return fragment;
    }

    private LoadingDialog dialog;
    private String findDealresult;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //持仓列表
                    initEvent(findDealresult);
                    dialog.close();
                    break;
            }


        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = requestView(inflater, R.layout.bai_moni_chi);
        ButterKnife.inject(this, view);
        dialog = new LoadingDialog(getContext(), "正在加载中");
        dialog.show();
        if (Login()) {
            findUserWallet(ShareUtil.getInstance().getUserNumber(getContext()));
            new Thread(runnable1).start();
        }
        initWindow();

        initListview();
        return view;
    }

    private void initListview() {
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

     /*   progressLayout.setFocusable(true);
        progressLayout.setFocusableInTouchMode(true);
        progressLayout.requestFocus();*/

        //解析
        final MoniCheModel Bean = new Gson().fromJson(fDresult, new TypeToken<MoniCheModel>() {
        }.getType());

        //列表
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);


        mAdapter = new CommonAdapter<MoniCheModel.ResultBean>(getActivity(), Bean.getResult(), R.layout.bai_moni_stock_home_items) {
            @Override
            public void convert(ViewHolder helper, final MoniCheModel.ResultBean item) {
                final LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.ll_ishiden);
                final ImageView imageView = (ImageView) helper.getView(R.id.im_image);
                final TextView textView = (TextView) helper.getView(R.id.tv_xianshi);

                // 形状不变  改变背景颜色
                final GradientDrawable p = (GradientDrawable) textView.getBackground();

                TextView stockName_stockid, riseAndFallValue_riseAndFall, now_riseAndFall,
                        amount, countVol, countVol_now, pingcang, detials;

                stockName_stockid = (TextView) helper.getView(R.id.stockName_stockid);
                riseAndFallValue_riseAndFall = (TextView) helper.getView(R.id.riseAndFallValue_riseAndFall);
                now_riseAndFall = (TextView) helper.getView(R.id.now_riseAndFall);
                amount = (TextView) helper.getView(R.id.amount);
                countVol = (TextView) helper.getView(R.id.countVol);
                countVol_now = (TextView) helper.getView(R.id.countVol_now);
                pingcang = (TextView) helper.getView(R.id.pingcang);
                detials = (TextView) helper.getView(R.id.detials);


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

                Double cVol_now = Double.valueOf(item.getNow()) * Double.valueOf(item.getCountVol());
                countVol_now.setText(cVol_now.toString());


                if (item.isFlag()) {
                    linearLayout.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                    p.setColor(getResources().getColor(R.color._069043));
                } else {
                    linearLayout.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    p.setColor(getResources().getColor(R.color.text_red));
                }
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.isFlag()) {
                            item.setFlag(false);
                            linearLayout.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
                        } else {
                            item.setFlag(true);
                            linearLayout.setVisibility(View.VISIBLE);
                            imageView.setVisibility(View.GONE);
                        }
                    }
                });

                pingcang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog = BDialog.showDialog(getContext(), R.layout.bai_dialog_pingcang, "", "", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditText editText = (EditText) alertDialog.findViewById(R.id.tv_number);
                                Log.d("Debug", "dialog上面填写的数量为" + editText.getText().toString());
                                alertDialog.dismiss();
                            }
                        }, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                    }
                });
                //详情
                detials.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), MoniDetailActivity.class));
                    }
                });


            }
        };
        mLvShopMore.setAdapter(mAdapter);

    }


    //查询股票交易的接口
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            SimulatMethondes util = new SimulatMethondes();
            util.findDeal(dialog, ShareUtil.getInstance().getUserNumber(getContext()), null, null, 10, 1, 1, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {

                    Log.i("持仓的股票的值", result.toString());
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    findDealresult = result;
                }
            });
        }
    };

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
                        if (String.valueOf(Bean.getResult().getTotleMoney()).length() >1 ) {
                            totleMoney.setText(String.valueOf(Bean.getResult().getTotleMoney()));
                        }
                        if (String.valueOf(Bean.getResult().getSupMoney()).length() >1 ) {
                            supMoney.setText(String.valueOf(Bean.getResult().getSupMoney()));
                        }




                    }
                });
            }
        };
        new Thread(runnable3).start();
    }

    //判断是否有登录
    boolean Login() {
        if (ShareUtil.getInstance().getUserNumber(getContext()).length() < 1 || ("").equals(ShareUtil.getInstance().getUserNumber(getContext()))) {
            Toast.makeText(getContext(), "你还没有登录", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void requestInit() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
