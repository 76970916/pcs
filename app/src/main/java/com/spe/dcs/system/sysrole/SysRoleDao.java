package com.spe.dcs.system.sysrole;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:角色
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysRoleDao {

    @Insert(onConflict = REPLACE)
    long save(SysRoleEntity sysRoleEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysRoleEntity> sysRoleEntities);

    @Delete
    void del(List<SysRoleEntity> sysRoleEntities);

    @Query("SELECT * FROM SYS_ROLE ")
    List<SysRoleEntity> loadList();

    @Query("SELECT * FROM SYS_ROLE WHERE ID= :roleId")
    List<SysRoleEntity> loadRole(String roleId);

}
