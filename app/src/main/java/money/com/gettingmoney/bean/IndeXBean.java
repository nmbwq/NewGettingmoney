package money.com.gettingmoney.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 * 指数列表
 */
public class IndeXBean  implements Serializable {


    /**
     * msg : 成功
     * listResponses1 : [{"news":"3281.60","downs":"11.29","name":"上证指数","id":"000001","type":1,"time":null,"rose":"0.35%\""},{"news":"3435.61","downs":"10.99","name":"A股指数","id":"000002","type":1,"time":null,"rose":"0.32%\""},{"news":"344.95","downs":"-0.35","name":"B股指数","id":"000003","type":1,"time":null,"rose":"-0.10%\""},{"news":"5847.57","downs":"15.97","name":"上证380","id":"000009","type":1,"time":null,"rose":"0.27%\""},{"news":"7613.23","downs":"21.24","name":"上证180","id":"000010","type":1,"time":null,"rose":"0.28%\""},{"news":"2781.51","downs":"1.91","name":"红利指数","id":"000015","type":1,"time":null,"rose":"0.07%\""},{"news":"2390.45","downs":"6.19","name":"上证50","id":"000016","type":1,"time":null,"rose":"0.26%\""},{"news":"3514.31","downs":"10.42","name":"沪深300","id":"000300","type":1,"time":null,"rose":"0.30%\""},{"news":"8542.32","downs":"20.31","name":"中证1000","id":"000852","type":1,"time":null,"rose":"0.24%\""},{"news":"3335.07","downs":"11.20","name":"中证100","id":"000903","type":1,"time":null,"rose":"0.34%\""},{"news":"6559.49","downs":"19.52","name":"中证500","id":"000905","type":1,"time":null,"rose":"0.30%\""},{"news":"4045.57","downs":"11.59","name":"中证800","id":"000906","type":1,"time":null,"rose":"0.29%\""}]
     * listResponses3 : [{"news":"20648.15","downs":"-41.09","name":"道琼斯工业平均","id":"DJIA","type":6,"time":null,"rose":"-0.20%\""},{"news":"5091.85","downs":"-9.28","name":"法国CAC","id":"FCHI","type":6,"time":null,"rose":"-0.18%\""},{"news":"7331.68","downs":"9.86","name":"英国富时100","id":"FTSE","type":6,"time":null,"rose":"0.13%\""},{"news":"12217.54","downs":"-64.80","name":"德国DAX","id":"GDAXI","type":6,"time":null,"rose":"-0.53%\""},{"news":"24271.69","downs":"-129.11","name":"恒生指数","id":"HSI","type":5,"time":null,"rose":"-0.53%\""},{"news":null,"downs":null,"name":"纳斯达克","id":"IXIC","type":6,"time":null,"rose":null},{"news":"2152.75","downs":"-8.10","name":"南韩综合","id":"KS11","type":6,"time":null,"rose":"-0.37%\""},{"news":"18597.06","downs":"-264.21","name":"日经225","id":"N225","type":6,"time":null,"rose":"-1.40%\""},{"news":"1152.49","downs":"16.51","name":"俄罗斯RTS","id":"RTS","type":6,"time":null,"rose":"1.45%\""},{"news":"29891.21","downs":"-83.03","name":"孟买Sensex30","id":"SENSEX","type":6,"time":null,"rose":"-0.28%\""},{"news":"2352.95","downs":"-7.21","name":"标普500","id":"SPX","type":6,"time":null,"rose":"-0.31%\""},{"news":"9897.80","downs":"-51.68","name":"台湾加权","id":"TWII","type":6,"time":null,"rose":"-0.52%\""},{"news":"10290.49","downs":"-74.83","name":"恒生国企指数","id":"HSCEI","type":5,"time":null,"rose":"-0.72%\""}]
     * listResponses2 : [{"news":"10656.17","downs":"29.04","name":"深证成指","id":"399001","type":2,"time":null,"rose":"0.27%\""},{"news":"4785.35","downs":"6.06","name":"深证100R","id":"399004","type":2,"time":null,"rose":"0.13%\""},{"news":"6872.39","downs":"10.59","name":"中小板指","id":"399005","type":2,"time":null,"rose":"0.15%\""},{"news":"1944.23","downs":"1.07","name":"创业板指","id":"399006","type":2,"time":null,"rose":"0.06%\""},{"news":"1478.18","downs":"1.79","name":"中小300","id":"399008","type":2,"time":null,"rose":"0.12%\""},{"news":"3118.54","downs":"4.27","name":"创业300","id":"399012","type":2,"time":null,"rose":"0.14%\""},{"news":"11841.30","downs":"22.32","name":"中小板综","id":"399101","type":2,"time":null,"rose":"0.19%\""},{"news":"2595.29","downs":"8.08","name":"创业板综","id":"399102","type":2,"time":null,"rose":"0.31%\""}]
     * status : 1
     */

