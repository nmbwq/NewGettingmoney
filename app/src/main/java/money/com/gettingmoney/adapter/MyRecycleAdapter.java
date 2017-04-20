package money.com.gettingmoney.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.Sell_buyBean;

/**
 * Created by FJX on 2017/4/9.
 */
public class MyRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<Sell_buyBean.FiveSaleBean> sellbean ;
    private List<Sell_buyBean.FiveBuyBean>buyBean;


    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    public MyRecycleAdapter(Context context,  List<Sell_buyBean.FiveSaleBean> beans) {
        this.sellbean = beans;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.item_simitem, parent, false));
        } else {
            return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.item_simitem, parent, false));
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {
          //  ((Item1ViewHolder) holder).mTextView.setText(titles[position]);
        } else if (holder instanceof Item2ViewHolder) {
           // ((Item2ViewHolder) holder).mTextView.setText(titles[position]);
        }
    }

    public int getItemViewType(int position) {
        if(position<5){
                return ITEM_TYPE.ITEM1.ordinal();}
        return ITEM_TYPE.ITEM2.ordinal();
    }

    public int getItemCount() {
        return sellbean.size();
    }

    //头布局 的ViewHolder
    public static class Item1ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public Item1ViewHolder(View itemView) {
            super(itemView);
          //  mTextView = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    //普通item 的ViewHolder
    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public Item2ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}