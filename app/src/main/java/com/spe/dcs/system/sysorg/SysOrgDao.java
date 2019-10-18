package com.spe.dcs.system.sysorg;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysOrgDao {
    @Insert(onConflict = REPLACE)
    void save(List<SysOrgEntity> SysOrgEntities);

    @Delete
    void del(List<SysOrgEntity> SysOrgEntities);

    @Query("SELECT * FROM SYS_ORG ")
    List<SysOrgEntity> loadList();

    @Query("SELECT org.name FROM SYS_ORG org WHERE org.CODE =:groupid")
    List<String> loadLists(String groupid);

    @Query("SELECT * FROM SYS_ORG org WHERE org.CODE =:code")
    SysOrgEntity findDataByCode(String code);
}
