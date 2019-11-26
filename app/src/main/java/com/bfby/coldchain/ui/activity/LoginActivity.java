package com.bfby.coldchain.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bfby.coldchain.Bean.LoginBean;
import com.bfby.coldchain.R;
import com.bfby.coldchain.common.constan.GlobalParams;
import com.bfby.coldchain.common.exception.BaseException;
import com.bfby.coldchain.common.exception.RxErrorHandler;
import com.bfby.coldchain.common.rx.RxHttpResponseCompatTest;
import com.bfby.coldchain.common.rx.RxHttpResponseCompatTests;
import com.bfby.coldchain.common.rx.subscriber.ErrorHandlerSubscriber;
import com.bfby.coldchain.common.rx.subscriber.ProgressDialogHandler;
import com.bfby.coldchain.common.widget.CheckPermissionsActivity;
import com.bfby.coldchain.common.widget.ClearEditText;
import com.bfby.coldchain.common.widget.ProjectUtil;
import com.bfby.coldchain.common.widget.RxToast;
import com.bfby.coldchain.common.widget.SPUtils;
import com.bfby.coldchain.di.component.Constants;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/18.
 */

public class LoginActivity extends CheckPermissionsActivity {
    @BindView(R.id.login_image_close)
    ImageView mLoginImageClose;
    @BindView(R.id.login_text_help)
    TextView mLoginTextHelp;
    @BindView(R.id.login_relativeLayout_top)
    RelativeLayout mLoginRelativeLayoutTop;
    @BindView(R.id.login_linearLayout_welcome)
    LinearLayout mLoginLinearLayoutWelcome;
    @BindView(R.id.edit_name)
    ClearEditText mEditName;
    @BindView(R.id.edit_pwd)
    EditText mEditPwd;
    @BindView(R.id.login_play_Password)
    CheckBox mLoginPlayPassword;
    @BindView(R.id.login_error_text)
    TextView mLoginErrorText;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    @BindView(R.id.text_forgetpwd)
    TextView mTextForgetpwd;
    @BindView(R.id.text_register)
    TextView mTextRegister;
    @BindView(R.id.login_all)
    LinearLayout mLoginAll;
    @BindView(R.id.login_bottom_text)
    TextView mLoginBottomText;
    @BindView(R.id.login_bottom_yonghu_text)
    TextView mLoginBottomYonghuText;
    @BindView(R.id.login_toor)
    RelativeLayout mLoginToor;

    private ProgressDialogHandler dialogHandler;
    private SPUtils sp;

    @Override
    protected int setLayoutId() {
        return R.layout.login_activity_layout;
    }

    @Override
    protected void init() {
        ImmersionBar.with(this)
                .titleBar(R.id.login_toor)
                .titleBarMarginTop(R.id.login_toor)
                .navigationBarColorInt(Color.WHITE)
                .statusBarDarkFont(true, 0.5f)
                .navigationBarDarkIcon(true, 0.5f)
                .init();

        dialogHandler = new ProgressDialogHandler(this);
        sp = SPUtils.getInstance(this);


        initView();

    }

    private void initView() {
        String name = sp.getString("name");
        String pwd = sp.getString("pwd");

        if (name != null && pwd != null) {
            mBtnSubmit.setEnabled(true);
            mEditName.setTextSize(18);
            mEditPwd.setTextSize(18);
        } else {
            mBtnSubmit.setEnabled(false);
            mEditName.setTextSize(16);
            mEditPwd.setTextSize(16);
        }

        mEditName.setText(name);
        mEditPwd.setText(pwd);

        mEditName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!mEditName.getText().toString().trim().equals(s)) {
                    mEditPwd.setText("");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mEditName.getText().toString().trim())
                        && !TextUtils.isEmpty(mEditPwd.getText().toString().trim())) {
                    mBtnSubmit.setEnabled(true);
                    mEditName.setTextSize(18);
                    mEditPwd.setTextSize(18);
                } else {
                    mBtnSubmit.setEnabled(false);
                    mEditName.setTextSize(18);
                    mEditPwd.setTextSize(18);
                }
            }
        });

        mEditPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mEditName.getText().toString().trim())
                        && !TextUtils.isEmpty(mEditPwd.getText().toString().trim())) {
                    mBtnSubmit.setEnabled(true);
                    mEditName.setTextSize(18);
                    mEditPwd.setTextSize(18);
                } else {
                    mBtnSubmit.setEnabled(false);
                    mEditName.setTextSize(16);
                    mEditPwd.setTextSize(16);
                }
            }
        });

        mLoginPlayPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //选择状态 显示明文--设置为可见的密码
                    mEditPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    mEditPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @OnClick({R.id.btn_submit, R.id.login_image_close, R.id.login_text_help, R.id.text_forgetpwd,
            R.id.login_bottom_yonghu_text})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_image_close:
                finishAll();
                break;

            case R.id.text_forgetpwd:
                //忘记密码
                Intent intent = new Intent(this, Updae_pwd_Activity.class);
                intent.putExtra("isForget", true);
                startActivity(intent);
                break;

            case R.id.login_text_help:
                //帮助
                startActivity(new Intent(this, HelpActivity.class));
                break;

            case R.id.login_bottom_yonghu_text:
                //用户协议
                startActivity(new Intent(this, UserAgreementActivity.class));
                break;

            case R.id.btn_submit:
                if (TextUtils.isEmpty(mEditName.getText().toString().trim())) {
                    RxToast.warning("用户名不能为空");
                } else if (TextUtils.isEmpty(mEditPwd.getText().toString().trim())) {
                    RxToast.warning("密码不能为空");
                } else {
                    login();
                }
                break;
            default:
                break;
        }
    }

    private void login() {

        dialogHandler.showProgressDialog();

        final String userId = mEditName.getText().toString();
        final String pwd = mEditPwd.getText().toString();


        mApi.Login(userId, pwd)
                .compose(RxHttpResponseCompatTests.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        RxToast.success("登录成功");

                        GlobalParams.setuserId(loginBean.getUser().getUserID());
                        GlobalParams.setuserName(loginBean.getUser().getUserName());
                        GlobalParams.setToken(loginBean.getToken());


                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                        mLoginErrorText.setText("");
                        sp.put("name", userId);
                        sp.put("pwd", pwd);
                        finish();
                    }

                    @Override
                    public void onError(Throwable t) {
                        RxErrorHandler mRxErrorHandler = new RxErrorHandler();
                        BaseException exception = mRxErrorHandler.handlerError(t);
                        mLoginErrorText.setText(exception.getDisplayMessage());

                        dialogHandler.dismissProgressDialog();
                    }
                });
    }

}
