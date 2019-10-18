package com.spe.dcs.project.ctrussaerialcro;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:桁架跨越
 * Author.
 * Data:
 */
@Dao
public interface CTrussAerialCroDao {
    @Insert(onConflict = REPLACE)
    long save(CTrussAerialCroEntity cTrussAerialCroEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CTrussAerialCroEntity> cBoxculvertCrossEntities);

    @Delete
    void del(CTrussAerialCroEntity cBoxculvertCrossEntities);

    @Delete
    void del(List<CTrussAerialCroEntity> cBoxculvertCrossEntities);

    @Query("SELECT * FROM C_TRUSS_AERIAL_CRO WHERE APPROVE_STATUS =:status ORDER BY COLLECTION_DATE DESC LIMIT :rows OFFSET :page ")
    List<CTrussAerialCroEntity> loadList(int page, int rows, String status);

    @Query("SELECT count(*) FROM C_TRUSS_AERIAL_CRO WHERE APPROVE_STATUS =:status ")
    int loadListSum(String status);

    @Query("SELECT * FROM C_TRUSS_AERIAL_CRO WHERE APPROVE_STATUS='owner'")
    List<CTrussAerialCroEntity> loadLocalList4Upload();

    @Query("SELECT * FROM C_TRUSS_AERIAL_CRO WHERE APPROVE_STATUS='supervisor'")
    List<CTrussAerialCroEntity> list4UploadSu();

}
