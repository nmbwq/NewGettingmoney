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
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
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
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.LoginActivity;
import money.com.gettingmoney.adapter.SimtradiListBuyAdp;
import money.com.gettingmoney.adapter.SimtradiListSellAdp;
import money.com.gettingmoney.adapter.SimtradingAdpter;
import money.com.gettingmoney.bai.Butils.BDialog;
import money.com.gettingmoney.bai.activity.MoniDetailActivity;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.model.MoniCheModel;
import money.com.gettingmoney.bai.model.MoniStockHomeModel;
import money.com.gettingmoney.bai.view.ListViewForScrollView;
import money.com.gettingmoney.bean.FindUserBean;
import money.com.gettingmoney.bean.OrderBeans;
import money.com.gettingmoney.bean.Sell_buyBean;
import money.com.gettingmoney.bean.SimulatBean;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.simulated_trading.SimulatMethondes;
import money.com.gettingmoney.weiget.LoadingDialog;


/**
 * Created by Administrator on 2016/8/16.
 * 模拟股票资产的卖出
 */
public class MoniDieFragment extends BaseFragment {

    @InjectView(R.id.mLvShopMore)
    ListViewForScrollView mLvShopMore;
    @InjectView(R.id.searchview)
    SearchView searchView;
    @InjectView(R.id.listview_edit)
    ListView listview_edit;
    //卖出listview
    @InjectView(R.id.sell_listview)
    ListView sell_listview;
    //买入
    @InjectView(R.id.buy_listview)
    ListView buy_listview;

    @InjectView(R.id.quancang)
    TextView quancang;
    @InjectView(R.id.twocang)
    TextView twocang;
    @InjectView(R.id.threecang)
    TextView threecang;
    @InjectView(R.id.stopfall)
    TextView stopfall;
    @InjectView(R.id.stoprise)
    TextView stoprise;
    @InjectView(R.id.saleTotal)
    TextView saleTotal;
    @InjectView(R.id.sell_num)
    EditText sell_num;
    @InjectView(R.id.totleMoney)
    TextView totleMoney;
    @InjectView(R.id.supMoney)
    TextView supMoney;
    @InjectView(R.id.tv_buy)
    TextView tv_buy;
    @InjectView(R.id.tv_number)
    TextView tvNumber;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;//最外一层的加载让他仿照apple那种下拉刷新的效果
    private CommonAdapter<MoniCheModel.ResultBean> mAdapter;
    private List<MoniStockHomeModel> mList;

    String itemshares_id;//全局变量选中的股票的id
    int a;//a=0代表是先搜索然后在买入，a=1代表是从平仓里添加股票

    private AlertDialog alertDialog;

    @InjectView(R.id.focus)
    LinearLayout focus;

    /**
     * 加载中的脚
     */
    private View footer;

    /**
     * 页数角标，从0开始。
     */
    // 0是下拉刷新的  1上拉加载
    private int page = 0;
    private int xiala;
    /**
     * 每页显示数量
     */
    private int num = 10;
    private boolean isFirst = true;//是否是第一次请求，控制footer只创建一次。
    private Map<TextView, CountDownTimer> leftTimeMap = new HashMap<>();
    private int pos;
    private LoadingDialog dialog;
    private String rusult1;//买入卖出的数据
    private OrderBeans beans = new OrderBeans(); //添加股票交易的上传的参数
    private LinearLayout mLlFooter;
    private TextView mTxtFooter;
    // 0 全仓 1 1/2仓 2 1/3仓
    int cang = -1;
    private String findDealresult = "";

