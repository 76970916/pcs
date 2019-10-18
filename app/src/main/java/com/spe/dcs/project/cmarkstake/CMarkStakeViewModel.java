package com.spe.dcs.project.cmarkstake;

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
 * Desc:29_施工标志桩
 * Author.
 * Data:
 */
public class CMarkStakeViewModel extends ViewModel {
    private static final String TAG = "CMarkStake";
    @Inject
    public CMarkStakeRepository cMarkStakeRepository;

    @Inject
    public CMarkStakeViewModel() {
        Log.d("wwww", "CMarkStakeViewModel被创建了");
    }


    //保存
    public LiveData<Resource<RespEntity>> save(CMarkStakeEntity cMarkStakeEntity, boolean isNew) {
        //if (TextUtils.isEmpty(cMarkStakeEntity.getId()))
        //cMarkStakeEntity.setId(String.valueOf(UUID.randomUUID()));
        if (TypeStatus.APP_SUPERVISOR.equals(cMarkStakeEntity.getApproveStatus())) {

        } else if (TypeStatus.APP_OWNER.equals(cMarkStakeEntity.getApproveStatus())) {

        } else {
            cMarkStakeEntity.setApproveStatus(TypeStatus.ENTER);
        }

        return cMarkStakeRepository.save(cMarkStakeEntity,isNew);
    }

//    上报

    public LiveData<Resource<FlagRespEntity>> report(List<CMarkStakeEntity> entities) {
        return cMarkStakeRepository.report(entities);
    }//    上报

    public LiveData<Resource<FlagRespEntity>> reports(String id) {
        return cMarkStakeRepository.reports(id);
    }


    //删除
    public LiveData<Resource<RespEntity>> delete(List<CMarkStakeEntity> entities) {
        return cMarkStakeRepository.delete(entities);
    }


    /**
     * 记录查询
     *
     * @param status 本地-0待上报  1已上报 2待审核
     * @return
     */
    public LiveData<Resource<Response<List<CMarkStakeEntity>>>> list(int page, int rows, String status) {
        return cMarkStakeRepository.list(page, rows, status);

    }

    public LiveData<Resource<List<CMarkStakeEntity>>> list4Upload() {
        return cMarkStakeRepository.list4Upload();

    }

    public LiveData<Resource<List<CMarkStakeEntity>>> list4UploadSu() {
        return cMarkStakeRepository.list4UploadSu();

    }

    /**
     * 监理审核
     *
     * @param entity
     * @return
     */
    public LiveData<Resource<FlagRespEntity>> completeTaskJudge(boolean isShen,CMarkStakeEntity entity, String owner) {
        return cMarkStakeRepository.completeTaskJudge(isShen,entity, owner);
    }

    //    撤回

    public LiveData<Resource<FlagRespEntity>> revoked(List<CMarkStakeEntity> entities, int type) {
        return cMarkStakeRepository.revoked(entities, type);
    }

    //获取base64图片
    public LiveData<Resource<String>> getPic(String path) {
        return cMarkStakeRepository.getPic(path);
    }

}
