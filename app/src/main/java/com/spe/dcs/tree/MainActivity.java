package com.spe.dcs.tree;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.PcsSharedPreferences;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.AddCookiesInterceptor;
import com.spe.dcs.app.net.ReceivedCookiesInterceptor;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.common.NewVersionEntity;
import com.spe.dcs.common.TypeStatus;
import com.spe.dcs.databinding.ActivityMainBinding;
import com.spe.dcs.project.mentityunit.MEntityUnitEntity;
import com.spe.dcs.project.mentityunit.MEntityUnitViewModel;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoEntity;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoViewModel;
import com.spe.dcs.project.mentityfuction.MEntityFuctionEntity;
import com.spe.dcs.project.mentityfuction.MEntityFuctionViewModel;
import com.spe.dcs.project.mprojectinfo.MProjectInfoViewModel;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoEntity;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoViewModel;
import com.spe.dcs.project.cstation.CStationEntity;
import com.spe.dcs.project.cstation.CStationViewModel;
import com.spe.dcs.project.cvalveroom.CValveRoomEntity;
import com.spe.dcs.project.cvalveroom.CValveRoomViewModel;
import com.spe.dcs.project.cwelder.CWelderEntity;
import com.spe.dcs.project.cwelder.CWelderViewModel;
import com.spe.dcs.project.cweldingprocedure.CWeldingProcedureEntity;
import com.spe.dcs.project.cweldingprocedure.CWeldingProcedureViewModel;
import com.spe.dcs.project.cweldingunit.CWeldingUnitEntity;
import com.spe.dcs.project.cweldingunit.CWeldingUnitViewModel;
import com.spe.dcs.system.sysandroiderror.SysAndroidErrorViewModel;
import com.spe.dcs.system.sysandroidupdate.SysAndroidUpdateDownLoadWebService;
import com.spe.dcs.system.sysandroidupdate.SysAndroidUpdateViewModel;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.system.syscategory.SysCategoryViewModel;
import com.spe.dcs.system.sysdomain.SysDomainEntity;
import com.spe.dcs.system.sysdomain.SysDomainViewModel;
import com.spe.dcs.system.sysfield.SysFieldEntity;
import com.spe.dcs.system.sysfield.SysFieldViewModel;
import com.spe.dcs.system.sysformfield.SysFormFieldEntity;
import com.spe.dcs.system.sysformfield.SysFormFieldViewModel;
import com.spe.dcs.system.sysformfieldtype.SysFormFieldTypeEntity;
import com.spe.dcs.system.sysformfieldtype.SysFormFieldTypeViewModel;
import com.spe.dcs.system.syslist.SysListEntity;
import com.spe.dcs.system.syslist.SysListViewModel;
import com.spe.dcs.system.syslistbutton.SysListButtonEntity;
import com.spe.dcs.system.syslistbutton.SysListButtonViewModel;
import com.spe.dcs.system.syslistfield.SysListFieldEntity;
import com.spe.dcs.system.syslistfield.SysListFieldViewModel;
import com.spe.dcs.system.sysorg.SysOrgEntity;
import com.spe.dcs.system.sysorg.SysOrgViewModel;
import com.spe.dcs.system.sysorgres.SysOrgResEntity;
import com.spe.dcs.system.sysorgres.SysOrgResViewModel;
import com.spe.dcs.system.sysres.SysResEntity;
import com.spe.dcs.system.sysres.SysResViewModel;
import com.spe.dcs.system.sysrole.SysRoleEntity;
import com.spe.dcs.system.sysrole.SysRoleViewModel;
import com.spe.dcs.system.sysroleres.SysRoleResEntity;
import com.spe.dcs.system.sysroleres.SysRoleResViewModel;
import com.spe.dcs.system.sysroleuser.SysRoleUserEntity;
import com.spe.dcs.system.sysroleuser.SysRoleUserViewModel;
import com.spe.dcs.system.systable.SysTableEntity;
import com.spe.dcs.system.systable.SysTableViewModel;
import com.spe.dcs.system.sysuser.SysUserEntity;
import com.spe.dcs.system.sysuser.SysUserViewModel;
import com.spe.dcs.system.sysuserres.SysUserResEntity;
import com.spe.dcs.system.sysuserres.SysUserResViewModel;
import com.spe.dcs.tree.holder.ChildHolder;
import com.spe.dcs.tree.holder.ParentHolder;
import com.spe.dcs.utility.CommonUtils;
import com.spe.dcs.utility.FileUtils;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文件名：MainActivity.java
 * 作  者： cj.rhuang@gmail.comqueryLocalDatas
 * 时  间： 2019/3/4 13:41
 * 描  述： 主页
 */
public class MainActivity extends DaggerAppCompatActivity implements MenuView.OnMenuClickListener {

