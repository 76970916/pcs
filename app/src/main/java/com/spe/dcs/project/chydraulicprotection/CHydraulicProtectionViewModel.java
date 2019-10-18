package com.spe.dcs.project.chydraulicprotection;

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
 * Desc:25_施工水工保护
 * Author.
 * Data:
 */
public class CHydraulicProtectionViewModel extends ViewModel {
    private static final String TAG = "CHydraulicProtection";
    @Inject
    public CHydraulicProtectionRepository cHydraulicProtectionRepository;

    @Inject
    public CHydraulicProtectionViewModel() {
        Log.d("wwww", "CHydraulicProtectionViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(CHydraulicProtectionEntity cHydraulicProtectionEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cHydraulicProtectionEntity.getId()))
        //cHydraulicProtectionEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cHydraulicProtectionEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cHydraulicProtectionEntity.getApproveStatus())) {

        } else {
            cHydraulicProtectionEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cHydraulicProtectionRepository.save(cHydraulicProtectionEntity, isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<CHydraulicProtectionEntity> entities) {
        return cHydraulicProtectionRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cHydraulicProtectionRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<CHydraulicProtectionEntity> entities) {
        return cHydraulicProtectionRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核
     * @return
     */
    public LiveData<Resource<Response<List<CHydraulicProtectionEntity>>>> list(int page, int rows, String status) {
        return cHydraulicProtectionRepository.list(page, rows, status);

    }

    public LiveData<Resource<List<CHydraulicProtectionEntity>>> list4Upload() {
        return cHydraulicProtectionRepository.list4Upload();

    }

    public LiveData<Resource<List<CHydraulicProtectionEntity>>> list4UploadSu() {
        return cHydraulicProtectionRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen,CHydraulicProtectionEntity entity, String owner) {
        return cHydraulicProtectionRepository.completeTaskJudge(isShen,entity, owner);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<CHydraulicProtectionEntity> entities, int type) {
        return cHydraulicProtectionRepository.revoked(entities, type);
    }

    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cHydraulicProtectionRepository.getPic(path);
    }

}
