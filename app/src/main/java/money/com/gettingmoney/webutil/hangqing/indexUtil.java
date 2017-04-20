package money.com.gettingmoney.webutil.hangqing;

import money.com.gettingmoney.bean.UserStocks;
import money.com.gettingmoney.bean.UserStockss;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/3/29.
 * 行情
 */
public interface indexUtil {
    //指数的列表的接口
    void indexlist(LoadingDialog dialog, int type, MyXutils.XCallBack callBack);

    //沪深的接口
    void hushen(LoadingDialog dialog, int currentPage, int pageSize, int orderStr, int orderType, MyXutils.XCallBack callBack);

    //港股的列表
    void ganggu(MyXutils.XCallBack callBack);

    //港股详情列表界面
    void ganggulist(LoadingDialog dialog,int page, MyXutils.XCallBack callBack);

    //板块列表
    void bankuanlist(LoadingDialog dialog, int type, int riseAndFall, int marketValue, int turnover, int rise, int fall, int ledRiseAndFall, MyXutils.XCallBack callBack);

    //股票详情
    void addStockDetail(String stockId, MyXutils.XCallBack callBack);

    //添加用户关注的股票
    void createStock(LoadingDialog dialog, String userNumber, UserStocks userStocks, MyXutils.XCallBack xCallBack);
    //查询用户关注的股票
    void findUserStock(LoadingDialog dialog, String userNumber, int pageSize, int currentPage, MyXutils.XCallBack xCallBack);
    //删除自选股里的股票
    void deleteUserStock(String userNumber, UserStockss userStocks, MyXutils.XCallBack xCallBack);
    //沪深股票K线图数据
    void addStockHistory(String stockId, int type, String time, MyXutils.XCallBack callBack);
     //分时图
    void domainStockHistory(LoadingDialog dialog,String domainCode, MyXutils.XCallBack callBack);
    //板块日K线图
    void domainStockgetByDay(LoadingDialog dialog,String domainCode, MyXutils.XCallBack callBack);
    //板块周K线图
    void domainStockgetByWeek(LoadingDialog dialog,String domainCode, MyXutils.XCallBack callBack);
    //板块月K线图
    void domainStockgetByMonth(LoadingDialog dialog,String domainCode, MyXutils.XCallBack callBack);
    //分时数据
    void indexbranchTime(LoadingDialog dialog,String stockId, String type, MyXutils.XCallBack callBack);
    //港股指数分时图访问网络
    void indexbranchTimeGang(LoadingDialog dialog,String stockId, String type,int hongType, MyXutils.XCallBack callBack);
    //指数详情接口
    void indexdetail(String stockId,MyXutils.XCallBack callBack);

    //港股详情
    void hongKongStockdetials(LoadingDialog dialog,String stockId,MyXutils.XCallBack xCallBack);
}
