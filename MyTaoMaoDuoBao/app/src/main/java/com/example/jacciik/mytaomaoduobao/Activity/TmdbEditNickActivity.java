package com.example.jacciik.mytaomaoduobao.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jacciik.mytaomaoduobao.R;
import com.example.jacciik.mytaomaoduobao.Bean.AliUpdateBean;
import com.example.jacciik.mytaomaoduobao.ACache;
import com.example.jacciik.mytaomaoduobao.NetWork.RetrofitHelper;
import com.example.jacciik.mytaomaoduobao.Utils.ConstantUtil;
import com.example.jacciik.mytaomaoduobao.Utils.PreferenceUtil;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
*个人信息编辑页面，昵称修改Activity
* */
public class TmdbEditNickActivity extends BaseActivity {
    @BindView(R.id.nickname)
    EditText nickname;
    @BindView(R.id.confirm)
    TextView confirm;

    ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Acache
        aCache =  ACache.get(TmdbEditNickActivity.this);
        //获取传过来的昵称
        Intent intent = getIntent();
        String nick = intent.getStringExtra("nick");
        String id = PreferenceUtil.getString(ConstantUtil.OPEN_ACCOUNT_ID , null);
        //设置昵称
        nickname.setText(nick);
        //光标在文本末
        nickname.setSelection(nickname.getText().length());

        confirm.setOnClickListener((View v) -> {

            //如果昵称为空
            if(("").equals(nickname.getText().toString())){
                Toast.makeText(TmdbEditNickActivity.this, "你还未设置昵称",
                        Toast.LENGTH_SHORT).show();
            }else{

                String nickText = nickname.getText().toString();

                //与云账户交互,更新数据
                RetrofitHelper
                        .getTaoMaoAPI()
                        .updateAccount(id, null ,nickText)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AliUpdateBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                //Log.d()
                            }

                            @Override
                            public void onNext(AliUpdateBean value) {
                                if ("success".equals(value.getResult())) {
                                    Toast.makeText(TmdbEditNickActivity.this, "保存成功",
                                            Toast.LENGTH_SHORT).show();
                                    //Settings.System.putString(getContentResolver(),ConstantUtil.DISPLAY_NAME,  nickname.getText().toString());
                                    aCache.put("nickName" , nickText ,60*60);
                                    PreferenceUtil.putString(ConstantUtil.DISPLAY_NAME , nickText);



                                } else {
                                    Toast.makeText(TmdbEditNickActivity.this, "设置失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("errorMsg", e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                //返回到TmdbMainActivity
                                finish();
                            }
                        });


            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_editnickname;
    }

}
