package money.com.gettingmoney.webutil.simulated_trading;

import money.com.gettingmoney.bean.OrderBeans;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/4/7.
 */
public interface simulatrading {
    //模拟股票交易的搜索列表
    void searchlist(LoadingDialog dialog, String userNumber,String stockId, String stockName,String pageSize,String currentPage,MyXutils.XCallBack callBack);
    //模拟股票交易的买入卖出
    void sell_buy(LoadingDialog dialog, String userNumber,String stockId,MyXutils.XCallBack callBack);
    //查询用户账户
    void findUserWallet(String userNumber,MyXutils.XCallBack callBack);
    //添加股票交易记录
    void addDeal(LoadingDialog dialog,String userNumber,OrderBeans beans,MyXutils.XCallBack callBack);
    //查询股票交易记录
    void findDeal(LoadingDialog dialog,String userNumber,String startTime,String endTime,int pageSize,int currentPage,int state,MyXutils.XCallBack callBack);
    //撤单的接口
    void cancelDeal(LoadingDialog dialog,String userNumber,String dealId,MyXutils.XCallBack callBack);
     //查询热门搜索股票
    void findHostHistory(LoadingDialog dialog,String userNumber,int pageSize,int currentPage,MyXutils.XCallBack callBack);


}
