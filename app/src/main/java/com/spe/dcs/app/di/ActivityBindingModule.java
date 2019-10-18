package com.spe.dcs.app.di;

import com.spe.dcs.common.ImageListActivity;
import com.spe.dcs.common.QrcodeActivity;
import com.spe.dcs.login.LoginActivity;
import com.spe.dcs.login.SettingActivity;
import com.spe.dcs.project.ccoatingrepair.CCoatingRepairActivity;
import com.spe.dcs.project.ccoatingrepair.CCoatingRepairListActivity;
import com.spe.dcs.project.ccoatingrepair.CCoatingRepairLookActivity;
import com.spe.dcs.project.ccoatingrepair.CCoatingRepairLookPicActivity;
import com.spe.dcs.project.chddcro.CHddCroActivity;
import com.spe.dcs.project.chddcro.CHddCroListActivity;
import com.spe.dcs.project.chddcro.CHddCroLookActivity;
import com.spe.dcs.project.chddcro.CHddCroLookPicActivity;
import com.spe.dcs.project.chydraulicprotection.CHydraulicProtectionActivity;
import com.spe.dcs.project.chydraulicprotection.CHydraulicProtectionListActivity;
import com.spe.dcs.project.chydraulicprotection.CHydraulicProtectionLookActivity;
import com.spe.dcs.project.chydraulicprotection.CHydraulicProtectionLookPicActivity;
import com.spe.dcs.project.cjointcoating.CJointCoatingActivity;
import com.spe.dcs.project.cjointcoating.CJointCoatingListActivity;
import com.spe.dcs.project.cjointcoating.CJointCoatingLookActivity;
import com.spe.dcs.project.cjointcoating.CJointCoatingLookPicActivity;
import com.spe.dcs.project.cmarkstake.CMarkStakeActivity;
import com.spe.dcs.project.cmarkstake.CMarkStakeListActivity;
import com.spe.dcs.project.cmarkstake.CMarkStakeLookActivity;
import com.spe.dcs.project.cmarkstake.CMarkStakeLookPicActivity;
import com.spe.dcs.project.creworkweldjoint.CReworkWeldJointActivity;
import com.spe.dcs.project.creworkweldjoint.CReworkWeldJointListActivity;
import com.spe.dcs.project.creworkweldjoint.CReworkWeldJointLookActivity;
import com.spe.dcs.project.creworkweldjoint.CReworkWeldJointLookPicActivity;
import com.spe.dcs.project.copencutcro.COpenCutCroActivity;
import com.spe.dcs.project.copencutcro.COpenCutCroListActivity;
import com.spe.dcs.project.copencutcro.COpenCutCroLookActivity;
import com.spe.dcs.project.copencutcro.COpenCutCroLookPicActivity;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroActivity;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroListActivity;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroLookActivity;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroLookPicActivity;
import com.spe.dcs.project.cwarningsign.CWarningSignActivity;
import com.spe.dcs.project.cwarningsign.CWarningSignListActivity;
import com.spe.dcs.project.cwarningsign.CWarningSignLookActivity;
import com.spe.dcs.project.cwarningsign.CWarningSignLookPicActivity;
import com.spe.dcs.project.cweldjoint.CWeldJointActivity;
import com.spe.dcs.project.cweldjoint.CWeldJointBackActivity;
import com.spe.dcs.project.cweldjoint.CWeldJointListActivity;
import com.spe.dcs.project.cweldjoint.CWeldJointLookActivity;
import com.spe.dcs.project.cweldjoint.CWeldJointLookPicActivity;
import com.spe.dcs.tree.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract QrcodeActivity contributeQrcodeActivity();

    @ContributesAndroidInjector
    abstract ImageListActivity contributeImageListActivity();

    @ContributesAndroidInjector
    abstract SettingActivity contributeSettingActivity();

//    @ContributesAndroidInjector
//    abstract CShortSegmentPrefabricatActivity contributeCShortSegmentPrefabricatActivity();
//
//    @ContributesAndroidInjector
//    abstract CShortSegmentPrefabricatListActivity contributeCShortSegmentPrefabricatListActivity();
//
//    @ContributesAndroidInjector
//    abstract CShortSegmentPrefabricatLookActivity contributeCShortSegmentPrefabricatLookActivity();
//
//
//    @ContributesAndroidInjector
//    abstract CShortSegmentPrefabricatLookPicActivity contributeLookPicActivity();
//


    //01_采办线路管材
