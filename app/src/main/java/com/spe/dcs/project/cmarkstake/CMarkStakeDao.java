package com.spe.dcs.project.cmarkstake;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:29_施工标志桩
 * Author.
 * Data:
 */
@Dao
public interface CMarkStakeDao {
    @Insert(onConflict = REPLACE)
    long save(CMarkStakeEntity cMarkStakeEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CMarkStakeEntity> cMarkStakeEntities);

    @Delete
    void del(CMarkStakeEntity cMarkStakeEntities);

    @Delete
    void del(List<CMarkStakeEntity> cMarkStakeEntities);

    //@Query("SELECT * FROM C_MARK_STAKE WHERE APPROVE_STATUS =:status ORDER BY PROJECT_NUMBER DESC,PIPELINE_NUMBER DESC,SECTION_NUMBER DESC,SEGMENT_CROSS_NUMBER DESC LIMIT :rows OFFSET :page ")
    @Query("SELECT * FROM C_MARK_STAKE WHERE APPROVE_STATUS =:status ORDER BY COLLECTION_DATE DESC LIMIT :rows OFFSET :page ")
    List<CMarkStakeEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_MARK_STAKE WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_MARK_STAKE WHERE APPROVE_STATUS='owner'")
    List<CMarkStakeEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_MARK_STAKE WHERE APPROVE_STATUS='supervisor'")
    List<CMarkStakeEntity> list4UploadSu();

}
