package money.com.gettingmoney.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.GangmeiDetialsAct;
import money.com.gettingmoney.adapter.GangmeiAdpReport;
import money.com.gettingmoney.bai.activity.Detials_KlineAct;
import money.com.gettingmoney.bean.GangguBeans;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.hangqing.indexuti;

public class GangmeiFragment extends Fragment implements View.OnClickListener {

    /**
     * 港美
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    private View view;
    private RelativeLayout mei_btn, gang_btn;
    private ImageView mei_img, gang_img;
    private int position = 0;
    RecyclerView mRecyclerView;
    GangmeiAdpReport adpReport;
    ExpandableListView mView;

    GangguBeans bean;
    List<GangguBeans.ResponseBean.HangSengBean> beanses = new ArrayList<>();
    List<GangguBeans.ResponseBean.StockListBean> beanList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gangmei, null);
        initView();
        initganggu();
        mView = (ExpandableListView) view.findViewById(R.id.el_list);


        return view;
    }

    private void initView() {
        mei_btn = (RelativeLayout) view.findViewById(R.id.mei_btn);
        gang_btn = (RelativeLayout) view.findViewById(R.id.gang_btn);
        mei_img = (ImageView) view.findViewById(R.id.mei_img);
        gang_img = (ImageView) view.findViewById(R.id.gang_img);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);

        mei_btn.setOnClickListener(this);
        gang_btn.setOnClickListener(this);
    }

    //港股
    void initganggu() {
        new Thread(runnable).start();

    }

    //美股
    void initmeigu() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mei_btn:
                if (position == 0) {
                    return;
                }
                mei_img.setVisibility(View.VISIBLE);
                gang_img.setVisibility(View.GONE);
                position = 0;
                break;

            case R.id.gang_btn:
                if (position == 1) {
                    return;
                }


                mei_img.setVisibility(View.GONE);
                gang_img.setVisibility(View.VISIBLE);
                position = 1;
                break;
        }
    }


    //访问网络并且解析港股列表的数据

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            indexuti indexuti = new indexuti();
            indexuti.ganggu(new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    //添加头部==
                    bean = new Gson().fromJson(result, new TypeToken<GangguBeans>() {
                    }.getType());

                    beanses = bean.getResponse().getHangSeng();
                    beanList = bean.getResponse().getStockList();




                    mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                    //recycleview的适配器
                    adpReport = new GangmeiAdpReport(beanses);
                    mRecyclerView.setAdapter(adpReport);

                    adpReport.setOnItemClickListener(new GangmeiAdpReport.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, String stocked, String stockname) {

                            Intent intent3 = new Intent(getContext(), Detials_KlineAct.class);
                            intent3.putExtra("stockId", stocked);
                            intent3.putExtra("shock_name", stockname);
                            intent3.putExtra("type", "港股指数");

                            getContext().startActivity(intent3);
                        }
                    });

                    //expendslistview适配器
                    mView.setAdapter(new Gangmei(getContext(), beanList));


                }
            });

        }
    };


    //下面的ExpandableList的适配器
    class Gangmei extends BaseExpandableListAdapter {

        Context context;
        //子视图显示文字
        List<GangguBeans.ResponseBean.StockListBean> beans = new ArrayList<>();

        public Gangmei(Context mcontext, List<GangguBeans.ResponseBean.StockListBean> beanList) {
            this.context = mcontext;
            this.beans = beanList;

        }

        //设置组视图的图片
        // int[] logos = new int[] { R.drawable.wei, R.drawable.shu,R.drawable.wu};
        // 设置组视图的显示文字
        private String[] generalsTypes = new String[]{"概念板块"};


        @Override
        public int getGroupCount() {
            return generalsTypes.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return beanList.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return generalsTypes[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return beanList;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_selfchosefg_header, null);

            TextView textView1 = (TextView) view.findViewById(R.id.header);
            TextView more = (TextView) view.findViewById(R.id.more);
            textView1.setText(getGroup(groupPosition).toString());
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContext().startActivity(new Intent(getContext(), GangmeiDetialsAct.class));
                }
            });
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_selefchose_bottom, null);
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView news = (TextView) view.findViewById(R.id.news);
            TextView down = (TextView) view.findViewById(R.id.down);
            TextView rose = (TextView) view.findViewById(R.id.rose);
            if (beanList.get(childPosition).getStockName() != null) {
                name.setText(beanList.get(childPosition).getStockName());
            }
            if (beanList.get(childPosition).getNow() != null) {
                news.setText(beanList.get(childPosition).getNow());
            }
            if (beanList.get(childPosition).getDiffer() != null) {
                down.setText(beanList.get(childPosition).getDiffer());
            }
            if (beanList.get(childPosition).getDifferRange() != null) {
                rose.setText(beanList.get(childPosition).getDifferRange());
            }
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }
}

