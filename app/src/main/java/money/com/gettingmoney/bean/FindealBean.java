package money.com.gettingmoney.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 * 撤单列表
 */
public class FindealBean implements Serializable{

    /**
     * result : [{"dealId":16,"stockId":"000538","userId":null,"amount":88,"countVol":200,"state":0,"type":0,"createTime":"2017-04-10 09:25:47.0","total":17600,"startTime":null,"endTime":null,"stockName":"云南白药","now":88.860001,"riseAndFallValue":"-172.00","riseAndFall":"-0.97%"},{"dealId":17,"stockId":"000538","userId":null,"amount":88,"countVol":200,"state":0,"type":0,"createTime":"2017-04-10 09:25:48.0","total":17600,"startTime":null,"endTime":null,"stockName":"云南白药","now":88.860001,"riseAndFallValue":"-172.00","riseAndFall":"-0.97%"},{"dealId":18,"stockId":"000538","userId":null,"amount":91,"countVol":400,"state":0,"type":0,"createTime":"2017-04-10 11:23:38.0","total":36400,"startTime":null,"endTime":null,"stockName":"云南白药","now":88.860001,"riseAndFallValue":"856.00","riseAndFall":"2.41%"},{"dealId":19,"stockId":"000538","userId":null,"amount":91,"countVol":400,"state":0,"type":0,"createTime":"2017-04-10 11:23:39.0","total":36400,"startTime":null,"endTime":null,"stockName":"云南白药","now":88.860001,"riseAndFallValue":"856.00","riseAndFall":"2.41%"},{"dealId":20,"stockId":"000538","userId":null,"amount":91,"countVol":400,"state":0,"type":0,"createTime":"2017-04-10 11:23:39.0","total":36400,"startTime":null,"endTime":null,"stockName":"云南白药","now":88.860001,"riseAndFallValue":"856.00","riseAndFall":"2.41%"},{"dealId":21,"stockId":"000538","userId":null,"amount":91,"countVol":400,"state":0,"type":0,"createTime":"2017-04-10 11:23:48.0","total":36400,"startTime":null,"endTime":null,"stockName":"云南白药","now":88.860001,"riseAndFallValue":"856.00","riseAndFall":"2.41%"},{"dealId":22,"stockId":"000538","userId":null,"amount":89,"countVol":400,"state":0,"type":0,"createTime":"2017-04-10 11:32:00.0","total":35600,"startTime":null,"endTime":null,"stockName":"云南白药","now":88.860001,"riseAndFallValue":"56.00","riseAndFall":"0.16%"},{"dealId":23,"stockId":"002615","userId":null,"amount":18,"countVol":400,"state":0,"type":0,"createTime":"2017-04-10 13:26:27.0","total":7200,"startTime":null,"endTime":null,"stockName":"哈尔斯","now":18.469999,"riseAndFallValue":"-188.00","riseAndFall":"-2.54%"}]
     * msg : 查询成功
     * allNum : 8
     * status : 1
     */

    private String msg;
    private int allNum;
    private int status;

    @Override
    public String toString() {
        return "FindealBean{" +
                "msg='" + msg + '\'' +
                ", allNum=" + allNum +
                ", status=" + status +
                ", result=" + result +
                '}';
    }

    private List<ResultBean> result;

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
         * dealId : 16
         * stockId : 000538
         * userId : null
         * amount : 88.0
         * countVol : 200
         * state : 0
         * type : 0
         * createTime : 2017-04-10 09:25:47.0
         * total : 17600.0
         * startTime : null
         * endTime : null
         * stockName : 云南白药
         * now : 88.860001
         * riseAndFallValue : -172.00
         * riseAndFall : -0.97%
         */

        private int dealId;
        private String stockId;
        private Object userId;
        private double amount;
        private int countVol;
        private int state;
        private int type;
        private String createTime;
        private double total;
        private Object startTime;
        private Object endTime;
        private String stockName;
        private double now;
        private String riseAndFallValue;
        private String riseAndFall;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "dealId=" + dealId +
                    ", stockId='" + stockId + '\'' +
                    ", userId=" + userId +
                    ", amount=" + amount +
                    ", countVol=" + countVol +
                    ", state=" + state +
                    ", type=" + type +
                    ", createTime='" + createTime + '\'' +
                    ", total=" + total +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", stockName='" + stockName + '\'' +
                    ", now=" + now +
                    ", riseAndFallValue='" + riseAndFallValue + '\'' +
                    ", riseAndFall='" + riseAndFall + '\'' +
                    '}';
        }

        public int getDealId() {
            return dealId;
        }

        public void setDealId(int dealId) {
            this.dealId = dealId;
        }

        public String getStockId() {
            return stockId;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getCountVol() {
            return countVol;
        }

        public void setCountVol(int countVol) {
            this.countVol = countVol;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public double getNow() {
            return now;
        }

        public void setNow(double now) {
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
}
