package money.com.gettingmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.Sell_buyBean;
import money.com.gettingmoney.contast.CosListAdapter;

/**
 * Created by Administrator on 2017/4/7.
 */
public class SimtradiListSellAdp extends CosListAdapter<Sell_buyBean.FiveSaleBean> {

    public SimtradiListSellAdp(Context context, List<Sell_buyBean.FiveSaleBean> beanList) {
        super(context, beanList);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        // 如果视图不存在就创建视图
        if (null == view) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_simitem, parent, false);
        }
        SimtradiListSellAdp.Viewholder holder = (SimtradiListSellAdp.Viewholder) view.getTag();// 第二步走
        if (null == holder) {
            holder = new SimtradiListSellAdp.Viewholder(view);
            view.setTag(holder);
        }


        final Sell_buyBean.FiveSaleBean mItemDataList = getList().get(position);
        if (mItemDataList.getAmount() != null) {
            holder.offer.setText(mItemDataList.getAmount());
        }
        if (mItemDataList.getCountVol() != null) {
            holder.number.setText(mItemDataList.getAmount());
        }

        int a=position+1;
        holder.sequence.setText("卖"+String.valueOf(a));



        return view;
    }

    class Viewholder {
        TextView offer, number,sequence;

        public Viewholder(View view) {
            offer = (TextView) view.findViewById(R.id.offer);
            number = (TextView) view.findViewById(R.id.number);
            sequence=(TextView)view.findViewById(R.id.sequence);


        }
    }
}
