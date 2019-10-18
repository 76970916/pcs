package com.spe.dcs.project.cweldjoint;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:03_施工焊口
 * Author.
 * Data:
 */
@Dao
public interface CWeldJointDao {
    @Insert(onConflict = REPLACE)
    long save(CWeldJointEntity cWeldEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CWeldJointEntity> cWeldEntities);

    @Delete
    void del(CWeldJointEntity cWeldEntities);

    @Delete
    void del(List<CWeldJointEntity> cWeldEntities);

    @Query("SELECT * FROM C_WELD_JOINT WHERE APPROVE_STATUS =:status ORDER BY PROJECT_NUMBER DESC,SUBPROJECT_NUMBER DESC,SECTION  DESC,WELD_JOINT_NUM DESC LIMIT :rows OFFSET :page")
    List<CWeldJointEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_WELD_JOINT WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_WELD_JOINT WHERE APPROVE_STATUS='owner'")
    List<CWeldJointEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_WELD_JOINT WHERE APPROVE_STATUS='supervisor'")
    List<CWeldJointEntity> list4UploadSu();

    @Query("SELECT * FROM C_WELD_JOINT WHERE SECTION LIKE :sectionNumber AND WELD_JOINT_NUM LIKE :weldNum AND STAKE_NUMBER LIKE :stakeNumber AND WELDING_UNIT_ID LIKE :weldingUnit  AND APPROVE_STATUS LIKE :status LIMIT :rows OFFSET :page")
    List<CWeldJointEntity> query2List(int page, int rows, String sectionNumber, String weldNum, String stakeNumber, String weldingUnit, String status);

    @Query("SELECT count(*)FROM C_WELD_JOINT WHERE SECTION LIKE :sectionNumber AND WELD_JOINT_NUM LIKE :weldNum AND STAKE_NUMBER LIKE :stakeNumber AND WELDING_UNIT_ID LIKE :weldingUnit  AND APPROVE_STATUS LIKE :status ")
    int query2ListSum(String sectionNumber, String weldNum, String stakeNumber, String weldingUnit, String status);


}
