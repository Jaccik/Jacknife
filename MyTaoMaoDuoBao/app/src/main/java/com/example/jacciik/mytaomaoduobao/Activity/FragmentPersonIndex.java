package com.example.jacciik.mytaomaoduobao.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jacciik.mytaomaoduobao.ACache;
import com.example.jacciik.mytaomaoduobao.Bean.Data;
import com.example.jacciik.mytaomaoduobao.Bean.VersionBean;
import com.example.jacciik.mytaomaoduobao.Fragment.BaseFragment;
import com.example.jacciik.mytaomaoduobao.NetWork.RetrofitHelper;
import com.example.jacciik.mytaomaoduobao.R;
import com.example.jacciik.mytaomaoduobao.Utils.ConstantUtil;
import com.example.jacciik.mytaomaoduobao.Utils.PreferenceUtil;
import com.example.jacciik.mytaomaoduobao.Utils.UtilFileDB;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

/**
 * Created by Jacciik on 2017/8/24.
 */

public class FragmentPersonIndex extends BaseFragment {

    @BindView(R.id.userLayout)
    RelativeLayout userLayout;
    @BindView(R.id.personImage)
    ImageView personImage;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.nickName)
    TextView nickName;

    @BindView(R.id.orderButton)
    ImageButton orderButton;
    @BindView(R.id.helpButton)
    ImageButton helpButton;
    @BindView(R.id.aboutButton)
    ImageButton aboutButton;
    @BindView(R.id.versionButton)
    ImageButton versionButton;
    @BindView(R.id.orderLayout)
    RelativeLayout orderLayout;
    @BindView(R.id.cartLayout)
    RelativeLayout cartLayout;
    @BindView(R.id.versionLayout)
    RelativeLayout versionLayout;

    @BindView(R.id.settings)
    ImageButton settings;
    Unbinder unbinder;

    ACache aCache;

    String iconBitmap = "iconBitmap";

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_person_index;
    }
    private static FragmentPersonIndex instance = null;
    public static FragmentPersonIndex newInstance() {
        if (instance == null) {
            instance = new FragmentPersonIndex();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @Override
    public void finishCreateView(Bundle state) {
        //判断是否登录,如果没有找到指定的键,默认为false
        aCache = ACache.get(getActivity());
        initUserInfo();
        orderLayout.setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), TmdbOrderActivity.class);
            startActivity(intent);
        });

        cartLayout.setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), TmdbCartActivity.class);
            startActivity(intent);
        });

        versionLayout.setOnClickListener((View v) ->
                updateVersion()
        );


    }

    //打开登录界面
    public void openAccount() {

    }


    //初始化用户信息
    public void initUserInfo() {
        boolean isLogin = PreferenceUtil.getBoolean(ConstantUtil.LOGIN_KEY, false);
        if (isLogin) {
            //获取保存的用户名
            String userName = PreferenceUtil.getString(ConstantUtil.LOGIN_NAME, null);
            String nick = PreferenceUtil.getString(ConstantUtil.DISPLAY_NAME, null);

            if (aCache.getAsBitmap(iconBitmap) == null) {
                personImage.setImageResource(R.drawable.svg_profile);
            } else {
                personImage.setImageBitmap(aCache.getAsBitmap(iconBitmap));
            }

            //设置用户名
            username.setText("用户名:" + (userName == null ? "" : userName));
            //设置昵称
            nickName.setText("昵称:" + (nick == null ? "" : nick));
            //如果已经登录,跳转到个人信息页面Activity
            userLayout.setOnClickListener((View v) -> {
                Intent intent = new Intent(getActivity(), TmdbSettingsActivity.class);
                startActivityForResult(intent, 1);
            });
            settings.setOnClickListener((View v) -> {
                Intent intent = new Intent(getActivity(), TmdbSettingsActivity.class);
                startActivity(intent);
            });
        } else {
            personImage.setImageResource(R.drawable.svg_profile);
            username.setText("未登录");
            nickName.setText("");
            //打开登录页面
            userLayout.setOnClickListener((View v) ->
                    openAccount()
            );
            settings.setOnClickListener((View v) ->
                    openAccount()
            );
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 3) {
                personImage.setImageBitmap(aCache.getAsBitmap(iconBitmap));
                //getImage(data.getStringExtra("urlimg"));
            } else {
                personImage.setImageResource(R.drawable.default_personal_image);
            }
        }
    }

    public void getImage(String url) {
        UtilFileDB.DELETEFile(aCache, "myimg");
        OkHttpUtils.get().url(url).tag(this).build().connTimeOut(20000)
                .readTimeOut(20000).writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        personImage.setImageBitmap(bitmap);
                        aCache.put("myimg", bitmap);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //对比版本用
    public void updateVersion() {
        Context context = getActivity().getApplicationContext();
        String localPackage = context.getPackageName();
        //当前版本号
        final List<String> versionList = new ArrayList<>();
        String appVersion = null;
        try {
            PackageInfo packageInfo = getContext().getApplicationContext().getPackageManager().getPackageInfo(localPackage, 0);
            appVersion = packageInfo.versionName;
            versionList.add(appVersion);
        } catch (PackageManager.NameNotFoundException e) {

        }
        //服务器版本号
        Observable<VersionBean> results = RetrofitHelper
                .getTaoMaoAPI()
                .getAppVersion();

        List<VersionBean> versionBeen = new ArrayList<>();

        results.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<VersionBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                //Do something
            }

            @Override
            public void onNext(VersionBean value) {
                if (value != null) {
                    versionBeen.add(value);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Tag", e.getMessage());
            }

            @Override
            public void onComplete() {
                Dialog alertDialog = new AlertDialog.Builder(getActivity()).
                        setTitle("提示信息").
                        setMessage("当前已是最新版本！").
                        setPositiveButton("确定", (DialogInterface dialog, int which) -> {
                            //点击确定按钮动作
                        }).
                        create();
                if (versionBeen.isEmpty()) {
                    alertDialog.show();
                } else {
                    Data data = versionBeen.get(0).getData();
                    String serverVersion = data.getServerVersion();
                    if (serverVersion.equals(versionList.get(0))) {
                        alertDialog.show();
                    } else {
                        //弹出更新框
                        Dialog updateDialog = new AlertDialog.Builder(getActivity())
                                .setTitle("更新提醒")
                                .setMessage("最新版本：" + serverVersion + ",请点击下载")
                                .setPositiveButton("立即更新", (DialogInterface dialogInterface, int i) -> {
                                    String action = "com.intent.click_notification";
                                    Intent intent = new Intent(action);
                                    getActivity().sendBroadcast(intent);

                                })
                                .setNegativeButton("暂不更新", (DialogInterface dialogInterface, int i) -> {
                                    dialogInterface.dismiss();

                                }).create();

                        updateDialog.show();
                    }
                }
            }
        });

        //弹出对话框
    }

    public void deleteAccount(String id){
        RetrofitHelper
                .getTaoMaoAPI()
                .deleteAccountById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Log.d()
                    }

                    @Override
                    public void onNext(String value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("errorMsg", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Complete", "hello");
                    }
                });
    }

}


