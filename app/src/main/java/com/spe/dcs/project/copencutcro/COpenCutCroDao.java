package com.spe.dcs.project.copencutcro;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:38_施工开挖穿越
 * Author.
 * Data:
 */
@Dao
public interface COpenCutCroDao {
    @Insert(onConflict = REPLACE)
    long save(COpenCutCroEntity cOpenCutCroEntity);

    @Insert(onConflict = REPLACE)
    void save(List<COpenCutCroEntity> cExcavationCrossEntities);

    @Delete
    void del(COpenCutCroEntity cExcavationCrossEntities);

    @Delete
    void del(List<COpenCutCroEntity> cExcavationCrossEntities);

    @Query("SELECT * FROM C_OPEN_CUT_CRO WHERE APPROVE_STATUS =:status ORDER BY COLLECTION_DATE DESC LIMIT :rows OFFSET :page ")
    List<COpenCutCroEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_OPEN_CUT_CRO WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_OPEN_CUT_CRO WHERE APPROVE_STATUS='owner'")
    List<COpenCutCroEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_OPEN_CUT_CRO WHERE APPROVE_STATUS='supervisor'")
    List<COpenCutCroEntity> list4UploadSu();

}