//    @ContributesAndroidInjector
//    abstract PPipeActivity contributePPipeActivity();
//
//    @ContributesAndroidInjector
//    abstract PPipeListActivity contributePPipeListActivity();

    //    //53_施工牺牲阳极
//    @ContributesAndroidInjector
//    abstract CSacrificialAnodeActivity contributeCSacrificialAnodeActivity();
//
//    @ContributesAndroidInjector
//    abstract CSacrificialAnodeListActivity contributeCSacrificialAnodeListActivity();
//
//    //18_施工磁粉检测子表
//    @ContributesAndroidInjector
//    abstract CMagneticTestSActivity contributeCMagneticTestSActivity();
//
//    @ContributesAndroidInjector
//    abstract CMagneticTestSListActivity contributeCMagneticTestSListActivity();
//
//    //16_施工渗透检测子表
//    @ContributesAndroidInjector
//    abstract CPenetrationTestSActivity contributeCPenetrationTestSActivity();
//
//    @ContributesAndroidInjector
//    abstract CPenetrationTestSListActivity contributeCPenetrationTestSListActivity();
//
//    //12_施工射线检测子表
//    @ContributesAndroidInjector
//    abstract CRayTestSActivity contributeCRayTestSActivity();
//
//    @ContributesAndroidInjector
//    abstract CRayTestSListActivity contributeCRayTestSListActivity();
//
//    //14_施工超声波检测子表
//    @ContributesAndroidInjector
//    abstract CUltrasonicTestSActivity contributeCUltrasonicTestSActivity();
//
//    @ContributesAndroidInjector
//    abstract CUltrasonicTestSListActivity contributeCUltrasonicTestSListActivity();
//
//    //02_施工冷弯管预制
//    @ContributesAndroidInjector
//    abstract CSitebendPrefabricatActivity contributeCSitebendPrefabricatActivity();
//
//    @ContributesAndroidInjector
//    abstract CSitebendPrefabricatListActivity contributeCSitebendPrefabricatListActivity();
//
//    @ContributesAndroidInjector
//    abstract CSitebendPrefabricatLookActivity contributeCSitebendPrefabricatLookActivity();
//
//    @ContributesAndroidInjector
//    abstract CSitebendPrefabricatLookPicActivity contributeCSitebendPrefabricatLookPicActivity();
//
    //06_防腐补口
    @ContributesAndroidInjector
    abstract CJointCoatingActivity contributeCJointCoatingActivity();

    @ContributesAndroidInjector
    abstract CJointCoatingListActivity contributeCJointCoatingListActivity();

    @ContributesAndroidInjector
    abstract CJointCoatingLookActivity contributeCJointCoatingLookActivity();

    @ContributesAndroidInjector
    abstract CJointCoatingLookPicActivity contributeCJointCoatingLookPicActivity();
//
    //09_施工防腐补伤
    @ContributesAndroidInjector
    abstract CCoatingRepairActivity contributeCAntiCoatingRepairActivity();

    @ContributesAndroidInjector
    abstract CCoatingRepairListActivity contributeCAntiCoatingRepairListActivity();

    @ContributesAndroidInjector
    abstract CCoatingRepairLookActivity contributeCAntiCoatingRepairLookActivity();

    @ContributesAndroidInjector
    abstract CCoatingRepairLookPicActivity contributeCAntiCoatingRepairLookPicActivity();
