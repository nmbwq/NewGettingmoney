package money.com.gettingmoney.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 * 港股list
 */
public class GangguListBean {


    /**
     * msg : 成功
     * stockList : [{"stockId":"00011","stockName":"恒生银行","stockCode":"00011.HK","type":5,"now":"158.399994","volume":"-0.01","differRange":"-1.10%","differ":"-1.700","lastUpdateTime":"2017-04-19 15:04:00.0"},{"stockId":"00012","stockName":"恒基地产","stockCode":"00012.HK","type":5,"now":"48.049999","volume":"0.0115","differRange":"-1.11%","differ":"-0.550","lastUpdateTime":"2017-04-19 15:04:00.0"},{"stockId":"00014","stockName":"希慎兴业","stockCode":"00014.HK","type":5,"now":"36.0","volume":"-0.0109","differRange":"0.55%","differ":"0.200","lastUpdateTime":"2017-04-19 15:04:00.0"},{"stockId":"00015","stockName":"盈信控股","stockCode":"00015.HK","type":5,"now":"0.95","volume":"0.0","differRange":"0.89%","differ":"0.010","lastUpdateTime":"2017-04-19 15:04:00.0"},{"stockId":"00016","stockName":"新鸿基地产","stockCode":"00016.HK","type":5,"now":"114.900002","volume":"-0.0017","differRange":"-1.78%","differ":"-2.100","lastUpdateTime":"2017-04-19 15:04:00.0"},{"stockId":"00017","stockName":"新世界发展","stockCode":"00017.HK","type":5,"now":"9.85","volume":"-0.012","differRange":"-1.43%","differ":"-0.140","lastUpdateTime":"2017-04-19 15:04:00.0"},{"stockId":"00018","stockName":"东方报业集团","stockCode":"00018.HK","type":5,"now":"0.91","volume":"0.0","differRange":"-2.20%","differ":"-0.020","lastUpdateTime":"2017-04-19 15:04:00.0"},{"stockId":"00019","stockName":"太古股份公司A","stockCode":"00019.HK","type":5,"now":"77.300003","volume":"-0.0057","differRange":"-0.53%","differ":"-0.400","lastUpdateTime":"2017-04-19 15:04:00.0"},{"stockId":"00020","stockName":"会德丰","stockCode":"00020.HK","type":5,"now":"60.950001","volume":"-0.0105","differRange":"0.58%","differ":"0.350","lastUpdateTime":"2017-04-19 15:04:00.0"},{"stockId":"00021","stockName":"大中华地产控股","stockCode":"00021.HK","type":5,"now":"0.235","volume":"-0.0447","differRange":"0.00%","differ":"0.000","lastUpdateTime":"2017-04-19 15:04:00.0"}]
     * status : 1
     */

    private String msg;
    private int status;
    private List<StockListBean> stockList;


    @Override
    public String toString() {
        return "GangguListBean{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", stockList=" + stockList +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<StockListBean> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockListBean> stockList) {
        this.stockList = stockList;
    }

    public static class StockListBean {
        /**
         * stockId : 00011
         * stockName : 恒生银行
         * stockCode : 00011.HK
         * type : 5
         * now : 158.399994
         * volume : -0.01
         * differRange : -1.10%
         * differ : -1.700
         * lastUpdateTime : 2017-04-19 15:04:00.0
         */

        private String stockId;
        private String stockName;
        private String stockCode;
        private int type;
        private String now;
        private String volume;
        private String differRange;
        private String differ;
        private String lastUpdateTime;

        @Override
        public String toString() {
            return "StockListBean{" +
                    "stockId='" + stockId + '\'' +
                    ", stockName='" + stockName + '\'' +
                    ", stockCode='" + stockCode + '\'' +
                    ", type=" + type +
                    ", now='" + now + '\'' +
                    ", volume='" + volume + '\'' +
                    ", differRange='" + differRange + '\'' +
                    ", differ='" + differ + '\'' +
                    ", lastUpdateTime='" + lastUpdateTime + '\'' +
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNow() {
            return now;
        }

        public void setNow(String now) {
            this.now = now;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getDifferRange() {
            return differRange;
        }

        public void setDifferRange(String differRange) {
            this.differRange = differRange;
        }

        public String getDiffer() {
            return differ;
        }

        public void setDiffer(String differ) {
            this.differ = differ;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }
    }
}
