package money.com.gettingmoney.bai.model;

/**
 * Created by Administrator on 2017/4/12.
 */
public class DetialskstockBean {


    /**
     * msg : 获取成功
     * buy5 : {"buy1Hand":"0","buy5Money":"0.00","buy2Hand":"0","buy2Money":"0.00","buy3Money":"0.00","buy4Hand":"0","buy1Money":"0.00","buy4Money":"0.00","buy3Hand":"0","buy5Hand":"0"}
     * flag : 0
     * sell5 : {"sell2Money":"0.00","sell3Hand":"0","sell5Money":"0.00","sell4Money":"0.00","sell3Money":"0.00","sell1Hand":"0","sell2Hand":"0","sell5Hand":"0","sell1Money":"0.00","sell4Hand":"0"}
     * stockDetail : {"stockId":"000716","hight":0,"low":0,"open":0,"preClose":8.06,"amount":"0","lastUpdateTime":"2017-04-12 15:26:23","avgPrice":63.74,"theOuter":0,"innerDisc":0,"limitUp":8.87,"limitDown":7.25,"quantity":0,"entrust":0,"pbratio":2.88,"circulatedShares":4359525525,"marketValue":5139092086}
     * stock : {"stockId":"000716","stockName":"黑芝麻","stockCode":"000716.SZ","type":2,"now":"0.0","volume":"0.0","differRange":"-","differ":"-","lastUpdateTime":"2017-04-12 15:29:29.0"}
     * status : 1
     */

    private String msg;
    private Buy5Bean buy5;
    private int flag;
    private Sell5Bean sell5;
    private StockDetailBean stockDetail;
    private StockBean stock;
    private int status;