//
//    //07施工防腐补口剥离强度试验
//    @ContributesAndroidInjector
//    abstract CAntiCoatingTestActivity contributeCAntiCoatingTestActivity();
//
//    @ContributesAndroidInjector
//    abstract CAntiCoatingTestListActivity contributeCAntiCoatingTestListActivity();
//
//    //08_施工电火花检测施工
//    @ContributesAndroidInjector
//    abstract CElectricDetectionActivity contributeCElectricDetectionActivity();
//
//    @ContributesAndroidInjector
//    abstract CElectricDetectionListActivity contributeCElectricDetectionListActivity();
//
//    //10_施工中线成果
//    @ContributesAndroidInjector
//    abstract CLineCompletionpointActivity contributeCLineCompletionpointActivity();
//
//    @ContributesAndroidInjector
//    abstract CLineCompletionpointListActivity ontributeCLineCompletionpointListActivity();
//
    //03_施工焊口
    @ContributesAndroidInjector
    abstract CWeldJointActivity contributeCWeldActivity();

    @ContributesAndroidInjector
    abstract CWeldJointBackActivity contributeCWeldBackActivity();

    @ContributesAndroidInjector
    abstract CWeldJointListActivity contributeCWeldListActivity();

    @ContributesAndroidInjector
    abstract CWeldJointLookActivity contributeCWeldLookActivity();

    @ContributesAndroidInjector
    abstract CWeldJointLookPicActivity contributeCWeldLookPicActivity();

    //    //05_施工割口
//    @ContributesAndroidInjector
//    abstract CWeldcutActivity contributeCWeldcutActivity();
//
//    @ContributesAndroidInjector
//    abstract CWeldcutListActivity contributeCWeldcutListActivity();
//
//04_施工返修口
    @ContributesAndroidInjector
    abstract CReworkWeldJointActivity contributeCWeldReworkActivity();

    @ContributesAndroidInjector
    abstract CReworkWeldJointListActivity contributeCWeldReworkListActivity();

    @ContributesAndroidInjector
    abstract CReworkWeldJointLookActivity contributeCWeldReworkLookActivity();

    @ContributesAndroidInjector
    abstract CReworkWeldJointLookPicActivity contributeCWeldReworkLookPicActivity();


    //
//    //21_施工管沟回填
//    @ContributesAndroidInjector
//    abstract CBackfillActivity contributeCBackfillActivity();
//
//    @ContributesAndroidInjector
//    abstract CBackfillListActivity contributeCBackfillListActivity();
//
//    //23_施工保温
//    @ContributesAndroidInjector
//    abstract CInsulationActivity contributeCInsulationActivity();
//
//    @ContributesAndroidInjector
//    abstract CInsulationListActivity contributeCInsulationListActivity();
//
//    //22_施工地貌恢复验收
//    @ContributesAndroidInjector
//    abstract CLandrestorAcceptActivity contributeCLandrestorAcceptActivity();
//
//    @ContributesAndroidInjector
//    abstract CLandrestorAcceptListActivity contributeCLandrestorAcceptListActivity();
//
//    //19_施工测量放线
//    @ContributesAndroidInjector
//    abstract CSurveyingActivity contributeCSurveyingActivity();
//
//    @ContributesAndroidInjector
//    abstract CSurveyingListActivity contributeCSurveyingListActivity();
//
//    //20_施工管沟开挖
//    @ContributesAndroidInjector
//    abstract CTrenchExcavationActivity contributeCTrenchExcavationActivity();
//
//    @ContributesAndroidInjector
//    abstract CTrenchExcavationListActivity contributeCTrenchExcavationListActivity();
//
//    //32_施工套管
//    @ContributesAndroidInjector
//    abstract CCasingPipeActivity contributeCCasingPipeActivity();
//
//    @ContributesAndroidInjector
//    abstract CCasingPipeListActivity contributeCCasingPipeListActivity();
//
//    //33_施工地质灾害监控系统
//    @ContributesAndroidInjector
//    abstract CGeologicMonitorActivity contributeCGeologicMonitorActivity();
//
//    @ContributesAndroidInjector
//    abstract CGeologicMonitorListActivity contributeCGeologicMonitorListActivity();
//
    //25_施工水工保护
    @ContributesAndroidInjector
    abstract CHydraulicProtectionActivity contributeCHydraulicProtectionActivity();

    @ContributesAndroidInjector
    abstract CHydraulicProtectionListActivity contributeCHydraulicProtectionListActivity();

    @ContributesAndroidInjector
    abstract CHydraulicProtectionLookActivity contributeCHydraulicProtectionLookActivity();

    @ContributesAndroidInjector
    abstract CHydraulicProtectionLookPicActivity contributeCHydraulicProtectionLookPicActivity();
