package money.com.gettingmoney.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.HushenAcvity;
import money.com.gettingmoney.adapter.ShenzhengAdapter;
import money.com.gettingmoney.bai.activity.Detials_KlineAct;
import money.com.gettingmoney.bean.IndeXBean;
import money.com.gettingmoney.bean.ShengzhenBean;
import money.com.gettingmoney.util.CheckNetUtils;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.hangqing.indexuti;
import money.com.gettingmoney.weiget.LoadingDialog;

public class ShenzhengFragment extends Fragment implements View.OnClickListener {

    private ListView indexlistview1, indexlistview2;
    ShenzhengAdapter adapter;
    List<ShengzhenBean.ResultListBean> beans;
    ShengzhenBean bean = new ShengzhenBean();
    private LoadingDialog dialog;
    private int xiala = 0;//0则代表的是下拉刷新 1则代表上啦加载更多
    private int currentPage = 1;//页数
    private int pageSize = 5;//固定每页显示10个数据
    private LinearLayout line1, line2;
    TextView name1, name2, name3, news1, news2, news3, rows1, rows2, rows3, downs1, downs2, downs3;
    private boolean vb1 = true;// true的状态是没有被点击的--涨幅榜
    private boolean vb2 = true;// true的状态是没有被点击的--涨跌榜
    private LinearLayout linearlayout1, linearlayout2, linearlayout3;
    private TextView more1, more2;
    List<IndeXBean.ListResponses1Bean> dataBeen1 = new ArrayList<>();

