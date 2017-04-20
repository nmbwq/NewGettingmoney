package money.com.gettingmoney.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 * 港股指数详情
 */
public class GangguDetials {

    /**
     * msg : 成功
     * code :
     * handicap : {"stockId":"HSI","name":"恒生指数","code":null,"news":"23924.54","equally":"-","downs":"-337.12","totalHands":"-","money":"70590013440","turnover":"-","volumeRatio":"-","highest":"24276.31","minimum":"23892.59","thisOpen":"24268.17","zuoshou":"24261.66","roseLimit":"-","downsLimit":"-","outerDisc":"-","invol":"-","tote":"-","tradableShares":"0","flowValue":"-","rose":"-1.39%"}
     * list : [{"time":"2017-04-10 09:30","newMoney":"24302.01","dealMoney":"0","average":"24302.01","rows4":null},{"time":"2017-04-10 09:31","newMoney":"24301.27","dealMoney":"0","average":"24301.64","rows4":null},{"time":"2017-04-10 09:32","newMoney":"24312.10","dealMoney":"0","average":"24305.13","rows4":null},{"time":"2017-04-10 09:33","newMoney":"24286.41","dealMoney":"0","average":"24300.45","rows4":null},{"time":"2017-04-10 09:34","newMoney":"24286.32","dealMoney":"0","average":"24297.62","rows4":null},{"time":"2017-04-10 09:35","newMoney":"24251.45","dealMoney":"0","average":"24289.93","rows4":null},{"time":"2017-04-10 09:36","newMoney":"24254.99","dealMoney":"0","average":"24284.94","rows4":null},{"time":"2017-04-10 09:37","newMoney":"24242.59","dealMoney":"0","average":"24279.64","rows4":null},{"time":"2017-04-10 09:38","newMoney":"24227.36","dealMoney":"0","average":"24273.83","rows4":null},{"time":"2017-04-10 09:39","newMoney":"24231.87","dealMoney":"0","average":"24269.64","rows4":null},{"time":"2017-04-10 09:40","newMoney":"24232.55","dealMoney":"0","average":"24266.27","rows4":null},{"time":"2017-04-10 09:41","newMoney":"24222.63","dealMoney":"0","average":"24262.63","rows4":null},{"time":"2017-04-10 09:42","newMoney":"24223.11","dealMoney":"0","average":"24259.59","rows4":null},{"time":"2017-04-10 09:43","newMoney":"24219.99","dealMoney":"0","average":"24256.76","rows4":null},{"time":"2017-04-10 09:44","newMoney":"24230.60","dealMoney":"0","average":"24255.02","rows4":null},{"time":"2017-04-10 09:45","newMoney":"24235.16","dealMoney":"0","average":"24253.78","rows4":null},{"time":"2017-04-10 09:46","newMoney":"24221.43","dealMoney":"0","average":"24251.87","rows4":null},{"time":"2017-04-10 09:47","newMoney":"24229.85","dealMoney":"0","average":"24250.65","rows4":null},{"time":"2017-04-10 09:48","newMoney":"24219.47","dealMoney":"0","average":"24249.01","rows4":null},{"time":"2017-04-10 09:49","newMoney":"24234.17","dealMoney":"0","average":"24248.27","rows4":null},{"time":"2017-04-10 09:50","newMoney":"24245.55","dealMoney":"0","average":"24248.14","rows4":null},{"time":"2017-04-10 09:51","newMoney":"24246.48","dealMoney":"0","average":"24248.06","rows4":null},{"time":"2017-04-10 09:52","newMoney":"24251.46","dealMoney":"0","average":"24248.21","rows4":null},{"time":"2017-04-10 09:53","newMoney":"24265.03","dealMoney":"0","average":"24248.91","rows4":null},{"time":"2017-04-10 09:54","newMoney":"24261.96","dealMoney":"0","average":"24249.43","rows4":null},{"time":"2017-04-10 09:55","newMoney":"24280.58","dealMoney":"0","average":"24250.63","rows4":null},{"time":"2017-04-10 09:56","newMoney":"24281.52","dealMoney":"0","average":"24251.78","rows4":null},{"time":"2017-04-10 09:57","newMoney":"24280.67","dealMoney":"0","average":"24252.81","rows4":null},{"time":"2017-04-10 09:58","newMoney":"24266.50","dealMoney":"0","average":"24253.28","rows4":null},{"time":"2017-04-10 09:59","newMoney":"24268.98","dealMoney":"0","average":"24253.8","rows4":null},{"time":"2017-04-10 10:00","newMoney":"24268.63","dealMoney":"0","average":"24254.28","rows4":null},{"time":"2017-04-10 10:01","newMoney":"24271.61","dealMoney":"0","average":"24254.82","rows4":null},{"time":"2017-04-10 10:02","newMoney":"24273.93","dealMoney":"0","average":"24255.4","rows4":null},{"time":"2017-04-10 10:03","newMoney":"24277.80","dealMoney":"0","average":"24256.06","rows4":null},{"time":"2017-04-10 10:04","newMoney":"24268.19","dealMoney":"0","average":"24256.41","rows4":null},{"time":"2017-04-10 10:05","newMoney":"24260.85","dealMoney":"0","average":"24256.53","rows4":null}]
     */

    private String msg;
    private String code;
    private HandicapBean handicap;
    private List<ListBean> list;

    @Override
    public String toString() {
        return "GangguDetials{" +
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
         * stockId : HSI
         * name : 恒生指数
         * code : null
         * news : 23924.54
         * equally : -
         * downs : -337.12
         * totalHands : -
         * money : 70590013440
         * turnover : -
         * volumeRatio : -
         * highest : 24276.31
         * minimum : 23892.59
         * thisOpen : 24268.17
         * zuoshou : 24261.66
         * roseLimit : -
         * downsLimit : -
         * outerDisc : -
         * invol : -
         * tote : -
         * tradableShares : 0
         * flowValue : -
         * rose : -1.39%
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
         * time : 2017-04-10 09:30
         * newMoney : 24302.01
         * dealMoney : 0
         * average : 24302.01
         * rows4 : null
         */

        private String time;
        private Float newMoney;
        private String dealMoney;
        private Float average;
        private Object rows4;


        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Float getNewMoney() {
            return newMoney;
        }

        public void setNewMoney(Float newMoney) {
            this.newMoney = newMoney;
        }

        public String getDealMoney() {
            return dealMoney;
        }

        public void setDealMoney(String dealMoney) {
            this.dealMoney = dealMoney;
        }

        public Float getAverage() {
            return average;
        }

        public void setAverage(Float average) {
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
