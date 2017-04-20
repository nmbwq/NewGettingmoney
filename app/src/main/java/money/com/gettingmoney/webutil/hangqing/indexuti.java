package money.com.gettingmoney.webutil.hangqing;

import java.util.HashMap;
import java.util.Map;

import money.com.gettingmoney.bean.UserStocks;
import money.com.gettingmoney.bean.UserStockss;
import money.com.gettingmoney.util.JsonUitl;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/3/29.
 */
public class indexuti implements indexUtil {
    //写网络方法--里面传递的参数  userNumber==token
    private String HOST = MyAppApiConfig.HOST_URL;

    @Override
    public void indexlist(LoadingDialog dialog, int type, MyXutils.XCallBack callBack) {
        String url = HOST + "index/list";
        HashMap<String, Object> map = new HashMap<>();
        map.put("type", type);
        MyXutils.getInstance().post(null, url, map, callBack);
    }


    /*
    * currentPage:页号
    * pageSize：每页大小
    * orderStr：排序字段 1,(最新价)/2,(涨跌幅)/3,(涨跌额)/4,(成交量)
    * orderType：排序类型 0,(降序)/1,(升序)
    * */
    @Override
    public void hushen(LoadingDialog dialog, int currentPage, int pageSize, int orderStr, int orderType, MyXutils.XCallBack callBack) {

        String url = HOST + "stock/stockList";
        Map<String, Object> map = new HashMap<>();
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("orderStr", orderStr);
        map.put("orderType", orderType);

        MyXutils.getInstance().post(null, url, map, callBack);
    }

    //港股
    @Override
    public void ganggu(MyXutils.XCallBack callBack) {
        String url = HOST + "hongKongStock/list";
        MyXutils.getInstance().post(null, url, null, callBack);
    }

    //港股list
    @Override
    public void ganggulist(LoadingDialog dialog,int page, MyXutils.XCallBack callBack) {
        String url = HOST + "hongKongStock/getMore";
        Map<String, Object> map = new HashMap<>();

        map.put("page", page);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }

    @Override
    public void bankuanlist(LoadingDialog dialog, int type, int riseAndFall, int marketValue, int turnover, int rise, int fall, int ledRiseAndFall, MyXutils.XCallBack callBack) {
        String url = HOST + "domainStock/findAllDomainStock";
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("riseAndFall", riseAndFall);
        map.put("marketValue", marketValue);
        map.put("turnover", turnover);
        map.put("rise", rise);
        map.put("fall", fall);
        map.put("ledRiseAndFall", ledRiseAndFall);
        MyXutils.getInstance().post(null, url, map, callBack);

    }

    @Override
    public void addStockDetail(String stockId, MyXutils.XCallBack callBack) {
        String url = HOST + "stock/addStockDetail";
        Map<String, Object> map = new HashMap<>();
        map.put("stockId", stockId);
        MyXutils.getInstance().post(null, url, map, callBack);
    }

    @Override
    public void createStock(LoadingDialog dialog, String userNumber, UserStocks userStocks, MyXutils.XCallBack xCallBack) {
        String url = HOST + "userStock/createStock";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("userStock", JsonUitl.objectToString(userStocks));
        MyXutils.getInstance().post(dialog, url, map, xCallBack);
    }

    @Override
    public void findUserStock(LoadingDialog dialog, String userNumber, int pageSize, int currentPage, MyXutils.XCallBack xCallBack) {
        String url = HOST + "userStock/findUserStock";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("pageSize", pageSize);
        map.put("currentPage", currentPage);
        MyXutils.getInstance().post(dialog, url, map, xCallBack);
    }

    @Override
    public void deleteUserStock(String userNumber, UserStockss userStocks, MyXutils.XCallBack xCallBack) {
        String url = HOST + "userStock/deleteUserStock";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("userStock", JsonUitl.objectToString(userStocks));
        MyXutils.getInstance().post(null, url, map, xCallBack);
    }


    @Override
    public void addStockHistory(String stockId, int type, String time, MyXutils.XCallBack callBack) {
        String url = HOST + "stock/addStockHistory";
        Map<String, Object> map = new HashMap<>();
        map.put("stockId", stockId);
        map.put("type", type);
        map.put("time", time);
        MyXutils.getInstance().post(null, url, map, callBack);
    }


    @Override
    public void domainStockHistory(LoadingDialog dialog, String domainCode, MyXutils.XCallBack callBack) {
        String url = HOST + "domainStock/getHistory";
        Map<String, Object> map = new HashMap<>();
        map.put("domainCode", domainCode);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }

    //板块日K线图
    @Override
    public void domainStockgetByDay(LoadingDialog dialog, String domainCode, MyXutils.XCallBack callBack) {
        String url = HOST + "domainStock/getByDay";
        Map<String, Object> map = new HashMap<>();
        map.put("domainCode", domainCode);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }

    //板块周K线图
    @Override
    public void domainStockgetByWeek(LoadingDialog dialog, String domainCode, MyXutils.XCallBack callBack) {

        String url = HOST + "domainStock/getByWeek";
        Map<String, Object> map = new HashMap<>();
        map.put("domainCode", domainCode);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }

    //板块月K线图
    @Override
    public void domainStockgetByMonth(LoadingDialog dialog, String domainCode, MyXutils.XCallBack callBack) {
        String url = HOST + "domainStock/getByMonth";
        Map<String, Object> map = new HashMap<>();
        map.put("domainCode", domainCode);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }

    //指数分时数据
    @Override
    public void indexbranchTime(LoadingDialog dialog, String stockId, String type, MyXutils.XCallBack callBack) {
        String url = HOST + "index/branchTime";
        Map<String, Object> map = new HashMap<>();
        map.put("stockId", stockId);
        map.put("type", type);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }

    @Override
    public void indexbranchTimeGang(LoadingDialog dialog, String stockId, String type, int hongType, MyXutils.XCallBack callBack) {
        String url = HOST + "index/branchTime";
        Map<String, Object> map = new HashMap<>();
        map.put("stockId", stockId);
        map.put("type", type);
        map.put("hongType", hongType);
        MyXutils.getInstance().post(dialog, url, map, callBack);
    }

    @Override
    public void indexdetail(String stockId, MyXutils.XCallBack callBack) {
        String url = HOST + "index/detail";
        Map<String, Object> map = new HashMap<>();
        map.put("stockId", stockId);
        MyXutils.getInstance().post(null, url, map, callBack);
    }


    //港股详情界面
    @Override
    public void hongKongStockdetials(LoadingDialog dialog, String stockId, MyXutils.XCallBack xCallBack) {
        String url = HOST + "hongKongStock/detail";
        Map<String, Object> map = new HashMap<>();
        map.put("stockId", stockId);
        MyXutils.getInstance().post(dialog, url, map, xCallBack);
    }
}