    /**
     * 沪深
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shenzheng, null);
        indexlistview1 = (ListView) view.findViewById(R.id.indexlistview1);
        indexlistview2 = (ListView) view.findViewById(R.id.indexlistview2);
        line1 = (LinearLayout) view.findViewById(R.id.line1);
        line2 = (LinearLayout) view.findViewById(R.id.line2);
        more1 = (TextView) view.findViewById(R.id.more1);
        more2 = (TextView) view.findViewById(R.id.more2);

        name1 = (TextView) view.findViewById(R.id.name1);
        name2 = (TextView) view.findViewById(R.id.name2);
        name3 = (TextView) view.findViewById(R.id.name3);

        news1 = (TextView) view.findViewById(R.id.news1);
        news2 = (TextView) view.findViewById(R.id.news2);
        news3 = (TextView) view.findViewById(R.id.news3);

        rows1 = (TextView) view.findViewById(R.id.rows1);
        rows2 = (TextView) view.findViewById(R.id.rows2);
        rows3 = (TextView) view.findViewById(R.id.rows3);

        downs1 = (TextView) view.findViewById(R.id.downs1);
        downs2 = (TextView) view.findViewById(R.id.downs2);
        downs3 = (TextView) view.findViewById(R.id.downs3);

        linearlayout1 = (LinearLayout) view.findViewById(R.id.linearlayout1);
        linearlayout2 = (LinearLayout) view.findViewById(R.id.linearlayout2);
        linearlayout3 = (LinearLayout) view.findViewById(R.id.linearlayout3);

        line2.setOnClickListener(this);
        line1.setOnClickListener(this);
        more1.setOnClickListener(this);
        more2.setOnClickListener(this);

        linearlayout1.setOnClickListener(this);
        linearlayout2.setOnClickListener(this);
        linearlayout3.setOnClickListener(this);

        //沪深里的top三个指数
        Index();
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line1:

                init1();

                if (vb1) {
                    indexlistview1.setVisibility(View.VISIBLE);
                    vb1 = false;
                } else {
                    indexlistview1.setVisibility(View.GONE);
                    vb1 = true;
                }

                break;

            case R.id.line2:
                init2();
                if (vb2) {
                    indexlistview2.setVisibility(View.VISIBLE);
                    vb2 = false;
                } else {
                    indexlistview2.setVisibility(View.GONE);
                    vb2 = true;
                }
                break;
            case R.id.more1:
                Intent intent1 = new Intent(getContext(), HushenAcvity.class);
                intent1.putExtra("orderStr", "2");
                intent1.putExtra("orderType", "1");

                getContext().startActivity(intent1);
                break;
            case R.id.more2:

                Intent intent2 = new Intent(getContext(), HushenAcvity.class);
                intent2.putExtra("orderStr", "3");
                intent2.putExtra("orderType", "0");

                getContext().startActivity(intent2);

                break;

            case R.id.linearlayout1:
                //传递股票id过去
                Intent intent3 = new Intent(getContext(), Detials_KlineAct.class);
                intent3.putExtra("stockId", dataBeen1.get(0).getId());
                intent3.putExtra("shock_name", dataBeen1.get(0).getName());
                intent3.putExtra("type","沪深指数");
                getContext().startActivity(intent3);

                break;

            case R.id.linearlayout2:
                //传递股票id过去
                Intent intent4 = new Intent(getContext(), Detials_KlineAct.class);
                intent4.putExtra("stockId", dataBeen1.get(1).getId());
                intent4.putExtra("shock_name", dataBeen1.get(1).getName());
                intent4.putExtra("type","沪深指数");

                getContext().startActivity(intent4);
                break;

            case R.id.linearlayout3:
                //传递股票id过去
                Intent intent5 = new Intent(getContext(), Detials_KlineAct.class);
                intent5.putExtra("stockId", dataBeen1.get(2).getId());
                intent5.putExtra("shock_name", dataBeen1.get(3).getName());
                intent5.putExtra("type","沪深指数");

                getContext().startActivity(intent5);
                break;

        }
    }


    void init1() {
        //判断是否有网络
        if (CheckNetUtils.isNetworkConnected(getContext())) {
            new Thread(runnable1).run();

        } else {
            Toast.makeText(getContext(), "无网络~~~", Toast.LENGTH_SHORT).show();
        }

    }


    void init2() {
        //判断是否有网络

        if (CheckNetUtils.isNetworkConnected(getContext())) {
            new Thread(runnable2).run();
        } else {
            Toast.makeText(getContext(), "无网络~~~", Toast.LENGTH_SHORT).show();
        }

    }

    //涨幅榜的访问网络
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {

            indexuti indexuti = new indexuti();
   /*
    * currentPage:页号
    * pageSize：每页大小
    * orderStr：排序字段 1,(最新价)/2,(涨跌幅)/3,(涨跌额)/4,(成交量)
    * orderType：排序类型 0,(降序)/1,(升序)
    * */
            indexuti.hushen(dialog, currentPage, pageSize, 2, 0, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {

                    bean = new Gson().fromJson(result, new TypeToken<ShengzhenBean>() {
                    }.getType());
                    beans = bean.getResultList();
                    adapter = new ShenzhengAdapter(getContext(), beans);
                    indexlistview1.setAdapter(adapter);


                }
            });

        }
    };


    //涨跌榜的访问网络

    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {

            indexuti indexuti = new indexuti();
   /*
    * currentPage:页号
    * pageSize：每页大小
    * orderStr：排序字段 1,(最新价)/2,(涨跌幅)/3,(涨跌额)/4,(成交量)
    * orderType：排序类型 0,(降序)/1,(升序)
    * */
            indexuti.hushen(dialog, currentPage, pageSize, 2, 0, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {

                    bean = new Gson().fromJson(result, new TypeToken<ShengzhenBean>() {
                    }.getType());
                    beans = bean.getResultList();
                    adapter = new ShenzhengAdapter(getContext(), beans);
                    indexlistview2.setAdapter(adapter);


                }
            });

        }
    };


    //访问网络并且解析指数列表的数据
    void Index() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                indexuti indexuti = new indexuti();

                indexuti.indexlist(null, 1, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        // dialog.show();
                        //添加头部==
                        IndeXBean bean = new Gson().fromJson(result, new TypeToken<IndeXBean>() {
                        }.getType());
                        dataBeen1 = bean.getListResponses1();
                        name1.setText(dataBeen1.get(0).getName());
                        name2.setText(dataBeen1.get(1).getName());
                        name3.setText(dataBeen1.get(3).getName());

                        news1.setText(dataBeen1.get(0).getNews());
                        news2.setText(dataBeen1.get(1).getNews());
                        news3.setText(dataBeen1.get(2).getNews());

                        rows1.setText(dataBeen1.get(0).getRose());
                        rows2.setText(dataBeen1.get(1).getRose());
                        rows3.setText(dataBeen1.get(2).getRose());

                        downs1.setText(dataBeen1.get(0).getDowns());
                        downs2.setText(dataBeen1.get(1).getDowns());
                        downs3.setText(dataBeen1.get(2).getDowns());


                    }
                });

            }
        };
        new Thread(runnable).start();
    }


}
