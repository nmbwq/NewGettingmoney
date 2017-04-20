package money.com.gettingmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.ShengzhenBean;
import money.com.gettingmoney.contast.CosListAdapter;

/**
 * Created by Administrator on 2017/3/31.
 * 沪深-点击item详情的适配器
 */
public class HushendetialsAdapter extends CosListAdapter<ShengzhenBean.ResultListBean> {

    public HushendetialsAdapter(Context context, List<ShengzhenBean.ResultListBean> beanList) {
        super(context, beanList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        // 如果视图不存在就创建视图
        if (null == view) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_hushen, parent, false);
        }
        HushendetialsAdapter.Viewholder holder = (HushendetialsAdapter.Viewholder) view.getTag();// 第二步走
        if (null == holder) {
            holder = new HushendetialsAdapter.Viewholder(view);
            view.setTag(holder);
        }


        final ShengzhenBean.ResultListBean mItemDataList = getList().get(position);

        if (!mItemDataList.getDifferRange().isEmpty()) {
            holder.rose.setText(mItemDataList.getDifferRange());
        }

        if (!mItemDataList.getDiffer().isEmpty()) {
            holder.down.setText(mItemDataList.getDiffer());

        }
        if (!mItemDataList.getStockName().isEmpty()) {
            holder.name.setText(mItemDataList.getStockName());

        }
        if (!mItemDataList.getNow().isEmpty()) {
            holder.news.setText(mItemDataList.getNow());
        }

        /*这里没法把string类型的转换成int类型的，因为这两个string类型里的值不仅仅是数字还有这个字符：“-”
        * 暂时还没有办法把跌的（负数）文字变成绿色、
        * 暂时还没有办法把涨的（正数）文字变成红色、
        * */
   /*     if (Integer.valueOf(mItemDataList.getDifferRange()).intValue()<0||Integer.valueOf(mItemDataList.getDiffer()).intValue()<0){
            holder.name.setTextColor(getContext().getResources().getColor(R.color._069043));
            holder.news.setTextColor(getContext().getResources().getColor(R.color._069043));
            holder.rose.setTextColor(getContext().getResources().getColor(R.color._069043));
            holder.down.setTextColor(getContext().getResources().getColor(R.color._069043));
        }else {
            holder.name.setTextColor(getContext().getResources().getColor(R.color.text_red));
            holder.news.setTextColor(getContext().getResources().getColor(R.color.text_red));
            holder.rose.setTextColor(getContext().getResources().getColor(R.color.text_red));
            holder.down.setTextColor(getContext().getResources().getColor(R.color.text_red));
        }*/


        return view;
    }

    class Viewholder {
        TextView name, news, rose, down;

        public Viewholder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            news = (TextView) view.findViewById(R.id.news);
            rose = (TextView) view.findViewById(R.id.rose);
            down = (TextView) view.findViewById(R.id.down);


        }
    }
}