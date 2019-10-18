package com.spe.dcs.project.chddcro;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.Response;
import com.spe.dcs.app.net.FlagRespEntity;
import com.spe.dcs.app.net.RespEntity;
import com.spe.dcs.common.TypeStatus;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc:41_施工定向钻穿越
 * Author.
 * Data:
 */
public class CHddCroViewModel extends ViewModel {
    private static final String TAG = "CHddCross";
    @Inject
    public CHddCroRepository cHddCroRepository;

    @Inject
    public CHddCroViewModel() {
        Log.d("wwww", "CHddCrossViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(CHddCroEntity cHddCroEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cHddCroEntity.getId()))
        //cHddCroEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cHddCroEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cHddCroEntity.getApproveStatus())) {

        } else {
            cHddCroEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cHddCroRepository.save(cHddCroEntity, isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<CHddCroEntity> entities) {
        return cHddCroRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cHddCroRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<CHddCroEntity> entities) {
        return cHddCroRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核
     * @return
     */
    public LiveData<Resource<Response<List<CHddCroEntity>>>> list(int page, int rows, String status) {
        return cHddCroRepository.list(page, rows, status);

    }

    public LiveData<Resource<List<CHddCroEntity>>> list4Upload() {
        return cHddCroRepository.list4Upload();

    }

    public LiveData<Resource<List<CHddCroEntity>>> list4UploadSu() {
        return cHddCroRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen, CHddCroEntity entity, String owner) {
        return cHddCroRepository.completeTaskJudge(isShen, entity, owner);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<CHddCroEntity> entities, int type) {
        return cHddCroRepository.revoked(entities, type);
    }

    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cHddCroRepository.getPic(path);
    }

    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(CHddCroEntity cHddCroEntity) {
        return cHddCroRepository.findUpdateId(cHddCroEntity);
    }

}
