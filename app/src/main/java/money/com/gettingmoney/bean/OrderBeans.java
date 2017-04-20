package money.com.gettingmoney.bean;

/**
 * Created by Administrator on 2017/4/8.
 * //添加股票交易的上传的参数
 */
public class OrderBeans {
    private String stockId;
    private int amount;
    private int countVol;
    private int type;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCountVol() {
        return countVol;
    }

    public void setCountVol(int countVol) {
        this.countVol = countVol;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
