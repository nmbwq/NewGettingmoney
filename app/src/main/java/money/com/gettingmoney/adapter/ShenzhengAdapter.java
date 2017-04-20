package money.com.gettingmoney.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.activity.Detials_KlineAct;
import money.com.gettingmoney.bean.ShengzhenBean;
import money.com.gettingmoney.contast.CosListAdapter;

/**
 * Created by Administrator on 2017/3/31.
 */
public class ShenzhengAdapter extends CosListAdapter<ShengzhenBean.ResultListBean> {

    public ShenzhengAdapter(Context context, List<ShengzhenBean.ResultListBean> beanList) {
        super(context, beanList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        // 如果视图不存在就创建视图
        if (null == view) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_shengzhen, parent, false);
        }
        ShenzhengAdapter.Viewholder holder = (ShenzhengAdapter.Viewholder) view.getTag();// 第二步走
        if (null == holder) {
            holder = new ShenzhengAdapter.Viewholder(view);
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

     view.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //传递股票id过去
             Intent intent = new Intent(getContext(), Detials_KlineAct.class);
             intent.putExtra("stockId", mItemDataList.getStockId());
             intent.putExtra("shock_name",mItemDataList.getStockName());
             intent.putExtra("type","沪深指数");
             getContext().startActivity(intent);
         }
     });

        return view;
    }

    class Viewholder {
        TextView name, news, rose, down;
        LinearLayout line;


        public Viewholder(View view) {

            name = (TextView) view.findViewById(R.id.name);
            news = (TextView) view.findViewById(R.id.news);
            rose = (TextView) view.findViewById(R.id.rose);
            down = (TextView) view.findViewById(R.id.down);
            line = (LinearLayout) view.findViewById(R.id.line);


        }
    }
}