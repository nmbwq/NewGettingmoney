package money.com.gettingmoney.webutil.Kline;

import java.util.HashMap;
import java.util.Map;

import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public class kilneUtil implements KilnUtil {

    //写网络方法--里面传递的参数  userNumber==token
    private String HOST = MyAppApiConfig.HOST_URL;
    /**
     * K线图
     * @param stockId  股票id
     * @param type  类型 0,(日)/1,(分时)/2,(周)/3,(月)
     * @param time 日、周、月 类型 可通过传递 年份获取某年的数据。分时直接传 last
     * @param callBack
     */
    @Override
    public void addStockHistory(String stockId, int type,  String time,MyXutils.XCallBack callBack) {
        String url = HOST+"stock/addStockHistory";
        Map<String, Object> map = new HashMap<>();
        map.put("stockId", stockId);
        map.put("type", type);
        map.put("time",time);
        MyXutils.getInstance().post(null,url,map,callBack);
    }


}
