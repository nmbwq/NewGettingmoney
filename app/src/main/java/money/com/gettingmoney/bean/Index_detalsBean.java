package money.com.gettingmoney.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
public class Index_detalsBean implements Serializable {

    /**
     * msg : 成功
     * code : 000002.SZ
     * handicap : {"stockId":"000002","name":"A股指数","code":null,"news":"3350.96","equally":"11.27","downs":"-23.28","totalHands":"16950032128","money":"191009632256","turnover":"0.57","volumeRatio":"0.00","highest":"3377.21","minimum":"3349.85","thisOpen":"3367.13","zuoshou":"3374.24","roseLimit":"3711.67","downsLimit":"3036.82","outerDisc":"7733058560","invol":"9216973568","tote":"35221488663615","tradableShares":"2997722808320","flowValue":"25252004405661","rose":"-0.69%"}
     * list : [{"time":"2017-04-12 09:30","newMoney":"3438.87","dealMoney":"0","average":"3438.87","rows4":null},{"time":"2017-04-12 09:31","newMoney":"3433.60","dealMoney":"6460231.04","average":"3437","rows4":null},{"time":"2017-04-12 09:32","newMoney":"3431.47","dealMoney":"4031288","average":"3435.62","rows4":null},{"time":"2017-04-12 09:33","newMoney":"3430.67","dealMoney":"3162674.08","average":"3434.79","rows4":null},{"time":"2017-04-12 09:34","newMoney":"3431.48","dealMoney":"3021022.08","average":"3434.25","rows4":null},{"time":"2017-04-12 09:35","newMoney":"3431.05","dealMoney":"2778820.96","average":"3433.91","rows4":null},{"time":"2017-04-12 09:36","newMoney":"3430.78","dealMoney":"2957955.04","average":"3433.61","rows4":null},{"time":"2017-04-12 09:37","newMoney":"3429.80","dealMoney":"2960228.96","average":"3433.25","rows4":null},{"time":"2017-04-12 09:38","newMoney":"3426.56","dealMoney":"2927076.96","average":"3432.8","rows4":null},{"time":"2017-04-12 09:39","newMoney":"3424.97","dealMoney":"2623023","average":"3432.27","rows4":null},{"time":"2017-04-12 09:40","newMoney":"3425.49","dealMoney":"2737708.96","average":"3431.75","rows4":null},{"time":"2017-04-12 09:41","newMoney":"3427.85","dealMoney":"2678510","average":"3431.4","rows4":null},{"time":"2017-04-12 09:42","newMoney":"3428.87","dealMoney":"2451874","average":"3431.22","rows4":null},{"time":"2017-04-12 09:43","newMoney":"3429.03","dealMoney":"2358478","average":"3431.11","rows4":null},{"time":"2017-04-12 09:44","newMoney":"3428.79","dealMoney":"2384960","average":"3431.02","rows4":null},{"time":"2017-04-12 09:45","newMoney":"3429.32","dealMoney":"2236452","average":"3430.95","rows4":null},{"time":"2017-04-12 09:46","newMoney":"3430.13","dealMoney":"1872928","average":"3430.91","rows4":null},{"time":"2017-04-12 09:47","newMoney":"3431.19","dealMoney":"1866521","average":"3430.9","rows4":null},{"time":"2017-04-12 09:48","newMoney":"3430.84","dealMoney":"1748158","average":"3430.91","rows4":null},{"time":"2017-04-12 09:49","newMoney":"3430.48","dealMoney":"1709495","average":"3430.9","rows4":null},{"time":"2017-04-12 09:50","newMoney":"3430.83","dealMoney":"1636715","average":"3430.9","rows4":null},{"time":"2017-04-12 09:51","newMoney":"3430.66","dealMoney":"1688825","average":"3430.9","rows4":null},{"time":"2017-04-12 09:52","newMoney":"3431.16","dealMoney":"1612337","average":"3430.9","rows4":null},{"time":"2017-04-12 09:53","newMoney":"3430.03","dealMoney":"1503405","average":"3430.89","rows4":null},{"time":"2017-04-12 09:54","newMoney":"3428.36","dealMoney":"1622701","average":"3430.86","rows4":null},{"time":"2017-04-12 09:55","newMoney":"3427.60","dealMoney":"1752566","average":"3430.79","rows4":null},{"time":"2017-04-12 09:56","newMoney":"3427.19","dealMoney":"1583259","average":"3430.71","rows4":null},{"time":"2017-04-12 09:57","newMoney":"3426.91","dealMoney":"1373089","average":"3430.63","rows4":null},{"time":"2017-04-12 09:58","newMoney":"3428.21","dealMoney":"1415925","average":"3430.57","rows4":null},{"time":"2017-04-12 09:59","newMoney":"3429.30","dealMoney":"1559553","average":"3430.54","rows4":null},{"time":"2017-04-12 10:00","newMoney":"3432.60","dealMoney":"1622010","average":"3430.54","rows4":null},{"time":"2017-04-12 10:01","newMoney":"3435.49","dealMoney":"1615763","average":"3430.61","rows4":null},{"time":"2017-04-12 10:02","newMoney":"3437.02","dealMoney":"1689532","average":"3430.73","rows4":null}]
     */

    private String msg;
    private String code;
    private HandicapBean handicap;
    private List<ListBean> list;

