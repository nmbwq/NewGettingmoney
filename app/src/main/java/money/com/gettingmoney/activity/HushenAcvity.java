package money.com.gettingmoney.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.adapter.HushendetialsAdapter;
import money.com.gettingmoney.bean.ShengzhenBean;
import money.com.gettingmoney.util.CheckNetUtils;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.hangqing.indexuti;
import money.com.gettingmoney.weiget.LoadingDialog;

//行情--沪深-点击更多进屠刀该页面
public class HushenAcvity extends BaseActivity implements View.OnClickListener {
    private TextView head_title, head_right, down, rose, deal;

    private PullToRefreshListView indexlistview;
    HushendetialsAdapter adapter;
    List<ShengzhenBean.ResultListBean> beans;
    ShengzhenBean bean = new ShengzhenBean();
    private LoadingDialog dialog;
    private int xiala = 0;//0则代表的是下拉刷新 1则代表上啦加载更多
    private int currentPage = 1;//页数
    private int pageSize = 20;//固定每页显示10个数据

    private String orderStr, orderType;//从上级页面传递过来的排序字段以及排序类型
    ImageView die, zhang;
    private boolean vb1 = true;// true的状态是没有被点击的--涨幅榜
    private boolean vb2 = true;// true的状态是没有被点击的--涨跌榜

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    dialog.close();
                    if (xiala == 0) {
                        //xiala==0代表刷新  xiala==1；代表加载更多
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter.upData(beans);
                        indexlistview.setAdapter(adapter);
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hushen_acvity);
        initView();
        dialog = new LoadingDialog(this, "正在加载");
        dialog.show();

        Intent intent = getIntent();
        orderStr = intent.getStringExtra("orderStr");
        orderType = intent.getStringExtra("orderType");

        Log.i("hfudhsfesdsfs", orderStr + "====" + orderType);

        Event();

    }

    //初始化
    private void initView() {
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        indexlistview = (PullToRefreshListView) findViewById(R.id.indexlistview);

        down = (TextView) this.findViewById(R.id.down);
        rose = (TextView) this.findViewById(R.id.rose);
        deal = (TextView) this.findViewById(R.id.deal);
        die = (ImageView) this.findViewById(R.id.die);
        zhang = (ImageView) this.findViewById(R.id.zhang);

        down.setOnClickListener(this);
        rose.setOnClickListener(this);
        deal.setOnClickListener(this);
        head_title.setText("国内指数");
        head_right.setVisibility(View.GONE);
        die.setImageResource(R.mipmap.die);
        zhang.setImageResource(R.mipmap.zhang);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.down:
                if (vb1) {
                    die.setImageResource(R.mipmap.zhang);
                    orderStr = "3";
                    orderType = "0";
                    Event();
                    vb1 = false;
                } else {
                    die.setImageResource(R.mipmap.die);
                    orderStr = "2";
                    orderType = "1";
                    Event();
                    vb1 = true;
                }
                break;
            case R.id.rose:
                if (vb2) {
                    zhang.setImageResource(R.mipmap.die);
                    orderStr = "2";
                    orderType = "1";
                    Event();
                    vb2 = false;
                } else {
                    zhang.setImageResource(R.mipmap.zhang);
                    orderStr = "3";
                    orderType = "0";
                    Event();
                    vb2 = true;
                }

                break;

            case R.id.deal:

                break;
        }
    }


    void Event() {
        if (CheckNetUtils.isNetworkConnected(this)) {

            if (!orderStr.isEmpty() && !orderType.isEmpty()) {
                new Thread(runnable).run();
                init();
            } else {
                Toast.makeText(this, "上级页面没有传递orderStr+orderType的值", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "无网络~~~", Toast.LENGTH_SHORT).show();
        }
    }


    void init() {
        // 上拉、下拉设定
        indexlistview.setMode(PullToRefreshBase.Mode.BOTH);
        // 下拉刷新 业务代码
        indexlistview.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        indexlistview.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        indexlistview
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                        currentPage = 1;
                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                        currentPage++;
                        xiala = 1;
                        new DataTask().execute();
                    }


                });
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            indexuti indexuti = new indexuti();
   /*
    * currentPage:页号
    * pageSize：每页大小
    * orderStr：排序字段 1,(最新价)/2,(涨跌幅)/3,(涨跌额)/4,(成交量)
    * orderType：排序类型 0,(降序)/1,(升序)
    * */
            indexuti.hushen(dialog, currentPage, pageSize, Integer.parseInt(orderStr), Integer.parseInt(orderType), new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    Log.i("hfudhsfesdsfs", result.toString());
                    bean = new Gson().fromJson(result, new TypeToken<ShengzhenBean>() {
                    }.getType());
                    beans = bean.getResultList();
                    adapter = new HushendetialsAdapter(HushenAcvity.this, beans);
                    indexlistview.setAdapter(adapter);

                    Message message = new Message();
                    message.what = 100;
                    handler.sendMessage(message);

                }
            });

        }
    };


    private class DataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {

            try {
                Thread.sleep(2000);
                new Thread(runnable).run();
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            indexlistview.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
}
