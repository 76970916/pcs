package com.spe.dcs.login;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.PcsSharedPreferences;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.databinding.ActivityLoginBinding;
import com.spe.dcs.system.sysorg.SysOrgViewModel;
import com.spe.dcs.system.sysrole.SysRoleEntity;
import com.spe.dcs.system.sysrole.SysRoleViewModel;
import com.spe.dcs.system.sysuser.SysUserEntity;
import com.spe.dcs.tree.MainActivity;


import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * 文件名：LoginActivity.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/21 9:58
 * 描  述： 登录页面
 */
public class LoginActivity extends DaggerAppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    @Inject
    PcsSharedPreferences sharedPreferences;
    private LoginViewModel loginViewModel;
    private SysOrgViewModel sysOrgViewModel;
    private ActivityLoginBinding binding;
    private ProgressDialog mProgressDialog;

    private static final int PERMISSIONS = 161;//请求码

    private boolean isRememberPassWord = false;
    private boolean isAutoLogin = false;
    private SysRoleViewModel sysRoleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        loginViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(LoginViewModel.class);
        sysOrgViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysOrgViewModel.class);
        sysRoleViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysRoleViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.wait));
        mProgressDialog.setCancelable(false);
        loginViewModel.getLoginLiveData().observe(this, resource -> {
            if (resource.status.equals(Status.LOADING)) {
                mProgressDialog.show();
                return;
            } else if (resource.status.equals(Status.ERROR)) {
                mProgressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "网络故障，请重试", Toast.LENGTH_SHORT).show();
            } else if (resource.status.equals(Status.SUCCESS)) {
                mProgressDialog.dismiss();
                RespEntity respEntity = resource.data;
                if (respEntity != null && respEntity.getCode() == 1) {
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();

                    // 登录成功并且选中自动登录按钮
                    if (isAutoLogin) {
                        sharedPreferences.setAutoLogin(isAutoLogin);
                    } else {
                        sharedPreferences.setAutoLogin(isAutoLogin);
                    }
                    //登录成功并且选中记住密码按钮
                    if (isRememberPassWord) {
                        sharedPreferences.setRemeberAccount(isRememberPassWord);
                    } else {
                        sharedPreferences.setRemeberAccount(isRememberPassWord);
                    }
                    String inputName = binding.loginInputName.getText().toString();
                    sharedPreferences.setUserName(inputName);
                    if (isRememberPassWord) {
                        String password = binding.loginInputPassword.getText().toString();
                        sharedPreferences.setPassword(password);
                    }
                    SysUserEntity sysUserEntity;
                    if (respEntity.getData() != null && respEntity.getData() instanceof LinkedTreeMap) {//在线模式
                        LinkedTreeMap map = (LinkedTreeMap) respEntity.getData();
                        GsonBuilder builder = new GsonBuilder();
                        builder.enableComplexMapKeySerialization();
                        Gson gson = builder.create();
                        String json = gson.toJson(map);
                        sysUserEntity = gson.fromJson(json, new TypeToken<SysUserEntity>() {
                        }.getType());

                    } else {//离线模式
                        sysUserEntity = (SysUserEntity) respEntity.getData();
                    }
                    PcsApplication.getInstance().setSysUserEntity(sysUserEntity);
                    //获取登陆用户的单位信息
                    getRoleListByUserId(sysUserEntity.getId());
//                    getOrgByCode(sysUserEntity.getOrgId());
                } else {
                    Toast.makeText(LoginActivity.this, "登陆失败" + respEntity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        isRememberPassWord = sharedPreferences.getRemeberAccount();
        isAutoLogin = sharedPreferences.getAutoLogin();

        binding.loginAutologin.setChecked(sharedPreferences.getAutoLogin());
        binding.loginRemeberAccount.setChecked(sharedPreferences.getRemeberAccount());
        binding.loginInputName.setText(sharedPreferences.getUserName());
        //自动登录
        binding.loginAutologin.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                isAutoLogin = true;
            } else {
                isAutoLogin = false;
            }
        });

//        记住密码
        binding.loginRemeberAccount.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {//选中
                isRememberPassWord = true;
            } else {//未选中
                isRememberPassWord = false;
            }
        });

        if (sharedPreferences.getRemeberAccount()) {
            binding.loginInputPassword.setText(sharedPreferences.getPassword());
        } else {
            binding.loginInputPassword.setText("");
        }

        binding.setPresenter(new Presenter());
        binding.loginSwitchBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.loginInputPassword.setTransformationMethod(isChecked ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
        });

//        如果选中自动登录
        if (sharedPreferences.getAutoLogin()) {
            loginViewModel.login(sharedPreferences.getUserName(), sharedPreferences.getPassword());
        }
    }

    //   通过userId获取本人对应 的角色(录入还是监理人员还是业主)
    private void getRoleListByUserId(String id) {
        sysRoleViewModel.findRoleListByUserId(id).observe(this, listResource -> {
            if (Status.LOADING.equals(listResource.status)) {
                mProgressDialog.show();
            } else if (Status.ERROR.equals(listResource.status)) {
                mProgressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "网络失败，请重试", Toast.LENGTH_SHORT).show();
                return;
            } else if (Status.SUCCESS.equals(listResource.status)) {
                List<SysRoleEntity> data = listResource.data;
                if (data != null && data.size() != 0) {
                    for (int i = 0; i < data.size(); i++) {
                        //code:ConstructManager:承包商数据负责人,负责本单位数据的录入。 （流程）,
                        // PDService:数字化服务,数字化数据的校验（流程）,
                        // ConstructionController:监理数据校验,负责数据校验、审核。本角色编号的负责数据校验，审核（流程）,
                        // ProjectManager:业主单位项目负责人, 本单位数据查看、抽检.属于建设单位的可以抽检（流程）.
                        //code如果是admin的话，还需要重新判断
                        if (i == 0) {
                            PcsApplication.getInstance().setSysRoleEntity(data.get(0));
                        }
                    }
                }
                getOrgByCode(PcsApplication.getInstance().getSysUserEntity().getOrgName());
            }
        });
    }

    //获取单位信息
    private void getOrgByCode(String code) {

        sysOrgViewModel.findDataByCode(code).observe(this, resource -> {
                    if (resource.status.equals(Status.LOADING)) {
                        mProgressDialog.show();
                        return;
                    } else if (resource.status.equals(Status.ERROR)) {
                        mProgressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "网络故障，请重试", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (resource.status.equals(Status.SUCCESS)) {
                        mProgressDialog.dismiss();
                        //获取数据，并保存到application中
                        if (resource.data != null) {
                            PcsApplication.getInstance().setSysOrgEntity(resource.data);
                            //登陆成功，跳转到主界面
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "获取单位信息失败，请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public class Presenter {
        //登录
        public void login_click(View view) {
            String name = binding.loginInputName.getText().toString();
            String password = binding.loginInputPassword.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(LoginActivity.this, getString(R.string.usr_name_not_null), Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, getString(R.string.usr_pwd_not_null), Toast.LENGTH_LONG).show();
                return;
            }

            loginViewModel.login(name, password);
//            throw new RuntimeException("test upload log");

//            LoginActivity.this.startActivity(new Intent(LoginActivity.this, TreeActivity.class));
//            finish();
        }

        //设置
        public void set_click(View view) {
            startActivity(new Intent(LoginActivity.this, SettingActivity.class));
        }

        //退出
        public void quit_click(View view) {
            finish();
        }
    }
}