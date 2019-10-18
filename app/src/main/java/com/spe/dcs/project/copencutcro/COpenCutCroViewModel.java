package com.spe.dcs.project.copencutcro;

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
 * Desc:38_施工开挖穿越
 * Author.
 * Data:
 */
public class COpenCutCroViewModel extends ViewModel {
    private static final String TAG = "CExcavationCross";
    @Inject
    public COpenCutCroRepository cOpenCutCroRepository;

    @Inject
    public COpenCutCroViewModel() {
        Log.d("wwww", "CExcavationCrossViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(COpenCutCroEntity cOpenCutCroEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cOpenCutCroEntity.getId()))
        //cOpenCutCroEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cOpenCutCroEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cOpenCutCroEntity.getApproveStatus())) {

        } else {
            cOpenCutCroEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cOpenCutCroRepository.save(cOpenCutCroEntity, isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<COpenCutCroEntity> entities) {
        return cOpenCutCroRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cOpenCutCroRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<COpenCutCroEntity> entities) {
        return cOpenCutCroRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核
     * @return
     */
    public LiveData<Resource<Response<List<COpenCutCroEntity>>>> list(int page, int rows, String status) {
        return cOpenCutCroRepository.list(page, rows, status);

    }

    public LiveData<Resource<List<COpenCutCroEntity>>> list4Upload() {
        return cOpenCutCroRepository.list4Upload();

    }

    public LiveData<Resource<List<COpenCutCroEntity>>> list4UploadSu() {
        return cOpenCutCroRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen, COpenCutCroEntity entity, String owner) {
        return cOpenCutCroRepository.completeTaskJudge(isShen, entity, owner);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<COpenCutCroEntity> entities, int type) {
        return cOpenCutCroRepository.revoked(entities, type);
    }

    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cOpenCutCroRepository.getPic(path);
    }

    //(修改功能)通过id去找数据
    public LiveData<Resource<RespEntity>> findUpdateId(COpenCutCroEntity cOpenCutCroEntity) {
        return cOpenCutCroRepository.findUpdateId(cOpenCutCroEntity);
    }

}
