/*
package money.com.gettingmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.IndeXBean;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.weiget.LoadingDialog;
import money.com.gettingmoney.weiget.MyGrideview;

*/
/**
 * Created by Administrator on 2017/4/5.
 * 自选适配器
 *//*

public class OptionalAdapter extends BaseAdapter {
    private Context context;
    private List<IndeXBean.ListResponsesBean> list;
    private OptionalAdapter adapter;
    private String imgurl = MyAppApiConfig.IMGHOST_URL;
    private LoadingDialog dialog;

    public OptionalAdapter(Context context, List<IndeXBean.ListResponsesBean> list) {
        super();
        this.context = context;
        this.list = list;
        this.adapter = this;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_index, null);
            holder.stock_grideview = (MyGrideview) convertView.findViewById(R.id.stock_grideview);
            holder.stock_groupname = (TextView) convertView.findViewById(R.id.stock_groupname);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (position == 0) {
            holder.stock_groupname.setText("上海");
        } else if (position == 1) {
            holder.stock_groupname.setText("深圳");
        } else if (position == 2) {
            holder.stock_groupname.setText("其他");
        }
        else {
            holder.stock_groupname.setVisibility(View.GONE);
            holder.stock_grideview.setVisibility(View.GONE);
        }

        StockAdapter adapter = new StockAdapter(context, list);
        holder.stock_grideview.setAdapter(adapter);

        return convertView;

    }

    class Holder {
        MyGrideview stock_grideview;
        TextView stock_groupname;
    }package
}
*/
