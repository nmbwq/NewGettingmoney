package money.com.gettingmoney.webutil.news;

import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/2/23.
 */
public interface INewsUtil {

    public void newslist(LoadingDialog dialog,int type,int currentPage,int pageSize,String userNumber,MyXutils.XCallBack callBack);
    public void commentList(int newsId,int currentPage,int pageSize,String userNumber,MyXutils.XCallBack callBack);
    public void addComment(LoadingDialog dialog,String userNumber,int newsId,String comment,MyXutils.XCallBack callBack);
    public void delComment(LoadingDialog dialog,String userNumber,int id,MyXutils.XCallBack callBack);
    public void getNewsData(int pageSize,int currentPage,String userNumber,int newsId,MyXutils.XCallBack callBack);
    public void addCollection(LoadingDialog dialog,String userNumber,int newsId,MyXutils.XCallBack callBack);
    public void delCollection(LoadingDialog dialog,String userNumber,int id,MyXutils.XCallBack callBack);
    public void TextSocket(String msg,String  userId,MyXutils.XCallBack callBack);

}
