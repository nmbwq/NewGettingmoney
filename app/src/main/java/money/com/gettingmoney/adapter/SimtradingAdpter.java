package money.com.gettingmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.SimulatBean;
import money.com.gettingmoney.contast.CosListAdapter;

/**
 * Created by Administrator on 2017/4/7.
 * 模拟交易的listview
 */
public class SimtradingAdpter extends CosListAdapter<SimulatBean.ResultBean> {

    public SimtradingAdpter(Context context, List<SimulatBean.ResultBean> beanList) {
        super(context, beanList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        // 如果视图不存在就创建视图
        if (null == view) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_simtrading, parent, false);
        }
        SimtradingAdpter.Viewholder holder = (SimtradingAdpter.Viewholder) view.getTag();// 第二步走
        if (null == holder) {
            holder = new SimtradingAdpter.Viewholder(view);
            view.setTag(holder);
        }


        final SimulatBean.ResultBean mItemDataList = getList().get(position);
        if (mItemDataList.getStockId() != null) {
            holder.shares_id.setText(mItemDataList.getStockId());
        }
        if (mItemDataList.getStockName() != null) {
            holder.shares_name.setText(mItemDataList.getStockName());
        }


        return view;
    }

    class Viewholder {
        TextView shares_name, shares_id;

        public Viewholder(View view) {
            shares_name = (TextView) view.findViewById(R.id.shares_name);
            shares_id = (TextView) view.findViewById(R.id.shares_id);


        }
    }
}