    private String msg;
    private int status;
    private List<ListResponses1Bean> listResponses1;
    private List<ListResponses3Bean> listResponses3;
    private List<ListResponses2Bean> listResponses2;

    @Override
    public String toString() {
        return "IndeXBean{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", listResponses1=" + listResponses1 +
                ", listResponses3=" + listResponses3 +
                ", listResponses2=" + listResponses2 +
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

    public List<ListResponses1Bean> getListResponses1() {
        return listResponses1;
    }

    public void setListResponses1(List<ListResponses1Bean> listResponses1) {
        this.listResponses1 = listResponses1;
    }

    public List<ListResponses3Bean> getListResponses3() {
        return listResponses3;
    }

    public void setListResponses3(List<ListResponses3Bean> listResponses3) {
        this.listResponses3 = listResponses3;
    }

    public List<ListResponses2Bean> getListResponses2() {
        return listResponses2;
    }

    public void setListResponses2(List<ListResponses2Bean> listResponses2) {
        this.listResponses2 = listResponses2;
    }

    public static class ListResponses1Bean {
        /**
         * news : 3281.60
         * downs : 11.29
         * name : 上证指数
         * id : 000001
         * type : 1
         * time : null
         * rose : 0.35%"
         */

        private String news;
        private String downs;
        private String name;
        private String id;
        private int type;
        private Object time;
        private String rose;

        @Override
        public String toString() {
            return "ListResponses1Bean{" +
                    "news='" + news + '\'' +
                    ", downs='" + downs + '\'' +
                    ", name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", type=" + type +
                    ", time=" + time +
                    ", rose='" + rose + '\'' +
                    '}';
        }

        public String getNews() {
            return news;
        }

        public void setNews(String news) {
            this.news = news;
        }

        public String getDowns() {
            return downs;
        }

        public void setDowns(String downs) {
            this.downs = downs;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public String getRose() {
            return rose;
        }

        public void setRose(String rose) {
            this.rose = rose;
        }
    }

    public static class ListResponses3Bean {
        /**
         * news : 20648.15
         * downs : -41.09
         * name : 道琼斯工业平均
         * id : DJIA
         * type : 6
         * time : null
         * rose : -0.20%"
         */

        private String news;
        private String downs;
        private String name;
        private String id;
        private int type;
        private Object time;
        private String rose;

        @Override
        public String toString() {
            return "ListResponses3Bean{" +
                    "news='" + news + '\'' +
                    ", downs='" + downs + '\'' +
                    ", name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", type=" + type +
                    ", time=" + time +
                    ", rose='" + rose + '\'' +
                    '}';
        }

        public String getNews() {
            return news;
        }

        public void setNews(String news) {
            this.news = news;
        }

        public String getDowns() {
            return downs;
        }

        public void setDowns(String downs) {
            this.downs = downs;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public String getRose() {
            return rose;
        }

        public void setRose(String rose) {
            this.rose = rose;
        }
    }

    public static class ListResponses2Bean {
        /**
         * news : 10656.17
         * downs : 29.04
         * name : 深证成指
         * id : 399001
         * type : 2
         * time : null
         * rose : 0.27%"
         */

        private String news;
        private String downs;
        private String name;
        private String id;
        private int type;
        private Object time;
        private String rose;

        @Override
        public String toString() {
            return "ListResponses2Bean{" +
                    "news='" + news + '\'' +
                    ", downs='" + downs + '\'' +
                    ", name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", type=" + type +
                    ", time=" + time +
                    ", rose='" + rose + '\'' +
                    '}';
        }

        public String getNews() {
            return news;
        }

        public void setNews(String news) {
            this.news = news;
        }

        public String getDowns() {
            return downs;
        }

        public void setDowns(String downs) {
            this.downs = downs;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public String getRose() {
            return rose;
        }

        public void setRose(String rose) {
            this.rose = rose;
        }
    }
}
