package com.spe.dcs.project.cwarningsign;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.Response;
import com.spe.dcs.app.net.FlagRespEntity;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.common.TypeStatus;
import com.spe.dcs.project.chddcro.CHddCroEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:30_施工警示牌
 * Author.
 * Data:
 */
public class CWarningSignViewModel extends ViewModel {
    private static final String TAG = "CWarningSign";
    @Inject
    public CWarningSignRepository cWarningSignRepository;

    @Inject
    public CWarningSignViewModel() {
        Log.d("wwww", "CWarningSignViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(CWarningSignEntity cWarningSignEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cWarningSignEntity.getId()))
        //cWarningSignEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cWarningSignEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cWarningSignEntity.getApproveStatus())) {

        } else {
            cWarningSignEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cWarningSignRepository.save(cWarningSignEntity,isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<CWarningSignEntity> entities) {
        return cWarningSignRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cWarningSignRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<CWarningSignEntity> entities) {
        return cWarningSignRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核 3已校验
     * @return
     */
    public LiveData<Resource<Response<List<CWarningSignEntity>>>> list(int page, int rows, String status) {
        return cWarningSignRepository.list(page, rows, status);

    }

    public LiveData<Resource<List<CWarningSignEntity>>> list4Upload() {
        return cWarningSignRepository.list4Upload();

    }

    public LiveData<Resource<List<CWarningSignEntity>>> list4UploadSu() {
        return cWarningSignRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen,CWarningSignEntity entity, String owner) {
        return cWarningSignRepository.completeTaskJudge(isShen,entity, owner);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<CWarningSignEntity> entities, int type) {
        return cWarningSignRepository.revoked(entities, type);
    }

    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cWarningSignRepository.getPic(path);
    }
    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CWarningSignEntity entity) {
        return cWarningSignRepository.findUpdateId(entity);
    }

}
