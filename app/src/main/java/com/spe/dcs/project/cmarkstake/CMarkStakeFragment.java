package com.spe.dcs.project.cmarkstake;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.Resource;
import com.spe.dcs.app.Response;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.app.net.Status;
import com.spe.dcs.common.DialogAdapter;
import com.spe.dcs.common.TypeStatus;
import com.spe.dcs.fragments.AsFragment;
import com.spe.dcs.project.cwarningsign.CWarningSignEntity;
import com.spe.dcs.project.cweldjoint.CWeldJointEntity;
import com.spe.dcs.system.sysfield.SysFieldEntity;
import com.spe.dcs.system.sysorg.SysOrgEntity;
import com.spe.dcs.system.sysrole.SysRoleEntity;
import com.spe.dcs.utility.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.spe.dcs.project.cweldjoint.CWeldJointFragment.TYPE_EMPTY;


/**
 * @Package: com.spe.dcs.project.cmarkstake
 * @ClassName：CMarkStakeFragment.java
 * @Author：
 * @CreateDate：
 * @Description： 待上报、已上报、监理审核
 */

public class CMarkStakeFragment extends AsFragment<CMarkStakeEntity> {
    private String mBeiZhu = "";//备注信息
    private int mPosition = 0;//记录监理审核的点击位置
    private ArrayList<ArrayList<String>> datas = new ArrayList<>();
    private List<String> mList = new ArrayList<>();
    private final ArrayList<String> mTags = new ArrayList<>();
    private List<CMarkStakeEntity> cMarkStakeEntities = new ArrayList<>();
    private List<CMarkStakeEntity> cMarkStakeEntitiesChecked = new ArrayList<>();
    private int mType;
    public static final int TYPE_CHECK_ALL = 5;
    public static final int TYPE_JIAOYAN = 3;
    public static final int TYPE_APPROVE = 2;
    public static final int TYPE_REPORTED = 1;
    public static final int TYPE_WAIT_REPORTED = 0;
    public static final int UPDATE_CODE = 131;
    private List<SysFieldEntity> mFiledEntities = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private CMarkStakeEntity cMarkStakeUpdateEntity;
    private CMarkStakeEntity cMarkStakeLookEntity;
    private int rows = 10;//
    private int page = 1;//页数
    private CMarkStakeViewModel cMarkStakeViewModel;
    private List<CMarkStakeEntity> updateMoreData = new ArrayList<>();
    private boolean isReproting = false;
    private boolean isDelete = false;
    private boolean isApproving = false;
    private boolean isRevoking = false;
    private SysRoleEntity sysOrgEntity;
    private boolean isJiao;