//
//    //26_施工数据采集伴行道路
//    @ContributesAndroidInjector
//    abstract CMaintenanceRoadActivity contributeCMaintenanceRoadActivity();
//
//    @ContributesAndroidInjector
//    abstract CMaintenanceRoadListActivity contributeCMaintenanceRoadListActivity();
//
//    //27_施工伴行道路转角点
//    @ContributesAndroidInjector
//    abstract CMaintenanceRoadPointActivity contributeCMaintenanceRoadPointActivity();
//
//    @ContributesAndroidInjector
//    abstract CMaintenanceRoadPointListActivity contributeCMaintenanceRoadPointListActivity();
//
//    //29_施工标志桩
    @ContributesAndroidInjector
    abstract CMarkStakeActivity contributeCMarkStakeActivity();

    @ContributesAndroidInjector
    abstract CMarkStakeListActivity contributeCMarkStakeListActivity();

    @ContributesAndroidInjector
    abstract CMarkStakeLookActivity contributeCMarkStakeLookActivity();

    @ContributesAndroidInjector
    abstract CMarkStakeLookPicActivity contributeCMarkStakeLookPicActivity();

    //
//    //31_施工线路附属物
//    @ContributesAndroidInjector
//    abstract CPipelineAppurtenanceActivity contributeCPipelineAppurtenanceActivity();
//
//    @ContributesAndroidInjector
//    abstract CPipelineAppurtenanceListActivity contributeCPipelineAppurtenanceListActivity();
//
//    //28_施工伴行道路桥涵
//    @ContributesAndroidInjector
//    abstract CRoadBridgeCulvertActivity contributeCRoadBridgeCulvertActivity();
//
//    @ContributesAndroidInjector
//    abstract CRoadBridgeCulvertListActivity contributeCRoadBridgeCulvertListActivity();
//
//    //24_施工地下障碍物
//    @ContributesAndroidInjector
//    abstract CUndergroundObstacleActivity contributeCUndergroundObstacleActivity();
//
//    @ContributesAndroidInjector
//    abstract CUndergroundObstacleListActivity contributeCUndergroundObstacleListActivity();
//
//    //30_施工警示牌
    @ContributesAndroidInjector
    abstract CWarningSignActivity contributeCWarningSignActivity();

    //
    @ContributesAndroidInjector
    abstract CWarningSignListActivity contributeCWarningSignListActivity();

    //
    @ContributesAndroidInjector
    abstract CWarningSignLookActivity contributeCWarningSignLookActivity();

    //
    @ContributesAndroidInjector
    abstract CWarningSignLookPicActivity contributeCWarningSignLookPicActivity();
