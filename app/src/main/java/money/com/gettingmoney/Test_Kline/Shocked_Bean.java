package money.com.gettingmoney.Test_Kline;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
public class Shocked_Bean {


        /**
         * msg : 返回成功
         * stockHistoryList : [{"stockId":"000001","price":9.39,"highest":9.42,"lowest":9.38,"closing":9.4,"vol":29521845,"vob":2.7750376E8,"updateTime":"2016-09-08","nowFzde":9.4,"nowChange":100,"type":0},{"stockId":"000001","price":9.4,"highest":9.43,"lowest":9.36,"closing":9.38,"vol":32743143,"vob":3.0752727E8,"updateTime":"2016-09-09","nowFzde":-0.02,"nowChange":-0.21,"type":0},{"stockId":"000001","price":9.29,"highest":9.32,"lowest":9.13,"closing":9.16,"vol":75658173,"vob":6.9775918E8,"updateTime":"2016-09-12","nowFzde":-0.22,"nowChange":-2.4,"type":0},{"stockId":"000001","price":9.18,"highest":9.21,"lowest":9.14,"closing":9.19,"vol":46093142,"vob":4.2311383E8,"updateTime":"2016-09-13","nowFzde":0.03,"nowChange":0.33,"type":0},{"stockId":"000001","price":9.17,"highest":9.18,"lowest":9.05,"closing":9.06,"vol":42148125,"vob":3.8408127E8,"updateTime":"2016-09-14","nowFzde":-0.13,"nowChange":-1.43,"type":0},{"stockId":"000001","price":9.05,"highest":9.14,"lowest":9.05,"closing":9.12,"vol":35434870,"vob":3.2257593E8,"updateTime":"2016-09-19","nowFzde":0.06,"nowChange":0.66,"type":0},{"stockId":"000001","price":9.12,"highest":9.12,"lowest":9.04,"closing":9.08,"vol":43667897,"vob":3.9635932E8,"updateTime":"2016-09-20","nowFzde":-0.04,"nowChange":-0.44,"type":0},{"stockId":"000001","price":9.08,"highest":9.1,"lowest":9.03,"closing":9.08,"vol":40566533,"vob":3.677675E8,"updateTime":"2016-09-21","nowFzde":0,"nowChange":0,"type":0},{"stockId":"000001","price":9.1,"highest":9.18,"lowest":9.09,"closing":9.16,"vol":49461323,"vob":4.5190902E8,"updateTime":"2016-09-22","nowFzde":0.08,"nowChange":0.87,"type":0},{"stockId":"000001","price":9.16,"highest":9.19,"lowest":9.14,"closing":9.15,"vol":28879877,"vob":2.6469951E8,"updateTime":"2016-09-23","nowFzde":-0.01,"nowChange":-0.11,"type":0},{"stockId":"000001","price":9.13,"highest":9.13,"lowest":9.04,"closing":9.04,"vol":43948186,"vob":3.9877186E8,"updateTime":"2016-09-26","nowFzde":-0.11,"nowChange":-1.22,"type":0},{"stockId":"000001","price":9.04,"highest":9.07,"lowest":9.01,"closing":9.06,"vol":40132367,"vob":3.6276923E8,"updateTime":"2016-09-27","nowFzde":0.02,"nowChange":0.22,"type":0},{"stockId":"000001","price":9.06,"highest":9.06,"lowest":9.03,"closing":9.05,"vol":27136600,"vob":2.4549947E8,"updateTime":"2016-09-28","nowFzde":-0.01,"nowChange":-0.11,"type":0},{"stockId":"000001","price":9.05,"highest":9.08,"lowest":9.05,"closing":9.06,"vol":30366429,"vob":2.7523967E8,"updateTime":"2016-09-29","nowFzde":0.01,"nowChange":0.11,"type":0},{"stockId":"000001","price":9.06,"highest":9.1,"lowest":9.05,"closing":9.07,"vol":30808486,"vob":2.7952648E8,"updateTime":"2016-09-30","nowFzde":0.01,"nowChange":0.11,"type":0},{"stockId":"000001","price":9.1,"highest":9.17,"lowest":9.08,"closing":9.12,"vol":61138159,"vob":5.5712228E8,"updateTime":"2016-10-10","nowFzde":0.05,"nowChange":0.55,"type":0},{"stockId":"000001","price":9.13,"highest":9.15,"lowest":9.11,"closing":9.15,"vol":36337078,"vob":3.3188652E8,"updateTime":"2016-10-11","nowFzde":0.03,"nowChange":0.33,"type":0},{"stockId":"000001","price":9.14,"highest":9.16,"lowest":9.12,"closing":9.13,"vol":27031620,"vob":2.4699553E8,"updateTime":"2016-10-12","nowFzde":-0.02,"nowChange":-0.22,"type":0},{"stockId":"000001","price":9.08,"highest":9.11,"lowest":9.05,"closing":9.07,"vol":63236796,"vob":5.7414248E8,"updateTime":"2016-10-13","nowFzde":-0.06,"nowChange":-0.66,"type":0},{"stockId":"000001","price":9.06,"highest":9.09,"lowest":9.04,"closing":9.09,"vol":33541176,"vob":3.0421868E8,"updateTime":"2016-10-14","nowFzde":0.02,"nowChange":0.22,"type":0},{"stockId":"000001","price":9.08,"highest":9.09,"lowest":9.03,"closing":9.05,"vol":35922316,"vob":3.2558245E8,"updateTime":"2016-10-17","nowFzde":-0.04,"nowChange":-0.44,"type":0},{"stockId":"000001","price":9.03,"highest":9.09,"lowest":9.03,"closing":9.09,"vol":59373709,"vob":5.3778412E8,"updateTime":"2016-10-18","nowFzde":0.04,"nowChange":0.44,"type":0}]
         */

