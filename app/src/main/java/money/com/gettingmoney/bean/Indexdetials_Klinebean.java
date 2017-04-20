package money.com.gettingmoney.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
public class Indexdetials_Klinebean implements Serializable {


    /**
     * msg : 成功
     * code : 000001.SZ
     * list : [{"id":null,"time":"2016-11-24","name":null,"open":"3237.43","close":"3241.74","high":"3257.86","low":"3232.87","turnover":"2672亿","vol":"232324527","ma5":"3228.448","ma10":"3216.915","ma20":"3171.711","ma30":"3145.735","downs":"0.01","rose":"3237.43"},{"id":null,"time":"2016-11-25","name":null,"open":"3241.24","close":"3261.94","high":"3262.44","low":"3209.60","turnover":"2591亿","vol":"233921601","ma5":"3242.264","ma10":"3223.505","ma20":"3179.594","ma30":"3152.339","downs":"8.51","rose":"3.81"},{"id":null,"time":"2016-11-28","name":null,"open":"3270.05","close":"3277.00","high":"3288.34","low":"3267.67","turnover":"3005亿","vol":"281551253","ma5":"3254.034","ma10":"3230.168","ma20":"3188.420","ma30":"3160.200","downs":"1.14","rose":"28.81"},{"id":null,"time":"2016-11-29","name":null,"open":"3269.23","close":"3282.92","high":"3301.21","low":"3263.40","turnover":"3481亿","vol":"320836287","ma5":"3260.948","ma10":"3237.761","ma20":"3196.444","ma30":"3166.835","downs":"-39.87","rose":"-0.82"},{"id":null,"time":"2016-11-30","name":null,"open":"3272.14","close":"3250.03","high":"3277.27","low":"3239.52","turnover":"2628亿","vol":"243576053","ma5":"3262.726","ma10":"3242.258","ma20":"3203.809","ma30":"3172.345","downs":"11.24","rose":"2.91"},{"id":null,"time":"2016-12-01","name":null,"open":"3257.03","close":"3273.31","high":"3279.67","low":"3256.26","turnover":"2616亿","vol":"237759864","ma5":"3269.040","ma10":"3248.744","ma20":"3211.027","ma30":"3178.640","downs":"-2.16","rose":"-15.11"},{"id":null,"time":"2016-12-02","name":null,"open":"3270.12","close":"3243.84","high":"3279.71","low":"3235.28","turnover":"2816亿","vol":"259567205","ma5":"3265.420","ma10":"3253.842","ma20":"3216.953","ma30":"3183.737","downs":"2.50","rose":"13.09"},{"id":null,"time":"2016-12-05","name":null,"open":"3203.78","close":"3204.71","high":"3219.52","low":"3194.88","turnover":"2386亿","vol":"223010394","ma5":"3250.962","ma10":"3252.498","ma20":"3220.522","ma30":"3186.285","downs":"-0.48","rose":"-66.34"},{"id":null,"time":"2016-12-06","name":null,"open":"3202.03","close":"3199.65","high":"3215.31","low":"3196.53","turnover":"1772亿","vol":"157572693","ma5":"3234.308","ma10":"3247.628","ma20":"3223.110","ma30":"3188.542","downs":"-18.30","rose":"-1.75"},{"id":null,"time":"2016-12-07","name":null,"open":"3198.48","close":"3222.24","high":"3222.43","low":"3189.49","turnover":"1894亿","vol":"169072847","ma5":"3228.750","ma10":"3245.738","ma20":"3227.803","ma30":"3192.073","downs":"-9.01","rose":"-3.55"},{"id":null,"time":"2016-12-08","name":null,"open":"3225.55","close":"3215.37","high":"3228.12","low":"3211.47","turnover":"1978亿","vol":"170710173","ma5":"3217.162","ma10":"3243.101","ma20":"3230.008","ma30":"3195.507","downs":"1.19","rose":"27.07"},{"id":null,"time":"2016-12-09","name":null,"open":"3209.34","close":"3232.88","high":"3244.80","low":"3207.04","turnover":"2264亿","vol":"204023956","ma5":"3214.970","ma10":"3240.195","ma20":"3231.850","ma30":"3199.794","downs":"-1.98","rose":"-16.21"},{"id":null,"time":"2016-12-12","name":null,"open":"3233.67","close":"3152.97","high":"3245.10","low":"3149.90","turnover":"2943亿","vol":"274708461","ma5":"3204.622","ma10":"3227.792","ma20":"3228.980","ma30":"3201.543","downs":"1.33","rose":"24.33"},{"id":null,"time":"2016-12-13","name":null,"open":"3138.99","close":"3155.04","high":"3162.50","low":"3118.71","turnover":"2032亿","vol":"185164495","ma5":"3195.700","ma10":"3215.004","ma20":"3226.382","ma30":"3202.630","downs":"-0.33","rose":"-94.68"},{"id":null,"time":"2016-12-14","name":null,"open":"3149.38","close":"3140.53","high":"3170.02","low":"3136.35","turnover":"2192亿","vol":"201358887","ma5":"3179.358","ma10":"3204.054","ma20":"3223.156","ma30":"3203.890","downs":"3.03","rose":"10.39"}]
     */

    private String msg;
    private String code;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : null
         * time : 2016-11-24
         * name : null
         * open : 3237.43
         * close : 3241.74
         * high : 3257.86
         * low : 3232.87
         * turnover : 2672亿
         * vol : 232324527
         * ma5 : 3228.448
         * ma10 : 3216.915
         * ma20 : 3171.711
         * ma30 : 3145.735
         * downs : 0.01
         * rose : 3237.43
         */

        private Object id;
        private String time;
        private Object name;
        private float open;
        private float close;
        private float high;
        private float low;
        private String turnover;
        private float vol;
        private String ma5;
        private String ma10;
        private String ma20;
        private String ma30;
        private Double downs;
        private Double rose;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
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

        public String getMa5() {
            return ma5;
        }

        public void setMa5(String ma5) {
            this.ma5 = ma5;
        }

        public String getMa10() {
            return ma10;
        }

        public void setMa10(String ma10) {
            this.ma10 = ma10;
        }

        public String getMa20() {
            return ma20;
        }

        public void setMa20(String ma20) {
            this.ma20 = ma20;
        }

        public String getMa30() {
            return ma30;
        }

        public void setMa30(String ma30) {
            this.ma30 = ma30;
        }

        public Double getDowns() {
            return downs;
        }

        public void setDowns(Double downs) {
            this.downs = downs;
        }

        public Double getRose() {
            return rose;
        }

        public void setRose(Double rose) {
            this.rose = rose;
        }
    }
}
