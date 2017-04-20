package money.com.gettingmoney.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.IndeXBean;

/**
 * Created by Administrator on 2017/4/6.
 * 指数-上海适配器
 */
public class MyAdIndesport extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<IndeXBean.ListResponses1Bean> dataBeen = new ArrayList<>();

    public MyAdIndesport(List<IndeXBean.ListResponses1Bean> dataBeen) {
        this.dataBeen = dataBeen;
        Log.i("指数是否传递值过来", dataBeen.toString());
    }

    public OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item, parent, false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        if (viewHolder instanceof Holder) {

            if (dataBeen.get(position).getName() != null) {
                ((Holder) viewHolder).stock_name.setText(dataBeen.get(position).getName());
            }

            if (dataBeen.get(position).getNews() != null) {
                ((Holder) viewHolder).news.setText(dataBeen.get(position).getNews());

            }
            if (dataBeen.get(position).getRose() != null) {
                ((Holder) viewHolder).rows.setText(dataBeen.get(position).getRose());

            }

            if (dataBeen.get(position).getDowns() != null) {
                ((Holder) viewHolder).downs.setText(dataBeen.get(position).getDowns());

            }


            if (mListener == null) return;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(position,dataBeen.get(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return dataBeen.size();

    }

    class Holder extends RecyclerView.ViewHolder {

        TextView stock_name, news, rows, downs;

        public Holder(View itemView) {
            super(itemView);

            stock_name = (TextView) itemView.findViewById(R.id.stock_name);
            news = (TextView) itemView.findViewById(R.id.news);

            rows = (TextView) itemView.findViewById(R.id.rows);

            downs = (TextView) itemView.findViewById(R.id.downs);


        }
    }

 public    interface OnItemClickListener {
        void onItemClick(int position,String stockId);
    }


}
