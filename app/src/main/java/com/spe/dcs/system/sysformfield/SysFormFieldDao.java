package com.spe.dcs.system.sysformfield;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Desc:表单表
 * Author.
 * Data:${DATA}
 */
@Dao
public interface SysFormFieldDao {
    @Insert(onConflict = REPLACE)
    long save(SysFormFieldEntity sysFormFieldEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysFormFieldEntity> sysFormFieldEntities);

    @Delete
    void del(List<SysFormFieldEntity> sysFormFieldEntities);

    @Query("SELECT * FROM SYS_FORM_FIELD ")
    List<SysFormFieldEntity> loadList();

}
