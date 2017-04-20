package money.com.gettingmoney.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.Kline.kilneUtil;

public class KLineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kline);
        Log.i("hahahfaad", "这种情况下是否启动了线程11");
        init();
    }

    void  init(){

        new Thread(runnable).run();


    }


    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            Log.i("hahahfaad","这种情况下是否启动了线程22");
            kilneUtil util=new kilneUtil();
            /**
             * K线图
             * @param stockId  股票id
             * @param type  类型 0,(日)/1,(分时)/2,(周)/3,(月)
             * @param time 日、周、月 类型 可通过传递 年份获取某年的数据。分时直接传 last
             * @param callBack
             */
            util.addStockHistory("000002", 2, "last", new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                   //获取服务器返回的k限制--分时--
                    Log.i("hahahfaad",result.toString());

                }
            });


        }
    };
}

