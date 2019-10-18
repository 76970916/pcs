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
import com.spe.dcs.project.ccoatingrepair.CCoatingRepairDao;
import com.spe.dcs.project.ccoatingrepair.CCoatingRepairEntity;
import com.spe.dcs.project.chddcro.CHddCroDao;
import com.spe.dcs.project.chddcro.CHddCroEntity;
import com.spe.dcs.project.chydraulicprotection.CHydraulicProtectionDao;
import com.spe.dcs.project.chydraulicprotection.CHydraulicProtectionEntity;
import com.spe.dcs.project.cjointcoating.CJointCoatingDao;
import com.spe.dcs.project.cjointcoating.CJointCoatingEntity;
import com.spe.dcs.project.cmarkstake.CMarkStakeDao;
import com.spe.dcs.project.cmarkstake.CMarkStakeEntity;
import com.spe.dcs.project.copencutcro.COpenCutCroDao;
import com.spe.dcs.project.copencutcro.COpenCutCroEntity;
import com.spe.dcs.project.creworkweldjoint.CReworkWeldJointDao;
import com.spe.dcs.project.creworkweldjoint.CReworkWeldJointEntity;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroDao;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroEntity;
import com.spe.dcs.project.cwarningsign.CWarningSignDao;
import com.spe.dcs.project.cwarningsign.CWarningSignEntity;
import com.spe.dcs.project.cweldjoint.CWeldJointDao;
import com.spe.dcs.project.cweldjoint.CWeldJointEntity;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoDao;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoEntity;
import com.spe.dcs.project.mentityfuction.MEntityFuctionDao;
import com.spe.dcs.project.mentityfuction.MEntityFuctionEntity;
import com.spe.dcs.project.mprojectinfo.MProjectInfoDao;
import com.spe.dcs.project.mprojectinfo.MProjectInfoEntity;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoDao;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoEntity;
import com.spe.dcs.project.cstation.CStationDao;
import com.spe.dcs.project.cstation.CStationEntity;

import com.spe.dcs.project.cvalveroom.CValveRoomDao;
import com.spe.dcs.project.cvalveroom.CValveRoomEntity;
import com.spe.dcs.project.cwelder.CWelderDao;
import com.spe.dcs.project.cwelder.CWelderEntity;
import com.spe.dcs.project.cweldingprocedure.CWeldingProcedureDao;
import com.spe.dcs.project.cweldingprocedure.CWeldingProcedureEntity;
import com.spe.dcs.project.cweldingunit.CWeldingUnitDao;
import com.spe.dcs.project.cweldingunit.CWeldingUnitEntity;
import com.spe.dcs.project.mentityunit.MEntityUnitDao;
import com.spe.dcs.project.mentityunit.MEntityUnitEntity;
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
import com.spe.dcs.system.syslist.SysListDao;
import com.spe.dcs.system.syslist.SysListEntity;
import com.spe.dcs.system.sysorg.SysOrgDao;
import com.spe.dcs.system.sysorg.SysOrgEntity;
import com.spe.dcs.system.systable.SysTableDao;
import com.spe.dcs.system.systable.SysTableEntity;
import com.spe.dcs.system.sysuser.SysUserDao;
import com.spe.dcs.system.sysuser.SysUserEntity;
import com.spe.dcs.utility.FileUtils;


