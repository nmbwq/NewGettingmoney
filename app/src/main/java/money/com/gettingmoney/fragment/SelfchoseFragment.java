package money.com.gettingmoney.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.activity.Detials_KlineAct;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.model.SelfchoseBean;
import money.com.gettingmoney.bean.UserStockss;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.hangqing.indexUtil;
import money.com.gettingmoney.webutil.hangqing.indexuti;
import money.com.gettingmoney.weiget.LoadingDialog;

public class SelfchoseFragment extends Fragment {
    /**
     * 自选
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    private PullToRefreshListView mLvShopMore;
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
    private LoadingDialog dialog;

    //适配器
    private CommonAdapter<SelfchoseBean.ResultBean> mAdapter;
    private UserStockss userStocks = new UserStockss();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selfchose, null);

        mLvShopMore = (PullToRefreshListView) view.findViewById(R.id.mLvShopMore);

        dialog = new LoadingDialog(getActivity(), "正在加载中");
        dialog.show();
        if (Login()) {

            init();

        }
        initEvent();
        initListview();
        return view;
    }


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

                    init();

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


    //查询用户关注的股票
    void init() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                indexUtil util = new indexuti();
                util.findUserStock(dialog, ShareUtil.getInstance().getUserNumber(getContext()), 10, 1, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        dialog.close();
                        Log.i("自选股返回的列表154", result);

                        SelfchoseBean simulatBean = new Gson().fromJson(result, new TypeToken<SelfchoseBean>() {
                        }.getType());

                        //0 下拉刷新 1下拉加载
                        if (xiala == 0) {
                            mAdapter.setmDatas(simulatBean.getResult());
                            Log.d("Debug", "下拉刷新  只有首页的数据");
                        } else {
                            mAdapter.addmDatas(simulatBean.getResult());
                            Log.d("Debug", "上拉加载  多页的数据");
                        }
                    }
                });
            }
        };

        new Thread(runnable).start();

    }


    private void initEvent() {
        //进来时候的加载的转转 代替

        mAdapter = new CommonAdapter<SelfchoseBean.ResultBean>(getContext(), null, R.layout.item_shengzhen) {
            @Override
            public void convert(final ViewHolder helper, final SelfchoseBean.ResultBean item) {


                helper.setText(R.id.name, item.getStockName());
                helper.setText(R.id.news, item.getNow());
                helper.setText(R.id.down, item.getDiffer());
                helper.setText(R.id.rose, item.getDifferRange());
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //传递股票id过去
                        Intent intent = new Intent(getContext(), Detials_KlineAct.class);
                        intent.putExtra("stockId", item.getSharesCode());
                        intent.putExtra("shock_name", item.getStockName());
                        intent.putExtra("type", "自选");

                        startActivity(intent);

                    }
                });

                helper.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("确定删除?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //删除的访问网络操作
                                userStocks.setUserSharesId(item.getUserSharesId());
                                Log.i("sfhskjfhsklfh", item.getUserSharesId() + "");
                                deletestocke();
                                mAdapter.removeData(item);
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });


                        builder.create().show();

                        Log.i("自选股票长按按钮", "1111");
                        return false;
                    }
                });

            }
        };

        mLvShopMore.setAdapter(mAdapter);

    }


    //删除的访问网络操作

    void deletestocke() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                indexuti indexuti = new indexuti();

                indexuti.deleteUserStock(ShareUtil.getInstance().getUserNumber(getContext()), userStocks, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Toast.makeText(getContext(), "删除股票成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        new Thread(runnable).start();
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