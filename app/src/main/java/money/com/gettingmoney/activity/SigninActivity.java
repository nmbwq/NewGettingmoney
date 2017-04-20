package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.utils.ToastUtils;
import money.com.gettingmoney.bean.User;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.other.OtherUtil;
import money.com.gettingmoney.webutil.user.UserUtil;
import money.com.gettingmoney.weiget.LoadingDialog;
import money.com.gettingmoney.weiget.TextViewTimeCountUtil;

public class SigninActivity extends money.com.gettingmoney.bai.main.base.BaseActivity implements View.OnClickListener {

    /**
     * 注册和忘记密码
     */
    //true 忘记密码操作  false 注册操作
    public static boolean flag = false;

    private TextView head_title;
    private EditText passw_phone, regiest_code, passw_new1, passw_new2;
    private TextView siagnin_sure, get_code;
    private LoadingDialog dialog;
    private String phone, code;
    private User user;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    long time = 60000;
                    //倒计时
                    TextViewTimeCountUtil tcu = new TextViewTimeCountUtil(SigninActivity.this,
                            time, 1000,
                            get_code, 1);
                    tcu.start();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (flag) {
            toolBar = new MyToolBar(this, R.mipmap.back_white, "忘记密码", "");
        } else {
            toolBar = new MyToolBar(this, R.mipmap.back_white, "注册", "");
        }

        setContentView(requestView(R.layout.activity_signin));


        initView();
    }

    @Override
    public void requestInit() {

    }

    private void initView() {
        passw_phone = (EditText) this.findViewById(R.id.passw_phone);
        regiest_code = (EditText) this.findViewById(R.id.regiest_code);
        passw_new1 = (EditText) this.findViewById(R.id.passw_new1);
        passw_new2 = (EditText) this.findViewById(R.id.passw_new2);
        siagnin_sure = (TextView) this.findViewById(R.id.siagnin_sure);
        get_code = (TextView) this.findViewById(R.id.get_code);

        get_code.setOnClickListener(this);
        siagnin_sure.setOnClickListener(this);
        if (flag){
            siagnin_sure.setText("确认");
        }else {
            siagnin_sure.setText("完成注册");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_code:
                phone = passw_phone.getText().toString();
                if (phone.length() == 11) {
                    new Thread(getcode).run();
                    Log.i("是否走了这一步", "zouyibu");
                } else {
                    ToastUtils.MyToast(SigninActivity.this, "请填写正确的手机号");
                }
                break;
            case R.id.siagnin_sure:
                code = regiest_code.getText().toString();
                String pwd1 = passw_new1.getText().toString();
                String pwd2 = passw_new2.getText().toString();
                if (!code.equals("") && !pwd1.equals("") && !pwd2.equals("") && phone.length() == 11) {
                    if (pwd1.equals(pwd2)) {
                        if (flag){
                            dialog = new LoadingDialog(SigninActivity.this, "修改中...");
                            dialog.show();
                            user = new User();
                            user.setCode(phone);
                            user.setPwd(pwd1);
                            new Thread(passReset).run();
                        }else {
                            dialog = new LoadingDialog(SigninActivity.this, "正在注册");
                            dialog.show();
                            user = new User();
                            user.setCode(phone);
                            user.setPwd(pwd1);
                            new Thread(signin).run();
                        }
                    } else {
                        ToastUtils.MyToast(SigninActivity.this, "两次密码不匹配");
                    }
                } else {
                    ToastUtils.MyToast(SigninActivity.this, "请把资料填写完整");
                }
                break;
        }
    }

    Runnable getcode = new Runnable() {
        @Override
        public void run() {
            OtherUtil util = new OtherUtil();
            util.sendSMS(phone, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    Log.i("发送成功", result);
                    ToastUtils.MyToast(SigninActivity.this, "验证码发送成功,请注意查收");
                    Message message = new Message();
                    message.what = 100;
                    handler.sendMessage(message);
                }
            });
        }
    };

    Runnable signin = new Runnable() {
        @Override
        public void run() {
            UserUtil util = new UserUtil();
            util.userSign(dialog, user, code, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    ToastUtils.MyToast(SigninActivity.this, "注册成功");
                    finish();
                }
            });
        }
    };
    Runnable passReset = new Runnable() {
        @Override
        public void run() {
            UserUtil util = new UserUtil();
            util.passReset(dialog, user, code, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    ToastUtils.MyToast(SigninActivity.this, "密码设置成功");
                    finish();
                }
            });
        }
    };
}
