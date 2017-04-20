package money.com.gettingmoney.bai.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.LoginActivity;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseActivity;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.homeNewsitem;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.news.NewsUtil;
import money.com.gettingmoney.weiget.LoadingDialog;


public class BusinessNewsActivity extends BaseActivity /*implements OnActionListener */ {

    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.mLvShopMore)
    PullToRefreshListView mLvShopMore;
    @InjectView(R.id.tv_no_data)
    TextView tvNoData;
    private LoadingDialog dialog;


    //测试带吗  大  啊啊的啊 的的啊的啊的啊大的啊的爱的 阿迪啊的啊大的啊

    //
//适配器
    private CommonAdapter<homeNewsitem> mAdapter;
    private List<homeNewsitem> mList = new ArrayList<>();

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

 //shdfsjfksfhskhfskfhskfhsk      bbbbbbbbbbb            ccccccccc
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolBar = new MyToolBar(this, "", "新闻列表", "");
        setContentView(requestView(R.layout.bai_news_list));
        ButterKnife.inject(this);
        type=getIntent().getIntExtra("type",1);
        Log.i("typetypetypetype", type-1+"");

        initEvent();
        initListview();
    }

    @Override
    public void requestInit() {

    }

    private void initListview() {
        progressLayout.setFocusable(true);
        progressLayout.setFocusableInTouchMode(true);
        progressLayout.requestFocus();
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
                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
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
                    new Thread(newsList).run();
                }
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {

            mLvShopMore.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    Runnable newsList = new Runnable() {
        @Override
        public void run() {
            NewsUtil util = new NewsUtil();
            util.newslist(dialog, type, page, num, ShareUtil.getInstance().getUserNumber(BusinessNewsActivity.this), new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    Log.i("新闻列表的点进去返回的值", result);
                    try {
                        JSONObject object = new JSONObject(result);
                        JSONArray newsList = object.getJSONArray("newsList");
                        mList = JSON.parseArray(newsList.toString(), homeNewsitem.class);
                        if (mList.size() != num) {
                            tvNoData.setVisibility(View.VISIBLE);
                        } else {
                            tvNoData.setVisibility(View.GONE);
                        }
                        //0 下拉刷新 1下拉加载
                        if (xiala == 0) {
                            mAdapter.setmDatas(mList);
                            Log.d("Debug", "下拉刷新  只有首页的数据");
                        } else {
                            mAdapter.addmDatas(mList);
                            Log.d("Debug", "上拉加载  多页的数据");
                        }
                        Log.d("Debug", "返回的json数据" + object);
                        progressLayout.showContent();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressLayout.showContent();
                    }
                }
            });
        }
    };

    private void initEvent() {
        //进来时候的加载的转转 代替
        progressLayout.showProgress();
        new DataTask().execute();

        mAdapter=new CommonAdapter<homeNewsitem>(BusinessNewsActivity.this, null, R.layout.bai_homenews_items) {
            @Override
            public void convert(ViewHolder helper, final homeNewsitem item) {
//                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) helper.getView(R.id.pic);
//                simpleDraweeView.setImageURI(item.getPic() + "");
                helper.setText(R.id.title1, item.getTitle());
                helper.setText(R.id.time, item.getTime());
                helper.setText(R.id.content, Html.fromHtml(item.getContent())+"");
                LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.ll_pinglun_number);
                linearLayout.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_comment_number, item.getCommentCount()+"条评论");
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(BusinessNewsActivity.this, NewsDetailActivity.class).putExtra(NewsDetailActivity.KEY_ID,item.getId()));

                    }
                });
            }
        };

        mLvShopMore.setAdapter(mAdapter);

    }



    //判断是否有登录
    boolean Login() {
        if (ShareUtil.getInstance().getUserNumber(BusinessNewsActivity.this).length() < 1 || ("").equals(ShareUtil.getInstance().getUserNumber(BusinessNewsActivity.this))) {
            startActivity(new Intent(BusinessNewsActivity.this, LoginActivity.class));
            return false;
        }
        return true;
    }
    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }


}
