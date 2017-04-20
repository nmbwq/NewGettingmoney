package money.com.gettingmoney.webutil.collect;

import java.util.HashMap;
import java.util.Map;

import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/2/23.
 */
public class CollectUtil implements IcollectUtil {

    //写网络方法--里面传递的参数  userNumber==token
    private String HOST = MyAppApiConfig.HOST_URL;
    /**
     * 添加收藏
     * @param userNumber
     * @param newsId
     * @param callBack
     */
    @Override
    public void addCollection(String userNumber, int newsId, MyXutils.XCallBack callBack) {
        String url = HOST+"news/addCollection";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("newsId", newsId);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 删除收藏
     * @param id
     * @param userNumber
     * @param callBack
     */
    @Override
    public void delCollection(int id, String userNumber, MyXutils.XCallBack callBack) {
        String url = HOST+"news/delCollection";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userNumber", userNumber);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 获取收藏列表
     * @param currentPage 页数
     * @param pageSize 每页加载多少数据
     * @param userNumber token
     * @param callBack xutils回调
     */
    @Override
    public void collectionList(LoadingDialog dialog,int currentPage, int pageSize, String userNumber, MyXutils.XCallBack callBack) {
        String url = HOST+"news/collectionList";
        Map<String, Object> map = new HashMap<>();
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("userNumber", userNumber);
        MyXutils.getInstance().post(dialog,url,map,callBack);
    }
}
