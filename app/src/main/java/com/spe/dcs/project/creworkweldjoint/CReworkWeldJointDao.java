package com.spe.dcs.project.creworkweldjoint;

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
public interface CReworkWeldJointDao {
    @Insert(onConflict = REPLACE)
    long save(CReworkWeldJointEntity cReworkWeldJointEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CReworkWeldJointEntity> cReworkWeldJointEntity);

    @Delete
    void del(CReworkWeldJointEntity cReworkWeldJointEntity);

    @Delete
    void del(List<CReworkWeldJointEntity> cReworkWeldJointEntity);

    @Query("SELECT * FROM C_REWORK_WELD_JOINT WHERE APPROVE_STATUS =:status ORDER BY PROJECT_NUMBER DESC,SUBPROJECT_NUMBER DESC,SECTION DESC,FUNCTIONAL_AREA_CODE DESC LIMIT :rows OFFSET :page ")
    List<CReworkWeldJointEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_REWORK_WELD_JOINT WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_REWORK_WELD_JOINT WHERE APPROVE_STATUS='owner'")
    List<CReworkWeldJointEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_REWORK_WELD_JOINT WHERE APPROVE_STATUS='supervisor'")
    List<CReworkWeldJointEntity> list4UploadSu();



}
