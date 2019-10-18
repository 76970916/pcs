package com.spe.dcs.project.chydraulicprotection;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:25_施工水工保护
 * Author.
 * Data:
 */
@Dao
public interface CHydraulicProtectionDao {
    @Insert(onConflict = REPLACE)
    long save(CHydraulicProtectionEntity cHydraulicProtectionEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CHydraulicProtectionEntity> cHydraulicProtectionEntities);

    @Delete
    void del(CHydraulicProtectionEntity cHydraulicProtectionEntities);

    @Delete
    void del(List<CHydraulicProtectionEntity> cHydraulicProtectionEntities);

   // @Query("SELECT * FROM C_HYDRAULIC_PROTECTION WHERE APPROVE_STATUS =:status ORDER BY PROJECT_NUMBER DESC,PIPELINE_NUMBER DESC,SECTION_NUMBER DESC,SEGMENT_CROSS_NUMBER DESC LIMIT :rows OFFSET :page ")
    @Query("SELECT * FROM C_HYDRAULIC_PROTECTION WHERE APPROVE_STATUS =:status ORDER BY COLLECTION_DATE DESC LIMIT :rows OFFSET :page ")
    List<CHydraulicProtectionEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_HYDRAULIC_PROTECTION WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_HYDRAULIC_PROTECTION WHERE APPROVE_STATUS='owner'")
    List<CHydraulicProtectionEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_HYDRAULIC_PROTECTION WHERE APPROVE_STATUS='supervisor'")
    List<CHydraulicProtectionEntity> list4UploadSu();

}
