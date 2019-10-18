package com.spe.dcs.project.cweldingprocedure;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:焊接工艺规程
 * Author.
 * Data:${DATA}
 */
@Dao
public interface CWeldingProcedureDao {
    @Insert(onConflict = REPLACE)
    long save(CWeldingProcedureEntity cWeldingProcedureEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CWeldingProcedureEntity> cWeldingProcedureEntities);

    @Query("SELECT * FROM C_WELDING_PROCEDURE ")
    List<CWeldingProcedureEntity> loadList();
}
