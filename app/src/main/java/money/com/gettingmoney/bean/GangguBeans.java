package money.com.gettingmoney.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */
public class GangguBeans {


    /**
     * status : 51
     * msg : mock
     * response : {"hangSeng":[{"id":"mock","name":"mock","news":"mock","Rose":"mock","downs":"mock"}],"stockList":[{"stockId":"mock","stockName":"mock","stockCode":"mock","now":"mock","differRange":"mock","differ":"mock"}]}
     */

    private int status;
    private String msg;
    private ResponseBean response;

    @Override
    public String toString() {
        return "GangguBeans{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", response=" + response +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        private List<HangSengBean> hangSeng;
        private List<StockListBean> stockList;

        @Override
        public String toString() {
            return "ResponseBean{" +
                    "hangSeng=" + hangSeng +
                    ", stockList=" + stockList +
                    '}';
        }

        public List<HangSengBean> getHangSeng() {
            return hangSeng;
        }

        public void setHangSeng(List<HangSengBean> hangSeng) {
            this.hangSeng = hangSeng;
        }

        public List<StockListBean> getStockList() {
            return stockList;
        }

        public void setStockList(List<StockListBean> stockList) {
            this.stockList = stockList;
        }

        public static class HangSengBean {
            /**
             * id : mock
             * name : mock
             * news : mock
             * Rose : mock
             * downs : mock
             */

            private String id;
            private String name;
            private String news;
            private String Rose;
            private String downs;

            @Override
            public String toString() {
                return "HangSengBean{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", news='" + news + '\'' +
                        ", Rose='" + Rose + '\'' +
                        ", downs='" + downs + '\'' +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNews() {
                return news;
            }

            public void setNews(String news) {
                this.news = news;
            }

            public String getRose() {
                return Rose;
            }

            public void setRose(String Rose) {
                this.Rose = Rose;
            }

            public String getDowns() {
                return downs;
            }

            public void setDowns(String downs) {
                this.downs = downs;
            }
        }

        public static class StockListBean {
            /**
             * stockId : mock
             * stockName : mock
             * stockCode : mock
             * now : mock
             * differRange : mock
             * differ : mock
             */

            private String stockId;
            private String stockName;
            private String stockCode;
            private String now;
            private String differRange;
            private String differ;

            @Override
            public String toString() {
                return "StockListBean{" +
                        "stockId='" + stockId + '\'' +
                        ", stockName='" + stockName + '\'' +
                        ", stockCode='" + stockCode + '\'' +
                        ", now='" + now + '\'' +
                        ", differRange='" + differRange + '\'' +
                        ", differ='" + differ + '\'' +
                        '}';
            }

            public String getStockId() {
                return stockId;
            }

            public void setStockId(String stockId) {
                this.stockId = stockId;
            }

            public String getStockName() {
                return stockName;
            }

            public void setStockName(String stockName) {
                this.stockName = stockName;
            }

            public String getStockCode() {
                return stockCode;
            }

            public void setStockCode(String stockCode) {
                this.stockCode = stockCode;
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
}
