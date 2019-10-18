package com.spe.dcs.app.di;

import com.spe.dcs.project.ccoatingrepair.CCoatingRepairFragment;
import com.spe.dcs.project.chddcro.CHddCroFragment;
import com.spe.dcs.project.chydraulicprotection.CHydraulicProtectionFragment;
import com.spe.dcs.project.cjointcoating.CJointCoatingFragment;
import com.spe.dcs.project.cmarkstake.CMarkStakeFragment;
import com.spe.dcs.project.creworkweldjoint.CReworkWeldJointFragment;
import com.spe.dcs.project.copencutcro.COpenCutCroFragment;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroFragment;
import com.spe.dcs.project.cwarningsign.CWarningSignFragment;
import com.spe.dcs.project.cweldjoint.CWeldJointFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {


    //    //53_施工牺牲阳极
//    @ContributesAndroidInjector
//    abstract CSacrificialAnodeFragment bindCSacrificialAnodeFragment();
//
//    //18_施工磁粉检测子表
//    @ContributesAndroidInjector
//    abstract CMagneticTestSFragment bindCMagneticTestSFragment();
//
//    //16_施工渗透检测子表
//    @ContributesAndroidInjector
//    abstract CPenetrationTestSFragment bindCPenetrationTestSFragment();
//
//    //12_施工射线检测子表
//    @ContributesAndroidInjector
//    abstract CRayTestSFragment bindCRayTestSFragment();
//
//    //14_施工超声波检测子表
//    @ContributesAndroidInjector
//    abstract CUltrasonicTestSFragment bindCUltrasonicTestSFragment();
//
//    //02_施工冷弯管预制
//    @ContributesAndroidInjector
//    abstract CSitebendPrefabricatFragment bindCSitebendPrefabricatFragment();
//
//    //01_施工短节预制
//    @ContributesAndroidInjector
//    abstract CShortSegmentPrefabricatFragment bindCShortSegmentPrefabricatFragment();
//
    //06_防腐补口
    @ContributesAndroidInjector
    abstract CJointCoatingFragment bindCJointCoatingFragment();

    //09_施工防腐补伤
    @ContributesAndroidInjector
    abstract CCoatingRepairFragment bindCAntiCoatingRepairFragment();
//
//    //07施工防腐补口剥离强度试验
//    @ContributesAndroidInjector
//    abstract CAntiCoatingTestFragment bindCAntiCoatingTestFragment();
//
//    //08_施工电火花检测施工
//    @ContributesAndroidInjector
//    abstract CElectricDetectionFragment bindCElectricDetectionFragment();
//
//    //10_施工中线成果
//    @ContributesAndroidInjector
//    abstract CLineCompletionpointFragment bindCLineCompletionpointFragment();
//
    //03_施工焊口
    @ContributesAndroidInjector
    abstract CWeldJointFragment bindCWeldFragment();

    //
//    //05_施工割口
//    @ContributesAndroidInjector
//    abstract CWeldcutFragment bindCWeldcutFragment();
//
//04_施工返修口
    @ContributesAndroidInjector
    abstract CReworkWeldJointFragment bindCWeldReworkFragment();

    //
//    //21_施工管沟回填
//    @ContributesAndroidInjector
//    abstract CBackfillFragment bindCBackfillFragment();
//
//    //23_施工保温
//    @ContributesAndroidInjector
//    abstract CInsulationFragment bindCInsulationFragment();
//
//    //22_施工地貌恢复验收
//    @ContributesAndroidInjector
//    abstract CLandrestorAcceptFragment bindCLandrestorAcceptFragment();
//
//    //19_施工测量放线
//    @ContributesAndroidInjector
//    abstract CSurveyingFragment bindCSurveyingFragment();
//
//    20_施工管沟开挖
//    @ContributesAndroidInjector
//    abstract CTrenchExcavationFragment bindCTrenchExcavationFragment();

    //
//    //32_施工套管
//    @ContributesAndroidInjector
//    abstract CCasingPipeFragment bindCCasingPipeFragment();
//
//
//    //33_施工地质灾害监控系统
//    @ContributesAndroidInjector
//    abstract CGeologicMonitorFragment bindCGeologicMonitorFragment();
//
//    //25_施工水工保护
    @ContributesAndroidInjector
    abstract CHydraulicProtectionFragment bindCHydraulicProtectionFragment();
//
//    //26_施工数据采集伴行道路
//    @ContributesAndroidInjector
//    abstract CMaintenanceRoadFragment bindCMaintenanceRoadFragment();
//
//    //27_施工伴行道路转角点
//    @ContributesAndroidInjector
//    abstract CMaintenanceRoadPointFragment bindCMaintenanceRoadPointFragment();
//
//    //29_施工标志桩
    @ContributesAndroidInjector
    abstract CMarkStakeFragment bindCMarkStakeFragment();

    //    //31_施工线路附属物
//    @ContributesAndroidInjector
//    abstract CPipelineAppurtenanceFragment bindCPipelineAppurtenanceFragment();
//
//    //28_施工伴行道路桥涵
//    @ContributesAndroidInjector
//    abstract CRoadBridgeCulvertFragment bindCRoadBridgeCulvertFragment();
//
//    //24_施工地下障碍物
//    @ContributesAndroidInjector
//    abstract CUndergroundObstacleFragment bindCUndergroundObstacleFragment();
//
//    //30_施工警示牌
    @ContributesAndroidInjector
    abstract CWarningSignFragment bindCWarningSignFragment();

    //
//    //17_施工磁粉检测主表
//    @ContributesAndroidInjector
//    abstract CMagneticTestMFragment bindCMagneticTestMFragment();
//
//    //15_施工渗透检测主表
//    @ContributesAndroidInjector
//    abstract CPenetrationTestMFragment bindCPenetrationTestMFragment();
//
//    //11_施工射线检测主表
//    @ContributesAndroidInjector
//    abstract CRayTestMFragment bindCRayTestMFragment();
//
//    //13_施工超声波检测主表
//    @ContributesAndroidInjector
//    abstract CUltrasonicTestMFragment bindCUltrasonicTestMFragment();
//
//    //37_施工管道测径
//    @ContributesAndroidInjector
//    abstract CPipelineCaliperFragment bindCPipelineCaliperFragment();
//
//    //35_施工管道干燥
//    @ContributesAndroidInjector
//    abstract CPipelineDryingFragment bindCPipelineDryingFragment();
//
//    //36_施工管道清管
//    @ContributesAndroidInjector
//    abstract CPipelinePiggingFragment bindCPipelinePiggingFragment();
//
//    //34_施工管道试压
//    @ContributesAndroidInjector
//    abstract CPressureTestFragment bindCPressureTestFragment();
//
//    //40_施工箱涵穿越
//    @ContributesAndroidInjector
//    abstract CTrussAerialCroFragment bindCBoxculvertCrossFragment();
//
//    //42_施工跨越
//    @ContributesAndroidInjector
//    abstract CCrossOverFragment bindCCrossOverFragment();
//
//    //38_施工开挖穿越
    @ContributesAndroidInjector
    abstract COpenCutCroFragment bindCOpenCutCroFragment();

//41_施工定向钻穿越
    @ContributesAndroidInjector
    abstract CHddCroFragment bindCHddCrossFragment();

    //桁架跨越
    @ContributesAndroidInjector
    abstract CTrussAerialCroFragment bindCTrussAerialCroFragment();
//
//    //39_施工顶管穿越
//    @ContributesAndroidInjector
//    abstract CPushpipeCrossFragment bindCPushpipeCrossFragment();
//
//    //45_施工光缆单独穿跨越
//    @ContributesAndroidInjector
//    abstract CFocCrossFragment bindCFocCrossFragment();
//
//    //47_施工光缆接头盒
//    @ContributesAndroidInjector
//    abstract CFocJointBoxFragment bindCFocJointBoxFragment();
//
//    //44_施工光缆敷设
//    @ContributesAndroidInjector
//    abstract CFocLayingFragment bindCFocLayingFragment();
//
//    //43_施工光缆单盘测试
//    @ContributesAndroidInjector
//    abstract CFocTestFragment bindCFocTestFragment();
//
//    //46_施工手孔
//    @ContributesAndroidInjector
//    abstract CHandHoleFragment bindCHandHoleFragment();
//
//    //48_施工通信标石
//    @ContributesAndroidInjector
//    abstract CTeMarkStoneFragment bindCTeMarkStoneFragment();
//
//    //52_施工辅助阳极地床
//    @ContributesAndroidInjector
//    abstract CAuanodeBedFragment bindCAuanodeBedFragment();
//
//    //50_施工阴极保护电缆
//    @ContributesAndroidInjector
//    abstract CCpCableFragment bindCCpCableFragment();
//
//    //51_施工阴保电缆连接点
//    @ContributesAndroidInjector
//    abstract CCpCableConnectionFragment bindCCpCableConnectionFragment();
//
//    //58_施工阴保电源
//    @ContributesAndroidInjector
//    abstract CCpPowerFragment bindCCpPowerFragment();
//
//    //49_施工阴极保护测试桩
//    @ContributesAndroidInjector
//    abstract CCpTestPostFragment bindCCpTestPostFragment();
//
//    //56_绝缘件
//    @ContributesAndroidInjector
//    abstract CInsulatingPartFragment bindCInsulatingPartFragment();
//
//    //57_施工绝缘接头保护器
//    @ContributesAndroidInjector
//    abstract CIslulationJointProtectorFragment bindCIslulationJointProtectorFragment();
//
//    //54_施工极性排流器
//    @ContributesAndroidInjector
//    abstract CPloarDrainagerFragment bindCPloarDrainagerFragment();
//
//    //55_施工固态去耦合器
//    @ContributesAndroidInjector
//    abstract CSolidStateDcdecoupFragment bindCSolidStateDcdecoupFragment();


}

