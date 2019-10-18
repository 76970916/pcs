package com.spe.dcs.project.ctrussaerialcro;

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
 * Desc:桁架跨越
 * Author.
 * Data:
 */
public class CTrussAerialCroViewModel extends ViewModel {
    private static final String TAG = "CBoxculvertCross";
    @Inject
    public CTrussAerialCroRepository cTrussAerialCroRepository;

    @Inject
    public CTrussAerialCroViewModel() {
        Log.d("wwww", "CBoxculvertCrossViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(CTrussAerialCroEntity cTrussAerialCroEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cTrussAerialCroEntity.getId()))
        //cTrussAerialCroEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cTrussAerialCroEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cTrussAerialCroEntity.getApproveStatus())) {

        } else {
            cTrussAerialCroEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cTrussAerialCroRepository.save(cTrussAerialCroEntity, isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<CTrussAerialCroEntity> entities) {
        return cTrussAerialCroRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cTrussAerialCroRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<CTrussAerialCroEntity> entities) {
        return cTrussAerialCroRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核
     * @return
     */
    public LiveData<Resource<Response<List<CTrussAerialCroEntity>>>> list(int page, int rows, String status) {
        return cTrussAerialCroRepository.list(page, rows, status);

    }

    public LiveData<Resource<List<CTrussAerialCroEntity>>> list4Upload() {
        return cTrussAerialCroRepository.list4Upload();

    }

    public LiveData<Resource<List<CTrussAerialCroEntity>>> list4UploadSu() {
        return cTrussAerialCroRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen, CTrussAerialCroEntity entity, String owner) {
        return cTrussAerialCroRepository.completeTaskJudge(isShen, entity, owner);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<CTrussAerialCroEntity> entities, int type) {
        return cTrussAerialCroRepository.revoked(entities, type);
    }

    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cTrussAerialCroRepository.getPic(path);
    }

    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CTrussAerialCroEntity cTrussAerialCroEntity) {
        return cTrussAerialCroRepository.findUpdateId(cTrussAerialCroEntity);
    }



}
