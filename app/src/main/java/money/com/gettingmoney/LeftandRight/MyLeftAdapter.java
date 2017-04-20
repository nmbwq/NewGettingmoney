package money.com.gettingmoney.LeftandRight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.GangguListBean;
import money.com.gettingmoney.contast.CosListAdapter;

public class MyLeftAdapter extends CosListAdapter<GangguListBean.StockListBean> {

    private Context context;
    private List<GangguListBean.StockListBean> list;

    public MyLeftAdapter(Context context, List<GangguListBean.StockListBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold;
        if (convertView == null) {
            hold = new ViewHold();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_left_item, null);
            hold.textView = (TextView) convertView.findViewById(R.id.left_container_textview0);
            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }
        if (list.get(position).getStockName() != null) {
            hold.textView.setText(list.get(position).getStockName());
        }
        return convertView;
    }

    static class ViewHold {
        TextView textView;
    }

}
