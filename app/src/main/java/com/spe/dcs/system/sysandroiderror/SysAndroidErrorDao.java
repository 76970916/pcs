package com.spe.dcs.system.sysandroiderror;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:错误日志
 * Author.
 * Data:${DATA}
 */

@Dao
public interface SysAndroidErrorDao {
    @Insert(onConflict = REPLACE)
    void save(List<SysAndroidErrorEntity> sysAndroidErrorEntities);

    @Delete
    void del(List<SysAndroidErrorEntity> sysAndroidErrorEntities);

    @Query("SELECT * FROM SYS_ANDROID_ERROR ")
    List<SysAndroidErrorEntity> loadList();
}