        private String msg;
        private List<StockHistoryListBean> stockHistoryList;


        @Override
        public String toString() {
                return "Shocked_Bean{" +
                        "msg='" + msg + '\'' +
                        ", stockHistoryList=" + stockHistoryList +
                        '}';
        }

        public String getMsg() {
                return msg;
        }

        public void setMsg(String msg) {
                this.msg = msg;
        }

        public List<StockHistoryListBean> getStockHistoryList() {
                return stockHistoryList;
        }

        public void setStockHistoryList(List<StockHistoryListBean> stockHistoryList) {
                this.stockHistoryList = stockHistoryList;
        }

        public static class StockHistoryListBean {
                /**
                 * stockId : 000001
                 * price : 9.39
                 * highest : 9.42
                 * lowest : 9.38
                 * closing : 9.4
                 * vol : 29521845
                 * vob : 2.7750376E8
                 * updateTime : 2016-09-08
                 * nowFzde : 9.4
                 * nowChange : 100.0
                 * type : 0
                 */

                private String stockId;
                private float price;
                private float highest;
                private float lowest;
                private float closing;
                private int vol;
                private double vob;
                private String updateTime;
                private double nowFzde;
                private double nowChange;
                private int type;

                @Override
                public String toString() {
                        return "StockHistoryListBean{" +
                                "stockId='" + stockId + '\'' +
                                ", price=" + price +
                                ", highest=" + highest +
                                ", lowest=" + lowest +
                                ", closing=" + closing +
                                ", vol=" + vol +
                                ", vob=" + vob +
                                ", updateTime='" + updateTime + '\'' +
                                ", nowFzde=" + nowFzde +
                                ", nowChange=" + nowChange +
                                ", type=" + type +
                                '}';
                }

                public String getStockId() {
                        return stockId;
                }

                public void setStockId(String stockId) {
                        this.stockId = stockId;
                }

                public float getPrice() {
                        return price;
                }

                public void setPrice(float price) {
                        this.price = price;
                }

                public float getHighest() {
                        return highest;
                }

                public void setHighest(float highest) {
                        this.highest = highest;
                }

                public float getLowest() {
                        return lowest;
                }

                public void setLowest(float lowest) {
                        this.lowest = lowest;
                }

                public float getClosing() {
                        return closing;
                }

                public void setClosing(float closing) {
                        this.closing = closing;
                }

                public int getVol() {
                        return vol;
                }

                public void setVol(int vol) {
                        this.vol = vol;
                }

                public double getVob() {
                        return vob;
                }

                public void setVob(double vob) {
                        this.vob = vob;
                }

                public String getUpdateTime() {
                        return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                        this.updateTime = updateTime;
                }

                public double getNowFzde() {
                        return nowFzde;
                }

                public void setNowFzde(double nowFzde) {
                        this.nowFzde = nowFzde;
                }

                public double getNowChange() {
                        return nowChange;
                }

                public void setNowChange(double nowChange) {
                        this.nowChange = nowChange;
                }

                public int getType() {
                        return type;
                }

                public void setType(int type) {
                        this.type = type;
                }
        }
}
