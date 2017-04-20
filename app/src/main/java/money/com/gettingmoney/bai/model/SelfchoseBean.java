package money.com.gettingmoney.bai.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
public class SelfchoseBean {


    /**
     * result : [{"userSharesId":16,"sharesCode":"000716","createTime":1491973727000,"stockName":"黑芝麻","now":"0.0","differRange":"-","differ":"-"},{"userSharesId":15,"sharesCode":"000001","createTime":1491967238000,"stockName":"平安银行","now":"9.16","differRange":"-0.11%","differ":"-0.01"},{"userSharesId":14,"sharesCode":"000538","createTime":1491966946000,"stockName":"云南白药","now":"88.860001","differRange":"-0.28%","differ":"-0.24"}]
     * msg : 查找成功
     * allNum : 3
     * status : 1
     */

    private String msg;
    private int allNum;
    private int status;

    @Override
    public String toString() {
        return "SelfchoseBean{" +
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
         * userSharesId : 16
         * sharesCode : 000716
         * createTime : 1491973727000
         * stockName : 黑芝麻
         * now : 0.0
         * differRange : -
         * differ : -
         */

        private int userSharesId;
        private String sharesCode;
        private String createTime;
        private String stockName;
        private String now;
        private String differRange;
        private String differ;

        public int getUserSharesId() {
            return userSharesId;
        }

        public void setUserSharesId(int userSharesId) {
            this.userSharesId = userSharesId;
        }

        public String getSharesCode() {
            return sharesCode;
        }

        public void setSharesCode(String sharesCode) {
            this.sharesCode = sharesCode;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public String getNow() {
            return now;
        }

        public void setNow(String now) {
            this.now = now;
        }

        public String getDifferRange() {
            return differRange;
        }

        public void setDifferRange(String differRange) {
            this.differRange = differRange;
        }

        public String getDiffer() {
            return differ;
        }

        public void setDiffer(String differ) {
            this.differ = differ;
        }
    }
}
