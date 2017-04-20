package money.com.gettingmoney.bean;

/**
 * Created by Administrator on 2017/4/12.
 */
public class UserStocks {
    private String sharesCode;

    public String getSharesCode() {
        return sharesCode;
    }

    @Override
    public String toString() {
        return "UserStocks{" +
                "sharesCode='" + sharesCode + '\'' +
                '}';
    }

    public void setSharesCode(String sharesCode) {
        this.sharesCode = sharesCode;
    }
}
