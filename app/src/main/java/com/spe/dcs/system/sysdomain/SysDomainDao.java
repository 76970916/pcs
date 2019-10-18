package com.spe.dcs.system.sysdomain;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:域表
 * Author.
 * Data:
 */
@Dao
public interface SysDomainDao {

    @Insert(onConflict = REPLACE)
    long save(SysDomainEntity SysDomainEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysDomainEntity> SysDomainEntities);

    @Delete
    void del(List<SysDomainEntity> SysDomainEntities);

    @Query("SELECT * FROM SYS_DOMAIN WHERE TYPE=:type ORDER BY SORTINDEX")
    List<SysDomainEntity> loadList(String type);

}
