package money.com.gettingmoney.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
public class Sell_buyBean {


    /**
     * msg : 成功
     * fiveSale : [{"amount":90,"countVol":21}]
     * fiveBuy : [{"amount":62,"countVol":87}]
     * stopFall : mock
     * stopRise : mock
     * saleTotal : 52
     * buyTotal : 52
     * now : mock
     * stock : {"stockId":"mock","stockName":"mock","stockCode":"mock"}
     */

    private String msg;
    private String stopFall;
    private String stopRise;
    private String saleTotal;
    private String buyTotal;
    private String now;
    private StockBean stock;
    private List<FiveSaleBean> fiveSale;
    private List<FiveBuyBean> fiveBuy;

    @Override
    public String toString() {
        return "Sell_buyBean{" +
                "msg='" + msg + '\'' +
                ", stopFall='" + stopFall + '\'' +
                ", stopRise='" + stopRise + '\'' +
                ", saleTotal=" + saleTotal +
                ", buyTotal=" + buyTotal +
                ", now='" + now + '\'' +
                ", stock=" + stock +
                ", fiveSale=" + fiveSale +
                ", fiveBuy=" + fiveBuy +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStopFall() {
        return stopFall;
    }

    public void setStopFall(String stopFall) {
        this.stopFall = stopFall;
    }

    public String getStopRise() {
        return stopRise;
    }

    public void setStopRise(String stopRise) {
        this.stopRise = stopRise;
    }

    public String getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(String saleTotal) {
        this.saleTotal = saleTotal;
    }

    public String getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(String buyTotal) {
        this.buyTotal = buyTotal;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public StockBean getStock() {
        return stock;
    }

    public void setStock(StockBean stock) {
        this.stock = stock;
    }

    public List<FiveSaleBean> getFiveSale() {
        return fiveSale;
    }

    public void setFiveSale(List<FiveSaleBean> fiveSale) {
        this.fiveSale = fiveSale;
    }

    public List<FiveBuyBean> getFiveBuy() {
        return fiveBuy;
    }

    public void setFiveBuy(List<FiveBuyBean> fiveBuy) {
        this.fiveBuy = fiveBuy;
    }

    public static class StockBean {
        /**
         * stockId : mock
         * stockName : mock
         * stockCode : mock
         */

        private String stockId;
        private String stockName;
        private String stockCode;

        @Override
        public String toString() {
            return "StockBean{" +
                    "stockId='" + stockId + '\'' +
                    ", stockName='" + stockName + '\'' +
                    ", stockCode='" + stockCode + '\'' +
                    '}';
        }

        public String getStockId() {
            return stockId;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public String getStockCode() {
            return stockCode;
        }

        public void setStockCode(String stockCode) {
            this.stockCode = stockCode;
        }
    }

    public static class FiveSaleBean {
        /**
         * amount : 90
         * countVol : 21
         */

        private String amount;
        private String countVol;

        @Override
        public String toString() {
            return "FiveSaleBean{" +
                    "amount=" + amount +
                    ", countVol=" + countVol +
                    '}';
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCountVol() {
            return countVol;
        }

        public void setCountVol(String countVol) {
            this.countVol = countVol;
        }
    }

    public static class FiveBuyBean {
        /**
         * amount : 62
         * countVol : 87
         */

        private String amount;
        private String countVol;

        public String getCountVol() {
            return countVol;
        }

        public void setCountVol(String countVol) {
            this.countVol = countVol;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
