package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import money.com.gettingmoney.R;

public class AboutActivity extends BaseActivity {

    /**
     * 关于我们今天是4月
     * @param savedInstanceState
     *
     */
    //今天是4月20号哈哈哈再过10天就放假了545325468512
    private TextView head_title,head_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("关于我们");
        head_right.setVisibility(View.GONE);

    }
}
