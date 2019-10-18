package com.spe.dcs.project.cstation;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:站场
 * Author.
 * Data:${DATA}
 */
@Dao
public interface CStationDao {
    @Insert(onConflict = REPLACE)
    long save(CStationEntity cStationEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CStationEntity> cStationEntities);

    @Query("SELECT * FROM C_STATION")
    List<CStationEntity> loadList();

}
