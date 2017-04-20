package money.com.gettingmoney.contast;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 基础的baseadapter，封装更新list的方法
 * 
 * @author fjx
 * @param <T>
 * 
 */
public class CosListAdapter<T> extends BaseAdapter {
	protected List<T> mList;
	protected Context mContext;

	public void add(Object data){
		try{
			mList.add((T) data);
			notifyDataSetChanged();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}

	public void clear(){
		mList.clear();
		notifyDataSetChanged();
	}

	public List<T> getList() {
		return mList;
	}

	public void setList(List<T> mList) {
		this.mList = mList;
	}

	public Context getContext() {
		return mContext;
	}

	public void setContext(Context mContext) {
		this.mContext = mContext;
	}


	public CosListAdapter(Context context, List<T> list) {
		mList =  list;
		mContext = context;
	}
	public CosListAdapter(){
		super();
	};

	/**
	 * 更新数据
	 */
	public void upData(List<T> list) {
		this.mList = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		try {
			return mList.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}
}