    @Override
    public String toString() {
        return "Index_detalsBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", handicap=" + handicap +
                ", list=" + list +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HandicapBean getHandicap() {
        return handicap;
    }

    public void setHandicap(HandicapBean handicap) {
        this.handicap = handicap;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class HandicapBean {
        /**
         * stockId : 000002
         * name : A股指数
         * code : null
         * news : 3350.96
         * equally : 11.27
         * downs : -23.28
         * totalHands : 16950032128
         * money : 191009632256
         * turnover : 0.57
         * volumeRatio : 0.00
         * highest : 3377.21
         * minimum : 3349.85
         * thisOpen : 3367.13
         * zuoshou : 3374.24
         * roseLimit : 3711.67
         * downsLimit : 3036.82
         * outerDisc : 7733058560
         * invol : 9216973568
         * tote : 35221488663615
         * tradableShares : 2997722808320
         * flowValue : 25252004405661
         * rose : -0.69%
         */

        private String stockId;
        private String name;
        private Object code;
        private String news;
        private String equally;
        private String downs;
        private String totalHands;
        private String money;
        private String turnover;
        private String volumeRatio;
        private String highest;
        private String minimum;
        private String thisOpen;
        private String zuoshou;
        private String roseLimit;
        private String downsLimit;
        private String outerDisc;
        private String invol;
        private String tote;
        private String tradableShares;
        private String flowValue;
        private String rose;

        @Override
        public String toString() {
            return "HandicapBean{" +
                    "stockId='" + stockId + '\'' +
                    ", name='" + name + '\'' +
                    ", code=" + code +
                    ", news='" + news + '\'' +
                    ", equally='" + equally + '\'' +
                    ", downs='" + downs + '\'' +
                    ", totalHands='" + totalHands + '\'' +
                    ", money='" + money + '\'' +
                    ", turnover='" + turnover + '\'' +
                    ", volumeRatio='" + volumeRatio + '\'' +
                    ", highest='" + highest + '\'' +
                    ", minimum='" + minimum + '\'' +
                    ", thisOpen='" + thisOpen + '\'' +
                    ", zuoshou='" + zuoshou + '\'' +
                    ", roseLimit='" + roseLimit + '\'' +
                    ", downsLimit='" + downsLimit + '\'' +
                    ", outerDisc='" + outerDisc + '\'' +
                    ", invol='" + invol + '\'' +
                    ", tote='" + tote + '\'' +
                    ", tradableShares='" + tradableShares + '\'' +
                    ", flowValue='" + flowValue + '\'' +
                    ", rose='" + rose + '\'' +
                    '}';
        }

        public String getStockId() {
            return stockId;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public String getNews() {
            return news;
        }

        public void setNews(String news) {
            this.news = news;
        }

        public String getEqually() {
            return equally;
        }

        public void setEqually(String equally) {
            this.equally = equally;
        }

        public String getDowns() {
            return downs;
        }

        public void setDowns(String downs) {
            this.downs = downs;
        }

        public String getTotalHands() {
            return totalHands;
        }

        public void setTotalHands(String totalHands) {
            this.totalHands = totalHands;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTurnover() {
            return turnover;
        }

        public void setTurnover(String turnover) {
            this.turnover = turnover;
        }

        public String getVolumeRatio() {
            return volumeRatio;
        }

        public void setVolumeRatio(String volumeRatio) {
            this.volumeRatio = volumeRatio;
        }

        public String getHighest() {
            return highest;
        }

        public void setHighest(String highest) {
            this.highest = highest;
        }

        public String getMinimum() {
            return minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }

        public String getThisOpen() {
            return thisOpen;
        }

        public void setThisOpen(String thisOpen) {
            this.thisOpen = thisOpen;
        }

        public String getZuoshou() {
            return zuoshou;
        }

        public void setZuoshou(String zuoshou) {
            this.zuoshou = zuoshou;
        }

        public String getRoseLimit() {
            return roseLimit;
        }

        public void setRoseLimit(String roseLimit) {
            this.roseLimit = roseLimit;
        }

        public String getDownsLimit() {
            return downsLimit;
        }

        public void setDownsLimit(String downsLimit) {
            this.downsLimit = downsLimit;
        }

        public String getOuterDisc() {
            return outerDisc;
        }

        public void setOuterDisc(String outerDisc) {
            this.outerDisc = outerDisc;
        }

        public String getInvol() {
            return invol;
        }

        public void setInvol(String invol) {
            this.invol = invol;
        }

        public String getTote() {
            return tote;
        }

        public void setTote(String tote) {
            this.tote = tote;
        }

        public String getTradableShares() {
            return tradableShares;
        }

        public void setTradableShares(String tradableShares) {
            this.tradableShares = tradableShares;
        }

        public String getFlowValue() {
            return flowValue;
        }

        public void setFlowValue(String flowValue) {
            this.flowValue = flowValue;
        }

        public String getRose() {
            return rose;
        }

        public void setRose(String rose) {
            this.rose = rose;
        }
    }

    public static class ListBean {
        /**
         * time : 2017-04-12 09:30
         * newMoney : 3438.87
         * dealMoney : 0
         * average : 3438.87
         * rows4 : null
         */

        private String time;
        private String newMoney;
        private String dealMoney;
        private String average;
        private Object rows4;


        @Override
        public String toString() {
            return "ListBean{" +
                    "time='" + time + '\'' +
                    ", newMoney='" + newMoney + '\'' +
                    ", dealMoney='" + dealMoney + '\'' +
                    ", average='" + average + '\'' +
                    ", rows4=" + rows4 +
                    '}';
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getNewMoney() {
            return newMoney;
        }

        public void setNewMoney(String newMoney) {
            this.newMoney = newMoney;
        }

        public String getDealMoney() {
            return dealMoney;
        }

        public void setDealMoney(String dealMoney) {
            this.dealMoney = dealMoney;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public Object getRows4() {
            return rows4;
        }

        public void setRows4(Object rows4) {
            this.rows4 = rows4;
        }
    }
}
