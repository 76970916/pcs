package com.spe.dcs.system.sysfield;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:字段表
 * Author.
 * Data:
 */
@Dao
public interface SysFieldDao {

    @Insert(onConflict = REPLACE)
    long save(SysFieldEntity SysFieldEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysFieldEntity> SysFieldEntities);

    @Delete
    void del(List<SysFieldEntity> SysFieldEntities);

    @Query("SELECT * FROM SYS_FIELD F  WHERE F.TABLEID= (SELECT ID FROM SYS_TABLE T WHERE T.CODE=:tableCode) AND F.SORTINDEX>0 ORDER BY F.SORTINDEX")
    List<SysFieldEntity> loadList(String tableCode);
}
