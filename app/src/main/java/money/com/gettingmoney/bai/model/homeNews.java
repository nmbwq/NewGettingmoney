package money.com.gettingmoney.bai.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/18 0018.
 */

public class homeNews implements Serializable {

    /**
     * msg : 新闻列表返回成功
     * newsList : [{"id":281,"title":"证监会：核发10家IPO批文 筹资总额不超过51亿元","type":1,"content":"<p class=\"art_t\">　　3月17日，我会按法定程序核准了以下企业的首发申请：<\/p>, <p class=\"art_t\">　　上交所主板：青岛利群百货集团股份有限公司、广东联泰环保股份有限公司、福建坤彩材料科技股份有限公司、江阴江化微电子材料股份有限公司、石家庄科林电气股份有限公；<\/p>, <p class=\"art_t\">　　深交所<a href=\"http://finance.sina.cn/finance_zt/keyword.d.html?vt=4&amp;k=中小板\">中小板<\/a>：湖北瀛通通讯线材股份有限公司、杭州星帅尔电器股份有限公司；<\/p>, <p class=\"art_t\">　　深交所创业板：深圳市广和通无线股份有限公司、广东凯普生物科技股份有限公司、浙江扬帆新材料股份有限公司。<\/p>, <p class=\"art_t\">　　本次将有江阴江化微电子材料股份有限公司、深圳市广和通无线股份有限公司等2家公司直接定价发行。上述企业及其承销商将分别与沪深交易所协商确定发行日程，并陆续刊登招股文件。上述企业筹资总额不超过51亿元。<\/p>","status":2,"time":"2017-03-17 21:00:59","pic":"","src":"中国证监会网站","url":"http://finance.sina.com.cn/stock/y/2017-03-17/doc-ifycnpiu8960038.shtml","count":0,"checkCollection":0,"commentCount":2,"deleted":0}]
     */

    private String msg;
    private List<NewsListBean> newsList;

    @Override
    public String toString() {
        return "homeNews{" +
                "msg='" + msg + '\'' +
                ", newsList=" + newsList +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewsListBean> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsListBean> newsList) {
        this.newsList = newsList;
    }

    public static class NewsListBean {
        /**
         * id : 281
         * title : 证监会：核发10家IPO批文 筹资总额不超过51亿元
         * type : 1
         * content : <p class="art_t">　　3月17日，我会按法定程序核准了以下企业的首发申请：</p>, <p class="art_t">　　上交所主板：青岛利群百货集团股份有限公司、广东联泰环保股份有限公司、福建坤彩材料科技股份有限公司、江阴江化微电子材料股份有限公司、石家庄科林电气股份有限公；</p>, <p class="art_t">　　深交所<a href="http://finance.sina.cn/finance_zt/keyword.d.html?vt=4&amp;k=中小板">中小板</a>：湖北瀛通通讯线材股份有限公司、杭州星帅尔电器股份有限公司；</p>, <p class="art_t">　　深交所创业板：深圳市广和通无线股份有限公司、广东凯普生物科技股份有限公司、浙江扬帆新材料股份有限公司。</p>, <p class="art_t">　　本次将有江阴江化微电子材料股份有限公司、深圳市广和通无线股份有限公司等2家公司直接定价发行。上述企业及其承销商将分别与沪深交易所协商确定发行日程，并陆续刊登招股文件。上述企业筹资总额不超过51亿元。</p>
         * status : 2
         * time : 2017-03-17 21:00:59
         * pic :
         * src : 中国证监会网站
         * url : http://finance.sina.com.cn/stock/y/2017-03-17/doc-ifycnpiu8960038.shtml
         * count : 0
         * checkCollection : 0
         * commentCount : 2
         * deleted : 0
         */

        private int id;
        private String title;
        private int type;
        private String content;
        private int status;
        private String time;
        private String pic;
        private String src;
        private String url;
        private int count;
        private int checkCollection;
        private int commentCount;
        private int deleted;

        @Override
        public String toString() {
            return "NewsListBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", content='" + content + '\'' +
                    ", status=" + status +
                    ", time='" + time + '\'' +
                    ", pic='" + pic + '\'' +
                    ", src='" + src + '\'' +
                    ", url='" + url + '\'' +
                    ", count=" + count +
                    ", checkCollection=" + checkCollection +
                    ", commentCount=" + commentCount +
                    ", deleted=" + deleted +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCheckCollection() {
            return checkCollection;
        }

        public void setCheckCollection(int checkCollection) {
            this.checkCollection = checkCollection;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }
    }
}
