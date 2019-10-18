package com.spe.dcs.project.creworkweldjoint;

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
 * Desc:04_施工返修口
 * Author.
 * Data:
 */
public class CReworkWeldJointViewModel extends ViewModel {
    private static final String TAG = "CWeldRework";
    @Inject
    public CReworkWeldJointRepository cReworkWeldJointRepository;

    @Inject
    public CReworkWeldJointViewModel() {
        Log.d("wwww", "CWeldReworkViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(CReworkWeldJointEntity cReworkWeldJointEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cReworkWeldJointEntity.getId()))
        //cReworkWeldJointEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cReworkWeldJointEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cReworkWeldJointEntity.getApproveStatus())) {

        } else {
            cReworkWeldJointEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cReworkWeldJointRepository.save(cReworkWeldJointEntity,isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<CReworkWeldJointEntity> entities) {
        return cReworkWeldJointRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cReworkWeldJointRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<CReworkWeldJointEntity> entities) {
        return cReworkWeldJointRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核
     * @return
     */
    public LiveData<Resource<Response<List<CReworkWeldJointEntity>>>> list(int page, int rows, String status) {
        return cReworkWeldJointRepository.list(page, rows, status);

    }

    public LiveData<Resource<List<CReworkWeldJointEntity>>> list4Upload() {
        return cReworkWeldJointRepository.list4Upload();

    }

    public LiveData<Resource<List<CReworkWeldJointEntity>>> list4UploadSu() {
        return cReworkWeldJointRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(CReworkWeldJointEntity entity, String owner) {
        return cReworkWeldJointRepository.completeTaskJudge(entity, owner);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<CReworkWeldJointEntity> entities, int type) {
        return cReworkWeldJointRepository.revoked(entities, type);
    }
    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CReworkWeldJointEntity cReworkWeldJointEntity) {
        return cReworkWeldJointRepository.findUpdateId(cReworkWeldJointEntity);
    }


    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cReworkWeldJointRepository.getPic(path);
    }

}
