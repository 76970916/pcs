package com.spe.dcs.system.sysformfieldtype;

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
public interface SysFormFieldTypeDao {
    @Insert(onConflict = REPLACE)
    long save(SysFormFieldTypeEntity sysFormFieldTypeEntity);

    @Insert(onConflict = REPLACE)
    void save(List<SysFormFieldTypeEntity> sysFormFieldTypeEntities);

    @Delete
    void del(List<SysFormFieldTypeEntity> sysFormFieldTypeEntities);

    @Query("SELECT * FROM SYS_FORM_FIELD_TYPE ")
    List<SysFormFieldTypeEntity> loadList();
}