    private static final String TAG = "MainActivity";
    @Inject
    ViewModelProvider.Factory dcsViewModelFactory;
    @Inject
    PcsSharedPreferences sharedPreferences;
    TextView textView;
    TreeViewModel treeViewModel;
    ActivityMainBinding binding;
    private ProgressDialog mProgressDialog;
    private long mLastClickTime;
    private boolean isUpdating;
    private List<SysCategoryEntity> mSysCategoryEntities;
    //需要上传的实体集合
    private List<SysCategoryEntity> mSysCategoryUploadEntities = new ArrayList<>();
    private List<SysListEntity> mSysListEntities;
    private static final String ROOT_DIR_ID = "1";
    private static final String[] TABLE_HIDDEN = {"根目录", "基础数据", "物资"};
    private SysAndroidErrorViewModel sysAndroidErrorViewModel;
    private SysAndroidUpdateViewModel sysAndroidUpdateViewModel;
    private ProgressDialog dialog;
    private boolean isFirstInstall;
    //填入空对象
    Class d = null;
    Object object = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        treeViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(TreeViewModel.class);
        sysAndroidErrorViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysAndroidErrorViewModel.class);
        sysAndroidUpdateViewModel = ViewModelProviders.of(this, dcsViewModelFactory).get(SysAndroidUpdateViewModel.class);

        setSupportActionBar(getToolbar());
        //getToolbar().setSubtitle(PcsApplication.getInstance().getSysUserEntity().getName()+"("+PcsApplication.getInstance().getSysOrgEntity().getName()+")");
//        binding.title.setText(getToolbar().getTitle());
//        binding.subtitle.setText(PcsApplication.getInstance().getSysUserEntity().getName());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.wait_update));
        mProgressDialog.setCancelable(false);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, binding.drawerLayout, getToolbar(), 0, 0);
//        binding.drawerLayout.setDrawerListener(toggle);
//        toggle.syncState();
//        binding.navigationView.setOnMenuClickListener(this);
//        textView = findViewById(R.id.first_menu);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initTreeDatas();
//            }
//        });

//        如果有网络状态下，就自动更新配置，没有网络状态下就读取本地,不用更新.
//        if (CommonUtils.isNetAvailable()) {
//            updateTables(false);
//        } else {
//            updateTables(true);
//        }

        isFirstInstall = sharedPreferences.getIsFirstInstall();
        if (isFirstInstall) {
            updateTables(false);
            sharedPreferences.setIsFirstInstall(false);
        } else {
            if (CommonUtils.isNetAvailable()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("更新配置");
                builder.setMessage("是否更新配置?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        updateTables(true);
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateTables(false);
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            } else {
//                无网络状态下，从本地获取数据
                updateTables(true);
            }
        }
//        桑
        sysAndroidErrorViewModel.uploadLog().observe(this, new Observer<Resource<RespEntity>>() {
            @Override
            public void onChanged(@Nullable Resource<RespEntity> respEntityResource) {
                if (respEntityResource.status.equals(Status.SUCCESS)) {
                    FileUtils.deleteFile(FileUtils.LOG);
                }
            }
        });


    }


    public android.support.v7.widget.Toolbar getToolbar() {
        return binding.toolbar;
    }


    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//            return;
