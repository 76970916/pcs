package com.spe.dcs.project.chddcro;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:41_施工定向钻穿越
 * Author.
 * Data:
 */
@Dao
public interface CHddCroDao {
    @Insert(onConflict = REPLACE)
    long save(CHddCroEntity cHddCroEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CHddCroEntity> cHddCrossEntities);

    @Delete
    void del(CHddCroEntity cHddCrossEntities);

    @Delete
    void del(List<CHddCroEntity> cHddCrossEntities);

    @Query("SELECT * FROM C_HDD_CRO WHERE APPROVE_STATUS =:status ORDER BY COLLECTION_DATE DESC LIMIT :rows OFFSET :page ")
    List<CHddCroEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_HDD_CRO WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_HDD_CRO WHERE APPROVE_STATUS='owner'")
    List<CHddCroEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_HDD_CRO WHERE APPROVE_STATUS='supervisor'")
    List<CHddCroEntity> list4UploadSu();

}