//
//    //17_施工磁粉检测主表
//    @ContributesAndroidInjector
//    abstract CMagneticTestMActivity contributeCMagneticTestMActivity();
//
//    @ContributesAndroidInjector
//    abstract CMagneticTestMListActivity contributeCMagneticTestMListActivity();
//
//    //15_施工渗透检测主表
//    @ContributesAndroidInjector
//    abstract CPenetrationTestMActivity contributeCPenetrationTestMActivity();
//
//    @ContributesAndroidInjector
//    abstract CPenetrationTestMListActivity contributeCPenetrationTestMListActivity();
//
//    //11_施工射线检测主表
//    @ContributesAndroidInjector
//    abstract CRayTestMActivity contributeCRayTestMActivity();
//
//    @ContributesAndroidInjector
//    abstract CRayTestMListActivity contributeCRayTestMListActivity();
//
//    //13_施工超声波检测主表
//    @ContributesAndroidInjector
//    abstract CUltrasonicTestMActivity contributeCUltrasonicTestMActivity();
//
//    @ContributesAndroidInjector
//    abstract CUltrasonicTestMListActivity contributeCUltrasonicTestMListActivity();
//
//    //37_施工管道测径
//    @ContributesAndroidInjector
//    abstract CPipelineCaliperActivity contributeCPipelineCaliperActivity();
//
//    @ContributesAndroidInjector
//    abstract CPipelineCaliperListActivity contributeCPipelineCaliperListActivity();
//
//    //35_施工管道干燥
//    @ContributesAndroidInjector
//    abstract CPipelineDryingActivity contributeCPipelineDryingActivity();
//
//    @ContributesAndroidInjector
//    abstract CPipelineDryingListActivity contributeCPipelineDryingListActivity();
//
//    //36_施工管道清管
//    @ContributesAndroidInjector
//    abstract CPipelinePiggingActivity contributeCPipelinePiggingActivity();
//
//    @ContributesAndroidInjector
//    abstract CPipelinePiggingListActivity contributeCPipelinePiggingListActivity();
//
//    //34_施工管道试压
//    @ContributesAndroidInjector
//    abstract CPressureTestActivity contributeCPressureTestActivity();
//
//    @ContributesAndroidInjector
//    abstract CPressureTestListActivity contributeCPressureTestListActivity();
//
//    //40_施工箱涵穿越
//    @ContributesAndroidInjector
//    abstract CBoxculvertCrossActivity contributeCBoxculvertCrossActivity();
//
//    @ContributesAndroidInjector
//    abstract CBoxculvertCrossListActivity contributeCBoxculvertCrossListActivity();
//
//    @ContributesAndroidInjector
//    abstract CBoxculvertCrossLookActivity contributeCBoxculvertCrossLookActivity();
//
//    @ContributesAndroidInjector
//    abstract CBoxculvertCrossLookPicActivity contributeCBoxculvertCrossLookPicActivity();
//
//    //42_施工跨越
//    @ContributesAndroidInjector
//    abstract CCrossOverActivity contributeCCrossOverActivity();
//
//    @ContributesAndroidInjector
//    abstract CCrossOverListActivity contributeCCrossOverListActivity();
//
//    @ContributesAndroidInjector
//    abstract CCrossOverLookActivity contributeCCrossOverLookActivity();
//
//    @ContributesAndroidInjector
//    abstract CCrossOverLookPicActivity contributeCCrossOverLookPicActivity();

    //38_施工开挖穿越
    @ContributesAndroidInjector
    abstract COpenCutCroActivity contributeCOpenCutCroActivity();

    //
    @ContributesAndroidInjector
    abstract COpenCutCroListActivity contributeCOpenCutCroListActivity();

    @ContributesAndroidInjector
    abstract COpenCutCroLookActivity contributeCOpenCutCroLookActivity();

    @ContributesAndroidInjector
    abstract COpenCutCroLookPicActivity contributeCOpenCutCroLookPicActivity();

    //41_施工定向钻穿越
    @ContributesAndroidInjector
    abstract CHddCroActivity contributeCHddCrossActivity();

    @ContributesAndroidInjector
    abstract CHddCroListActivity contributeCHddCrossListActivity();

    @ContributesAndroidInjector
    abstract CHddCroLookActivity contributeCHddCrossLookActivity();

    @ContributesAndroidInjector
    abstract CHddCroLookPicActivity contributeCHddCrossLookPicActivity();

    //桁架跨越
    @ContributesAndroidInjector
    abstract CTrussAerialCroActivity contributeCTrussAerialCroActivity();

    @ContributesAndroidInjector
    abstract CTrussAerialCroListActivity contributeCTrussAerialCroListActivity();

    @ContributesAndroidInjector
    abstract CTrussAerialCroLookActivity contributeCTrussAerialCroLookActivity();

    @ContributesAndroidInjector
    abstract CTrussAerialCroLookPicActivity contributeCTrussAerialCroLookPicActivity();

    //39_施工顶管穿越