//        }
//        if (System.currentTimeMillis() - mLastClickTime > 2000) {
//            Toast.makeText(MainActivity.this, "再次点击退出", Toast.LENGTH_SHORT).show();
//            mLastClickTime = System.currentTimeMillis();
//            return;
//        }

        super.onBackPressed();
    }

    //版本更新
    private void upgrade() {

        if (!CommonUtils.isNetAvailable()) {
            Toast.makeText(this, getString(R.string.net_is_unavailable), Toast.LENGTH_LONG).show();
            return;
        }
        sysAndroidUpdateViewModel.findNewVersion().observe(this, new Observer<Resource<NewVersionEntity>>() {
            @Override
            public void onChanged(@Nullable Resource<NewVersionEntity> respEntityResource) {
                if (respEntityResource.status.equals(Status.SUCCESS)) {
                    NewVersionEntity newVersion = (NewVersionEntity) respEntityResource.data;//接口请求的
                    String newestVersion = newVersion.getNewestVersion();
                    String version = PcsApplication.version;//本地的
                    if (TextUtils.isEmpty(version) || TextUtils.isEmpty(newestVersion)) {
                        return;
                    }
                    //            结果说明：0代表相等，1代表version1大于version2，-1代表version1小于version2
                    int i = compareVersion(newestVersion, version);
                    if ((i == 1)) {
//                弹出版本更新的弹框
                        showUpdateDialog(MainActivity.this);

                    } else {
                        Toast.makeText(MainActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
                    }
                } else if (respEntityResource.status.equals(Status.ERROR)) {
                    Toast.makeText(MainActivity.this, respEntityResource.message, Toast.LENGTH_SHORT).show();
                } else if (respEntityResource.status.equals(Status.LOADING)) {
                    return;
                }
            }
        });
    }

    private void showUpdateDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("版本升级");
        builder.setMessage("发现新版本，请及时更新");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                loadFromSerVer(context);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    private File getFileFromServer(Call<ResponseBody> call, Response<ResponseBody> response, ProgressDialog dialog) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //获取到文件的大小
            dialog.setMax((int) response.body().contentLength());

            InputStream is = response.body().byteStream();
            File file = new File(FileUtils.SAVE_APK, "DCS.apk");
            if (!file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;

            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                dialog.setMessage("正在下载...." + ((int) response.body().contentLength() / total + "%"));

            }
            fos.close();
            bis.close();
            is.close();
            dialog.dismiss();
            return file;
        } else {
            return null;
        }

    }

    private void loadFromSerVer(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setProgress(0);
        dialog.setTitle("版本更新");
        dialog.setMessage("下载中,请稍后...");
        dialog.setCancelable(false);
        dialog.show();
        new Thread() {
            @Override
            public void run() {
                Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(sharedPreferences.getBaseUrl());
                OkHttpClient okhttpClient = new OkHttpClient.Builder()
                        .connectTimeout(5000, TimeUnit.SECONDS)
                        .connectTimeout(5000, TimeUnit.SECONDS)
                        .readTimeout(5000, TimeUnit.SECONDS)
                        .addInterceptor(new ReceivedCookiesInterceptor()) //这部分
                        .addInterceptor(new AddCookiesInterceptor()) //这部分
                        .build();
                SysAndroidUpdateDownLoadWebService retrofit = retrofitBuilder
                        .client(okhttpClient)
                        .build().create(SysAndroidUpdateDownLoadWebService.class);

                Call<ResponseBody> call = retrofit.download();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            File file = getFileFromServer(call, response, dialog);
                            installApk(context, file);
                            dialog.dismiss(); //结束掉进度条对话框
                            Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            //下载apk失败
                            Looper.prepare();
                            Toast.makeText(MainActivity.this, "下载新版本失败", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        }.start();

    }

    private void installApk(Context context, File file) {
        try {
            Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } else {
                // 声明需要的临时的权限
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                // 第二个参数，即第一步中配置的authorities
                Uri contentUri = FileProvider.getUriForFile(
                        context
                        , "com.spe.dcs.fileprovider"
                        , file);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            }
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //比较大小
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");

        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    //上传数据
    private void queryLocalDatas() {
        if (!CommonUtils.isNetAvailable()) {
            Toast.makeText(this, getString(R.string.net_is_unavailable), Toast.LENGTH_LONG).show();
            return;
        }
        mProgressDialog.setMessage(getString(R.string.upload_datas));
        mProgressDialog.show();
        //存放要上传的数据
        Map<Class, List> listMap = new HashMap<>();
        //查询需要上传数据返回结果
        List<Boolean> queryBooleanList = new ArrayList<>();
        List<Boolean> queryBooleanListSu = new ArrayList<>();
        //遍历所有需要上传的实体
        for (SysCategoryEntity entity : mSysCategoryUploadEntities) {
            //计算出类名的前缀
            String[] names = entity.getCode().toLowerCase().split("_");
            String code1 = "";
            for (String name : names) {
                code1 += (new StringBuilder()).append(Character.toUpperCase(name.charAt(0))).append(name.substring(1)).toString();
            }
            final String code = code1;
            try {
                //生成vm类
                Class c = Class.forName(getPackageName() + ".project." + code.toLowerCase() + "." + code + "ViewModel");
                //生成vm实例
                ViewModel vm = ViewModelProviders.of(this, dcsViewModelFactory).get(c);
                //获取list4upload方法 ，list4upload方法用来获取需要上传的数据

                Method upload = c.getDeclaredMethod("list4Upload", d);
                //执行list4upload方法

                LiveData<Resource<List>> liveData = (LiveData<Resource<List>>) upload.invoke(vm, object);
                liveData.observe(MainActivity.this, listResource -> {
                    if (listResource.status.equals(Status.LOADING))
                        return;
                    else if (listResource.status.equals(Status.ERROR)) {

                        return;
                    } else {
                        List entities = listResource.data;
                        queryBooleanList.add(true);
                        //暂存数据到相应的列表中
                        listMap.put(c, entities);
                        //如果所有实体要上传数据获取成功，则进行上传
                        if (queryBooleanList.size() == mSysCategoryUploadEntities.size()) {
                            uploadLocalDatas(listMap);
                        }
                    }
                });
//               执行list4Upload()方法
                LiveData<Resource<List>> livedData = (LiveData<Resource<List>>) upload.invoke(vm, object);
                livedData.observe(MainActivity.this, listResource -> {
                    if (listResource.status.equals(Status.LOADING))
                        return;
                    else if (listResource.status.equals(Status.ERROR)) {
                        Toast.makeText(this, code + "获取审核数据失败", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        List entities = listResource.data;
                        queryBooleanList.add(true);
                        //暂存数据到相应的列表中
                        listMap.put(c, entities);
                        //如果所有实体要上传数据获取成功，则进行上传
                        if (queryBooleanList.size() == mSysCategoryUploadEntities.size()) {
                            uploadLocalDatas(listMap);
                        }
                    }
                });


            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            }
        }
    }

    private void uploadLocalSuDatas(Map<Class, List> listMap) {
        //计算上传数据总个数
        int size = 0;
        Iterator<Class> iteratorSize = listMap.keySet().iterator();
        while (iteratorSize.hasNext()) {
            Class c = iteratorSize.next();
            List entities = listMap.get(c);
            size += entities.size();
        }
        if (size == 0) {
            mProgressDialog.dismiss();
            Toast.makeText(this, "没有已上报的数据需要上传", Toast.LENGTH_LONG).show();
            return;
        }
        final int total = size;
        size = 0;
        //上传数据
        Iterator<Class> iterator = listMap.keySet().iterator();
        List<Boolean> trueResults = new ArrayList<>();
        List<Boolean> falseResults = new ArrayList<>();
        while (iterator.hasNext()) {
            Class c = iterator.next();
            List entities = listMap.get(c);
            if (entities.isEmpty()) {
                continue;
            }
            //size += entities.size();
            ViewModel vm = ViewModelProviders.of(this, dcsViewModelFactory).get(c);
            try {
                //entity类
                Class cls = entities.get(0).getClass();
                Method save = c.getDeclaredMethod("save", cls);
                for (Object e : entities) {
                    //获取setApproveStatus方法，设置审核状态
                    Method set = e.getClass().getDeclaredMethod("setApproveStatus", String.class);
                    //设置审核状态为"APP_SUPERVISOR"
                    set.invoke(e, TypeStatus.APP_SUPERVISOR);
                    //执行save，从网络中保存数据
                    ((LiveData<Resource<RespEntity>>) save.invoke(vm, e)).observe(MainActivity.this, respEntityResource -> {
                        if (respEntityResource.status.equals(Status.LOADING)) {
                            return;
                        } else if (respEntityResource.status.equals(Status.ERROR)) {
                            falseResults.add(false);
                        } else {
                            if (respEntityResource.data.getCode() == 1) {//返回成功
                                trueResults.add(true);
                            } else {//返回失败
                                falseResults.add(false);
                            }
                        }
                        //上传完成
                        if ((falseResults.size() + trueResults.size()) == total) {
                            mProgressDialog.dismiss();
                            if (trueResults.size() == total) {
                                Toast.makeText(MainActivity.this, "上传完成,已上报数据中全部(" + trueResults.size() + "条)数据上传成功", Toast.LENGTH_LONG).show();
                            } else if (falseResults.size() == total) {
                                Toast.makeText(MainActivity.this, "上传完成,已上报数据中全部(" + falseResults.size() + "条)数据上传失败", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "上传完成,已上报数据中有" + trueResults.size() + "条数据上传成功，有" + falseResults.size() + "条数据上传失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void uploadLocalDatas(Map<Class, List> listMap) {
        //计算上传数据总个数
        int size = 0;
        Iterator<Class> iteratorSize = listMap.keySet().iterator();
        while (iteratorSize.hasNext()) {
            Class c = iteratorSize.next();
            List entities = listMap.get(c);
            size += entities.size();
        }
        if (size == 0) {
            mProgressDialog.dismiss();
            Toast.makeText(this, "没有已审核的数据需要上传", Toast.LENGTH_LONG).show();
            return;
        }
        final int total = size;
        size = 0;
        //上传数据
        Iterator<Class> iterator = listMap.keySet().iterator();
        List<Boolean> trueResults = new ArrayList<>();
        List<Boolean> falseResults = new ArrayList<>();
        while (iterator.hasNext()) {
            Class c = iterator.next();
            List entities = listMap.get(c);
            if (entities.isEmpty()) {
                continue;
            }
            //size += entities.size();
            ViewModel vm = ViewModelProviders.of(this, dcsViewModelFactory).get(c);
            try {
                //entity类
                Class cls = entities.get(0).getClass();
                Method save = c.getDeclaredMethod("save", cls);
                for (Object e : entities) {
                    //获取setApproveStatus方法，设置审核状态
                    Method set = e.getClass().getDeclaredMethod("setApproveStatus", String.class);
                    //设置审核状态为"APP--->APP_OWNER"
                    set.invoke(e, TypeStatus.APP_OWNER);
                    //执行save，从网络中保存数据
                    ((LiveData<Resource<RespEntity>>) save.invoke(vm, e)).observe(MainActivity.this, (Resource<RespEntity> respEntityResource) -> {
                        if (respEntityResource.status.equals(Status.LOADING)) {
                            return;
                        } else if (respEntityResource.status.equals(Status.ERROR)) {
                            falseResults.add(false);
                        } else {
                            if (respEntityResource.data.getCode() == 1) {//返回成功
                                trueResults.add(true);
                            } else {//返回失败
                                falseResults.add(false);
                            }
                        }
                        //上传完成
                        if ((falseResults.size() + trueResults.size()) == total) {
                            mProgressDialog.dismiss();
                            if (trueResults.size() == total) {
                                Toast.makeText(MainActivity.this, "上传完成,已审核全部(" + trueResults.size() + "条)数据上传成功", Toast.LENGTH_LONG).show();
                            } else if (falseResults.size() == total) {
                                Toast.makeText(MainActivity.this, "上传完成,已审核全部(" + falseResults.size() + "条)数据上传失败", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "上传完成,已审核有" + trueResults.size() + "条数据上传成功，有" + falseResults.size() + "条数据上传失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
    }


    //更新配置
    private void updateTables(boolean local) {
        if (!CommonUtils.isNetAvailable() && !local) {
            Toast.makeText(this, getString(R.string.net_is_unavailable), Toast.LENGTH_LONG).show();
            return;
        }
        mProgressDialog.setMessage(getString(R.string.wait_update));
        isUpdating = !local && true;
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysCategoryViewModel.class).list(local).observe(this, new Observer<Resource<List<SysCategoryEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysCategoryEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    mProgressDialog.show();
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                } else if (resource.status.equals(Status.SUCCESS)) {
                    mSysCategoryEntities = resource.data;
                   /* if ("owner".equals(PcsApplication.getInstance().getSysOrgEntity().getType().trim())) {
                        SysCategoryEntity mEntity = new SysCategoryEntity();
                        mEntity.setParentid("1");
                        mEntity.setName("施工统计分析");
                        mEntity.setId("123456789");

                        if (mSysCategoryEntities.size() == 12) {
                            mSysCategoryEntities.add(mEntity);
                        }
                    }*/

                }
                updateSysList(local);
            }
        });
    }

    private void updateSysList(boolean local) {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysListViewModel.class).list(local).observe(this, new Observer<Resource<List<SysListEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysListEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    if (local) mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                } else if (resource.status.equals(Status.SUCCESS)) {
                    mSysListEntities = resource.data;
//                    只有业主才能看到统计分析
                   /* if ("owner".equals(PcsApplication.getInstance().getSysOrgEntity().getType().trim())) {
                        SysListEntity entity = new SysListEntity();
                        entity.setId("111111");
                        entity.setCode("ANALYSICOF_MONTHINCREASING_PIPELINE");
                        entity.setCategoryid("123456789");
                        entity.setName("月新增长管线统计");

                        SysListEntity entity2 = new SysListEntity();
                        entity2.setId("222222");
                        entity2.setCode("ANALYSICOF_WELD_PIPELINE");
                        entity2.setCategoryid("123456789");
                        entity2.setName("管道焊接累计长度统计");

                        SysListEntity entity3 = new SysListEntity();
                        entity3.setId("333333");
                        entity3.setCode("ANALYSICOF_WELD_CONDITION");
                        entity3.setCategoryid("123456789");
                        entity3.setName("焊接情况统计");

                        SysListEntity entity4 = new SysListEntity();
                        entity4.setId("444444");
                        entity4.setCode("ANALYSICOF_DATA_CONDITION");
                        entity4.setCategoryid("123456789");
                        entity4.setName("采集数据录入、上报、审核情况统计");

                        if (mSysListEntities.size() == 65) {
                            mSysListEntities.add(entity);
                        }
                        if (mSysListEntities.size() == 66) {
                            mSysListEntities.add(entity2);
                        }
                        if (mSysListEntities.size() == 67) {
                            mSysListEntities.add(entity3);
                        }
                        if (mSysListEntities.size() == 68) {
                            mSysListEntities.add(entity4);
                        }
                    }*/
                    initTreeDatas();
                    if (local) mProgressDialog.dismiss();
                }
                if (isUpdating)
                    updateSysTables();
            }
        });
    }

    private void updateSysTables() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysTableViewModel.class).list(false).observe(this, new Observer<Resource<List<SysTableEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysTableEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                } else if (resource.status.equals(Status.SUCCESS)) {
                }
                if (isUpdating) updateSysUsers();
            }
        });
    }

    private void updateSysUsers() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysUserViewModel.class).list(false)
                .observe(this, new Observer<Resource<List<SysUserEntity>>>() {
                    boolean isError = false;

                    @Override
                    public void onChanged(@Nullable Resource<List<SysUserEntity>> resource) {
                        if (resource.status.equals(Status.LOADING)) {
                            return;
                        } else if (resource.status.equals(Status.ERROR)) {
                            mProgressDialog.dismiss();
                            if (isError) return;
                            isError = true;
                            Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                        } else if (resource.status.equals(Status.SUCCESS)) {

                        }
                        if (isUpdating)
                            updateSysFields();
                    }
                });
    }

    private void updateSysFields() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysFieldViewModel.class).list(false, "").observe(this, new Observer<Resource<List<SysFieldEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysFieldEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating)
                    updateDomain();
            }
        });

    }

    private void updateDomain() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysDomainViewModel.class).list(false, "").observe(this, new Observer<Resource<List<SysDomainEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysDomainEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateSysOrg();
            }
        });
    }

    private void updateSysOrg() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysOrgViewModel.class).list(false).observe(this, new Observer<Resource<List<SysOrgEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysOrgEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
//                if (isUpdating) updateCWeldingUnit();
                if (isUpdating) updateSysFormField();
            }
        });
    }

    private void updateSysFormField() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysFormFieldViewModel.class).list(false).observe(this, new Observer<Resource<List<SysFormFieldEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysFormFieldEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateSysFormFieldType();
            }
        });


    }

    private void updateSysFormFieldType() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysFormFieldTypeViewModel.class).list(false).observe(this, new Observer<Resource<List<SysFormFieldTypeEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysFormFieldTypeEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateSysListButton();
            }
        });

    }

    private void updateSysListButton() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysListButtonViewModel.class).list(false).observe(this, new Observer<Resource<List<SysListButtonEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysListButtonEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateListField();
            }
        });
    }

    private void updateListField() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysListFieldViewModel.class).list(false).observe(this, new Observer<Resource<List<SysListFieldEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysListFieldEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateSysOrgRes();
            }
        });
    }

    private void updateSysOrgRes() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysOrgResViewModel.class).list(false).observe(this, new Observer<Resource<List<SysOrgResEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysOrgResEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateSysRes();
            }
        });
    }

    private void updateSysRes() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysResViewModel.class).list(false).observe(this, new Observer<Resource<List<SysResEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysResEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateSysRole();
            }
        });

    }

    private void updateSysRole() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysRoleViewModel.class).list(false).observe(this, new Observer<Resource<List<SysRoleEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysRoleEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateSysRoleRes();
            }
        });
    }

    private void updateSysRoleRes() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysRoleResViewModel.class).list(false).observe(this, new Observer<Resource<List<SysRoleResEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysRoleResEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateSysUserRes();
            }
        });
    }

    private void updateSysUserRes() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysUserResViewModel.class).list(false).observe(this, new Observer<Resource<List<SysUserResEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysUserResEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
//                if (isUpdating) updateCWeldingUnit();
                if (isUpdating) updateSysRoleUser();
            }
        });
    }

    private void updateSysRoleUser() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(SysRoleUserViewModel.class).list(false).observe(this, new Observer<Resource<List<SysRoleUserEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<SysRoleUserEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateCWeldingUnit();
            }
        });
    }

    private void updateCWeldingUnit() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(CWeldingUnitViewModel.class).list(false).observe(this, new Observer<Resource<List<CWeldingUnitEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<CWeldingUnitEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateWeldingProcedure();
            }
        });
    }

    //    焊接工艺规程
    private void updateWeldingProcedure() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(CWeldingProcedureViewModel.class).list(false).observe(this, new Observer<Resource<List<CWeldingProcedureEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<CWeldingProcedureEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateCPipeSegment();//功能区
            }
        });
    }

    private void updateCPipeSegment() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(MEntityFuctionViewModel.class).list(false).observe(this, new Observer<Resource<List<MEntityFuctionEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<MEntityFuctionEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateCPipeLine();//子项目
            }
        });
    }

    //单元
    public void updateUnit() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(MEntityUnitViewModel.class).list(false).observe(this, new Observer<Resource<List<MEntityUnitEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<MEntityUnitEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateCSection();  //标段
            }
        });
    }

    //子项目
    private void updateCPipeLine() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(MOneProjectInfoViewModel.class).list(false).observe(this, new Observer<Resource<List<MOneProjectInfoEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<MOneProjectInfoEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateUnit();  //单元
            }
        });
    }

    //标段
    private void updateCSection() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(MContractorInfoViewModel.class).list(false).observe(this, new Observer<Resource<List<MContractorInfoEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<MContractorInfoEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateCWelder();//焊工
            }
        });
    }

    //             站场
    private void updateCStation() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(CStationViewModel.class).list(false).observe(this, new Observer<Resource<List<CStationEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<CStationEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateCWelder();
            }
        });
    }

    //           阀室(没有)
    private void updateCValveRoom() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(CValveRoomViewModel.class).list(false).observe(this, new Observer<Resource<List<CValveRoomEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<CValveRoomEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateCWelder();
            }
        });
    }

    //施工焊工数据
    private void updateCWelder() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(CWelderViewModel.class).list(false).observe(this, new Observer<Resource<List<CWelderEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<CWelderEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateSuperVisior();//全部监理单位
            }
        });
    }

    //全部监理单位
    private void updateSuperVisior() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(MContractorInfoViewModel.class).listSupervisior(false).observe(this, new Observer<Resource<List<MContractorInfoEntity>>>() {
            boolean isError = false;

            @Override
            public void onChanged(@Nullable Resource<List<MContractorInfoEntity>> resource) {
                if (resource.status.equals(Status.LOADING)) {
                    return;
                } else if (resource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    if (isError) return;
                    isError = true;
                    Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                }
                if (isUpdating) updateCProject();//项目
            }
        });
    }


    private void updateCProject() {
        ViewModelProviders.of(this, dcsViewModelFactory).get(MProjectInfoViewModel.class).list(false).observe(this, resource -> {
            if (resource.status.equals(Status.LOADING)) {
                return;
            } else if (resource.status.equals(Status.ERROR)) {
                mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, resource.message, Toast.LENGTH_SHORT).show();
            } else if (resource.status.equals(Status.SUCCESS)) {
                mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, getString(R.string.update_finish), Toast.LENGTH_LONG).show();
            }
        });
    }

    private static final String[] filters =

            {
                    "水工保护",
                    "开挖穿越", "定向钻穿越",
                    "桁架跨越", "焊口/接口", "标志桩", "警示牌",
                    "返修焊口/接口", "防腐补口", "防腐补伤",

            };

    //加载树形结构
    private void initTreeDatas() {
        List<SysCategoryEntity> parent1 = new ArrayList<>();// 1.  过滤是实施-数据采集和实施-详细计划的根目录的数据，并添加到集合中
        List<SysCategoryEntity> childs = new ArrayList<>();
        for (int i = 0; i < mSysCategoryEntities.size(); i++) {
            if (!TextUtils.isEmpty(mSysCategoryEntities.get(i).getName())) {
                if ("施工-数据采集".equals(mSysCategoryEntities.get(i).getName()) || "实施详细设计".equals(mSysCategoryEntities.get(i).getName())) {
                    parent1.add(mSysCategoryEntities.get(i));
                }
            }
        }

        List<SysCategoryEntity> firstFilterList = new ArrayList<>();
        for (int i = 0; i < mSysCategoryEntities.size(); i++) {
            for (int j = 0; j < parent1.size(); j++) {
                if (parent1.get(j).getId().equals(mSysCategoryEntities.get(i).getParentid())) {
                    if (!"施工-数据采集".equals(mSysCategoryEntities.get(i).getName()) || !"实施-详细设计".equals(mSysCategoryEntities.get(i).getName())) {
                        firstFilterList.add(mSysCategoryEntities.get(i));
                    }
                }
            }
        }

        for (SysListEntity entity : mSysListEntities) {
            SysCategoryEntity categoryEntity = new SysCategoryEntity();
            for (SysCategoryEntity sysCategoryEntity : firstFilterList) {
                if (entity.getCategoryid() != null) {
                    if (entity.getCategoryid().equals(sysCategoryEntity.getId())) {
                        if (!Arrays.asList(filters).contains(entity.getName()))
                            continue;
                        categoryEntity.setCode(entity.getCode());
                        categoryEntity.setName(entity.getName());
                        categoryEntity.setParentid(entity.getCategoryid());
                        childs.add(categoryEntity);
                    }
                }
            }
        }
//        过滤数据
        List<SysCategoryEntity> parents = new ArrayList<>();
        for (int i = 0; i < mSysCategoryEntities.size(); i++) {

            if (!mSysCategoryEntities.get(i).getId().equals(ROOT_DIR_ID)) {
                if (!("实施-数据采集".equals(mSysCategoryEntities.get(i).getName())) || !("实施-详细设计".equals(mSysCategoryEntities.get(i).getName())))
                    parents.add(mSysCategoryEntities.get(i));
            }
        }
        //根节点
        TreeNode root = TreeNode.root();
        //第一层 实施-数据采集和实施-详细计划
        for (SysCategoryEntity entity : parent1) {
//            第一层文件夹
            TreeNode parent = new TreeNode(entity).setViewHolder(new ParentHolder(this));
//    如果根目录的id和mSysCategoryEntities集合中的catoryId一样，就添加到此根目录下
            for (SysCategoryEntity catogory : parents) {
                if (catogory.getName().equals("线路") || catogory.getName().equals("穿跨越") || catogory.getName().equals("水工")) {
                    if (entity.getId().equals(catogory.getParentid())) {
//                    第二层  文件夹
                        TreeNode p = new TreeNode(catogory).setViewHolder(new ParentHolder(this));
//                    第三层  具体的表
                        for (SysCategoryEntity c : childs) {
                            if (c.getParentid().equals(catogory.getId())) {
                                p.addChild(new TreeNode(c).setViewHolder(new ChildHolder(this)).setExpanded(false));
                                mSysCategoryUploadEntities.add(c);
                            }
                        }
                        parent.addChild(p);
                    }
                }

            }
            root.addChild(parent);
        }

        AndroidTreeView mTreeView = new AndroidTreeView(this, root);
        mTreeView.setDefaultAnimation(true);
        mTreeView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom, true);
        mTreeView.setDefaultNodeClickListener((node, value) -> {

        });
        binding.container.removeAllViews();
        binding.container.addView(mTreeView.getView());
    }

