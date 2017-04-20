package money.com.gettingmoney.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.AboutActivity;
import money.com.gettingmoney.activity.CollectActivity;
import money.com.gettingmoney.activity.HelpcenterActivity;
import money.com.gettingmoney.activity.MywalletActivity;
import money.com.gettingmoney.activity.SetActivity;
import money.com.gettingmoney.app.MoneyApplication;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.utils.ZhUtils;
import money.com.gettingmoney.bean.User;
import money.com.gettingmoney.util.ActivityJump;
import money.com.gettingmoney.util.JsonUitl;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.other.OtherUtil;
import money.com.gettingmoney.webutil.user.UserUtil;
import money.com.gettingmoney.weiget.CircleImageView;
import money.com.gettingmoney.weiget.LoadingDialog;

public class MyFragment extends BaseFragment implements View.OnClickListener {


    /**
     * 我的界面
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    private RelativeLayout mywallet_btn, myshare_btn, mycollect_btn, myabout_btn, myhelpcenter_btn, myset_btn;
    private View view;
    private Context context;
    private CircleImageView user_headimg;
    private LoadingDialog dialog;
    private String headpath;
    private String headurl;
    private User user;
    private PopupWindow selectpoupWindow;
    private TextView user_name;
    private LinearLayout my_parent;

    private String mPath;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    new Thread(uploadimg).run();
                    break;
                case 200:
                    ImageLoader.getInstance().displayImage(headurl, user_headimg);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        //整体的布局  包含自己设置的状态栏的布局
//        View view = requestView(inflater, R.layout.fragment_my);
//
        view = inflater.inflate(R.layout.fragment_my, null);
        context = MoneyApplication.getContext();

        //设置整体布局的属性 宽高 是垂直还是横向
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        //说白了 （就是手动加载一个状态栏的高度的布局）
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ZhUtils.getStatusBarHeight(getActivity())));
        linearLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.themeColor));
        layout.addView(view);
        layout.addView(linearLayout, 0);
        initView();
        return layout;
    }

    private void initView() {
        mywallet_btn = (RelativeLayout) view.findViewById(R.id.mywallet_btn);
        myshare_btn = (RelativeLayout) view.findViewById(R.id.myshare_btn);
        mycollect_btn = (RelativeLayout) view.findViewById(R.id.mycollect_btn);
        myabout_btn = (RelativeLayout) view.findViewById(R.id.myabout_btn);
        myhelpcenter_btn = (RelativeLayout) view.findViewById(R.id.myhelpcenter_btn);
        myset_btn = (RelativeLayout) view.findViewById(R.id.myset_btn);
        user_headimg = (CircleImageView) view.findViewById(R.id.user_headimg);
        user_name = (TextView) view.findViewById(R.id.user_name);
        my_parent = (LinearLayout) view.findViewById(R.id.my_parent);

        user = ShareUtil.getInstance().getUser(getActivity());
        ImageLoader.getInstance().displayImage(user.getHeadImg(), user_headimg);
        user_name.setText(user.getNickName());
        mywallet_btn.setOnClickListener(this);
        myshare_btn.setOnClickListener(this);
        mycollect_btn.setOnClickListener(this);
        myabout_btn.setOnClickListener(this);
        myhelpcenter_btn.setOnClickListener(this);
        myset_btn.setOnClickListener(this);
        user_headimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mywallet_btn:
                ActivityJump.jumpActivity(getActivity(), MywalletActivity.class);
                break;
            case R.id.myshare_btn:
                showupdataname();
                break;
            case R.id.mycollect_btn:
                ActivityJump.jumpActivity(getActivity(), CollectActivity.class);
                break;
            case R.id.myabout_btn:
                ActivityJump.jumpActivity(getActivity(), AboutActivity.class);
                break;
            case R.id.myhelpcenter_btn:
                ActivityJump.jumpActivity(getActivity(), HelpcenterActivity.class);
                break;
            case R.id.myset_btn:
                ActivityJump.jumpActivity(getActivity(), SetActivity.class);
                break;
            case R.id.user_headimg:
                Intent intent5 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent5, 101);
                break;
            case R.id.share_weixin:
                new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.WEIXIN)
                        .withText("hello")
                        .setCallback(umShareListener)
                        .share();
                break;
        }
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {

            return;
        }
        switch (requestCode) {
            case 101://从系统相册中返回的结果
            {

//			 Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
//			 user_headPortrait.setImageBitmap(cameraBitmap);
                Uri uri_DCIM = null;
                if (data.getData() != null) {
                    uri_DCIM = data.getData();
                } else {
                    uri_DCIM = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                }
                mPath = getRealFilePath(uri_DCIM);
     /*           Cursor cursor = getActivity().getContentResolver().query(uri_DCIM, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

                if (cursor != null) {
                    if (cursor.moveToFirst()) {
//                    headurl = "file://"+cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        headpath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    }
                }*/
                Log.d("Debug","上传图片的回调方法中的图片地址"+mPath);
                //相机照相  上传图片
                Bitmap bitmap = ZhUtils.ImageZip.decodeSampledBitmapFromPath(mPath, 480, 800);
                user_headimg.setImageBitmap(bitmap);

