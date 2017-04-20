package money.com.gettingmoney.bean;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class TransBean {


    /**
     * result : {"supMoney":8.50414652397E9,"totleMoney":1.7012E10,"shareMoney":8.50785347603E9,"fallAndRise":"0.69%","value":2069476.022}
     * msg : 成功
     * status : 1
     */

    private ResultBean result;
    private String msg;
    private int status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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

    public static class ResultBean {
        /**
         * supMoney : 8.50414652397E9
         * totleMoney : 1.7012E10
         * shareMoney : 8.50785347603E9
         * fallAndRise : 0.69%
         * value : 2069476.022
         */

        private double supMoney;
        private double totleMoney;
        private double shareMoney;
        private String fallAndRise;
        private double value;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "supMoney=" + supMoney +
                    ", totleMoney=" + totleMoney +
                    ", shareMoney=" + shareMoney +
                    ", fallAndRise='" + fallAndRise + '\'' +
                    ", value=" + value +
                    '}';
        }

        public double getSupMoney() {
            return supMoney;
        }

        public void setSupMoney(double supMoney) {
            this.supMoney = supMoney;
        }

        public double getTotleMoney() {
            return totleMoney;
        }

        public void setTotleMoney(double totleMoney) {
            this.totleMoney = totleMoney;
        }

        public double getShareMoney() {
            return shareMoney;
        }

        public void setShareMoney(double shareMoney) {
            this.shareMoney = shareMoney;
        }

        public String getFallAndRise() {
            return fallAndRise;
        }

        public void setFallAndRise(String fallAndRise) {
            this.fallAndRise = fallAndRise;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}
