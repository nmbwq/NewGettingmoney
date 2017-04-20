package money.com.gettingmoney.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
public class HotSearchviewBeans {


    /**
     * result : [{"id":null,"stockId":"002068","isDelete":null,"createTime":null,"stockName":"黑猫股份"}]
     * msg : 成功
     * allNum : 20
     * status : 1
     */

    private String msg;
    private int allNum;
    private int status;

    @Override
    public String toString() {
        return "HotSearchviewBeans{" +
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
         * id : null
         * stockId : 002068
         * isDelete : null
         * createTime : null
         * stockName : 黑猫股份
         */

        private String id;
        private String stockId;
        private String isDelete;
        private String createTime;
        private String stockName;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id=" + id +
                    ", stockId='" + stockId + '\'' +
                    ", isDelete=" + isDelete +
                    ", createTime=" + createTime +
                    ", stockName='" + stockName + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStockId() {
            return stockId;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public Object getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public Object getCreateTime() {
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
    }
}
