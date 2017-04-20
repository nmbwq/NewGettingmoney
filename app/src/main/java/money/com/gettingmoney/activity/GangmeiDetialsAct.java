package money.com.gettingmoney.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.LeftandRight.MyLeftAdapter;
import money.com.gettingmoney.LeftandRight.MyRightAdapter;
import money.com.gettingmoney.LeftandRight.SyncHorizontalScrollView;
import money.com.gettingmoney.LeftandRight.UtilTools;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bean.GangguListBean;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.hangqing.indexuti;
import money.com.gettingmoney.weiget.LoadingDialog;

//港股更多
public class GangmeiDetialsAct extends Activity {

    GangguListBean gangguListBean;
    List<GangguListBean.StockListBean> beanList = new ArrayList<>();
    private LinearLayout leftContainerView;
    private ListView leftListView;
    private LinearLayout rightContainerView;
    private ListView rightListView;
    private SyncHorizontalScrollView titleHorsv;
    private SyncHorizontalScrollView contentHorsv;
    private LoadingDialog dialog;
    MyLeftAdapter myLeftadapter;
    MyRightAdapter myRightAdapter;
    private CommonAdapter<GangguListBean.StockListBean> leftadp;//左边适配器
    private CommonAdapter<GangguListBean.StockListBean> rightadp;//右边适配器


    int page = 1;
    int xiala ;
    PullToRefreshScrollView pullToRefreshScrollVie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gangmei_detials);
        dialog = new LoadingDialog(GangmeiDetialsAct.this, "正在加载中");
        dialog.show();
        leftContainerView = (LinearLayout) findViewById(R.id.left_container);
        leftListView = (ListView) findViewById(R.id.left_container_listview);
        rightContainerView = (LinearLayout) findViewById(R.id.right_container);
        rightListView = (ListView) findViewById(R.id.right_container_listview);
        titleHorsv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        contentHorsv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);

        pullToRefreshScrollVie = (PullToRefreshScrollView) findViewById(R.id.pullToRefreshScrollVie);
        // 设置两个水平控件的联动
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);

        init();
        Event();


    }

    void Event() {

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
                init();
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

    void init() {
        new Thread(runnable).start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            indexuti indexuti = new indexuti();
            indexuti.ganggulist(dialog, page, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    gangguListBean = new Gson().fromJson(result, new TypeToken<GangguListBean>() {
                    }.getType());
                    beanList = gangguListBean.getStockList();
                    Log.i("港股更多的界面", beanList.toString());

             /*       // 添加左边内容数据
                    leftContainerView.setBackgroundColor(Color.YELLOW);
                    myLeftadapter = new MyLeftAdapter(GangmeiDetialsAct.this, beanList);
                    leftListView.setAdapter(myLeftadapter);
                    UtilTools.setListViewHeightBasedOnChildren(leftListView);

                    // 添加右边内容数据
                    rightContainerView.setBackgroundColor(Color.GRAY);
                    myRightAdapter = new MyRightAdapter(GangmeiDetialsAct.this, beanList);
                    rightListView.setAdapter(myRightAdapter);
                    UtilTools.setListViewHeightBasedOnChildren(rightListView);*/

                    //0 下拉刷新 1下拉加载
              /*      if (xiala == 0) {*/
                    leftadp();
                    rightadp();
                        leftadp.setmDatas(beanList);
                    rightadp.setmDatas(beanList);
                    Log.d("Debug", "下拉刷新  只有首页的数据");
        /*            } else {
                        leftadp.addmDatas(gangguListBean.getStockList());
                        rightadp.addmDatas(gangguListBean.getStockList());
*/
                    Log.d("Debug", "上拉加载  多页的数据");
                  //  }

                    dialog.close();

                }
            });
        }
    };


    private void leftadp() {
        leftContainerView.setBackgroundColor(Color.YELLOW);
        leftadp = new CommonAdapter<GangguListBean.StockListBean>(GangmeiDetialsAct.this, null, R.layout.layout_left_item) {
            @Override
            public void convert(ViewHolder helper, GangguListBean.StockListBean item) {
                TextView textView = (TextView) helper.getView(R.id.left_container_textview0);
                if (item.getStockName() != null) {
                    textView.setText(item.getStockName());
                }
            }
        };

        leftListView.setAdapter(leftadp);
        UtilTools.setListViewHeightBasedOnChildren(leftListView);
    }


    private void rightadp() {
        rightContainerView.setBackgroundColor(Color.GRAY);
        rightadp = new CommonAdapter<GangguListBean.StockListBean>(GangmeiDetialsAct.this, null, R.layout.layout_right_item) {
            @Override
            public void convert(ViewHolder helper, GangguListBean.StockListBean item) {
                TextView textView0 = (TextView) helper.getView(R.id.right_item_textview0);
                TextView textView1 = (TextView) helper.getView(R.id.right_item_textview1);
                TextView textView2 = (TextView) helper.getView(R.id.right_item_textview2);
                if (item.getNow() != null) {
                    textView0.setText(item.getNow());
                }
                if (item.getDifferRange() != null) {
                    textView1.setText(item.getDifferRange());
                }
                if (item.getDiffer() != null) {
                    textView2.setText(item.getDiffer());
                }

            }
        };
        rightListView.setAdapter(rightadp);
        UtilTools.setListViewHeightBasedOnChildren(rightListView);
    }


}
