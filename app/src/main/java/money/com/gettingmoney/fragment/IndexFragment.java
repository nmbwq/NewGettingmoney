package money.com.gettingmoney.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.Indexdetails_act;
import money.com.gettingmoney.adapter.MyAdIndeQt;
import money.com.gettingmoney.adapter.MyAdIndeSz;
import money.com.gettingmoney.adapter.MyAdIndesport;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bean.IndeXBean;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.hangqing.indexuti;
import money.com.gettingmoney.weiget.LoadingDialog;

public class IndexFragment extends BaseFragment implements View.OnClickListener {

    /**
     * 指数
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    private View view;
    RecyclerView indexlistview1, indexlistview2, indexlistview3;
    LinearLayout line1, line2, line3;
    private boolean vb1 = true;// true的状态是没有被点击的--上海
    private boolean vb2 = true;// true的状态是没有被点击的--深圳
    private boolean vb3 = true;// true的状态是没有被点击的--其他
    IndeXBean bean;
    private MyAdIndesport adpReport;
    private LoadingDialog dialog;
    TextView more1, more2, more3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index, null);
        indexlistview1 = (RecyclerView) view.findViewById(R.id.el_list1);
        indexlistview2 = (RecyclerView) view.findViewById(R.id.el_list2);
        indexlistview3 = (RecyclerView) view.findViewById(R.id.el_list3);
        line1 = (LinearLayout) view.findViewById(R.id.line1);
        line2 = (LinearLayout) view.findViewById(R.id.line2);
        line3 = (LinearLayout) view.findViewById(R.id.line3);

        more1 = (TextView) view.findViewById(R.id.more1);
        more2 = (TextView) view.findViewById(R.id.more2);
        more3 = (TextView) view.findViewById(R.id.more3);

        more1.setOnClickListener(this);
        more2.setOnClickListener(this);
        more3.setOnClickListener(this);

        line1.setOnClickListener(this);
        line2.setOnClickListener(this);
        line3.setOnClickListener(this);

        dialog = new LoadingDialog(getContext(), "正在加载");

        new Thread(runnable).start();
        initWindow();
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line1:

                if (vb1) {
                    indexlistview1.setVisibility(View.GONE);
                    vb1 = false;
                } else {
                    indexlistview1.setVisibility(View.VISIBLE);
                    vb1 = true;
                }
                break;
            case R.id.line2:

                if (vb2) {
                    indexlistview2.setVisibility(View.VISIBLE);
                    vb2 = false;
                } else {
                    indexlistview2.setVisibility(View.GONE);
                    vb2 = true;
                }
                break;
            case R.id.line3:
                if (vb3) {
                    indexlistview3.setVisibility(View.VISIBLE);
                    vb3 = false;
                } else {
                    indexlistview3.setVisibility(View.GONE);
                    vb3 = true;
                }
                break;


            case R.id.more1:
                Toast.makeText(getContext(),"此功能暂未开放",Toast.LENGTH_SHORT).show();
                break;
            case R.id.more2:
                Toast.makeText(getContext(),"此功能暂未开放",Toast.LENGTH_SHORT).show();
                break;
            case R.id.more3:
                Toast.makeText(getContext(),"此功能暂未开放",Toast.LENGTH_SHORT).show();
                break;


        }

    }

    @Override
    public void requestInit() {

    }


    //访问网络并且解析指数列表的数据

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            indexuti indexuti = new indexuti();

            indexuti.indexlist(dialog, 1, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                   // dialog.show();
                    //添加头部==
                    bean = new Gson().fromJson(result, new TypeToken<IndeXBean>() {
                    }.getType());
                    List<IndeXBean.ListResponses1Bean> dataBeen1 = bean.getListResponses1();
                    List<IndeXBean.ListResponses2Bean> dataBeen2 = bean.getListResponses2();
                    List<IndeXBean.ListResponses3Bean> dataBeen3 = bean.getListResponses3();


                    indexlistview1.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                    indexlistview2.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                    indexlistview3.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));


                    adpReport = new MyAdIndesport(dataBeen1);//上海
                    MyAdIndeSz adIndeSz = new MyAdIndeSz(dataBeen2);//深圳
                    MyAdIndeQt adIndeQt = new MyAdIndeQt(dataBeen3);//其他
                    dialog.close();
                    indexlistview1.setAdapter(adpReport);
                    indexlistview2.setAdapter(adIndeSz);
                    indexlistview3.setAdapter(adIndeQt);
                    adpReport.setOnItemClickListener(new MyAdIndesport.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, String stockId) {
                            Intent intent = new Intent(getContext(), Indexdetails_act.class);
                            intent.putExtra("stockId", stockId);
                            startActivity(intent);
                        }
                    });

                    adIndeSz.setOnItemClickListener(new MyAdIndeSz.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position,String stockId) {
                            Intent intent = new Intent(getContext(), Indexdetails_act.class);
                            intent.putExtra("stockId", stockId);
                            startActivity(intent);
                        }
                    });

                    adIndeQt.setOnItemClickListener(new MyAdIndeQt.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position,String stockId) {
                            Intent intent = new Intent(getContext(), Indexdetails_act.class);
                            intent.putExtra("stockId", stockId);
                            startActivity(intent);
                        }
                    });


                }
            });

        }
    };


}