    public static CMarkStakeFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type", type);
        CMarkStakeFragment fragment = new CMarkStakeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ArrayList<String> initTags() {
        mTags.clear();
        switch (mType) {
            case TYPE_CHECK_ALL:
            case TYPE_JIAOYAN:
            case TYPE_APPROVE:
            case TYPE_REPORTED:
            case TYPE_WAIT_REPORTED:
                mTags.add(0, "");
                break;
        }
        for (SysFieldEntity entity : mFiledEntities)
            mTags.add(entity.getName());
        return mTags;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFiledEntities = ((CMarkStakeListActivity) getActivity()).getFiledEntities();
        mType = getArguments().getInt("type");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public ArrayList<ArrayList<String>> parseEntityToString(List<CMarkStakeEntity> entities) {
        ArrayList<ArrayList<String>> datas = new ArrayList<>();
        for (CMarkStakeEntity entity : entities) {
            ArrayList<String> list = new ArrayList<>();
//            if (mType != TYPE_REPORTED)
            list.add("");
            for (SysFieldEntity filedEntity : mFiledEntities) {
                list.add((String) CommonUtils.getFieldValueByName(filedEntity.getCode(), entity));
            }
            datas.add(list);
        }
        return datas;
    }

    @Override
    public void onItemClickListener(int position) {

        CMarkStakeEntity entity = cMarkStakeEntities.get(position);
        entity.setChecked(!entity.isChecked());
    }

    @Override
    public void onRefreshData() {
        page = 1;
        rows = 10;
        switch (mType) {
            case TYPE_WAIT_REPORTED:
                getWaitReportedData(page, rows);
                break;
            case TYPE_REPORTED:
                getReportedData(page, rows);
                break;
            case TYPE_APPROVE:
                getRevokedData(page, rows);
                break;
            case TYPE_JIAOYAN:
                getJiaoYanData(page, rows);
                break;
            case TYPE_CHECK_ALL:
                getAllData(page, rows);
                break;
        }

    }

    @Override
    public void onLoadMoreData() {
        page++;
        switch (mType) {
            case TYPE_WAIT_REPORTED:
                getWaitReportedData(page, rows);
                break;
            case TYPE_REPORTED:
                getReportedData(page, rows);
                break;
            case TYPE_APPROVE:
                getRevokedData(page, rows);
                break;
            case TYPE_JIAOYAN:
                getJiaoYanData(page, rows);
                break;
            case TYPE_CHECK_ALL:
                getAllData(page, rows);
                break;
        }
    }


    //更新列表数据
    public void updateList(List<CMarkStakeEntity> entities) {
        cMarkStakeEntities = entities;
        super.updateList(entities);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList.clear();
        mList.add(getString(R.string.qualifie));
        mList.add(getString(R.string.unqualified));
        sysOrgEntity = PcsApplication.getInstance().getSysRoleEntity();
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.wait));
        mProgressDialog.setCancelable(false);

        cMarkStakeViewModel = ViewModelProviders.of(getActivity()).get(CMarkStakeViewModel.class);

