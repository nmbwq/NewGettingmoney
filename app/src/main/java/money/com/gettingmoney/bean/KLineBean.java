package money.com.gettingmoney.bean;

import java.util.List;

/**
 *
 *行情点击上证指数进入到k线图的返回结果
 *
 */
public class KLineBean {


    /**
     * msg : 返回成功
     * stockHistoryList : [{"stockId":"000001","closing":9.13,"vol":644000,"vob":5884065,"updateTime":"2017-03-29 09:30","nowFzde":9.13,"nowChange":100,"priceAvg":9.137,"type":1},{"stockId":"000001","closing":9.15,"vol":429016,"vob":3922088,"updateTime":"2017-03-29 09:31","nowFzde":0.02,"nowChange":0.22,"priceAvg":9.139,"type":1},{"stockId":"000001","closing":9.14,"vol":613284,"vob":5604421,"updateTime":"2017-03-29 09:32","nowFzde":-0.01,"nowChange":-0.11,"priceAvg":9.139,"type":1},{"stockId":"000001","closing":9.13,"vol":94500,"vob":863533,"updateTime":"2017-03-29 09:33","nowFzde":-0.01,"nowChange":-0.11,"priceAvg":9.139,"type":1},{"stockId":"000001","closing":9.13,"vol":213120,"vob":1946116,"updateTime":"2017-03-29 09:34","nowFzde":0,"nowChange":0,"priceAvg":9.138,"type":1},{"stockId":"000001","closing":9.14,"vol":226153,"vob":2065036,"updateTime":"2017-03-29 09:35","nowFzde":0.01,"nowChange":0.11,"priceAvg":9.137,"type":1},{"stockId":"000001","closing":9.13,"vol":69100,"vob":631037,"updateTime":"2017-03-29 09:36","nowFzde":-0.01,"nowChange":-0.11,"priceAvg":9.137,"type":1},{"stockId":"000001","closing":9.13,"vol":444700,"vob":4060366,"updateTime":"2017-03-29 09:37","nowFzde":0,"nowChange":0,"priceAvg":9.136,"type":1},{"stockId":"000001","closing":9.12,"vol":88995,"vob":812281,"updateTime":"2017-03-29 09:38","nowFzde":-0.01,"nowChange":-0.11,"priceAvg":9.136,"type":1},{"stockId":"000001","closing":9.12,"vol":108900,"vob":993456,"updateTime":"2017-03-29 09:39","nowFzde":0,"nowChange":0,"priceAvg":9.135,"type":1},{"stockId":"000001","closing":9.12,"vol":165720,"vob":1511575,"updateTime":"2017-03-29 09:40","nowFzde":0,"nowChange":0,"priceAvg":9.135,"type":1},{"stockId":"000001","closing":9.12,"vol":372236,"vob":3395004,"updateTime":"2017-03-29 09:41","nowFzde":0,"nowChange":0,"priceAvg":9.133,"type":1},{"stockId":"000001","closing":9.13,"vol":178900,"vob":1631888,"updateTime":"2017-03-29 09:42","nowFzde":0.01,"nowChange":0.11,"priceAvg":9.132,"type":1},{"stockId":"000001","closing":9.12,"vol":284876,"vob":2598170,"updateTime":"2017-03-29 09:43","nowFzde":-0.01,"nowChange":-0.11,"priceAvg":9.132,"type":1},{"stockId":"000001","closing":9.12,"vol":103100,"vob":939638,"updateTime":"2017-03-29 09:44","nowFzde":0,"nowChange":0,"priceAvg":9.131,"type":1},{"stockId":"000001","closing":9.12,"vol":169020,"vob":1541142,"updateTime":"2017-03-29 09:45","nowFzde":0,"nowChange":0,"priceAvg":9.131,"type":1},{"stockId":"000001","closing":9.12,"vol":93200,"vob":849367,"updateTime":"2017-03-29 09:46","nowFzde":0,"nowChange":0,"priceAvg":9.13,"type":1},{"stockId":"000001","closing":9.12,"vol":92280,"vob":841008,"updateTime":"2017-03-29 09:47","nowFzde":0,"nowChange":0,"priceAvg":9.13,"type":1},{"stockId":"000001","closing":9.12,"vol":102200,"vob":931207,"updateTime":"2017-03-29 09:48","nowFzde":0,"nowChange":0,"priceAvg":9.13,"type":1},{"stockId":"000001","closing":9.12,"vol":650600,"vob":5928787,"updateTime":"2017-03-29 09:49","nowFzde":0,"nowChange":0,"priceAvg":9.127,"type":1},{"stockId":"000001","closing":9.12,"vol":487300,"vob":4441001,"updateTime":"2017-03-29 09:50","nowFzde":0,"nowChange":0,"priceAvg":9.126,"type":1},{"stockId":"000001","closing":9.12,"vol":238500,"vob":2174806,"updateTime":"2017-03-29 09:51","nowFzde":0,"nowChange":0,"priceAvg":9.126,"type":1},{"stockId":"000001","closing":9.12,"vol":134100,"vob":1222777,"updateTime":"2017-03-29 09:52","nowFzde":0,"nowChange":0,"priceAvg":9.126,"type":1},{"stockId":"000001","closing":9.12,"vol":260740,"vob":2377797,"updateTime":"2017-03-29 09:53","nowFzde":0,"nowChange":0,"priceAvg":9.125,"type":1},{"stockId":"000001","closing":9.11,"vol":93544,"vob":852656,"updateTime":"2017-03-29 09:54","nowFzde":-0.01,"nowChange":-0.11,"priceAvg":9.125,"type":1},{"stockId":"000001","closing":9.12,"vol":209500,"vob":1909989,"updateTime":"2017-03-29 09:55","nowFzde":0.01,"nowChange":0.11,"priceAvg":9.125,"type":1}]
     */

    private String msg;
    private List<StockHistoryListBean> stockHistoryList;

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
         * closing : 9.13
         * vol : 644000
         * vob : 5884065
         * updateTime : 2017-03-29 09:30
         * nowFzde : 9.13
         * nowChange : 100.0
         * priceAvg : 9.137
         * type : 1
         */

        private String stockId;
        private double closing;
        private int vol;
        private int vob;
        private String updateTime;
        private double nowFzde;
        private double nowChange;
        private double priceAvg;
        private int type;

        public String getStockId() {
            return stockId;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public double getClosing() {
            return closing;
        }

        public void setClosing(double closing) {
            this.closing = closing;
        }

        public int getVol() {
            return vol;
        }

        public void setVol(int vol) {
            this.vol = vol;
        }

        public int getVob() {
            return vob;
        }

        public void setVob(int vob) {
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

        public double getPriceAvg() {
            return priceAvg;
        }

        public void setPriceAvg(double priceAvg) {
            this.priceAvg = priceAvg;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
