package money.com.gettingmoney.adapter;

/**
 * Created by Administrator on 2017/3/30.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Mr.Ye on 2016/11/25.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter {

    protected Context mContext;
    protected List<T> mList;

    public BaseRecyclerAdapter(Context mContext, List<T> mList) {
        this.mContext = mContext;
        this.mList = mList;
        Log.i("baeadfsdfsnda", mList.toString());

    }

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {


        return mList.size();
    }

}
