package money.com.gettingmoney.bai.freagment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.MoniCheModel;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.simulated_trading.SimulatMethondes;
import money.com.gettingmoney.weiget.LoadingDialog;


/**
 * Created by Administrator on 2016/8/16.
 * 模拟股票资产的撤单
 */
public class MoniCheFragment extends BaseFragment {


    @InjectView(R.id.mLvShopMore2)
    ListView mLvShopMore;
    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.tv_qubie)
    TextView tvQubie;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;


    //适配器
    private CommonAdapter<MoniCheModel.ResultBean> mAdapter;
    private List<MoniCheModel> mList = new ArrayList<>();


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
    private int page = 0;
    /**
     * 每页显示数量
     */
    private int num = 10;
    private boolean isFirst = true;//是否是第一次请求，控制footer只创建一次。
    private Map<TextView, CountDownTimer> leftTimeMap = new HashMap<>();
    private int pos;
    private String findDealresult;
    int type;

    private LoadingDialog dialog1;

    public static MoniCheFragment getInstance() {
        MoniCheFragment fragment = new MoniCheFragment();
        return fragment;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //撤单列表
                    initEvent(findDealresult);
                    dialog1.close();
                    break;
                case 2:
                    //撤单成功
                    dialog1.close();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = requestView(inflater, R.layout.bai_moni_che);
        ButterKnife.inject(this, view);
        initWindow();
        // initEvent();
        initListview();

        if (Login()) {
            dialog1 = new LoadingDialog(getContext(), "正在加载中");
            dialog1.show();
            new Thread(runnable1).start();
        }
        return view;
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

    private void initEvent(String fDresult) {

        //解析
        final MoniCheModel Bean = new Gson().fromJson(fDresult, new TypeToken<MoniCheModel>() {
        }.getType());


        //列表
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);


        mAdapter = new CommonAdapter<MoniCheModel.ResultBean>(getActivity(), Bean.getResult(), R.layout.bai_moni_che_item) {
            @Override
            public void convert(ViewHolder helper, final MoniCheModel.ResultBean item) {

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


                //撤单

                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("撤掉股票交易的单子")
                                .setMessage("你真的要撤单么？")
                                .setIcon(R.mipmap.ic_launcher);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //撤单的访问网络og
                                //撤单的接口
                                dialog1 = new LoadingDialog(getContext(), "正在加载中");
                                dialog1.show();
                                Chedan(String.valueOf(item.getDealId()));
                                mAdapter.removeData(item);

                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //点击取消掉

                            }
                        });
                        builder.create().show();

                    }
                });

            }
        };

        mLvShopMore.setAdapter(mAdapter);

    }

    @Override
    public void requestInit() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    //查询股票交易的接口
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            SimulatMethondes util = new SimulatMethondes();
            util.findDeal(dialog1, ShareUtil.getInstance().getUserNumber(getContext()), null, null, 10, 1, 0, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {

                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    findDealresult = result;

                }
            });
        }
    };

    void Chedan(final String dealId) {

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                SimulatMethondes util = new SimulatMethondes();
                util.cancelDeal(dialog1, ShareUtil.getInstance().getUserNumber(getContext()), dealId, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {

                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);

                    }
                });
            }
        };
        new Thread(runnable2).start();
    }

    //判断是否有登录
    boolean Login() {
        if (ShareUtil.getInstance().getUserNumber(getContext()).length() < 1 || ("").equals(ShareUtil.getInstance().getUserNumber(getContext()))) {
            Toast.makeText(getContext(), "你还没有登录", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