                dialog = new LoadingDialog(getActivity(), "正在上传");
                dialog.show();

                OtherUtil webutil = new OtherUtil();
                webutil.uploadImageToQiniu(mPath, ShareUtil.getInstance().gettoken(), new UpCompletionHandler() {
                    @Override
                    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                        if (responseInfo.isOK()) {
                            Log.i("qiniu", "Upload Success");
                            Log.i("上传之后返回", "http://oomw08wq9.bkt.clouddn.com/" + s + ".jpg");

                            headurl = "http://oomw08wq9.bkt.clouddn.com/" + s;
                            Message message = new Message();
                            message.what = 100;
                            handler.sendMessage(message);
                        } else {
                            dialog.close();
                            Toast.makeText(getActivity(), "图片上传失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
//
                break;
            }
        }
    }

    Runnable uploadimg = new Runnable() {
        @Override
        public void run() {
            UserUtil util = new UserUtil();

            user.setHeadImg(headurl);
            util.updateUser(dialog, ShareUtil.getInstance().getUserNumber(context), user, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    ShareUtil.getInstance().saveUser(context, JsonUitl.objectToString(user));
                    Message message = new Message();
                    message.what = 200;
                    handler.sendMessage(message);
                }
            });
        }
    };

    private void showupdataname() {
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.item_share,
                null);
        view2.findViewById(R.id.share_weixin).setOnClickListener(this);

        WindowManager wm2 = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        int width2 = wm2.getDefaultDisplay().getWidth();

        selectpoupWindow = new PopupWindow(view2);
        // 设置SelectPicPopupWindow弹出窗体的宽
        selectpoupWindow.setWidth(width2);
        // 设置SelectPicPopupWindow弹出窗体的高
        selectpoupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        selectpoupWindow.setAnimationStyle(R.style.clear_animstyle);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000);
        selectpoupWindow.setBackgroundDrawable(dw);
        WindowManager.LayoutParams lp2 = getActivity().getWindow().getAttributes();
        lp2.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp2);
        selectpoupWindow.setFocusable(true);
        selectpoupWindow.setTouchable(true);
        selectpoupWindow.showAtLocation(my_parent, Gravity.BOTTOM, 0, -100);

        selectpoupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        // 显示窗口
//			selectpoupWindow.showAtLocation(this.findViewById(R.id.fabu), 0, 0, 50);
        view2.setOnKeyListener(new View.OnKeyListener() {// 对返回键进行监听 实现点击popupWindow消失

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_BACK) {

                    selectpoupWindow.dismiss();

                    selectpoupWindow = null;
                }
                return false;
            }
        });

    }

    @Override
    public void requestInit() {

    }


    public String getRealFilePath(final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