//        List<SysCategoryEntity> childs = new ArrayList<>();
//        for (SysListEntity entity : mSysListEntities) {
//            SysCategoryEntity categoryEntity = new SysCategoryEntity();
//            for (SysCategoryEntity sysCategoryEntity : mSysCategoryEntities) {
//                if (entity.getCategoryid().equals(sysCategoryEntity.getId())) {
//                    if (!Arrays.asList(filters).contains(entity.getName()))
//                        continue;
//                    categoryEntity.setCode(entity.getCode());
//                    categoryEntity.setName(entity.getName());
//                    categoryEntity.setParentid(entity.getCategoryid());
//                    childs.add(categoryEntity);
//                }
//            }
//        }
//        List<SysCategoryEntity> parents = new ArrayList<>();
//        for (SysCategoryEntity treeEntity : mSysCategoryEntities) {
//            if (treeEntity.getId().equals(ROOT_DIR_ID) || Arrays.binarySearch(TABLE_HIDDEN, treeEntity.getName()) >= 0)
//                continue;
//            if (TextUtils.isEmpty(treeEntity.getParentid()) || treeEntity.getParentid().equals(ROOT_DIR_ID))
//                parents.add(treeEntity);
//
//        }
//
//        //根节点
//        TreeNode root = TreeNode.root();
//        for (SysCategoryEntity treeEntity : parents) {
//            TreeNode p = new TreeNode(treeEntity).setViewHolder(new ParentHolder(this));
//            for (SysCategoryEntity c : childs) {
//                if (c.getParentid().equals(treeEntity.getId())) {
//                    p.addChild(new TreeNode(c).setViewHolder(new ChildHolder(this)).setExpanded(false));
//                    mSysCategoryUploadEntities.add(c);
//                }
//            }
//            if (p.getChildren().isEmpty()) continue;
//            root.addChild(p);
//        }
//
//        AndroidTreeView mTreeView = new AndroidTreeView(this, root);
//        mTreeView.setDefaultAnimation(true);
//        mTreeView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom, true);
//        mTreeView.setDefaultNodeClickListener((node, value) -> {
//
//        });
//        binding.container.removeAllViews();
//        binding.container.addView(mTreeView.getView());


    @Override
    public void onUpgradeClick() {
        //版本更新
        upgrade();
    }

    @Override
    public void onUploadClick() {

        if ("supervisor".equals(PcsApplication.getInstance().getSysOrgEntity().getType().trim())) {//审核人员有：已上报和审核
            queryLocalDatas();//已审核中需要上传的数据
            queryLocalSuDatas();//已上报中需要上传的数据
        } else if ("acquisition".equals(PcsApplication.getInstance().getSysOrgEntity().getType().trim())) {//录入人员只有已上报
            queryLocalSuDatas();//已上报中需要上传的数据
        }
//        binding.drawerLayout.closeDrawer(Gravity.LEFT);
    }

    private void queryLocalSuDatas() {
        //上传数据
        if (!CommonUtils.isNetAvailable()) {
            Toast.makeText(this, getString(R.string.net_is_unavailable), Toast.LENGTH_LONG).show();
            return;
        }
        mProgressDialog.setMessage(getString(R.string.upload_datas));
        mProgressDialog.show();
        //存放要上传的数据
        Map<Class, List> listMap = new HashMap<>();
        //查询需要上传数据返回结果
        List<Boolean> queryBooleanList = new ArrayList<>();
        //遍历所有需要上传的实体
        for (SysCategoryEntity entity : mSysCategoryUploadEntities) {
            //计算出类名的前缀
            String[] names = entity.getCode().toLowerCase().split("_");
            String code1 = "";
            for (String name : names) {
                code1 += (new StringBuilder()).append(Character.toUpperCase(name.charAt(0))).append(name.substring(1)).toString();
            }
            final String code = code1;
            try {
                //生成vm类
                Class c = Class.forName(getPackageName() + ".project." + code.toLowerCase() + "." + code + "ViewModel");
                //生成vm实例
                ViewModel vm = ViewModelProviders.of(this, dcsViewModelFactory).get(c);
                //获取list4uploadSu方法 ，list4upload方法用来获取需要上传的数据
                Method upLoadSu = c.getDeclaredMethod("list4UploadSu", d);
                //执行list4upload方法
                LiveData<Resource<List>> liveData = (LiveData<Resource<List>>) upLoadSu.invoke(vm, object);
                liveData.observe(MainActivity.this, listResource -> {
                    if (listResource.status.equals(Status.LOADING))
                        return;
                    else if (listResource.status.equals(Status.ERROR)) {
                        Toast.makeText(this, code + "获取已上报数据失败", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        List entities = listResource.data;
                        queryBooleanList.add(true);
                        //暂存数据到相应的列表中
                        listMap.put(c, entities);
                        //如果所有实体要上传数据获取成功，则进行上传
                        if (queryBooleanList.size() == mSysCategoryUploadEntities.size()) {
                            uploadLocalSuDatas(listMap);
                        }
                    }
                });

            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                mProgressDialog.dismiss();
            }
        }

    }

    @Override
    public void onUpdateClick() {
        //更新配置
        updateTables(false);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
