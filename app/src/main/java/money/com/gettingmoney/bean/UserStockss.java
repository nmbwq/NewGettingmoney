package money.com.gettingmoney.bean;

/**
 * Created by Administrator on 2017/4/12.
 */
public class UserStockss {
    private  int  userSharesId;

    public int  getUserSharesId() {
        return userSharesId;
    }

    public void setUserSharesId(int userSharesId) {
        this.userSharesId = userSharesId;
    }

    @Override
    public String toString() {
        return "UserStockss{" +
                "userSharesId='" + userSharesId + '\'' +
                '}';
    }
}
