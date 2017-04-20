package money.com.gettingmoney.activity;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bean.Collection;
import money.com.gettingmoney.util.JsonUitl;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.collect.CollectUtil;
import money.com.gettingmoney.webutil.news.NewsUtil;
import money.com.gettingmoney.weiget.LoadingDialog;

public class CollectActivity extends BaseActivity {

    /**
     * 收藏
     *
     * @param savedInstanceState
     */
    private TextView head_title, head_right;
    private PullToRefreshListView collect_listview;
    private int page = 1;
    private int xiala = 0;
    private CommonAdapter<Collection> mAdapter;
    private LoadingDialog dialog;
    public List<Collection> collectionList = new ArrayList<>();
    int position;
    //收藏id（删除的时候用）
    Integer id;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("我的收藏");
        head_right.setVisibility(View.GONE);
        initView();
    }

    private void initView() {
        collect_listview = (PullToRefreshListView) this.findViewById(R.id.collect_listview);
        initListview();
        dialog = new LoadingDialog(this, "正在加载");
        dialog.show();
        collectionList = new ArrayList<>();

        mAdapter = new CommonAdapter<Collection>(CollectActivity.this, null, R.layout.bai_homenews_items) {
            @Override
            public void convert(final ViewHolder helper, Collection item) {
                helper.setText(R.id.title1, item.getTitle());
                helper.setText(R.id.time, item.getTime());
                //将带有html的标签的string转化成string
                helper.setText(R.id.content, Html.fromHtml(item.getContent())+"");
                id = item.getCollectionId();
                ImageView viewById = (ImageView) helper.getConvertView().findViewById(R.id.pic);
                TextView Delete = (TextView) helper.getConvertView().findViewById(R.id.tv_delete);
                Delete.setVisibility(View.VISIBLE);
                Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Debug", "删除的操作");
                        new Thread(delCollection).run();
                        //适配器里面要删除的item位置
                         position = helper.getPosition();
                    }
                });
            }

        };

        new Thread(getnews).run();
    }

    private void initListview() {
        // 上拉、下拉设定
        collect_listview.setMode(PullToRefreshBase.Mode.BOTH);
        // 下拉刷新 业务代码
        collect_listview.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        collect_listview.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        collect_listview
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
            // Simulates a background job.
            try {
                Thread.sleep(2000);
                new Thread(getnews).run();
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            collect_listview.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    Runnable getnews = new Runnable() {
        @Override
        public void run() {
            CollectUtil util = new CollectUtil();
            util.collectionList(dialog, page, 8, ShareUtil.getInstance().getUserNumber(CollectActivity.this), new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        collectionList = (ArrayList<Collection>) JsonUitl.stringToList(object.getJSONArray("collectionList").toString(), Collection.class);
                        Log.d("Debug", "返回的数据是" + collectionList);
                        if (xiala == 0) {
                            if (xiala == 0) {
                                mAdapter.setmDatas(collectionList);
                            } else {
                                mAdapter.setmDatas(collectionList);
                            }

                            collect_listview.setAdapter(mAdapter);
                        } else {
//                        collect_listview.requestLayout();
                            mAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    Runnable delCollection = new Runnable() {
        @Override
        public void run() {
            final LoadingDialog dialog = new LoadingDialog(CollectActivity.this, "删除中...");
            dialog.show();
            NewsUtil util = new NewsUtil();
            util.delCollection(dialog, ShareUtil.getInstance().getUserNumber(CollectActivity.this), id, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        Log.d("Debug", "返回的数据是" + "删除成功");
                        mAdapter.notifyDataSetChanged();
                        mAdapter.removeData(position);
                        dialog.close();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };


}
