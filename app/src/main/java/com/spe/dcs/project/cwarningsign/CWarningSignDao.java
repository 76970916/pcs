package com.spe.dcs.project.cwarningsign;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:30_施工警示牌
 * Author.
 * Data:
 */
@Dao
public interface CWarningSignDao {
    @Insert(onConflict = REPLACE)
    long save(CWarningSignEntity cWarningSignEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CWarningSignEntity> cWarningSignEntities);

    @Delete
    void del(CWarningSignEntity cWarningSignEntities);

    @Delete
    void del(List<CWarningSignEntity> cWarningSignEntities);

    //@Query("SELECT * FROM C_WARNING_SIGN WHERE APPROVE_STATUS =:status ORDER BY PROJECT_NUMBER DESC,PIPELINE_NUMBER DESC,SECTION_NUMBER DESC,SEGMENT_CROSS_NUMBER DESC LIMIT :rows OFFSET :page ")
    //@Query("SELECT * FROM C_WARNING_SIGN WHERE APPROVE_STATUS =:status ORDER BY PROJECT_NUMBER DESC,SUBPROJECT_NUMBER DESC,PROJECT_UNIT_NUMBER DESC,FUNCTIONAL_AREA_CODE DESC,SECTION_NUMBER DESC LIMIT :rows OFFSET :page ")
    @Query("SELECT * FROM C_WARNING_SIGN WHERE APPROVE_STATUS =:status ORDER BY COLLECTION_DATE DESC LIMIT :rows OFFSET :page ")
    List<CWarningSignEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_WARNING_SIGN WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_WARNING_SIGN WHERE APPROVE_STATUS='owner'")
    List<CWarningSignEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_WARNING_SIGN WHERE APPROVE_STATUS='supervisor'")
    List<CWarningSignEntity> list4UploadSu();

}