        //上报
        binding.report.setOnClickListener(v -> {
            if (isReproting) {
                Toast.makeText(getActivity(), getString(R.string.is_reporting), Toast.LENGTH_SHORT).show();
                return;
            } else {
                isReproting = true;
                cMarkStakeEntitiesChecked.clear();
                for (CMarkStakeEntity entity : cMarkStakeEntities)
                    if (entity.isChecked()) {
                        entity.setApproveStatus(TypeStatus.SUPERVISOR);
                        cMarkStakeEntitiesChecked.add(entity);
                    }
                if (cMarkStakeEntitiesChecked.isEmpty()) {
                    Toast.makeText(getActivity(), getString(R.string.need_report_data), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Boolean> trueResults = new ArrayList<>(); //上报正确的集合
                List<Boolean> falseResults = new ArrayList<>();//上报失败的集合
//               有网络状态下，多选状态下，选中几条就上报几条
                if (CommonUtils.isNetAvailable()) {
                    for (int i = 0; i < cMarkStakeEntitiesChecked.size(); i++) {
                        cMarkStakeViewModel.reports(cMarkStakeEntitiesChecked.get(i).getId()).observe(this, flagRespEntityResource -> {
                            if (flagRespEntityResource.status.equals(Status.SUCCESS)) {
                                if (flagRespEntityResource.data.isFlag()) {
                                    trueResults.add(true);
                                    isReproting = false;
                                } else {
                                    falseResults.add(false);
                                }
                            } else if (flagRespEntityResource.status.equals(Status.ERROR)) {
                                falseResults.add(false);

                                isReproting = false;
                            } else if (flagRespEntityResource.status.equals(Status.LOADING)) {
                                mProgressDialog.show();
                            }

                            if (trueResults.size() + falseResults.size() == cMarkStakeEntitiesChecked.size()) {
                                mProgressDialog.dismiss();
                                onResume();
                                if (trueResults.size() == cMarkStakeEntitiesChecked.size()) {
                                    Toast.makeText(getActivity(), "上报成功" + trueResults.size() + "条", Toast.LENGTH_SHORT).show();
                                } else if (falseResults.size() == cMarkStakeEntitiesChecked.size()) {
                                    Toast.makeText(getActivity(), "上报失败" + falseResults.size() + "条", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "上报成功" + trueResults.size() + "条" + "上报失败" + falseResults.size() + "条", Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                    }
                } else {
//                  无网络条件下走本地，上报的是集合
                    cMarkStakeViewModel.report(cMarkStakeEntitiesChecked).observe(this, flagRespEntityResource -> {
                        if (flagRespEntityResource.status.equals(Status.SUCCESS)) {
                            if (flagRespEntityResource.data.isFlag()) {
                                isReproting = false;
                                Toast.makeText(getActivity(), getString(R.string.report_success), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), flagRespEntityResource.data.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                            onResume();
                        } else if (flagRespEntityResource.status.equals(Status.ERROR)) {
                            mProgressDialog.dismiss();
                            isReproting = false;
                            Toast.makeText(getActivity(), getString(R.string.report_failed) + flagRespEntityResource.message, Toast.LENGTH_SHORT).show();
                        } else if (flagRespEntityResource.status.equals(Status.LOADING)) {
                            mProgressDialog.show();
                        }
                    });

                }
            }

        });
        //上报删除
        binding.del.setOnClickListener((View v) -> {
            if (isDelete) {
                Toast.makeText(getActivity(), getString(R.string.is_deleting), Toast.LENGTH_SHORT).show();
                return;
            } else {
                cMarkStakeEntitiesChecked.clear();
                for (CMarkStakeEntity entity : cMarkStakeEntities)
                    if (entity.isChecked())
                        cMarkStakeEntitiesChecked.add(entity);
                if (cMarkStakeEntitiesChecked.isEmpty()) {
                    Toast.makeText(getActivity(), getString(R.string.need_del_data), Toast.LENGTH_SHORT).show();
                    return;
                } else if (cMarkStakeEntitiesChecked.size() > 1) {
                    Toast.makeText(getActivity(), getString(R.string.need_del_one_data), Toast.LENGTH_SHORT).show();
                    return;
                }
                isDelete = true;
                cMarkStakeViewModel.delete(cMarkStakeEntitiesChecked).observe(this, (Resource<RespEntity> respEntityResource) -> {
                    if (respEntityResource.status == Status.SUCCESS) {
                        mProgressDialog.dismiss();
                        if (respEntityResource.data.getCode() == 1) {
                            isDelete = false;
                            Toast.makeText(getActivity(), getString(R.string.del_success), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), respEntityResource.data.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        onResume();
                    } else if (respEntityResource.status == Status.LOADING) {
                        mProgressDialog.show();

                    } else if (respEntityResource.status == Status.ERROR) {
                        mProgressDialog.dismiss();
                        isDelete = false;
                        Toast.makeText(getActivity(), getString(R.string.de_failed) + respEntityResource.message, Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });
        //监理审核
        binding.approve.setOnClickListener(v -> {
            if (isApproving) {
                Toast.makeText(getActivity(), getString(R.string.is_approving), Toast.LENGTH_SHORT).show();
                return;
            } else {
                cMarkStakeEntitiesChecked.clear();
                for (CMarkStakeEntity entity : cMarkStakeEntities)
                    if (entity.isChecked()) {
                        entity.setApproveStatus(TypeStatus.DADACHECK);
                        cMarkStakeEntitiesChecked.add(entity);
                    }
                if (cMarkStakeEntitiesChecked.isEmpty()) {
                    Toast.makeText(getActivity(), getString(R.string.need_select_data), Toast.LENGTH_SHORT).show();
                    return;
                } else if (cMarkStakeEntitiesChecked.size() > 1) {
                    Toast.makeText(getActivity(), getString(R.string.need_one_select_data), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    createDialog();
                }
            }
        });

//       修改
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cMarkStakeEntitiesChecked.clear();
                for (CMarkStakeEntity entity : cMarkStakeEntities)
                    if (entity.isChecked()) {
                        entity.setApproveStatus(TypeStatus.ENTER);
                        cMarkStakeEntitiesChecked.add(entity);
                        cMarkStakeUpdateEntity = entity;
                    }
                if (cMarkStakeEntitiesChecked.isEmpty()) {
                    Toast.makeText(getActivity(), getString(R.string.need_update_data), Toast.LENGTH_SHORT).show();
                    return;
                } else if (cMarkStakeEntitiesChecked.size() > 1) {
                    Toast.makeText(getActivity(), getString(R.string.need_one_data), Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getActivity(), CMarkStakeActivity.class);
                intent.putExtra(CMarkStakeEntity.class.getSimpleName(), cMarkStakeUpdateEntity);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

//       撤回

        binding.widthdraw.setOnClickListener((View v) -> {

            if (isRevoking) {
                Toast.makeText(getActivity(), getString(R.string.is_reworking), Toast.LENGTH_SHORT).show();
                return;
            } else {
                int type = 1;
                switch (mType) {
                    case TYPE_REPORTED:
                        type = TYPE_REPORTED;
                        break;
                    case TYPE_APPROVE:
                        type = TYPE_APPROVE;
                        break;
                }

                cMarkStakeEntitiesChecked.clear();
                for (CMarkStakeEntity entity : cMarkStakeEntities)
                    if (entity.isChecked())
                        cMarkStakeEntitiesChecked.add(entity);
                if (cMarkStakeEntitiesChecked.isEmpty()) {
                    Toast.makeText(getActivity(), getString(R.string.need_withdraw_data), Toast.LENGTH_SHORT).show();
                    return;
                } else if (cMarkStakeEntitiesChecked.size() > 1) {
                    Toast.makeText(getActivity(), getString(R.string.need_one_revoke_data), Toast.LENGTH_SHORT).show();
                    return;
                }
                isRevoking = true;
                cMarkStakeViewModel.revoked(cMarkStakeEntitiesChecked, type).observe(this, flagRespEntityResource -> {
                    if (flagRespEntityResource.status.equals(Status.SUCCESS)) {
                        mProgressDialog.dismiss();
                        if (flagRespEntityResource.data.isFlag()) {
                            isRevoking = false;
                            Toast.makeText(getActivity(), getString(R.string.withdraw_success), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), flagRespEntityResource.data.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        onResume();
                    } else if (flagRespEntityResource.status.equals(Status.ERROR)) {
                        mProgressDialog.dismiss();
                        isRevoking = false;
                        Toast.makeText(getActivity(), getString(R.string.withdraw_failed), Toast.LENGTH_SHORT).show();
                    } else if (flagRespEntityResource.status.equals(Status.LOADING)) {
                        mProgressDialog.show();
                    }
                });


            }
        });

//        抽查
        binding.randomCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonUtils.isNetAvailable()) {
                    cMarkStakeEntitiesChecked.clear();
                    for (CMarkStakeEntity entity : cMarkStakeEntities)
                        if (entity.isChecked())
                            cMarkStakeEntitiesChecked.add(entity);
                    if (cMarkStakeEntitiesChecked.isEmpty()) {
                        Toast.makeText(getActivity(), getString(R.string.need_check_data), Toast.LENGTH_SHORT).show();
                        return;
                    } else if (cMarkStakeEntitiesChecked.size() > 1) {
                        Toast.makeText(getActivity(), getString(R.string.need_one_check_data), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    createCheckDialog();

                } else {
                    Toast.makeText(getActivity(), "业主抽查必须在有网络的情况下才能进行抽查", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

//        查看
        binding.look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cMarkStakeEntitiesChecked.clear();
                for (CMarkStakeEntity entity : cMarkStakeEntities)
                    if (entity.isChecked()) {
                        cMarkStakeLookEntity = entity;
                        cMarkStakeEntitiesChecked.add(entity);
                    }

                if (cMarkStakeEntitiesChecked.isEmpty()) {
                    Toast.makeText(getActivity(), getString(R.string.need_look_data), Toast.LENGTH_SHORT).show();
                    return;
                } else if (cMarkStakeEntitiesChecked.size() > 1) {
                    Toast.makeText(getActivity(), getString(R.string.need_one_look_data), Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), CMarkStakeLookActivity.class);
                intent.putExtra(CMarkStakeEntity.class.getSimpleName(), cMarkStakeLookEntity);
                startActivity(intent);

            }
        });
        //校验
        binding.randomJiaoyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cMarkStakeEntitiesChecked.clear();
                for (CMarkStakeEntity entity : cMarkStakeEntities)
                    if (entity.isChecked()) {
                        cMarkStakeLookEntity = entity;
                        cMarkStakeEntitiesChecked.add(entity);
                    }

                if (cMarkStakeEntitiesChecked.isEmpty()) {
                    Toast.makeText(getActivity(), getString(R.string.need_jiaoyan_data), Toast.LENGTH_SHORT).show();
                    return;
                } else if (cMarkStakeEntitiesChecked.size() > 1) {
                    Toast.makeText(getActivity(), getString(R.string.need_jiaoyan_data), Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    createJiaoYanDialog();
                }
            }
        });

        if (sysOrgEntity != null) {
            if ("ConstructionController".equals(sysOrgEntity.getCode().trim())) {
//审核人员
                switch (mType) {
                    case TYPE_WAIT_REPORTED:
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.GONE);
                        break;
                    case TYPE_REPORTED:
                        //已上报只有审核按钮
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.VISIBLE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.GONE);
                        binding.randomCheck.setVisibility(View.GONE);
                        break;
                    case TYPE_APPROVE:
                        //审核人 已审核显示查看
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.randomJiaoyan.setVisibility(View.GONE);
                        binding.look.setVisibility(View.VISIBLE);
                        binding.randomCheck.setVisibility(View.GONE);
                        break;
                }
            } else if ("ConstructManager".equals(sysOrgEntity.getCode().trim())) {
//               录入人员
                switch (mType) {
                    case TYPE_WAIT_REPORTED:
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.GONE);
                        binding.randomCheck.setVisibility(View.GONE);
                        break;
                    case TYPE_REPORTED:
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.GONE);
                        binding.randomCheck.setVisibility(View.GONE);
                        break;
                    case TYPE_APPROVE:
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.GONE);
                        binding.randomCheck.setVisibility(View.GONE);
                        break;
                }
            } else if (("ProjectManager".equals(sysOrgEntity.getCode().trim()))) {
//               业主
                switch (mType) {
                    case TYPE_WAIT_REPORTED:
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.GONE);

                        break;
                    case TYPE_REPORTED:
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.GONE);
                        binding.randomCheck.setVisibility(View.GONE);
                        break;
                    case TYPE_APPROVE:
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.VISIBLE);
                        binding.randomCheck.setVisibility(View.VISIBLE);
                        break;
                    case TYPE_JIAOYAN:
                        //业主 已校验里只有抽查按钮
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.GONE);
                        binding.randomCheck.setVisibility(View.VISIBLE);
                        binding.randomJiaoyan.setVisibility(View.GONE);
                        break;
                }
            }else if("PDService".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())){
                //校验人员
                switch (mType) {
                    case TYPE_APPROVE:
                        //已审核tab中显示查看和校验两个按钮
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.VISIBLE);
                        binding.randomCheck.setVisibility(View.GONE);
                        binding.randomJiaoyan.setVisibility(View.VISIBLE);
                        break;
                    case TYPE_JIAOYAN:
                        //已校验里只有查看按钮
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.VISIBLE);
                        binding.randomCheck.setVisibility(View.GONE);
                        binding.randomJiaoyan.setVisibility(View.GONE);
                        break;
                }

            }else if("admin".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())){
                //管理员
                switch (mType){
                    case TYPE_JIAOYAN:
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.query.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.GONE);
                        binding.randomCheck.setVisibility(View.VISIBLE);
                        break;
                    case TYPE_CHECK_ALL:
                        binding.report.setVisibility(View.GONE);
                        binding.del.setVisibility(View.GONE);
                        binding.update.setVisibility(View.GONE);
                        binding.query.setVisibility(View.GONE);
                        binding.approve.setVisibility(View.GONE);
                        binding.widthdraw.setVisibility(View.GONE);
                        binding.look.setVisibility(View.VISIBLE);
                        binding.randomCheck.setVisibility(View.GONE);
                        break;
                }
            }
        }
    }

