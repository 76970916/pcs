package com.spe.dcs.project.mentityunit;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:单元
 * Author.
 * Data:${DATA}
 */
@Dao
public interface MEntityUnitDao {
    @Insert(onConflict = REPLACE)
    long save(MEntityUnitEntity mEntityUnitEntity);

    @Insert(onConflict = REPLACE)
    void save(List<MEntityUnitEntity> mEntityUnitEntities);

    @Query("SELECT * FROM M_ENTITY_UNIT ")
    List<MEntityUnitEntity> loadList();

    @Query("SELECT * FROM M_ENTITY_UNIT WHERE ONE_PROJECT_ID =:pipelineNumber")
    List<MEntityUnitEntity> loadLists(String pipelineNumber);
}
