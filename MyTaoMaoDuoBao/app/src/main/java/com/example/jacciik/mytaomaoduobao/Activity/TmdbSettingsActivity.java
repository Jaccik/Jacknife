package com.example.jacciik.mytaomaoduobao.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.jacciik.mytaomaoduobao.R;
import com.example.jacciik.mytaomaoduobao.ACache;
import com.example.jacciik.mytaomaoduobao.Utils.ConstantUtil;
import com.example.jacciik.mytaomaoduobao.Utils.PreferenceUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;

public class TmdbSettingsActivity extends BaseActivity {
    @BindView(R.id.backforward)
    ImageButton backforward;
    @BindView(R.id.imageLayout)
    LinearLayout imageLayout;
    @BindView(R.id.personImage)
    ImageView personImage;
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.backgroundButton)
    ImageButton backgroundButton;
    @BindView(R.id.nickLayout)
    LinearLayout nickLayout;
    @BindView(R.id.nickButton)
    ImageButton nickButton;
    @BindView(R.id.nickText)
    TextView nickText;
    @BindView(R.id.logout)
    Button logoutButton;
    @BindView(R.id.userText)
    TextView userText;
    @BindView(R.id.userLayout)
    LinearLayout userLayout;
    @BindView(R.id.infoLabel)
    RelativeLayout infoLabel;

    PopupWindow pop;
    LinearLayout ll_popup;

    ACache aCache;

    Uri imageUri;

    String iconBitmap = "iconBitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        aCache = ACache.get(TmdbSettingsActivity.this);

        if (aCache.getAsBitmap(iconBitmap) == null) {
            personImage.setImageResource(R.drawable.svg_profile);
        } else {
            personImage.setImageBitmap(aCache.getAsBitmap(iconBitmap));
        }

        //获取登录的用户名
        String userName = PreferenceUtil.getString(ConstantUtil.LOGIN_NAME, "未登录");
        //获取昵称
        String displayName = PreferenceUtil.getString(ConstantUtil.DISPLAY_NAME, null);
        //设置用户名TextView
        userText.setText(userName);
        nickText.setText(displayName == null ? "" : displayName);
        //用户名无法修改
        userLayout.setOnClickListener((View view) ->
                Toast.makeText(TmdbSettingsActivity.this, "用户名无法修改", Toast.LENGTH_SHORT).show()
        );
        //返回按钮,返回到TmdbMainActivity
        infoLabel.setOnClickListener((View v) -> {
            Intent intent = new Intent();
            setResult(3, intent);
            finish();
        });

        imageLayout.setOnClickListener((View v) -> {
            showPopupWindow();
            ll_popup.startAnimation(AnimationUtils.loadAnimation(
                    TmdbSettingsActivity.this, R.anim.activity_translate_in));
            pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        });


        nickLayout.setOnClickListener((View v) -> {
            Intent intent = new Intent(TmdbSettingsActivity.this, TmdbEditNickActivity.class);
            String name = nickText.getText().toString();
            intent.putExtra("nick", name);
            intent.setClass(TmdbSettingsActivity.this, TmdbEditNickActivity.class);
            startActivityForResult(intent, 99);
        });

        logoutButton.setOnClickListener((View v) -> {
            PreferenceUtil.clearLoginData();
            //退出IM登录
            logoutIM();
            //返回到TmdbPersonInfoActivity
            onBackPressed();
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.tmdb_fragment_person_setting;
    }

    public void logoutIM() {

    }


    /****
     * 头像提示框
     */
    public void showPopupWindow() {
        pop = new PopupWindow(this);
        //获取LayoutInflater
        View view = getLayoutInflater().inflate(R.layout.tmdb_item_popupwindows,
                null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button cameraButton = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button photoButton = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        Button cancelButton = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener((View v) -> {
            pop.dismiss();
            ll_popup.clearAnimation();

        });
        cameraButton.setOnClickListener((View v) -> {
            File outputImage = new File(getExternalCacheDir(), "personImage.jpg");
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intentCamera = new Intent();
            if (ContextCompat.checkSelfPermission(TmdbSettingsActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(TmdbSettingsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(TmdbSettingsActivity.this, Manifest.permission.CAMERA)) {
                    //ToastUtils.showShort(this, "您已经拒绝过一次");
                }
                ActivityCompat.requestPermissions(TmdbSettingsActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0x03);
            } else {//有权限直接调用系统相机拍照
                if (hasSdcard()) {
                    imageUri = Uri.fromFile(outputImage);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intentCamera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        imageUri = FileProvider.getUriForFile(TmdbSettingsActivity.this, "com.bukailiu.taomaoduobao.provider", outputImage);
                    } else {
                        imageUri = Uri.fromFile(outputImage);
                    }
                    intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intentCamera, 1);
                } else {
                    Toast.makeText(TmdbSettingsActivity.this, "没有sd", Toast.LENGTH_SHORT).show();
                }
            }

            pop.dismiss();
            ll_popup.clearAnimation();
        });
        photoButton.setOnClickListener((View v) -> {
            //获取读取存储的权限
            if (ContextCompat.checkSelfPermission(TmdbSettingsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(TmdbSettingsActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0x03);
            }
            Intent picture = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(picture, 2);
            pop.dismiss();
            ll_popup.clearAnimation();
        });
        cancelButton.setOnClickListener((View v) -> {
            pop.dismiss();
            ll_popup.clearAnimation();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        }




    public void saveBitmapFile(Bitmap bitmap, String path) {
        File file = new File(path);//将要保存图片的路径
        FileOutputStream outputStream = null;
        BufferedOutputStream bos = null;
        try {
            outputStream = new FileOutputStream(file);
            bos = new BufferedOutputStream(outputStream);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void staffFileupload(File file) {
        if (false) {
            showToastShort("网络未连接");
            return;
        }
        //上传图片
        x.http().post(MYUPDATEIMG(file), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });


    }

    /***
     * 修改头像
     *
     * @return
     */
    public static final RequestParams MYUPDATEIMG(File file) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("c", "profile");
        params.addBodyParameter("a", "setavatar");
        params.addBodyParameter("uid", "");
        params.addBodyParameter("username", "");
        if (file != null) {
            params.addBodyParameter("filedata", file);
        }
        return params;
    }

    private void showToastShort(String string) {
        Toast.makeText(TmdbSettingsActivity.this, string, Toast.LENGTH_LONG).show();
    }

    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }


}