@Database(entities = {
        SysUserEntity.class,
//        CShortSegmentPrefabricatEntity.class,
        SysFieldEntity.class,
        SysTableEntity.class,
        SysDomainEntity.class,
        SysCategoryEntity.class,
        SysOrgEntity.class,
        SysListEntity.class,
        MProjectInfoEntity.class,
        MEntityFuctionEntity.class,
        MOneProjectInfoEntity.class,
        MContractorInfoEntity.class,
        COpenCutCroEntity.class,
//        CSacrificialAnodeEntity.class,
//        CMagneticTestSEntity.class,
//        CPenetrationTestSEntity.class,
//        CRayTestSEntity.class,
//        CUltrasonicTestSEntity.class,
//        CCrossEntity.class,
//        CSitebendPrefabricatEntity.class,
//        CAntiCoatingEntity.class,
//        CAntiCoatingRepairEntity.class,
//        CAntiCoatingTestEntity.class,
//        CElectricDetectionEntity.class,
//        CLineCompletionpointEntity.class,
//        CWeldJointEntity.class,
//        CWeldcutEntity.class,
//        CWeldReworkEntity.class,
//        CBackfillEntity.class,
//        CInsulationEntity.class,
//        CLandrestorAcceptEntity.class,
//        CSurveyingEntity.class,
//        CTrenchExcavationEntity.class,
//        CCasingPipeEntity.class,
//        CGeologicMonitorEntity.class,
        CHydraulicProtectionEntity.class,
//        CMaintenanceRoadEntity.class,
//        CMaintenanceRoadPointEntity.class,
        CMarkStakeEntity.class,
//        CPipelineAppurtenanceEntity.class,
//        CRoadBridgeCulvertEntity.class,
//        CUndergroundObstacleEntity.class,
        CWarningSignEntity.class,
//        CMagneticTestMEntity.class,
//        CPenetrationTestMEntity.class,
//        CRayTestMEntity.class,
//        CUltrasonicTestMEntity.class,
//        CPipelineCaliperEntity.class,
//        CPipelineDryingEntity.class,
//        CPipelinePiggingEntity.class,
//        CPressureTestEntity.class,
//        CTrussAerialCroEntity.class,
//        CCrossOverEntity.class,
//        CExcavationCrossEntity.class,
        CHddCroEntity.class,
//        CPushpipeCrossEntity.class,
//        CFocCrossEntity.class,
//        CFocJointBoxEntity.class,
//        CFocLayingEntity.class,
//        CFocTestEntity.class,
//        CHandHoleEntity.class,
//        CTeMarkStoneEntity.class,
//        CAuanodeBedEntity.class,
//        CCpCableEntity.class,
//        CCpCableConnectionEntity.class,
//        CCpPowerEntity.class,
//        CCpTestPostEntity.class,
//        CInsulatingPartEntity.class,
//        CIslulationJointProtectorEntity.class,
//        CPloarDrainagerEntity.class,
//        CSolidStateDcdecoupEntity.class,
        CCoatingRepairEntity.class,
        CJointCoatingEntity.class,
        CReworkWeldJointEntity.class,
        CWeldingUnitEntity.class,
        CWeldJointEntity.class,
        CWeldingProcedureEntity.class,
        CStationEntity.class,
        CValveRoomEntity.class,
        CWelderEntity.class,
        SysAndroidErrorEntity.class,
        SysAndroidUpdateEntity.class,
        MEntityUnitEntity.class,
        CTrussAerialCroEntity.class,

}, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class PcsDatabase extends RoomDatabase {

    private static PcsDatabase sInstance;

    public static PcsDatabase getDatabase(Context context) {
        if (sInstance == null) {
            synchronized (SysDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), PcsDatabase.class,
                            FileUtils.DB + "pcs.db").build();
                }
            }
        }
        return sInstance;
    }
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

    public abstract CWeldingUnitDao cWeldingUnitDao();

    public abstract CWeldingProcedureDao cWeldingProcedureDao();

    public abstract CStationDao cStationDao();

    public abstract CValveRoomDao cValveRoomDao();

    public abstract CWelderDao cWelderDao();

    public abstract SysAndroidErrorDao sysAndroidErrorDao();

    public abstract SysAndroidUpdateDao sysAndroidUpdateDao();


//    //53_施工牺牲阳极
//    public abstract CSacrificialAnodeDao cSacrificialAnodeDao();
//
//    //    18_施工磁粉检测子表
//    public abstract CMagneticTestSDao cMagneticTestSDao();
//
//    //16_施工渗透检测子表
//    public abstract CPenetrationTestSDao cPenetrationTestSDao();
//
//    //12_施工射线检测子表
//    public abstract CRayTestSDao cRayTestSDao();
//
//    //14_施工超声波检测子表
//    public abstract CUltrasonicTestSDao cUltrasonicTestSDao();
//
//    //59.5穿跨越
//    public abstract CCrossDao cCrossDao();

    //59.10施工单位
//    public abstract CConstructionUnitDao cConstructionUnitDao();

    //59.11施工人员
//    public abstract CConstructorDao cConstructorDao();


    //项目
    public abstract MProjectInfoDao cProjectDao();

    //子项目
    public abstract MOneProjectInfoDao cPipelineDao();

    //单元
    public abstract MEntityUnitDao entityUnitDao();

    //功能区
    public abstract MEntityFuctionDao cPipeSegmentDao();

    //标段
    public abstract MContractorInfoDao cSectionDao();


    //    //02_施工冷弯管预制
//    public abstract CSitebendPrefabricatDao cSitebendPrefabricatDao();
//
//    //01_施工短节预制
//    public abstract CShortSegmentPrefabricatDao cShortSegmentPrefabricatDao();
//
    //06_防腐补口
    public abstract CJointCoatingDao cJointCoatingDao();

    //
    //09_施工防腐补伤
    public abstract CCoatingRepairDao cAntiCoatingRepairDao();

    //
//    //07施工防腐补口剥离强度试验
//    public abstract CAntiCoatingTestDao cAntiCoatingTestDao();
//
//    //08_施工电火花检测施工
//    public abstract CElectricDetectionDao cElectricDetectionDao();
//
//    //10_施工中线成果
//    public abstract CLineCompletionpointDao cLineCompletionpointDao();
//
    //03_施工焊口
    public abstract CWeldJointDao cWeldDao();

    //
//    //05_施工割口
//    public abstract CWeldcutDao cWeldcutDao();
//
//04_施工返修口
    public abstract CReworkWeldJointDao cReworkWeldJointDao();

    //
