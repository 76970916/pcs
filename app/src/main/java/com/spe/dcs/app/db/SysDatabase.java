package com.spe.dcs.app.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.spe.dcs.login.LoginDao;
import com.spe.dcs.system.sysandroiderror.SysAndroidErrorDao;
import com.spe.dcs.system.sysandroiderror.SysAndroidErrorEntity;
import com.spe.dcs.system.sysandroidupdate.SysAndroidUpdateDao;
import com.spe.dcs.system.sysandroidupdate.SysAndroidUpdateEntity;
import com.spe.dcs.system.syscategory.SysCategoryDao;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.spe.dcs.system.sysdomain.SysDomainDao;
import com.spe.dcs.system.sysdomain.SysDomainEntity;
import com.spe.dcs.system.sysfield.SysFieldDao;
import com.spe.dcs.system.sysfield.SysFieldEntity;
import com.spe.dcs.system.sysformfield.SysFormFieldDao;
import com.spe.dcs.system.sysformfield.SysFormFieldEntity;
import com.spe.dcs.system.sysformfieldtype.SysFormFieldTypeDao;
import com.spe.dcs.system.sysformfieldtype.SysFormFieldTypeEntity;
import com.spe.dcs.system.syslist.SysListDao;
import com.spe.dcs.system.syslist.SysListEntity;
import com.spe.dcs.system.syslistbutton.SysListButtonDao;
import com.spe.dcs.system.syslistbutton.SysListButtonEntity;
import com.spe.dcs.system.syslistfield.SysListFieldDao;
import com.spe.dcs.system.syslistfield.SysListFieldEntity;
import com.spe.dcs.system.sysorg.SysOrgDao;
import com.spe.dcs.system.sysorg.SysOrgEntity;
import com.spe.dcs.system.sysorgres.SysOrgResDao;
import com.spe.dcs.system.sysorgres.SysOrgResEntity;
import com.spe.dcs.system.sysres.SysResDao;
import com.spe.dcs.system.sysres.SysResEntity;
import com.spe.dcs.system.sysrole.SysRoleDao;
import com.spe.dcs.system.sysrole.SysRoleEntity;
import com.spe.dcs.system.sysroleres.SysRoleResDao;
import com.spe.dcs.system.sysroleres.SysRoleResEntity;
import com.spe.dcs.system.sysroleuser.SysRoleUserDao;
import com.spe.dcs.system.sysroleuser.SysRoleUserEntity;
import com.spe.dcs.system.systable.SysTableDao;
import com.spe.dcs.system.systable.SysTableEntity;
import com.spe.dcs.system.sysuser.SysUserDao;
import com.spe.dcs.system.sysuser.SysUserEntity;
import com.spe.dcs.system.sysuserres.SysUserResDao;
import com.spe.dcs.system.sysuserres.SysUserResEntity;
import com.spe.dcs.utility.FileUtils;


@Database(entities = {
        SysUserEntity.class,
        SysFieldEntity.class,
        SysTableEntity.class,
        SysDomainEntity.class,
        SysCategoryEntity.class,
        SysOrgEntity.class,
        SysListEntity.class,
        SysAndroidErrorEntity.class,
        SysAndroidUpdateEntity.class,
        SysFormFieldEntity.class,
        SysFormFieldTypeEntity.class,
        SysListButtonEntity.class,
        SysListFieldEntity.class,
        SysOrgResEntity.class,
        SysResEntity.class,
        SysRoleEntity.class,
        SysRoleResEntity.class,
        SysRoleUserEntity.class,
        SysUserResEntity.class

}, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class SysDatabase extends RoomDatabase {

    private static SysDatabase sInstance;

    public static SysDatabase getSysDatabase(Context context) {
        if (sInstance == null) {
            synchronized (SysDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), SysDatabase.class,
                            FileUtils.DB + "sys.db").build();
                }
            }
        }
        return sInstance;
    }

    private static RoomDatabase.Callback sOnOpenCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    //第一次创建数据库时调用，但是在创建所有表之后调用的
                    super.onCreate(db);
                    Log.e("liu","===============sys onCreat 被执行了=======================");
                }

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    //当数据库被打开时调用
                    super.onOpen(db);
                    Log.e("liu","===============sys onOpen 被执行了=======================");
                    //initializeData();
                }
    };
    public static void onDestroy() {
        sInstance = null;
    }

    public abstract LoginDao loginDao();
    public abstract SysCategoryDao sysCategoryDao();

    public abstract SysOrgDao sysOrgDao();

    public abstract SysFieldDao sysFieldDao();

    public abstract SysListDao sysListDao();

    public abstract SysTableDao sysTableDao();

    public abstract SysUserDao sysUserDao();

    public abstract SysDomainDao sysDomainDao();

    public abstract SysAndroidErrorDao sysAndroidErrorDao();

    public abstract SysAndroidUpdateDao sysAndroidUpdateDao();

    public abstract SysFormFieldDao sysFormFieldDao();

    public abstract SysFormFieldTypeDao sysFormFieldTypeDao();

    public abstract SysListButtonDao sysListButtonDao();

    public abstract SysListFieldDao sysListFieldDao();

    public abstract SysOrgResDao sysOrgResDao();

    public abstract SysResDao sysResDao();

    public abstract SysRoleDao sysRoleDao();

    public abstract SysRoleResDao sysRoleResDao();

    public abstract SysRoleUserDao sysRoleUserDao();

    public abstract SysUserResDao sysUserResDao();


}
