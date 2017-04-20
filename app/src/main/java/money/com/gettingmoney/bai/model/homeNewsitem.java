package money.com.gettingmoney.bai.model;

/**
 * Created by Administrator on 2017/4/11.
 */
public class homeNewsitem {

    private  int id;
    private String title;
    //    新闻类型
    private int type;
    //    新闻内容
    private String content;
    //    图片地址
    private String pic;
    //    未查看新闻的数量
    private int count;
    //   当前新闻评论数量
    private  String commentCount;
    //当前时间
    private String  time;

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public homeNewsitem() {
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "homeNewsitem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", pic='" + pic + '\'' +
                ", count=" + count +
                ", commentCount=" + commentCount +
                ", time='" + time + '\'' +
                '}';
    }
}
