package money.com.gettingmoney.bai.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 * 板块的日K线图实体类
 */
public class PlateStock_bean {

    /**
     * msg : 成功
     * detail : {"domainCode":null,"open":"1427.46","close":"1436.02","high":"1427.93","low":"1405.38","turnover":"4063961536","vol":"3674722","now":"1409.34","riseAndFallValue":"-26.68","riseAndFall":"-1.86%"}
     * list : [{"domainCode":null,"updateTime":"2016-11-30","open":"1146.50","close":"1139.53","high":"1148.47","low":"1134.79","turnover":"0","vol":"3286343","m5":"1154.384","m10":"1148.131","m20":"1127.266"},{"domainCode":null,"updateTime":"2016-12-01","open":"1145.33","close":"1155.35","high":"1158.36","low":"1145.21","turnover":"0","vol":"3469748","m5":"1155.178","m10":"1150.223","m20":"1130.725"},{"domainCode":null,"updateTime":"2016-12-02","open":"1159.85","close":"1159.82","high":"1178.42","low":"1158.79","turnover":"0","vol":"4874971","m5":"1156.102","m10":"1153.059","m20":"1134.423"},{"domainCode":null,"updateTime":"2016-12-05","open":"1149.94","close":"1155.68","high":"1160.34","low":"1144.65","turnover":"0","vol":"4387324","m5":"1152.562","m10":"1154.896","m20":"1137.615"},{"domainCode":null,"updateTime":"2016-12-06","open":"1156.38","close":"1163.65","high":"1166.23","low":"1155.17","turnover":"0","vol":"3578644","m5":"1154.806","m10":"1155.606","m20":"1141.225"},{"domainCode":null,"updateTime":"2016-12-07","open":"1160.10","close":"1186.05","high":"1186.51","low":"1157.09","turnover":"0","vol":"4118046","m5":"1164.110","m10":"1159.247","m20":"1146.251"},{"domainCode":null,"updateTime":"2016-12-08","open":"1186.10","close":"1186.53","high":"1192.99","low":"1183.83","turnover":"0","vol":"3479197","m5":"1170.346","m10":"1162.762","m20":"1150.359"},{"domainCode":null,"updateTime":"2016-12-09","open":"1185.61","close":"1180.41","high":"1189.68","low":"1176.50","turnover":"0","vol":"2831935","m5":"1174.464","m10":"1165.283","m20":"1153.408"},{"domainCode":null,"updateTime":"2016-12-12","open":"1183.57","close":"1134.74","high":"1193.11","low":"1134.45","turnover":"0","vol":"4656309","m5":"1170.276","m10":"1161.419","m20":"1153.513"},{"domainCode":null,"updateTime":"2016-12-13","open":"1128.44","close":"1169.42","high":"1170.62","low":"1125.47","turnover":"0","vol":"4725909","m5":"1171.430","m10":"1163.118","m20":"1155.372"},{"domainCode":null,"updateTime":"2016-12-14","open":"1160.74","close":"1186.31","high":"1198.75","low":"1156.38","turnover":"0","vol":"5454461","m5":"1171.482","m10":"1167.796","m20":"1157.964"},{"domainCode":null,"updateTime":"2016-12-15","open":"1176.34","close":"1197.04","high":"1199.99","low":"1173.96","turnover":"0","vol":"4811094","m5":"1173.584","m10":"1171.965","m20":"1161.094"},{"domainCode":null,"updateTime":"2016-12-16","open":"1193.66","close":"1200.87","high":"1202.38","low":"1190.77","turnover":"0","vol":"5337653","m5":"1177.676","m10":"1176.070","m20":"1164.565"},{"domainCode":null,"updateTime":"2016-12-19","open":"1197.97","close":"1211.95","high":"1214.35","low":"1188.68","turnover":"0","vol":"5519575","m5":"1193.118","m10":"1181.697","m20":"1168.297"},{"domainCode":null,"updateTime":"2016-12-20","open":"1202.98","close":"1189.90","high":"1203.83","low":"1182.90","turnover":"0","vol":"5158251","m5":"1197.214","m10":"1184.322","m20":"1169.964"},{"domainCode":null,"updateTime":"2016-12-21","open":"1193.84","close":"1206.77","high":"1207.99","low":"1189.81","turnover":"0","vol":"5189534","m5":"1201.306","m10":"1186.394","m20":"1172.821"},{"domainCode":null,"updateTime":"2016-12-22","open":"1205.29","close":"1204.39","high":"1212.06","low":"1191.55","turnover":"0","vol":"4920833","m5":"1202.776","m10":"1188.180","m20":"1175.471"},{"domainCode":null,"updateTime":"2016-12-23","open":"1199.97","close":"1193.81","high":"1211.54","low":"1190.89","turnover":"0","vol":"4478791","m5":"1201.364","m10":"1189.520","m20":"1177.402"},{"domainCode":null,"updateTime":"2016-12-26","open":"1185.26","close":"1191.19","high":"1192.69","low":"1167.00","turnover":"0","vol":"3738181","m5":"1197.212","m10":"1195.165","m20":"1178.292"}]
     */

    private String msg;
    private DetailBean detail;
    private List<ListBean> list;

    @Override
    public String toString() {
        return "PlateStock_bean{" +
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
         * open : 1427.46
         * close : 1436.02
         * high : 1427.93
         * low : 1405.38
         * turnover : 4063961536
         * vol : 3674722
         * now : 1409.34
         * riseAndFallValue : -26.68
         * riseAndFall : -1.86%
         */

        private String domainCode;
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

        public String getDomainCode() {
            return domainCode;
        }

        public void setDomainCode(String domainCode) {
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
         * updateTime : 2016-11-30
         * open : 1146.50
         * close : 1139.53
         * high : 1148.47
         * low : 1134.79
         * turnover : 0
         * vol : 3286343
         * m5 : 1154.384
         * m10 : 1148.131
         * m20 : 1127.266
         */

        private Object domainCode;
        private String updateTime;
        private float open;
        private float close;
        private float high;
        private float low;
        private String turnover;
        private float vol;
        private String m5;
        private String m10;
        private String m20;

        @Override
        public String toString() {
            return "ListBean{" +
                    "domainCode=" + domainCode +
                    ", updateTime='" + updateTime + '\'' +
                    ", open='" + open + '\'' +
                    ", close='" + close + '\'' +
                    ", high='" + high + '\'' +
                    ", low='" + low + '\'' +
                    ", turnover='" + turnover + '\'' +
                    ", vol='" + vol + '\'' +
                    ", m5='" + m5 + '\'' +
                    ", m10='" + m10 + '\'' +
                    ", m20='" + m20 + '\'' +
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

        public float getOpen() {
            return open;
        }

        public void setOpen(float open) {
            this.open = open;
        }

        public float getClose() {
            return close;
        }

        public void setClose(float close) {
            this.close = close;
        }

        public float getHigh() {
            return high;
        }

        public void setHigh(float high) {
            this.high = high;
        }

        public float getLow() {
            return low;
        }

        public void setLow(float low) {
            this.low = low;
        }

        public String getTurnover() {
            return turnover;
        }

        public void setTurnover(String turnover) {
            this.turnover = turnover;
        }

        public float getVol() {
            return vol;
        }

        public void setVol(float vol) {
            this.vol = vol;
        }

        public String getM5() {
            return m5;
        }

        public void setM5(String m5) {
            this.m5 = m5;
        }

        public String getM10() {
            return m10;
        }

        public void setM10(String m10) {
            this.m10 = m10;
        }

        public String getM20() {
            return m20;
        }

        public void setM20(String m20) {
            this.m20 = m20;
        }
    }
}
