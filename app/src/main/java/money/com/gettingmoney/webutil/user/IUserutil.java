package money.com.gettingmoney.webutil.user;

import money.com.gettingmoney.bean.User;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/2/23.
 */
public interface IUserutil {

    void userSign(LoadingDialog dialog, User user, String validateCode, MyXutils.XCallBack callBack);

    void passReset(LoadingDialog dialog, User user, String validateCode, MyXutils.XCallBack callBack);

    void userLogin(LoadingDialog dialog, String code, String pwd, MyXutils.XCallBack callBack);

    void updateUserPwd(LoadingDialog dialog, String userNumber, String pwd, String newPwd, MyXutils.XCallBack callBack);

    void updateUser(LoadingDialog dialog, String userNumber, User user, MyXutils.XCallBack callBack);

    void feedback(LoadingDialog dialog, String userNumber, String content, MyXutils.XCallBack callBack);

    void findUserWallet(LoadingDialog dialog, String userNumber, MyXutils.XCallBack callBack);
}
