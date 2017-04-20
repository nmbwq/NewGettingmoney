package money.com.gettingmoney.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 * 板块分时图实体类
 */


public class TimeKlineBean {


    /**
     * msg : 成功
     * detail : {"domainCode":null,"open":"1797.41","close":"1804.70","high":"1808.43","low":"1781.40","turnover":"1817534352","vol":"1913556","now":"1796.98","riseAndFallValue":"-7.72","riseAndFall":"-0.43%"}
     * list : [{"domainCode":null,"updateTime":"2017-04-17 09:30","nowMarketValue":"1797.41","riseAndFallValue":"0","arg":"1797.41"},{"domainCode":null,"updateTime":"2017-04-17 09:31","nowMarketValue":"1785.34","riseAndFallValue":"41211.03","arg":"1791.38"},{"domainCode":null,"updateTime":"2017-04-17 09:32","nowMarketValue":"1786.90","riseAndFallValue":"22305","arg":"1789.88"},{"domainCode":null,"updateTime":"2017-04-17 09:33","nowMarketValue":"1784.34","riseAndFallValue":"22973","arg":"1788.50"},{"domainCode":null,"updateTime":"2017-04-17 09:34","nowMarketValue":"1788.92","riseAndFallValue":"23830.21","arg":"1788.58"},{"domainCode":null,"updateTime":"2017-04-17 09:35","nowMarketValue":"1786.06","riseAndFallValue":"24621.09","arg":"1788.16"},{"domainCode":null,"updateTime":"2017-04-17 09:36","nowMarketValue":"1790.63","riseAndFallValue":"24105.53","arg":"1788.51"},{"domainCode":null,"updateTime":"2017-04-17 09:37","nowMarketValue":"1789.72","riseAndFallValue":"20823","arg":"1788.67"},{"domainCode":null,"updateTime":"2017-04-17 09:38","nowMarketValue":"1789.20","riseAndFallValue":"28007.47","arg":"1788.72"},{"domainCode":null,"updateTime":"2017-04-17 09:39","nowMarketValue":"1789.71","riseAndFallValue":"29673.2","arg":"1788.82"},{"domainCode":null,"updateTime":"2017-04-17 09:40","nowMarketValue":"1784.03","riseAndFallValue":"35774.98","arg":"1788.39"},{"domainCode":null,"updateTime":"2017-04-17 09:41","nowMarketValue":"1783.46","riseAndFallValue":"20302.08","arg":"1787.98"},{"domainCode":null,"updateTime":"2017-04-17 09:42","nowMarketValue":"1784.86","riseAndFallValue":"25193.77","arg":"1787.74"},{"domainCode":null,"updateTime":"2017-04-17 09:43","nowMarketValue":"1785.47","riseAndFallValue":"15484.28","arg":"1787.58"},{"domainCode":null,"updateTime":"2017-04-17 09:44","nowMarketValue":"1785.97","riseAndFallValue":"15138.77","arg":"1787.47"},{"domainCode":null,"updateTime":"2017-04-17 09:45","nowMarketValue":"1787.79","riseAndFallValue":"15831.61","arg":"1787.49"},{"domainCode":null,"updateTime":"2017-04-17 09:46","nowMarketValue":"1788.05","riseAndFallValue":"18111.61","arg":"1787.52"},{"domainCode":null,"updateTime":"2017-04-17 09:47","nowMarketValue":"1786.69","riseAndFallValue":"17591.82","arg":"1787.48"},{"domainCode":null,"updateTime":"2017-04-17 09:48","nowMarketValue":"1786.40","riseAndFallValue":"14627.83","arg":"1787.42"},{"domainCode":null,"updateTime":"2017-04-17 09:49","nowMarketValue":"1786.33","riseAndFallValue":"18923.07","arg":"1787.36"},{"domainCode":null,"updateTime":"2017-04-17 09:50","nowMarketValue":"1788.07","riseAndFallValue":"12603.39","arg":"1787.40"},{"domainCode":null,"updateTime":"2017-04-17 09:51","nowMarketValue":"1787.18","riseAndFallValue":"12220.94","arg":"1787.39"},{"domainCode":null,"updateTime":"2017-04-17 09:52","nowMarketValue":"1787.27","riseAndFallValue":"9701.67","arg":"1787.38"},{"domainCode":null,"updateTime":"2017-04-17 09:53","nowMarketValue":"1785.41","riseAndFallValue":"11011.1","arg":"1787.30"},{"domainCode":null,"updateTime":"2017-04-17 09:54","nowMarketValue":"1787.46","riseAndFallValue":"8676.8","arg":"1787.31"},{"domainCode":null,"updateTime":"2017-04-17 09:55","nowMarketValue":"1788.13","riseAndFallValue":"9671.5","arg":"1787.34"},{"domainCode":null,"updateTime":"2017-04-17 09:56","nowMarketValue":"1790.70","riseAndFallValue":"12158.77","arg":"1787.46"},{"domainCode":null,"updateTime":"2017-04-17 09:57","nowMarketValue":"1793.27","riseAndFallValue":"10833.67","arg":"1787.67"},{"domainCode":null,"updateTime":"2017-04-17 09:58","nowMarketValue":"1793.33","riseAndFallValue":"11798.38","arg":"1787.87"},{"domainCode":null,"updateTime":"2017-04-17 09:59","nowMarketValue":"1795.37","riseAndFallValue":"9020.69","arg":"1788.12"}]
     */

