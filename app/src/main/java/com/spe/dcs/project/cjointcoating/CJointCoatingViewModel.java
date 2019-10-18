package com.spe.dcs.project.cjointcoating;

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
 * Desc:06_防腐补口
 * Author.
 * Data:
 */
public class CJointCoatingViewModel extends ViewModel {
    private static final String TAG = "CJointCoating";
    @Inject
    public CJointCoatingRepository cJointCoatingRepository;

    @Inject
    public CJointCoatingViewModel() {
        Log.d("wwww", "CJointCoatingViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(CJointCoatingEntity cAntiCoatingEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cAntiCoatingEntity.getId()))
        //cAntiCoatingEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cAntiCoatingEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cAntiCoatingEntity.getApproveStatus())) {

        } else {
            cAntiCoatingEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cJointCoatingRepository.save(cAntiCoatingEntity,isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<CJointCoatingEntity> entities) {
        return cJointCoatingRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cJointCoatingRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<CJointCoatingEntity> entities) {
        return cJointCoatingRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核
     * @return
     */
    public LiveData<Resource<Response<List<CJointCoatingEntity>>>> list(int page, int rows, String status) {
        return cJointCoatingRepository.list(page, rows, status);

    }

    public LiveData<Resource<List<CJointCoatingEntity>>> list4Upload() {
        return cJointCoatingRepository.list4Upload();

    }

    public LiveData<Resource<List<CJointCoatingEntity>>> list4UploadSu() {
        return cJointCoatingRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(CJointCoatingEntity entity, String owner) {
        return cJointCoatingRepository.completeTaskJudge(entity, owner);
    }
    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CJointCoatingEntity cJointCoatingEntity) {
        return cJointCoatingRepository.findUpdateId(cJointCoatingEntity);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<CJointCoatingEntity> entities, int type) {
        return cJointCoatingRepository.revoked(entities, type);
    }

    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cJointCoatingRepository.getPic(path);
    }

}
