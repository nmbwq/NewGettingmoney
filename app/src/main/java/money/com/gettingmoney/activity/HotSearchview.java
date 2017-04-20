package money.com.gettingmoney.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.activity.Detials_KlineAct;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bean.HotSearchviewBeans;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.simulated_trading.SimulatMethondes;
import money.com.gettingmoney.weiget.LoadingDialog;
/*
* 首页和行情页面点击的搜索图标按钮
* */

public class HotSearchview extends Activity implements View.OnClickListener {

    TextView tv_cose;
    SearchView searchView;
    PullToRefreshListView mLvShopMore;
    private LoadingDialog dialog;
    private CommonAdapter<HotSearchviewBeans.ResultBean> mAdapter;

    private String hotresult;
    private int page = 1;
    private int refres;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initEvent(hotresult);
                    dialog.close();

                    break;
                case 2:
                    initEvent(hotresult);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_searchview);
        searchView = (SearchView) findViewById(R.id.serachview);

        setSearchView();

        if (Login()) {
            Hotsearchview();
        }
        //设置刷新的效果
        initListview();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (!TextUtils.isEmpty(newText)) {
                    dialog = new LoadingDialog(HotSearchview.this, "正在加载");
                    dialog.show();
                    Toast.makeText(HotSearchview.this, "输入的值" + newText, Toast.LENGTH_SHORT).show();
                    Findsearchview("", newText);
                } else {
                }
                return false;
            }
        });

    }

    //设置searchview的特点
    void setSearchView() {
        tv_cose = (TextView) findViewById(R.id.tv_cose);
        tv_cose.setOnClickListener(this);
        mLvShopMore = (PullToRefreshListView) findViewById(R.id.mLvShopMore);

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        //获取到TextView的控件
        TextView textView = (TextView) searchView.findViewById(id);
        //设置字体大小为14sp
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);//14sp
        textView.setGravity(Gravity.BOTTOM);
        searchView.setIconifiedByDefault(true);

    }

    //热门股票接口访问网络
    void Hotsearchview() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SimulatMethondes util = new SimulatMethondes();
                util.findHostHistory(dialog, ShareUtil.getInstance().getUserNumber(HotSearchview.this), 10, page, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        dialog = new LoadingDialog(HotSearchview.this, "正在加载中");
                        dialog.show();
                        hotresult = result;

                        Log.i("热门查询根际id和名字查询股票结果", result);
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);


                    }
                });

            }
        };
        new Thread(runnable).start();

    }

    //根际id和名字查询股票
    void Findsearchview(final String stockId, final String stockName) {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                SimulatMethondes util = new SimulatMethondes();
                util.searchlist(dialog, ShareUtil.getInstance().getUserNumber(HotSearchview.this), stockId, stockName, "15", "1", new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        dialog.close();
                        hotresult=result;
                        Log.i("热门查询根际id和名字查询股票结果", result);
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                    }
                });

            }
        };
        new Thread(runnable1).start();

    }


    //刷新
    private void initListview() {

        // 上拉、下拉设定
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
                        refres = 0;//为刷新
                        Log.i("查询热门hot股票的", "这是下拉刷新");
                        if (Login()) {
                            Hotsearchview();
                        }
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                        page++;
                        refres = 1;//为加载更多

                        Log.i("查询热门hot股票的", "这是上拉加载");
                        if (Login()) {
                            Hotsearchview();
                        }
                    }
                });

    }


    //添加适配器
    private void initEvent(String result) {
        //解析
        HotSearchviewBeans simulatBean = new Gson().fromJson(result, new TypeToken<HotSearchviewBeans>() {
        }.getType());


        mAdapter = new CommonAdapter<HotSearchviewBeans.ResultBean>(HotSearchview.this, simulatBean.getResult(), R.layout.item_simtrading) {
            @Override
            public void convert(ViewHolder helper, final HotSearchviewBeans.ResultBean item) {
                TextView shares_id, shares_name;
                shares_id = (TextView) helper.getView(R.id.shares_id);
                shares_name = (TextView) helper.getView(R.id.shares_name);
                shares_id.setText(item.getStockId());
                shares_name.setText(item.getStockName());

                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //传递股票id过去
                        Intent intent=new Intent(HotSearchview.this,Detials_KlineAct.class);
                        intent.putExtra("stockId",item.getStockId());
                        intent.putExtra("shock_name",item.getStockName());
                        intent.putExtra("type","沪深指数");
                        startActivity(intent);
                    }
                });


            }
        };
        if (refres == 0) {
            mAdapter.setmDatas(simulatBean.getResult());

        } else {
            mAdapter.addmDatas(simulatBean.getResult());

        }

        mLvShopMore.setAdapter(mAdapter);
        mLvShopMore.onRefreshComplete();

    }


    //判断是否有登录
    boolean Login() {
        if (ShareUtil.getInstance().getUserNumber(HotSearchview.this).length() < 1 || ("").equals(ShareUtil.getInstance().getUserNumber(HotSearchview.this))) {
            Toast.makeText(HotSearchview.this, "你还没有登录", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cose:
                finish();
                break;

        }
    }
}





