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

public class MyRightAdapter extends CosListAdapter {
    private Context context;
    List<GangguListBean.StockListBean> list;

    public MyRightAdapter(Context context, List<GangguListBean.StockListBean> models) {
        super();
        this.context = context;
        this.list = models;
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
        ViewHold viewHold;
        if (convertView == null) {
            viewHold = new ViewHold();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_right_item, null);
            viewHold.textView0 = (TextView) convertView.findViewById(R.id.right_item_textview0);
            viewHold.textView1 = (TextView) convertView.findViewById(R.id.right_item_textview1);
            viewHold.textView2 = (TextView) convertView.findViewById(R.id.right_item_textview2);

            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        if (list.get(position).getNow() != null) {
            viewHold.textView0.setText(list.get(position).getNow());
        }
        if (list.get(position).getDifferRange() != null) {
            viewHold.textView1.setText(list.get(position).getDifferRange());
        }
        if (list.get(position).getDiffer() != null) {
            viewHold.textView2.setText(list.get(position).getDiffer());
        }


        return convertView;
    }

    static class ViewHold {

        TextView textView0, textView1, textView2;
    }

}
