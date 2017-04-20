package money.com.gettingmoney.webutil.Kline;

import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public interface KilnUtil {
     void addStockHistory(String stockId, int type,String time, MyXutils.XCallBack callBack);

}
