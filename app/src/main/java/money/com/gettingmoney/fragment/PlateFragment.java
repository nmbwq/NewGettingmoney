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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.adapter.MyAdpReport;
import money.com.gettingmoney.bai.activity.Platedetials_KlineAct;
import money.com.gettingmoney.bean.Beans;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.hangqing.indexUtil;
import money.com.gettingmoney.webutil.hangqing.indexuti;
import money.com.gettingmoney.weiget.LoadingDialog;

/*
* 板块界面
* */
public class PlateFragment extends Fragment {


    List<Beans.ResultBean> beanses = new ArrayList<>();
    private MyAdpReport adpReport;
    private LoadingDialog dialog;
    ExpandableListView mView;

    Beans beans;

    /**
     * 板块
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plate, null);
        mView = (ExpandableListView) view.findViewById(R.id.el_list);

        dialog = new LoadingDialog(getContext(), "正在加载");
        dialog.show();
        init();

        return view;
    }

    //访问网络
    void init() {

        new Thread(runnable).start();


    }


    //访问网络获取板块列表的数据
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            indexUtil indexUtil = new indexuti();
            indexUtil.bankuanlist(dialog, 1, 1, 0, 0, 0, 0, 0, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    dialog.close();
                    beans = new Gson().fromJson(result, new TypeToken<Beans>() {
                    }.getType());
                    beanses = beans.getResult();
                    //把解析beanses的值传递到expensd里去
                    mView.setAdapter(new Oplate(getContext(), beanses, beans));



                }
            });

        }
    };

    //ExpendListview   板块
    class Oplate extends BaseExpandableListAdapter {
        Context mcontext;
        List<Beans.ResultBean> beanses1 = new ArrayList<>();
        Beans beans = new Beans();

        public Oplate(Context context, List<Beans.ResultBean> beanlist, Beans beans) {
            this.mcontext = context;
            this.beanses1 = beanlist;
            this.beans = beans;
        }

        //设置组视图的显示文字
        private String[] generalsTypes = new String[]{"概念板块"};
        //子视图显示文字
        private String[][] generals = new String[][]{
                {"夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修"},
                {"马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云"},
                {"吕蒙", "陆逊", "孙权", "周瑜", "孙尚香"}

        };

        //返回第一个布局的条数
        @Override
        public int getGroupCount() {
            return generalsTypes.length;
        }

        //返回的第二个布局的条数---暂时板块只有一个概念板块--故只有一个-我就选择了用第一个布局返回的条数长度
        @Override
        public int getChildrenCount(int groupPosition) {

            return generalsTypes.length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return generalsTypes[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return beanses1;
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
            TextView header = (TextView) view.findViewById(R.id.header);
            TextView more = (TextView) view.findViewById(R.id.more);
            header.setText(getGroup(groupPosition).toString());
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "此功能暂未开放", Toast.LENGTH_LONG).show();

                }
            });


            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_rycstragess, null);
            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);

            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

            adpReport = new MyAdpReport(beanses1);
            mRecyclerView.setAdapter(adpReport);


            adpReport.setOnItemClickListener(new MyAdpReport.OnItemClickListener() {
                @Override
                public void onItemClick(int position, String domainCode, String stockname) {
                    //传递股票id过去
                    Intent intent = new Intent(getContext(), Platedetials_KlineAct.class);
                    intent.putExtra("stockId", domainCode);
                    intent.putExtra("shock_name", stockname);
                    startActivity(intent);
                }
            });
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

}
