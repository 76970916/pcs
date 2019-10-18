package com.spe.dcs.project.cweldjoint;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.Response;
import com.spe.dcs.app.net.FlagRespEntity;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.common.TypeStatus;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:03_施工焊口
 * Author.
 * Data:
 */
public class CWeldJointViewModel extends ViewModel {
    private static final String TAG = "CWeld";
    @Inject
    public CWeldJointRepository cWeldJointRepository;

    @Inject
    public CWeldJointViewModel() {
        Log.d("wwww", "CWeldViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(CWeldJointEntity cWeldEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cWeldEntity.getId()))
        //cWeldEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cWeldEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cWeldEntity.getApproveStatus())) {

        } else {
            cWeldEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cWeldJointRepository.save(cWeldEntity, isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<CWeldJointEntity> entities) {
        return cWeldJointRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cWeldJointRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<CWeldJointEntity> entities) {
        return cWeldJointRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核
     * @return
     */
    public LiveData<Resource<Response<List<CWeldJointEntity>>>> list(int page, int rows, String status) {
        return cWeldJointRepository.list(page, rows, status);

    }

    //根据条件查询
    public LiveData<Resource<Response<List<CWeldJointEntity>>>> queryList(int page, int rows, String sectionNumber, String weldNum, String stakeNumber, String weldingUnit, String status) {
        return cWeldJointRepository.queryList(page, rows, sectionNumber, weldNum, stakeNumber, weldingUnit, status);

    }

    public LiveData<Resource<List<CWeldJointEntity>>> list4Upload() {
        return cWeldJointRepository.list4Upload();

    }

    public LiveData<Resource<List<CWeldJointEntity>>> list4UploadSu() {
        return cWeldJointRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(CWeldJointEntity entity, String owner) {
        return cWeldJointRepository.completeTaskJudge(entity, owner);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<CWeldJointEntity> entities, int type) {
        return cWeldJointRepository.revoked(entities, type);
    }
    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CWeldJointEntity cWeldJointEntity) {
        return cWeldJointRepository.findUpdateId(cWeldJointEntity);
    }


    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cWeldJointRepository.getPic(path);
    }

    //    获取月新增长管线统计
    public LiveData<Resource<CWeldJointIncreaseEntity>> getIncreasePipeline(String year) {
        return cWeldJointRepository.getIncreasePipeline(year);
    }

    //    获取焊接累计长度统计
    public LiveData<Resource<List<CWeldJointLengthEntity>>> getAllLength() {
        return cWeldJointRepository.getAllLength();
    }

    //    获取焊接情况统计
    public LiveData<Resource<List<CWeldJointConditionEntity>>> getWeldStatistic(String startMonth, String endMonth) {
        return cWeldJointRepository.getWeldStatistic(startMonth, endMonth);
    }

    //    获取采集数据录入，上报，审核情况统计
    public LiveData<Resource<List<CWeldJointCollectionEntity>>> getDataCollection(String startMonth, String endMonth) {
        return cWeldJointRepository.getDataCollection(startMonth, endMonth);
    }
}
