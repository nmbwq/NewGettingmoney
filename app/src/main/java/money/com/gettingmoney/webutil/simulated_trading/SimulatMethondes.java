package money.com.gettingmoney.webutil.simulated_trading;

import java.util.HashMap;
import java.util.Map;

import money.com.gettingmoney.bean.OrderBeans;
import money.com.gettingmoney.util.JsonUitl;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/4/7.
 */
public class SimulatMethondes implements simulatrading {
    //模拟股票交易的搜索列表

    /*
    *userNumber  账户的token值
    *stockId    股票id
    * stockName  股票名称
    * pageSize  每页显示条数
    * currentPage 当前页
    * */

    private String HOST = MyAppApiConfig.HOST_URL;

    @Override
    public void searchlist(LoadingDialog dialog, String userNumber, String stockId, String stockName, String pageSize, String currentPage, MyXutils.XCallBack callBack) {
        String url = HOST + "deal/getStock";
        Map<String, Object> map = new HashMap<>();

        map.put("userNumber", userNumber);
        map.put("stockId", stockId);
        map.put("stockName", stockName);
        map.put("pageSize", pageSize);
        map.put("currentPage", currentPage);

        MyXutils.getInstance().post(dialog, url, map, callBack);

    }

    @Override
    public void sell_buy(LoadingDialog dialog, String userNumber, String stockId, MyXutils.XCallBack callBack) {
        String url = HOST + "deal/getStockById";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("stockId", stockId);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }

    @Override
    public void findUserWallet(String userNumber, MyXutils.XCallBack callBack) {
        String url = HOST + "deal/findUserWallet";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        MyXutils.getInstance().post(null, url, map, callBack);
    }

    /*
    * userNumber:token
    * order--上传的股票的信息
    * */
    @Override
    public void addDeal(LoadingDialog dialog, String userNumber, OrderBeans beans, MyXutils.XCallBack callBack) {
        String url = HOST + "deal/addDeal";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("order", JsonUitl.objectToString(beans));
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }
    /* 查询股票交易记录
     *userNumber:token
     * startTime:开始时间
     * endTime：介绍时间
     * pageSize每页的显示多少个
     * currentPage：当前是第几页
     * state：查询的状态， 0是为成交，1是成交，2是已经撤销
     **/

    @Override
    public void findDeal(LoadingDialog dialog, String userNumber, String startTime, String endTime, int pageSize, int currentPage, int state, MyXutils.XCallBack callBack) {

        String url = HOST + "deal/findDeal";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("pageSize", pageSize);
        map.put("currentPage", currentPage);
        map.put("state", state);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }

    /*
    * 撤单的
    * */
    @Override
    public void cancelDeal(LoadingDialog dialog, String userNumber, String dealId, MyXutils.XCallBack callBack) {
        String url = HOST + "deal/cancelDeal";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("dealId", dealId);
        MyXutils.getInstance().post(null, url, map, callBack);
    }

    @Override
    public void findHostHistory(LoadingDialog dialog, String userNumber, int pageSize, int currentPage, MyXutils.XCallBack callBack) {
        String url = HOST + "deal/findHistory";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("pageSize", pageSize);
        map.put("currentPage", currentPage);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }
}
