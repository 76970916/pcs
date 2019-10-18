package com.spe.dcs.project.mcontractorinfo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:59.3施工标段
 * Author.
 * Data:
 */
@Dao
public interface MContractorInfoDao {

    @Insert(onConflict = REPLACE)
    long save(MContractorInfoEntity mContractorInfoEntity);

    @Insert(onConflict = REPLACE)
    void save(List<MContractorInfoEntity> mContractorInfoEntities);

    @Query("SELECT * FROM M_CONTRACTOR_INFO ")
    List<MContractorInfoEntity> loadList();

    @Query("SELECT * FROM M_CONTRACTOR_INFO WHERE CONTRACTOR_TYPE =:pipelineNumber")
    List<MContractorInfoEntity> loadLists(String pipelineNumber);

}