    private String msg;
    private DetailBean detail;
    private List<ListBean> list;

    @Override
    public String toString() {
        return "TimeKlineBean{" +
                "msg='" + msg + '\'' +
                ", detail=" + detail +
                ", list=" + list +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class DetailBean {
        /**
         * domainCode : null
         * open : 1797.41
         * close : 1804.70
         * high : 1808.43
         * low : 1781.40
         * turnover : 1817534352
         * vol : 1913556
         * now : 1796.98
         * riseAndFallValue : -7.72
         * riseAndFall : -0.43%
         */

        private Object domainCode;
        private String open;
        private String close;
        private String high;
        private String low;
        private String turnover;
        private String vol;
        private String now;
        private String riseAndFallValue;
        private String riseAndFall;

        @Override
        public String toString() {
            return "DetailBean{" +
                    "domainCode=" + domainCode +
                    ", open='" + open + '\'' +
                    ", close='" + close + '\'' +
                    ", high='" + high + '\'' +
                    ", low='" + low + '\'' +
                    ", turnover='" + turnover + '\'' +
                    ", vol='" + vol + '\'' +
                    ", now='" + now + '\'' +
                    ", riseAndFallValue='" + riseAndFallValue + '\'' +
                    ", riseAndFall='" + riseAndFall + '\'' +
                    '}';
        }

        public Object getDomainCode() {
            return domainCode;
        }

        public void setDomainCode(Object domainCode) {
            this.domainCode = domainCode;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public String getClose() {
            return close;
        }

        public void setClose(String close) {
            this.close = close;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getTurnover() {
            return turnover;
        }

        public void setTurnover(String turnover) {
            this.turnover = turnover;
        }

        public String getVol() {
            return vol;
        }

        public void setVol(String vol) {
            this.vol = vol;
        }

        public String getNow() {
            return now;
        }

        public void setNow(String now) {
            this.now = now;
        }

        public String getRiseAndFallValue() {
            return riseAndFallValue;
        }

        public void setRiseAndFallValue(String riseAndFallValue) {
            this.riseAndFallValue = riseAndFallValue;
        }

        public String getRiseAndFall() {
            return riseAndFall;
        }

        public void setRiseAndFall(String riseAndFall) {
            this.riseAndFall = riseAndFall;
        }
    }

    public static class ListBean {
        /**
         * domainCode : null
         * updateTime : 2017-04-17 09:30
         * nowMarketValue : 1797.41
         * riseAndFallValue : 0
         * arg : 1797.41
         */

        private Object domainCode;
        private String updateTime;
        private float nowMarketValue;
        private float riseAndFallValue;
        private float arg;

        @Override
        public String toString() {
            return "ListBean{" +
                    "domainCode=" + domainCode +
                    ", updateTime='" + updateTime + '\'' +
                    ", nowMarketValue='" + nowMarketValue + '\'' +
                    ", riseAndFallValue='" + riseAndFallValue + '\'' +
                    ", arg='" + arg + '\'' +
                    '}';
        }

        public Object getDomainCode() {
            return domainCode;
        }

        public void setDomainCode(Object domainCode) {
            this.domainCode = domainCode;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public float getNowMarketValue() {
            return nowMarketValue;
        }

        public void setNowMarketValue(float nowMarketValue) {
            this.nowMarketValue = nowMarketValue;
        }

        public float getRiseAndFallValue() {
            return riseAndFallValue;
        }

        public void setRiseAndFallValue(float riseAndFallValue) {
            this.riseAndFallValue = riseAndFallValue;
        }

        public float getArg() {
            return arg;
        }

        public void setArg(float arg) {
            this.arg = arg;
        }
    }
}
