package com.spe.dcs.project.ccoatingrepair;

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
 * Desc:09_施工防腐补伤
 * Author.
 * Data:
 */
public class CCoatingRepairViewModel extends ViewModel {
    private static final String TAG = "CAntiCoatingRepair";
    @Inject
    public CCoatingRepairRepository cCoatingRepairRepository;

    @Inject
    public CCoatingRepairViewModel() {
        Log.d("wwww", "CAntiCoatingRepairViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(CCoatingRepairEntity cAntiCoatingRepairEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cAntiCoatingRepairEntity.getId()))
        //cAntiCoatingRepairEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cAntiCoatingRepairEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cAntiCoatingRepairEntity.getApproveStatus())) {

        } else {
            cAntiCoatingRepairEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cCoatingRepairRepository.save(cAntiCoatingRepairEntity,isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<CCoatingRepairEntity> entities) {
        return cCoatingRepairRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cCoatingRepairRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<CCoatingRepairEntity> entities) {
        return cCoatingRepairRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核
     * @return
     */
    public LiveData<Resource<Response<List<CCoatingRepairEntity>>>> list(int page, int rows, String status) {
        return cCoatingRepairRepository.list(page, rows, status);

    }

    public LiveData<Resource<List<CCoatingRepairEntity>>> list4Upload() {
        return cCoatingRepairRepository.list4Upload();

    }

    public LiveData<Resource<List<CCoatingRepairEntity>>> list4UploadSu() {
        return cCoatingRepairRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(CCoatingRepairEntity entity, String owner) {
        return cCoatingRepairRepository.completeTaskJudge(entity, owner);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<CCoatingRepairEntity> entities, int type) {
        return cCoatingRepairRepository.revoked(entities, type);
    }
    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CCoatingRepairEntity cCoatingRepairEntity) {
        return cCoatingRepairRepository.findUpdateId(cCoatingRepairEntity);
    }

    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cCoatingRepairRepository.getPic(path);
    }

}
