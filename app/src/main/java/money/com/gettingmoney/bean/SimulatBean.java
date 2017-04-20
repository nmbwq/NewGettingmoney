package money.com.gettingmoney.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
public class SimulatBean {


    /**
     * result : [{"stockId":"000538","stockName":"云南白药","stockCode":"000538.SZ","type":2,"now":"88.860001","volume":"0.0579","differRange":"-0.28%","differ":"-0.24","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"00100","stockName":"白马户外媒体","stockCode":"00100.HK","type":5,"now":"8.85","volume":"-0.0056","differRange":"-0.47%","differ":"-0.040","lastUpdateTime":"2017-03-29 16:15:00.0"},{"stockId":"002145","stockName":"中核钛白","stockCode":"002145.SZ","type":2,"now":"6.49","volume":"0.0031","differRange":"0.47%","differ":"0.03","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"00239","stockName":"白花油","stockCode":"00239.HK","type":5,"now":"3.57","volume":"0.0","differRange":"-1.67%","differ":"-0.060","lastUpdateTime":"2017-03-29 16:15:00.0"},{"stockId":"002687","stockName":"乔治白","stockCode":"002687.SZ","type":2,"now":"10.84","volume":"-0.0119","differRange":"-2.04%","differ":"-0.22","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"00815","stockName":"中国白银集团","stockCode":"00815.HK","type":5,"now":"1.54","volume":"-0.0064","differRange":"1.32%","differ":"0.020","lastUpdateTime":"2017-03-29 16:15:00.0"},{"stockId":"00874","stockName":"白云山","stockCode":"00874.HK","type":5,"now":"24.25","volume":"-0.0122","differRange":"0.00%","differ":"0.000","lastUpdateTime":"2017-03-29 16:15:00.0"},{"stockId":"600004","stockName":"白云机场","stockCode":"600004.SH","type":1,"now":"15.96","volume":"-0.0179","differRange":"0.69%","differ":"0.11","lastUpdateTime":"2017-03-29 16:15:08.0"},{"stockId":"600332","stockName":"白云山","stockCode":"600332.SH","type":1,"now":"29.450001","volume":"0.002","differRange":"-0.28%","differ":"-0.08","lastUpdateTime":"2017-03-29 16:15:08.0"},{"stockId":"600559","stockName":"老白干酒","stockCode":"600559.SH","type":1,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:08.0"},{"stockId":"601212","stockName":"白银有色","stockCode":"601212.SH","type":1,"now":"16.290001","volume":"0.0571","differRange":"-7.39%","differ":"-1.13","lastUpdateTime":"2017-03-29 16:15:08.0"},{"stockId":"603099","stockName":"长白山","stockCode":"603099.SH","type":1,"now":"19.209999","volume":"0.0095","differRange":"-2.33%","differ":"-0.44","lastUpdateTime":"2017-03-29 16:15:08.0"},{"stockId":"603861","stockName":"白云电器","stockCode":"603861.SH","type":1,"now":"30.83","volume":"-0.0163","differRange":"-5.66%","differ":"-1.61","lastUpdateTime":"2017-03-29 16:15:08.0"},{"stockId":"DNB","stockName":"邓白氏","stockCode":"DNB.N","type":6,"now":"108.379997","volume":"0.0","lastUpdateTime":"2017-03-18 17:05:35.0"},{"stockId":"WTM","stockName":"白山保险集团","stockCode":"WTM.N","type":6,"now":"907.169983","volume":"0.0","lastUpdateTime":"2017-03-18 17:21:08.0"}]
     * msg : 成功
     * allNum : 15
     * status : 1
     */

    private String msg;
    private int allNum;
    private int status;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "SimulatBean{" +
                "msg='" + msg + '\'' +
                ", allNum=" + allNum +
                ", status=" + status +
                ", result=" + result +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * stockId : 000538
         * stockName : 云南白药
         * stockCode : 000538.SZ
         * type : 2
         * now : 88.860001
         * volume : 0.0579
         * differRange : -0.28%
         * differ : -0.24
         * lastUpdateTime : 2017-03-29 16:15:05.0
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
            return "ResultBean{" +
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
