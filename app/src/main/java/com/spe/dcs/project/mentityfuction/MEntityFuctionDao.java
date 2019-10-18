package com.spe.dcs.project.mentityfuction;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:功能区
 * Author.
 * Data:
 */

@Dao
public interface MEntityFuctionDao {

    @Insert(onConflict = REPLACE)
    long save(MEntityFuctionEntity mEntityFuctionEntity);

    @Insert(onConflict = REPLACE)
    void save(List<MEntityFuctionEntity> mEntityFuctionEntities);

    @Query("SELECT * FROM M_ENTITY_FUCTION ")
    List<MEntityFuctionEntity> loadList();

    @Query("SELECT * FROM M_ENTITY_FUCTION WHERE PRO_UNIT_ID =:sectionNumber")
    List<MEntityFuctionEntity> loadLists(String sectionNumber);

//    @Query("SELECT * FROM M_ENTITY_FUCTION WHERE SECTION_NUMBER =:sectionNumber AND TYPE = :xld")
//    List<MEntityFuctionEntity> loadListsType(String sectionNumber, String xld);

}