//    //21_施工管沟回填
//    public abstract CBackfillDao cBackfillDao();
//
//    //    23_施工保温
//    public abstract CInsulationDao cInsulationDao();
//
//    //22_施工地貌恢复验收
//    public abstract CLandrestorAcceptDao cLandrestorAcceptDao();
//
//    //19_施工测量放线
//    public abstract CSurveyingDao cSurveyingDao();
//
//    //20_施工管沟开挖
//    public abstract CTrenchExcavationDao cTrenchExcavationDao();
//
//    //32_施工套管
//    public abstract CCasingPipeDao cCasingPipeDao();
//
//    //33_施工地质灾害监控系统
//    public abstract CGeologicMonitorDao cGeologicMonitorDao();
//
//    //25_施工水工保护
    public abstract CHydraulicProtectionDao cHydraulicProtectionDao();

    //
//    //26_施工数据采集伴行道路
//    public abstract CMaintenanceRoadDao cMaintenanceRoadDao();
//
//    //27_施工伴行道路转角点
//    public abstract CMaintenanceRoadPointDao cMaintenanceRoadPointDao();
//
//    //29_施工标志桩
    public abstract CMarkStakeDao cMarkStakeDao();

    //    //31_施工线路附属物
//    public abstract CPipelineAppurtenanceDao cPipelineAppurtenanceDao();
//
//    //28_施工伴行道路桥涵
//    public abstract CRoadBridgeCulvertDao cRoadBridgeCulvertDao();
//
//    //24_施工地下障碍物
//    public abstract CUndergroundObstacleDao cUndergroundObstacleDao();
//
//    //30_施工警示牌
    public abstract CWarningSignDao cWarningSignDao();

    //    //17_施工磁粉检测主表
//    public abstract CMagneticTestMDao cMagneticTestMDao();
//
//    //15_施工渗透检测主表
//    public abstract CPenetrationTestMDao cPenetrationTestMDao();
//
//    //11_施工射线检测主表
//    public abstract CRayTestMDao cRayTestMDao();
//
//    //13_施工超声波检测主表
//    public abstract CUltrasonicTestMDao cUltrasonicTestMDao();
//
//    //37_施工管道测径
//    public abstract CPipelineCaliperDao cPipelineCaliperDao();
//
//    //35_施工管道干燥
//    public abstract CPipelineDryingDao cPipelineDryingDao();
//
//    //36_施工管道清管
//    public abstract CPipelinePiggingDao cPipelinePiggingDao();
//
//    //34_施工管道试压
//    public abstract CPressureTestDao cPressureTestDao();
//
//    //40_施工箱涵穿越
//    public abstract CTrussAerialCroDao cBoxculvertCrossDao();
//
//    //    42_施工跨越
//    public abstract CCrossOverDao cCrossOverDao();
//
//
//    //38_施工开挖穿越
    public abstract COpenCutCroDao cExcavationCrossDao();

    //41_施工定向钻穿越
    public abstract CHddCroDao cHddCrossDao();

    //    桁架跨越
    public abstract CTrussAerialCroDao cTrussAerialCroDao();

//    //39_施工顶管穿越
//    public abstract CPushpipeCrossDao cPushpipeCrossDao();
//
//    //45_施工光缆单独穿跨越
//    public abstract CFocCrossDao cFocCrossDao();
//
//    //47_施工光缆接头盒
//    public abstract CFocJointBoxDao cFocJointBoxDao();
//
//    //44_施工光缆敷设
//    public abstract CFocLayingDao cFocLayingDao();
//
//    //43_施工光缆单盘测试
//    public abstract CFocTestDao cFocTestDao();
//
//    //46_施工手孔
//    public abstract CHandHoleDao cHandHoleDao();
//
//    //48_施工通信标石
//    public abstract CTeMarkStoneDao cTeMarkStoneDao();
//
//    //52_施工辅助阳极地床
//    public abstract CAuanodeBedDao cAuanodeBedDao();
//
//    //50_施工阴极保护电缆
//    public abstract CCpCableDao cCpCableDao();
//
//    //51_施工阴保电缆连接点
//    public abstract CCpCableConnectionDao cCpCableConnectionDao();
//
//    //58_施工阴保电源
//    public abstract CCpPowerDao cCpPowerDao();
//
//    //49_施工阴极保护测试桩
//    public abstract CCpTestPostDao cCpTestPostDao();
//
//    //56_绝缘件
//    public abstract CInsulatingPartDao cInsulatingPartDao();
//
//    //57_施工绝缘接头保护器
//    public abstract CIslulationJointProtectorDao cIslulationJointProtectorDao();
//
//    //54_施工极性排流器
//    public abstract CPloarDrainagerDao cPloarDrainagerDao();
//
//    //55_施工固态去耦合器
//    public abstract CSolidStateDcdecoupDao cSolidStateDcdecoupDao();


}
