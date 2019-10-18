package com.spe.dcs.project.ccoatingrepair;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.spe.dcs.project.cweldjoint.CWeldJointEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * 文件名：CCoatingRepairDao.java
 * 作  者：
 * 时  间：
 * 描  述： 防腐补伤
 */
@Dao
public interface CCoatingRepairDao {
    @Insert(onConflict = REPLACE)
    long save(CCoatingRepairEntity cCoatingRepairEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CCoatingRepairEntity> cCoatingRepairEntity);

    @Delete
    void del(CCoatingRepairEntity cCoatingRepairEntity);

    @Delete
    void del(List<CCoatingRepairEntity> cWeldEntcCoatingRepairEntityities);

    @Query("SELECT * FROM C_COATING_REPAIR WHERE APPROVE_STATUS =:status ORDER BY PROJECT_NUMBER DESC,SUBPROJECT_NUMBER DESC,SECTION DESC,FUNCTIONAL_AREA_CODE DESC LIMIT :rows OFFSET :page ")
    List<CCoatingRepairEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_COATING_REPAIR WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_COATING_REPAIR WHERE APPROVE_STATUS='owner'")
    List<CCoatingRepairEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_COATING_REPAIR WHERE APPROVE_STATUS='supervisor'")
    List<CCoatingRepairEntity> list4UploadSu();



}
