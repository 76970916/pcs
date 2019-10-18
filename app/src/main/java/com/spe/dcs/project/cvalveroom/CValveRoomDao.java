package com.spe.dcs.project.cvalveroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:阀室
 * Author.
 * Data:${DATA}
 */
@Dao
public interface CValveRoomDao {
    @Insert(onConflict = REPLACE)
    long save(CValveRoomEntity cValveRoomEntity);

    @Insert(onConflict = REPLACE)
    void save(List<CValveRoomEntity> cValveRoomEntities);

    @Query("SELECT * FROM C_VALVEROOM ")
    List<CValveRoomEntity> loadList();
}