    private void createCheckDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.random_check));
        builder.setMessage(getString(R.string.is_random));


        builder.setNegativeButton(R.string.bt_cancle, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.setPositiveButton(R.string.bt_sure, (dialogInterface, i) -> {

            CMarkStakeEntity entity = cMarkStakeEntitiesChecked.get(0);
            entity.setRemark("");//备注
            entity.setJudge("unqualified");//抽查的话为unqualified
            cMarkStakeViewModel.completeTaskJudge(false,entity, TypeStatus.OWNER).observe(CMarkStakeFragment.this, flagRespEntityResource -> {
                if (flagRespEntityResource.status.equals(Status.SUCCESS)) {
                    mProgressDialog.dismiss();
                    if (flagRespEntityResource.data.isFlag()) {

                        Toast.makeText(getActivity(), getString(R.string.check_success), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), flagRespEntityResource.data.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    onResume();
                } else if (flagRespEntityResource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getActivity(), getString(R.string.check_failed) + flagRespEntityResource.data.getMsg(), Toast.LENGTH_SHORT).show();
                } else if (flagRespEntityResource.status.equals(Status.LOADING)) {
                    mProgressDialog.show();
                }

            });
        });
        builder.show();

    }


    @Override
    public void onResume() {
        super.onResume();
        switch (mType) {
            case TYPE_WAIT_REPORTED:
                page = 1;
                getWaitReportedData(page, rows);    //获取待上报列表数据
                break;
            case TYPE_REPORTED:
                page = 1;
                getReportedData(page, rows);//已上报数据
                break;
            case TYPE_APPROVE:
                page = 1;
                getRevokedData(page, rows);//已审核数据
                break;
            case TYPE_JIAOYAN:
                page = 1;
                getJiaoYanData(page, rows);//已校验数据
                break;
            case TYPE_CHECK_ALL:
                page = 1;
                getAllData(page, rows);//全部数据
                break;
        }
    }
    //全部数据
    private void getAllData(int page, int rows) {
        cMarkStakeViewModel.list(page, rows, TypeStatus.ALLDATA).observe(this, (Resource<Response<List<CMarkStakeEntity>>> respEntityResource) -> {
            if (respEntityResource.status == Status.SUCCESS) {
                mProgressDialog.dismiss();
                if (page == 1) {
                    updateList(respEntityResource.data.getData());
                } else {
                    updateMoreData = respEntityResource.data.getData();
                    cMarkStakeEntities.addAll(updateMoreData);
                    updateListLoadMore(updateMoreData);
                }
                Toast.makeText(getActivity(), getString(R.string.query_success), Toast.LENGTH_SHORT).show();
            } else if (respEntityResource.status == Status.LOADING)
                mProgressDialog.show();
            else if (respEntityResource.status == Status.ERROR) {
                mProgressDialog.dismiss();
                Toast.makeText(getActivity(), getString(R.string.query_failed) + respEntityResource.message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //已校验列表数据
    private void getJiaoYanData(int page, int rows) {
        cMarkStakeViewModel.list(page, rows, TypeStatus.PROJECTMANAGER).observe(this, (Resource<Response<List<CMarkStakeEntity>>> respEntityResource) -> {
            if (respEntityResource.status == Status.SUCCESS) {
                mProgressDialog.dismiss();
                if (page == 1) {
                    updateList(respEntityResource.data.getData());
                } else {
                    updateMoreData = respEntityResource.data.getData();
                    cMarkStakeEntities.addAll(updateMoreData);
                    updateListLoadMore(updateMoreData);
                }
                Toast.makeText(getActivity(), getString(R.string.query_success), Toast.LENGTH_SHORT).show();
            } else if (respEntityResource.status == Status.LOADING)
                mProgressDialog.show();
            else if (respEntityResource.status == Status.ERROR) {
                mProgressDialog.dismiss();
                Toast.makeText(getActivity(), getString(R.string.query_failed) + respEntityResource.message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //          已审核列表数据
    private void getRevokedData(int page, int rows) {
        cMarkStakeViewModel.list(page, rows, TypeStatus.OWNERS).observe(this, (Resource<Response<List<CMarkStakeEntity>>> respEntityResource) -> {
            if (respEntityResource.status == Status.SUCCESS) {
                mProgressDialog.dismiss();
                if (page == 1) {
                    updateList(respEntityResource.data.getData());
                } else {
                    updateMoreData = respEntityResource.data.getData();
                    cMarkStakeEntities.addAll(updateMoreData);
                    updateListLoadMore(updateMoreData);
                }
                Toast.makeText(getActivity(), getString(R.string.query_success), Toast.LENGTH_SHORT).show();
            } else if (respEntityResource.status == Status.LOADING)
                mProgressDialog.show();
            else if (respEntityResource.status == Status.ERROR) {
                mProgressDialog.dismiss();
                Toast.makeText(getActivity(), getString(R.string.query_failed) + respEntityResource.message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //             已上报数据列表
    private void getReportedData(int page, int rows) {
        cMarkStakeViewModel.list(page, rows, TypeStatus.SUPERVISOR).observe(this, (Resource<Response<List<CMarkStakeEntity>>> respEntityResource) -> {
            if (respEntityResource.status == Status.SUCCESS) {
                mProgressDialog.dismiss();
                if (page == 1) {
                    updateList(respEntityResource.data.getData());
                } else {
                    updateMoreData = respEntityResource.data.getData();
                    cMarkStakeEntities.addAll(updateMoreData);
                    updateListLoadMore(updateMoreData);
                }
                Toast.makeText(getActivity(), getString(R.string.query_success), Toast.LENGTH_SHORT).show();
            } else if (respEntityResource.status == Status.LOADING)
                mProgressDialog.show();
            else if (respEntityResource.status == Status.ERROR) {
                mProgressDialog.dismiss();
                Toast.makeText(getActivity(), getString(R.string.query_failed) + respEntityResource.message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取待上报列表数据
    private void getWaitReportedData(int page, int rows) {

        cMarkStakeViewModel.list(page, rows, TypeStatus.ENTER).observe(this, (Resource<Response<List<CMarkStakeEntity>>> respEntityResource) -> {
            if (respEntityResource.status == Status.SUCCESS) {
                mProgressDialog.dismiss();
                if (page == 1) {
                    updateList(respEntityResource.data.getData());
                } else {
                    updateMoreData = respEntityResource.data.getData();
                    cMarkStakeEntities.addAll(updateMoreData);
                    updateListLoadMore(updateMoreData);
                }


                Toast.makeText(getActivity(), getString(R.string.query_success), Toast.LENGTH_SHORT).show();
            } else if (respEntityResource.status == Status.LOADING)
                mProgressDialog.show();
            else if (respEntityResource.status == Status.ERROR) {
                mProgressDialog.dismiss();
                Toast.makeText(getActivity(), getString(R.string.query_failed) + respEntityResource.message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createJiaoYanDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_approve, null);
        ListView listView = view.findViewById(R.id.lv);
        EditText edBeiZhu = view.findViewById(R.id.ed_beizhu);
        DialogAdapter adapter = new DialogAdapter(getActivity(), mList);
        listView.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.jinli_check));
        builder.setView(view);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            mPosition = i;
            adapter.setIndex(i);
        });
        builder.setNegativeButton(R.string.bt_cancle, (dialogInterface, i) -> dialogInterface.dismiss());

        builder.setPositiveButton(R.string.bt_sure, (dialogInterface, i) -> {
            isJiao = true;
            mBeiZhu = edBeiZhu.getText().toString().trim();
            CMarkStakeEntity entity = cMarkStakeEntitiesChecked.get(0);
            entity.setRemark(mBeiZhu);//备注
            if (mPosition == 0) {
                entity.setJudge("qualified"); //合格
            } else if (mPosition == 1) {
                entity.setJudge("unqualified");//不合格
            }
            cMarkStakeViewModel.completeTaskJudge(false, entity, TypeStatus.PROJECTMANAGER).observe(this, flagRespEntityResource -> {
                if (flagRespEntityResource.status.equals(Status.SUCCESS)) {
                    mProgressDialog.dismiss();
                    if (flagRespEntityResource.data.isFlag()) {
                        isJiao = false;
                        Toast.makeText(getActivity(), getString(R.string.jiaoyan_success), Toast.LENGTH_SHORT).show();
                        onResume();
                    } else {
                        Toast.makeText(getActivity(), flagRespEntityResource.data.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else if (flagRespEntityResource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    isJiao = false;
                    Toast.makeText(getActivity(), getString(R.string.jiaoyan_failed) + flagRespEntityResource.data.getMsg(), Toast.LENGTH_SHORT).show();
                } else if (flagRespEntityResource.status.equals(Status.LOADING)) {
                    mProgressDialog.show();
                }

            });
        });
        builder.show();
    }
    private void createDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_approve, null);
        ListView listView = view.findViewById(R.id.lv);
        EditText edBeiZhu = view.findViewById(R.id.ed_beizhu);
        DialogAdapter adapter = new DialogAdapter(getActivity(), mList);
        listView.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.approved));
        builder.setView(view);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            mPosition = i;
            adapter.setIndex(i);
        });
        builder.setNegativeButton(R.string.bt_cancle, (dialogInterface, i) -> dialogInterface.dismiss());

        builder.setPositiveButton(R.string.bt_sure, (dialogInterface, i) -> {
            isApproving = true;
            mBeiZhu = edBeiZhu.getText().toString().trim();
            CMarkStakeEntity entity = cMarkStakeEntitiesChecked.get(0);
            entity.setRemark(mBeiZhu);//备注

          /*  entity.setSupervisionUnitId(PcsApplication.getInstance().getSysOrgEntity().getName());//监理单位

            entity.setSupervisionEngineer(PcsApplication.getInstance().getSysUserEntity().getName());//监理工程师*/
            if (mPosition == 0) {
                entity.setJudge("qualified"); //合格
            } else if (mPosition == 1) {
                entity.setJudge("unqualified");//不合格
            }
            cMarkStakeViewModel.completeTaskJudge(true,entity, TypeStatus.OWNERS).observe(CMarkStakeFragment.this, flagRespEntityResource -> {
                if (flagRespEntityResource.status.equals(Status.SUCCESS)) {
                    mProgressDialog.dismiss();
                    if (flagRespEntityResource.data.isFlag()) {
                        isApproving = false;
                        Toast.makeText(getActivity(), getString(R.string.approve_success), Toast.LENGTH_SHORT).show();
                        onResume();
                    } else {
                        Toast.makeText(getActivity(), flagRespEntityResource.data.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else if (flagRespEntityResource.status.equals(Status.ERROR)) {
                    mProgressDialog.dismiss();
                    isApproving = false;
                    Toast.makeText(getActivity(), getString(R.string.approve_failed) + flagRespEntityResource.data.getMsg(), Toast.LENGTH_SHORT).show();
                } else if (flagRespEntityResource.status.equals(Status.LOADING)) {
                    mProgressDialog.show();
                }

            });
        });
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == UPDATE_CODE && resultCode == RESULT_OK) {
            cMarkStakeEntitiesChecked.clear();
            page = 1;
            getWaitReportedData(page, rows);
        }
    }
}