    public static MoniDieFragment getInstance() {
        MoniDieFragment fragment = new MoniDieFragment();
        return fragment;
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    //解析
                    Sell_buyBean Bean = new Gson().fromJson(rusult1, new TypeToken<Sell_buyBean>() {
                    }.getType());

                    if (Bean.getStopRise() != null && ("").equals(Bean.getStopRise())) {
                        stoprise.setText("涨跌" + Bean.getStopRise());
                    } else {
                        stoprise.setText("涨跌--");
                    }
                    if (Bean.getStopFall() != null && ("").equals(Bean.getStopFall())) {
                        stopfall.setText("跌停" + Bean.getStopFall());
                    } else {
                        stopfall.setText("跌停--");
                    }
                    if (!("").equals(Bean.getSaleTotal()) && Bean.getSaleTotal() != null) {
                        if (Integer.parseInt(Bean.getSaleTotal()) < 100) {
                            Toast.makeText(getContext(), "总共能够买入的股票没到100", Toast.LENGTH_SHORT).show();
                        } else {
                            saleTotal.setText(Bean.getSaleTotal());
                        }
                    } else {
                        //因为暂时没有可买的数据--所以我暂时先写个500这样好计算全仓，1/2仓，1/3仓等
                        //能被100整除
                        saleTotal.setText("0");
                    }
                    //现价
                    if (Bean.getNow() != null && ("").equals(Bean.getNow())) {
                        tvNumber.setText("0");
                    } else {
                        tvNumber.setText(Bean.getNow());
                    }
                    //卖
                    if (Bean.getFiveSale().size() < 1) {
                        beforesell();
                    } else {
                        SimtradiListSellAdp adpter = new SimtradiListSellAdp(getContext(), Bean.getFiveSale());
                        sell_listview.setAdapter(adpter);
                    }
                    //买
                    if (Bean.getFiveBuy().size() < 1) {
                        beforebuy();
                    } else {
                        SimtradiListBuyAdp adpter = new SimtradiListBuyAdp(getContext(), Bean.getFiveBuy());
                        sell_listview.setAdapter(adpter);
                    }
                    dialog.close();
                    break;

                case 3:
                    if (a==0){
                        dialog.close();
                        Toast.makeText(getContext(), "卖出股票成功", Toast.LENGTH_SHORT).show();
                    }else {
                        alertDialog.dismiss();
                        Toast.makeText(getContext(), "卖出股票成功", Toast.LENGTH_SHORT).show();

                    }
                    break;
                case 4:
                    initEvent();
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = requestView(inflater, R.layout.bai_moni_die);
        ButterKnife.inject(this, view);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        //获取到TextView的控件
        TextView textView = (TextView) searchView.findViewById(id);
        //设置字体大小为14sp
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);//14sp
        textView.setGravity(Gravity.BOTTOM);
        beforebuy();
        beforesell();
        if (Login()) {
            findUserWallet(ShareUtil.getInstance().getUserNumber(getContext()));
            //卖出下面列表的交易记录
            findDeal();
        }
        AA();
        initWindow();
        initListview();
        return view;
    }


    //searcheview 的搜索

    void AA() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0) {
                    Log.e("onQueryTextSubmit", "我是点击回车按钮");
                    searchView.setIconified(true);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //如果用户没登录则进入登录界面
                if (!Login()) {
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                } else {
                    if (!TextUtils.isEmpty(newText)) {
                        init("", newText);
                        listview_edit.setFilterText(newText);

                    } else {
                        listview_edit.setVisibility(View.GONE);
                        listview_edit.clearTextFilter();
                    }
                }
                return false;
            }
        });

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
                    //查询股票交易记录
                    findDeal();

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


    void findDeal() {
        //查询股票交易的接口
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                SimulatMethondes util = new SimulatMethondes();
                util.findDeal(dialog, ShareUtil.getInstance().getUserNumber(getContext()), null, null, 10, 1, 1, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Message message = new Message();
                        message.what = 4;
                        handler.sendMessage(message);
                        findDealresult = result;
                    }
                });
            }
        };
        new Thread(runnable1).start();
    }


    //点击输入框listview获取
    void init(final String stockId, final String stockName) {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                SimulatMethondes util = new SimulatMethondes();
                util.searchlist(null, ShareUtil.getInstance().getUserNumber(getContext()), stockId, stockName, "15", "1", new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        //解析
                        SimulatBean simulatBean = new Gson().fromJson(result, new TypeToken<SimulatBean>() {
                        }.getType());
                        //适配器
                        SimtradingAdpter adpter = new SimtradingAdpter(getContext(), simulatBean.getResult());
                        listview_edit.setAdapter(adpter);
                        listview_edit.setVisibility(View.VISIBLE);
                        listview_edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                TextView name = (TextView) view.findViewById(R.id.shares_name);
                                listview_edit.setVisibility(View.GONE);
                                searchView.setQuery(name.getText().toString(), false);
                                TextView shares_id = (TextView) view.findViewById(R.id.shares_id);
                                itemshares_id = shares_id.getText().toString().trim();
                                sell_buy(shares_id.getText().toString());
                            }
                        });
                    }
                });
            }
        };
        new Thread(runnable1).start();
    }


    //买入卖出时的数据填充
    void sell_buy(final String shares_id) {
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                SimulatMethondes util = new SimulatMethondes();
                util.sell_buy(dialog, ShareUtil.getInstance().getUserNumber(getContext()), shares_id, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        dialog = new LoadingDialog(getContext(), "正在加载");
                        dialog.show();
                        rusult1 = result;
                        Log.i("卖出的的买入卖出的时间填充返回的结果", result);
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                    }
                });
            }
        };
        new Thread(runnable2).start();
    }

    //当用户第一次进入模拟交易的买涨界面-还没有搜索股票--卖出的假值
    void beforesell() {
        //卖
        List<Sell_buyBean.FiveSaleBean> beans = new ArrayList<>();
        Sell_buyBean.FiveSaleBean bean;

        bean = new Sell_buyBean.FiveSaleBean();
        for (int i = 0; i < 5; i++) {
            bean.setAmount("--");
            bean.setCountVol("--");
            beans.add(bean);
        }
        SimtradiListSellAdp selladpter = new SimtradiListSellAdp(getContext(), beans);
        sell_listview.setAdapter(selladpter);
    }

    //当用户第一次进入模拟交易的买涨界面-还没有搜索股票--买入的假值
    void beforebuy() {
        List<Sell_buyBean.FiveBuyBean> buyBeans = new ArrayList<>();
        Sell_buyBean.FiveBuyBean buybean;
        buybean = new Sell_buyBean.FiveBuyBean();
        for (int i = 0; i < 5; i++) {
            buybean.setAmount("--");
            buybean.setCountVol("--");
            buyBeans.add(buybean);
        }
        SimtradiListBuyAdp buyadapter = new SimtradiListBuyAdp(getContext(), buyBeans);
        buy_listview.setAdapter(buyadapter);
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
                        Log.i("查询用户资产的跌跌跌访问网络", result);
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

    //判断可买多少股票--并且都是要以整100的开始买
    boolean input() {
        //判断是否是字符串--如果是数字
        for (int i = 0; i < saleTotal.getText().toString().length(); i++) {
            System.out.println(saleTotal.getText().toString().charAt(i));
            if (!Character.isDigit(saleTotal.getText().toString().charAt(i))) {
                return false;
            }
        }
        //判断数字是否大于100
        if (Integer.parseInt(saleTotal.getText().toString()) < 100) {
            Toast.makeText(getContext(), "因股小于100，请手动输入你想要买的股", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    //判断点击买入添加交易是否满足一下条件
    boolean addshare() {
        if (ShareUtil.getInstance().getUserNumber(getContext()).length() < 1 || ("").equals(ShareUtil.getInstance().getUserNumber(getContext()))) {
            Toast.makeText(getContext(), "你还没有登录", Toast.LENGTH_SHORT).show();
            return false;
        }
        int i = (int) Float.parseFloat(tvNumber.getText().toString());
        if (i == 0) {
            Toast.makeText(getContext(), "成交金额不能为0", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (String.valueOf(i).length() < 1) {
            Toast.makeText(getContext(), "成交金额不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sell_num.getText().toString().length() < 1) {
            Toast.makeText(getContext(), "可买入量不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @OnClick({R.id.im_jian, R.id.im_jia, R.id.quancang, R.id.twocang, R.id.threecang, R.id.tv_buy})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:
                if (addshare()) {
                    dialog = new LoadingDialog(getContext(), "正在加载添加股票");
                    dialog.show();
                    int i = (int) Float.parseFloat(tvNumber.getText().toString());
                    beans.setStockId(itemshares_id);
                    beans.setAmount(i);
                    beans.setType(0);//这里的Type：0为买入，1为卖出--目前为止卖出还没有--所以我现在测试写的是1
                    beans.setCountVol(Integer.parseInt(sell_num.getText().toString()));
                    addDeal(ShareUtil.getInstance().getUserNumber(getContext()));
                    a=0;
                }

                break;


            case R.id.im_jian:
                Float f = Float.parseFloat(tvNumber.getText().toString());
                f = f - 1;
                if (f > 0) {
                    tvNumber.setText(f + "");
                }

                break;
            case R.id.im_jia:
                Float f1 = Float.parseFloat(tvNumber.getText().toString());
                f1 = f1 + 1;
                if (f1 > 0) {
                    tvNumber.setText(f1 + "");
                }
                break;
            case R.id.quancang:
                quancang.setBackgroundColor(getResources().getColor(R.color._e93030));
                quancang.setTextColor(getResources().getColor(R.color.white));
                twocang.setBackgroundColor(getResources().getColor(R.color.white));
                twocang.setTextColor(getResources().getColor(R.color._e93030));
                threecang.setBackgroundColor(getResources().getColor(R.color.white));
                threecang.setTextColor(getResources().getColor(R.color._e93030));
                cang = 0;
                if (input()) {
                    for (int i = 1; i <= Integer.parseInt(saleTotal.getText().toString()); i++) {
                        if (i % 100 == 0) {
                            sell_num.setText(String.valueOf(i));
                        }
                    }
                }


                break;
            case R.id.twocang:
                twocang.setBackgroundColor(getResources().getColor(R.color._e93030));
                twocang.setTextColor(getResources().getColor(R.color.white));
                quancang.setBackgroundColor(getResources().getColor(R.color.white));
                quancang.setTextColor(getResources().getColor(R.color._e93030));
                threecang.setBackgroundColor(getResources().getColor(R.color.white));
                threecang.setTextColor(getResources().getColor(R.color._e93030));
                cang = 1;

                if (input()) {
                    int twochang = Integer.parseInt(saleTotal.getText().toString()) / 2;
                    if (twochang < 100) {
                        sell_num.setText("100");
                    } else {

                        for (int i = 1; i <= twochang; i++) {
                            if (i % 100 == 0) {
                                sell_num.setText(String.valueOf(i));
                            }
                        }
                    }

                }
                break;
            case R.id.threecang:
                threecang.setBackgroundColor(getResources().getColor(R.color._e93030));
                threecang.setTextColor(getResources().getColor(R.color.white));
                twocang.setBackgroundColor(getResources().getColor(R.color.white));
                twocang.setTextColor(getResources().getColor(R.color._e93030));
                quancang.setBackgroundColor(getResources().getColor(R.color.white));
                quancang.setTextColor(getResources().getColor(R.color._e93030));
                cang = 2;
                if (input()) {
                    int thrchang = Integer.parseInt(saleTotal.getText().toString()) / 3;

                    if (thrchang < 100) {
                        sell_num.setText("100");
                    } else {
                        for (int i = 1; i <= thrchang; i++) {
                            if (i % 100 == 0) {
                                sell_num.setText(String.valueOf(i));
                            }
                        }
                    }
                    break;
                }

        }
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

                        Message message = new Message();
                        message.what = 3;
                        handler.sendMessage(message);
                        Log.i("卖出添加股票返回值", result);
                    }
                });
            }
        };
        new Thread(runnable4).start();
    }

    //下面的热门交易记录表
    private void initEvent() {
        //列表
        //解析
        final MoniCheModel Bean = new Gson().fromJson(findDealresult, new TypeToken<MoniCheModel>() {
        }.getType());

        //列表
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);

        mAdapter = new CommonAdapter<MoniCheModel.ResultBean>(getActivity(), Bean.getResult(), R.layout.bai_moni_stock_home_items) {
            @Override
            public void convert(ViewHolder baseViewHolder, final MoniCheModel.ResultBean item) {
                final LinearLayout linearLayout = (LinearLayout) baseViewHolder.getView(R.id.ll_ishiden);
                final ImageView imageView = (ImageView) baseViewHolder.getView(R.id.im_image);
                final TextView textView = (TextView) baseViewHolder.getView(R.id.tv_xianshi);

                TextView pingcang, detials;
                pingcang = (TextView) baseViewHolder.getView(R.id.pingcang);
                detials = (TextView) baseViewHolder.getView(R.id.detials);

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
                        } else {
                            item.setFlag(true);
                            linearLayout.setVisibility(View.VISIBLE);
                            imageView.setVisibility(View.GONE);
                        }
                    }
                });

                //平仓
                pingcang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog = BDialog.showDialog(getContext(), R.layout.bai_dialog_pingcang, "", "", new View.OnClickListener() {
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

                                            dialog= new LoadingDialog(getContext(), "正在加载中");
                                            dialog.show();
                                            addDeal(ShareUtil.getInstance().getUserNumber(getContext()));
                                            a=1;
                                        } else {
                                            Toast.makeText(getContext(), "只能整倍的买，例如100,200,300...等等", Toast.LENGTH_SHORT).show();

                                        }

                                    } else {
                                        Toast.makeText(getContext(), "购买股票数量不能为空", Toast.LENGTH_SHORT).show();
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


    //判断当前fragment是否显示在屏幕上 在setUserVisibleHint这个方法上做处理
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            //相当于Fragment的onResume
            Log.i("判断当前fragment显示在屏幕上", "买出的MonidieFragment在屏幕中显示");
        } else {
            //相当于Fragment的onPause
            Log.i("判断当前fragment显示在屏幕上", "买出的MonidieFragment没在屏幕中显示");
        }
    }


    /*
*
* 只需要在serchview
*  的父容器布局里面加上android:focusable="true"android:focusableInTouchMode="true"就在进入的时候不会自动获取焦点，
       但是当你点击searchview 获取焦点后，到别的activity再回来的时候，失效了，总是自动获取焦点并且弹出小键盘，很是烦人。
       这里有个办法就是在代码里面设置linearlayout,回到这个activity肯定会执行onresume方法，让它执行上面的代码，就不再会自动获取焦点了。
* */
    @Override
    public void onResume() {
        super.onResume();
        focus.setFocusable(true);
        focus.setFocusableInTouchMode(true);
        focus.requestFocus();
    }

    //当当晚的fragment不在屏幕上的时候执行，这个方法，就把searchview里的值设置成空的这样为了防止他在请求无网络
    @Override
    public void onStop() {
        super.onStop();
        searchView.setQuery("", false);
    }

}