//    @ContributesAndroidInjector
//    abstract CPushpipeCrossActivity contributeCPushpipeCrossActivity();
//
//    @ContributesAndroidInjector
//    abstract CPushpipeCrossListActivity contributeCPushpipeCrossListActivity();
//
//    @ContributesAndroidInjector
//    abstract CPushpipeCrossLookActivity contributeCPushpipeCrossLookActivity();
//
//    @ContributesAndroidInjector
//    abstract CPushpipeCrossLookPicActivity contributeCPushpipeCrossLookPicActivity();
//
//    //45_施工光缆单独穿跨越
//    @ContributesAndroidInjector
//    abstract CFocCrossActivity contributeCFocCrossActivity();
//
//    @ContributesAndroidInjector
//    abstract CFocCrossListActivity contributeCFocCrossListActivity();
//
//    //47_施工光缆接头盒
//    @ContributesAndroidInjector
//    abstract CFocJointBoxActivity contributeCFocJointBoxActivity();
//
//    @ContributesAndroidInjector
//    abstract CFocJointBoxListActivity contributeCFocJointBoxListActivity();
//
//    //44_施工光缆敷设
//    @ContributesAndroidInjector
//    abstract CFocLayingActivity contributeCFocLayingActivity();
//
//    @ContributesAndroidInjector
//    abstract CFocLayingListActivity contributeCFocLayingListActivity();
//
//    //43_施工光缆单盘测试
//    @ContributesAndroidInjector
//    abstract CFocTestActivity contributeCFocTestActivity();
//
//    @ContributesAndroidInjector
//    abstract CFocTestListActivity contributeCFocTestListActivity();
//
//    //46_施工手孔
//    @ContributesAndroidInjector
//    abstract CHandHoleActivity contributeCHandHoleActivity();
//
//    @ContributesAndroidInjector
//    abstract CHandHoleListActivity contributeCHandHoleListActivity();
//
//    //48_施工通信标石
//    @ContributesAndroidInjector
//    abstract CTeMarkStoneActivity contributeCTeMarkStoneActivity();
//
//    @ContributesAndroidInjector
//    abstract CTeMarkStoneListActivity contributeCTeMarkStoneListActivity();
//
//    //52_施工辅助阳极地床
//    @ContributesAndroidInjector
//    abstract CAuanodeBedActivity contributeCAuanodeBedActivity();
//
//    @ContributesAndroidInjector
//    abstract CAuanodeBedListActivity contributeCAuanodeBedListActivity();
//
//    //50_施工阴极保护电缆
//    @ContributesAndroidInjector
//    abstract CCpCableActivity contributeCCpCableActivity();
//
//    @ContributesAndroidInjector
//    abstract CCpCableListActivity contributeCCpCableListActivity();
//
//    //51_施工阴保电缆连接点
//    @ContributesAndroidInjector
//    abstract CCpCableConnectionActivity contributeCCpCableConnectionActivity();
//
//    @ContributesAndroidInjector
//    abstract CCpCableConnectionListActivity contributeCCpCableConnectionListActivity();
//
//    //58_施工阴保电源
//    @ContributesAndroidInjector
//    abstract CCpPowerActivity contributeCCpPowerActivity();
//
//    @ContributesAndroidInjector
//    abstract CCpPowerListActivity contributeCCpPowerListActivity();
//
//    //49_施工阴极保护测试桩
//    @ContributesAndroidInjector
//    abstract CCpTestPostActivity contributeCCpTestPostActivity();
//
//    @ContributesAndroidInjector
//    abstract CCpTestPostListActivity contributeCCpTestPostListActivity();
//
//    //56_绝缘件
//    @ContributesAndroidInjector
//    abstract CInsulatingPartActivity contributeCInsulatingPartActivity();
//
//    @ContributesAndroidInjector
//    abstract CInsulatingPartListActivity contributeCInsulatingPartListActivity();
//
//    //57_施工绝缘接头保护器
//    @ContributesAndroidInjector
//    abstract CIslulationJointProtectorActivity contributeCIslulationJointProtectorActivity();
//
//    @ContributesAndroidInjector
//    abstract CIslulationJointProtectorListActivity contributeCIslulationJointProtectorListActivity();
//
//    //54_施工极性排流器
//    @ContributesAndroidInjector
//    abstract CPloarDrainagerActivity contributeCPloarDrainagerActivity();
//
//    @ContributesAndroidInjector
//    abstract CPloarDrainagerListActivity contributeCPloarDrainagerListActivity();
//
//    //55_施工固态去耦合器
//    @ContributesAndroidInjector
//    abstract CSolidStateDcdecoupActivity contributeCSolidStateDcdecoupActivity();
//
//    @ContributesAndroidInjector
//    abstract CSolidStateDcdecoupListActivity contributeCSolidStateDcdecoupListActivity();

//    @ContributesAndroidInjector
//    abstract AnalysicofDataConditionListActivity contributeAnalysicofDataConditionListActivity();
//
//    @ContributesAndroidInjector
//    abstract AnalysicofMonthincreasingPipelineListActivity contributeAnalysicofMonthincreasingPipelineListActivity();
//
//    @ContributesAndroidInjector
//    abstract AnalysicofWeldPipelineListActivity contributeAnalysicofWeldPipelineListActivity();
//
//    @ContributesAndroidInjector
//    abstract AnalysicofWeldConditionListActivity contributeAnalysicofWeldConditionListActivity();


}
