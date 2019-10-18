package com.spe.dcs.project.cjointcoating;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:04_施工返修口
 * Author.
 * Data:
 */
@Dao
public interface CJointCoatingDao {
    @Insert(onConflict = REPLACE)
    long save(CJointCoatingEntity cJointCoatingEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CJointCoatingEntity> cJointCoatingEntity);

    @Delete
    void del(CJointCoatingEntity cJointCoatingEntity);

    @Delete
    void del(List<CJointCoatingEntity> cJointCoatingEntity);

    @Query("SELECT * FROM C_JOINT_COATING WHERE APPROVE_STATUS =:status ORDER BY PROJECT_NUMBER DESC,SUBPROJECT_NUMBER DESC,SECTION DESC,FUNCTIONAL_AREA_CODE DESC LIMIT :rows OFFSET :page ")
    List<CJointCoatingEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_JOINT_COATING WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_JOINT_COATING WHERE APPROVE_STATUS='owner'")
    List<CJointCoatingEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_JOINT_COATING WHERE APPROVE_STATUS='supervisor'")
    List<CJointCoatingEntity> list4UploadSu();

}
