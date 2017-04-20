package money.com.gettingmoney.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 * 板块列表的实体类
 */
public class Beans  implements Serializable {

    /**
     * result : [{"domainCode":"mock","name":"mock","riseAndFall":14,"marketValue":98,"turnover":99,"rise":34,"fall":26,"led":"mock","ledRiseAndFall":14,"include":"mock"}]
     * allNum : 95
     */

    private int allNum;
    private List<ResultBean> result;

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * domainCode : mock
         * name : mock
         * riseAndFall : 14
         * marketValue : 98
         * turnover : 99
         * rise : 34
         * fall : 26
         * led : mock
         * ledRiseAndFall : 14
         * include : mock
         */

        private String domainCode;
        private String name;
        private String riseAndFall;
        private String marketValue;
        private String turnover;
        private String rise;
        private String fall;
        private String led;
        private String ledRiseAndFall;
        private String include;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "domainCode='" + domainCode + '\'' +
                    ", name='" + name + '\'' +
                    ", riseAndFall=" + riseAndFall +
                    ", marketValue=" + marketValue +
                    ", turnover=" + turnover +
                    ", rise=" + rise +
                    ", fall=" + fall +
                    ", led='" + led + '\'' +
                    ", ledRiseAndFall=" + ledRiseAndFall +
                    ", include='" + include + '\'' +
                    '}';
        }

        public String getDomainCode() {
            return domainCode;
        }

        public void setDomainCode(String domainCode) {
            this.domainCode = domainCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRiseAndFall() {
            return riseAndFall;
        }

        public void setRiseAndFall(String riseAndFall) {
            this.riseAndFall = riseAndFall;
        }

        public String getMarketValue() {
            return marketValue;
        }

        public void setMarketValue(String marketValue) {
            this.marketValue = marketValue;
        }

        public String getTurnover() {
            return turnover;
        }

        public void setTurnover(String turnover) {
            this.turnover = turnover;
        }

        public String getRise() {
            return rise;
        }

        public void setRise(String rise) {
            this.rise = rise;
        }

        public String getFall() {
            return fall;
        }

        public void setFall(String fall) {
            this.fall = fall;
        }

        public String getLed() {
            return led;
        }

        public void setLed(String led) {
            this.led = led;
        }

        public String getLedRiseAndFall() {
            return ledRiseAndFall;
        }

        public void setLedRiseAndFall(String ledRiseAndFall) {
            this.ledRiseAndFall = ledRiseAndFall;
        }

        public String getInclude() {
            return include;
        }

        public void setInclude(String include) {
            this.include = include;
        }
    }
}
