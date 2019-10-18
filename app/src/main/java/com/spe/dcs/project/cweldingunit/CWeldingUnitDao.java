package com.spe.dcs.project.cweldingunit;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:施工机组
 * Author.
 * Data:${DATA}
 */
@Dao
public interface CWeldingUnitDao {

    @Insert(onConflict = REPLACE)
    long save(CWeldingUnitEntity cWeldingUnitEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CWeldingUnitEntity> cWeldingUnitEntities);

    @Delete
    void del(List<CWeldingUnitEntity> cWeldingUnitEntities);

    @Query("SELECT * FROM C_WELDING_UNIT ")
    List<CWeldingUnitEntity> loadList();

}
