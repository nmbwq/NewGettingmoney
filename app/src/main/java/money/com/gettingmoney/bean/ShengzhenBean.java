package money.com.gettingmoney.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */
public class ShengzhenBean implements Serializable {

    /**
     * msg : 返回成功
     * resultList : [{"stockId":"000033","stockName":"*ST新都","stockCode":"000033.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"000050","stockName":"深天马A","stockCode":"000050.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-4.30%","differ":"-0.85","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"000034","stockName":"神州数码","stockCode":"000034.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"000301","stockName":"东方市场","stockCode":"000301.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"000007","stockName":"全新好","stockCode":"000007.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"000153","stockName":"丰原药业","stockCode":"000153.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"000029","stockName":"深深房A","stockCode":"000029.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"000042","stockName":"中洲控股","stockCode":"000042.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"000032","stockName":"深桑达A","stockCode":"000032.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:05.0"},{"stockId":"000155","stockName":"*ST川化","stockCode":"000155.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-03-29 16:15:05.0"}]
     * allNum : 3234
     * status : 1
     */

    private String msg;
    private int allNum;
    private int status;
    private List<ResultListBean> resultList;

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

    public List<ResultListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultListBean> resultList) {
        this.resultList = resultList;
    }

    public static class ResultListBean {
        /**
         * stockId : 000033
         * stockName : *ST新都
         * stockCode : 000033.SZ
         * type : 2
         * now : 0.0
         * volume : 0.0
         * differRange : -
         * differ : -
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