    @Override
    public String toString() {
        return "DetialskstockBean{" +
                "msg='" + msg + '\'' +
                ", buy5=" + buy5 +
                ", flag=" + flag +
                ", sell5=" + sell5 +
                ", stockDetail=" + stockDetail +
                ", stock=" + stock +
                ", status=" + status +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Buy5Bean getBuy5() {
        return buy5;
    }

    public void setBuy5(Buy5Bean buy5) {
        this.buy5 = buy5;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Sell5Bean getSell5() {
        return sell5;
    }

    public void setSell5(Sell5Bean sell5) {
        this.sell5 = sell5;
    }

    public StockDetailBean getStockDetail() {
        return stockDetail;
    }

    public void setStockDetail(StockDetailBean stockDetail) {
        this.stockDetail = stockDetail;
    }

    public StockBean getStock() {
        return stock;
    }

    public void setStock(StockBean stock) {
        this.stock = stock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class Buy5Bean {
        /**
         * buy1Hand : 0
         * buy5Money : 0.00
         * buy2Hand : 0
         * buy2Money : 0.00
         * buy3Money : 0.00
         * buy4Hand : 0
         * buy1Money : 0.00
         * buy4Money : 0.00
         * buy3Hand : 0
         * buy5Hand : 0
         */

        private String buy1Hand;
        private String buy5Money;
        private String buy2Hand;
        private String buy2Money;
        private String buy3Money;
        private String buy4Hand;
        private String buy1Money;
        private String buy4Money;
        private String buy3Hand;
        private String buy5Hand;

        @Override
        public String toString() {
            return "Buy5Bean{" +
                    "buy1Hand='" + buy1Hand + '\'' +
                    ", buy5Money='" + buy5Money + '\'' +
                    ", buy2Hand='" + buy2Hand + '\'' +
                    ", buy2Money='" + buy2Money + '\'' +
                    ", buy3Money='" + buy3Money + '\'' +
                    ", buy4Hand='" + buy4Hand + '\'' +
                    ", buy1Money='" + buy1Money + '\'' +
                    ", buy4Money='" + buy4Money + '\'' +
                    ", buy3Hand='" + buy3Hand + '\'' +
                    ", buy5Hand='" + buy5Hand + '\'' +
                    '}';
        }

        public String getBuy1Hand() {
            return buy1Hand;
        }

        public void setBuy1Hand(String buy1Hand) {
            this.buy1Hand = buy1Hand;
        }

        public String getBuy5Money() {
            return buy5Money;
        }

        public void setBuy5Money(String buy5Money) {
            this.buy5Money = buy5Money;
        }

        public String getBuy2Hand() {
            return buy2Hand;
        }

        public void setBuy2Hand(String buy2Hand) {
            this.buy2Hand = buy2Hand;
        }

        public String getBuy2Money() {
            return buy2Money;
        }

        public void setBuy2Money(String buy2Money) {
            this.buy2Money = buy2Money;
        }

        public String getBuy3Money() {
            return buy3Money;
        }

        public void setBuy3Money(String buy3Money) {
            this.buy3Money = buy3Money;
        }

        public String getBuy4Hand() {
            return buy4Hand;
        }

        public void setBuy4Hand(String buy4Hand) {
            this.buy4Hand = buy4Hand;
        }

        public String getBuy1Money() {
            return buy1Money;
        }

        public void setBuy1Money(String buy1Money) {
            this.buy1Money = buy1Money;
        }

        public String getBuy4Money() {
            return buy4Money;
        }

        public void setBuy4Money(String buy4Money) {
            this.buy4Money = buy4Money;
        }

        public String getBuy3Hand() {
            return buy3Hand;
        }

        public void setBuy3Hand(String buy3Hand) {
            this.buy3Hand = buy3Hand;
        }

        public String getBuy5Hand() {
            return buy5Hand;
        }

        public void setBuy5Hand(String buy5Hand) {
            this.buy5Hand = buy5Hand;
        }
    }

    public static class Sell5Bean {
        /**
         * sell2Money : 0.00
         * sell3Hand : 0
         * sell5Money : 0.00
         * sell4Money : 0.00
         * sell3Money : 0.00
         * sell1Hand : 0
         * sell2Hand : 0
         * sell5Hand : 0
         * sell1Money : 0.00
         * sell4Hand : 0
         */

        private String sell2Money;
        private String sell3Hand;
        private String sell5Money;
        private String sell4Money;
        private String sell3Money;
        private String sell1Hand;
        private String sell2Hand;
        private String sell5Hand;
        private String sell1Money;
        private String sell4Hand;

        @Override
        public String toString() {
            return "Sell5Bean{" +
                    "sell2Money='" + sell2Money + '\'' +
                    ", sell3Hand='" + sell3Hand + '\'' +
                    ", sell5Money='" + sell5Money + '\'' +
                    ", sell4Money='" + sell4Money + '\'' +
                    ", sell3Money='" + sell3Money + '\'' +
                    ", sell1Hand='" + sell1Hand + '\'' +
                    ", sell2Hand='" + sell2Hand + '\'' +
                    ", sell5Hand='" + sell5Hand + '\'' +
                    ", sell1Money='" + sell1Money + '\'' +
                    ", sell4Hand='" + sell4Hand + '\'' +
                    '}';
        }

        public String getSell2Money() {
            return sell2Money;
        }

        public void setSell2Money(String sell2Money) {
            this.sell2Money = sell2Money;
        }

        public String getSell3Hand() {
            return sell3Hand;
        }

        public void setSell3Hand(String sell3Hand) {
            this.sell3Hand = sell3Hand;
        }

        public String getSell5Money() {
            return sell5Money;
        }

        public void setSell5Money(String sell5Money) {
            this.sell5Money = sell5Money;
        }

        public String getSell4Money() {
            return sell4Money;
        }

        public void setSell4Money(String sell4Money) {
            this.sell4Money = sell4Money;
        }

        public String getSell3Money() {
            return sell3Money;
        }

        public void setSell3Money(String sell3Money) {
            this.sell3Money = sell3Money;
        }

        public String getSell1Hand() {
            return sell1Hand;
        }

        public void setSell1Hand(String sell1Hand) {
            this.sell1Hand = sell1Hand;
        }

        public String getSell2Hand() {
            return sell2Hand;
        }

        public void setSell2Hand(String sell2Hand) {
            this.sell2Hand = sell2Hand;
        }

        public String getSell5Hand() {
            return sell5Hand;
        }

        public void setSell5Hand(String sell5Hand) {
            this.sell5Hand = sell5Hand;
        }

        public String getSell1Money() {
            return sell1Money;
        }

        public void setSell1Money(String sell1Money) {
            this.sell1Money = sell1Money;
        }

        public String getSell4Hand() {
            return sell4Hand;
        }

        public void setSell4Hand(String sell4Hand) {
            this.sell4Hand = sell4Hand;
        }
    }

    public static class StockDetailBean {
        /**
         * stockId : 000716
         * hight : 0.0
         * low : 0.0
         * open : 0.0
         * preClose : 8.06
         * amount : 0
         * lastUpdateTime : 2017-04-12 15:26:23
         * avgPrice : 63.74
         * theOuter : 0.0
         * innerDisc : 0.0
         * limitUp : 8.87
         * limitDown : 7.25
         * quantity : 0.0
         * entrust : 0.0
         * pbratio : 2.88
         * circulatedShares : 4359525525
         * marketValue : 5139092086
         */

        private String stockId;
        private double hight;
        private double low;
        private double open;
        private double preClose;
        private String amount;
        private String lastUpdateTime;
        private double avgPrice;
        private double theOuter;
        private double innerDisc;
        private double limitUp;
        private double limitDown;
        private double quantity;
        private double entrust;
        private double pbratio;
        private long circulatedShares;
        private long marketValue;


        @Override
        public String toString() {
            return "StockDetailBean{" +
                    "stockId='" + stockId + '\'' +
                    ", hight=" + hight +
                    ", low=" + low +
                    ", open=" + open +
                    ", preClose=" + preClose +
                    ", amount='" + amount + '\'' +
                    ", lastUpdateTime='" + lastUpdateTime + '\'' +
                    ", avgPrice=" + avgPrice +
                    ", theOuter=" + theOuter +
                    ", innerDisc=" + innerDisc +
                    ", limitUp=" + limitUp +
                    ", limitDown=" + limitDown +
                    ", quantity=" + quantity +
                    ", entrust=" + entrust +
                    ", pbratio=" + pbratio +
                    ", circulatedShares=" + circulatedShares +
                    ", marketValue=" + marketValue +
                    '}';
        }

        public String getStockId() {
            return stockId;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public double getHight() {
            return hight;
        }

        public void setHight(double hight) {
            this.hight = hight;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getPreClose() {
            return preClose;
        }

        public void setPreClose(double preClose) {
            this.preClose = preClose;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public double getAvgPrice() {
            return avgPrice;
        }

        public void setAvgPrice(double avgPrice) {
            this.avgPrice = avgPrice;
        }

        public double getTheOuter() {
            return theOuter;
        }

        public void setTheOuter(double theOuter) {
            this.theOuter = theOuter;
        }

        public double getInnerDisc() {
            return innerDisc;
        }

        public void setInnerDisc(double innerDisc) {
            this.innerDisc = innerDisc;
        }

        public double getLimitUp() {
            return limitUp;
        }

        public void setLimitUp(double limitUp) {
            this.limitUp = limitUp;
        }

        public double getLimitDown() {
            return limitDown;
        }

        public void setLimitDown(double limitDown) {
            this.limitDown = limitDown;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public double getEntrust() {
            return entrust;
        }

        public void setEntrust(double entrust) {
            this.entrust = entrust;
        }

        public double getPbratio() {
            return pbratio;
        }

        public void setPbratio(double pbratio) {
            this.pbratio = pbratio;
        }

        public long getCirculatedShares() {
            return circulatedShares;
        }

        public void setCirculatedShares(long circulatedShares) {
            this.circulatedShares = circulatedShares;
        }

        public long getMarketValue() {
            return marketValue;
        }

        public void setMarketValue(long marketValue) {
            this.marketValue = marketValue;
        }
    }

    public static class StockBean {
        /**
         * stockId : 000716
         * stockName : 黑芝麻
         * stockCode : 000716.SZ
         * type : 2
         * now : 0.0
         * volume : 0.0
         * differRange : -
         * differ : -
         * lastUpdateTime : 2017-04-12 15:29:29.0
         */

        private String stockId;
        private String stockName;
        private String stockCode;
        private int type;
        private String now;
        private String volume;
        private String differRange;
        private String differ;
        private String lastUpdateTime;


        @Override
        public String toString() {
            return "StockBean{" +
                    "stockId='" + stockId + '\'' +
                    ", stockName='" + stockName + '\'' +
                    ", stockCode='" + stockCode + '\'' +
                    ", type=" + type +
                    ", now='" + now + '\'' +
                    ", volume='" + volume + '\'' +
                    ", differRange='" + differRange + '\'' +
                    ", differ='" + differ + '\'' +
                    ", lastUpdateTime='" + lastUpdateTime + '\'' +
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNow() {
            return now;
        }

        public void setNow(String now) {
            this.now = now;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
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

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }
    }
}
