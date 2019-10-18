package com.spe.dcs.app.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.spe.dcs.common.ImageViewModel;
import com.spe.dcs.common.QrcodeViewModel;
import com.spe.dcs.login.LoginViewModel;
import com.spe.dcs.project.ccoatingrepair.CCoatingRepairViewModel;
import com.spe.dcs.project.chddcro.CHddCroViewModel;
import com.spe.dcs.project.chydraulicprotection.CHydraulicProtectionViewModel;
import com.spe.dcs.project.cjointcoating.CJointCoatingViewModel;
import com.spe.dcs.project.cmarkstake.CMarkStakeViewModel;
import com.spe.dcs.project.creworkweldjoint.CReworkWeldJointViewModel;
import com.spe.dcs.project.copencutcro.COpenCutCroViewModel;
import com.spe.dcs.project.ctrussaerialcro.CTrussAerialCroViewModel;
import com.spe.dcs.project.cwarningsign.CWarningSignViewModel;
import com.spe.dcs.project.cweldjoint.CWeldJointViewModel;
import com.spe.dcs.project.mentityunit.MEntityUnitViewModel;
import com.spe.dcs.project.moneprojectinfo.MOneProjectInfoViewModel;
import com.spe.dcs.project.mentityfuction.MEntityFuctionViewModel;
import com.spe.dcs.project.mprojectinfo.MProjectInfoViewModel;
import com.spe.dcs.project.mcontractorinfo.MContractorInfoViewModel;
import com.spe.dcs.project.cstation.CStationViewModel;
import com.spe.dcs.project.cvalveroom.CValveRoomViewModel;
import com.spe.dcs.project.cwelder.CWelderViewModel;
import com.spe.dcs.project.cweldingprocedure.CWeldingProcedureViewModel;
import com.spe.dcs.project.cweldingunit.CWeldingUnitViewModel;
import com.spe.dcs.system.sysandroiderror.SysAndroidErrorViewModel;
import com.spe.dcs.system.sysandroidupdate.SysAndroidUpdateViewModel;
import com.spe.dcs.system.syscategory.SysCategoryViewModel;
import com.spe.dcs.system.sysdomain.SysDomainViewModel;
import com.spe.dcs.system.sysfield.SysFieldViewModel;
import com.spe.dcs.system.sysformfield.SysFormFieldViewModel;
import com.spe.dcs.system.sysformfieldtype.SysFormFieldTypeViewModel;
import com.spe.dcs.system.syslist.SysListViewModel;
import com.spe.dcs.system.syslistbutton.SysListButtonViewModel;
import com.spe.dcs.system.syslistfield.SysListFieldViewModel;
import com.spe.dcs.system.sysorg.SysOrgViewModel;
import com.spe.dcs.system.sysorgres.SysOrgResViewModel;
import com.spe.dcs.system.sysres.SysResViewModel;
import com.spe.dcs.system.sysrole.SysRoleViewModel;
import com.spe.dcs.system.sysroleres.SysRoleResViewModel;
import com.spe.dcs.system.sysroleuser.SysRoleUserViewModel;
import com.spe.dcs.system.systable.SysTableViewModel;
import com.spe.dcs.system.sysuser.SysUserViewModel;
import com.spe.dcs.system.sysuserres.SysUserResViewModel;
import com.spe.dcs.tree.TreeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author rebeccafranks
 * @since 2018/01/02.
 */
@Module
public abstract class ViewModelBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TreeViewModel.class)
    abstract ViewModel bindTreeViewModel(TreeViewModel treeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(QrcodeViewModel.class)
    abstract ViewModel bindQrcodeViewModel(QrcodeViewModel qrcodeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel.class)
    abstract ViewModel bindImageViewModel(ImageViewModel qrcodeViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(PcsViewModelFactory factory);


    @Binds
    @IntoMap
    @ViewModelKey(SysCategoryViewModel.class)
    abstract ViewModel bindSysCategoryViewModel(SysCategoryViewModel sysCategoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysOrgViewModel.class)
    abstract ViewModel bindSysOrgViewModel(SysOrgViewModel sysOrgViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(SysTableViewModel.class)
    abstract ViewModel bindSysTableViewModel(SysTableViewModel sysCategoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysDomainViewModel.class)
    abstract ViewModel bindSysDomainViewModel(SysDomainViewModel sysCategoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysUserViewModel.class)
    abstract ViewModel bindSysUserViewModel(SysUserViewModel sysCategoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysListViewModel.class)
    abstract ViewModel bindSysListViewModel(SysListViewModel sysCategoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysFieldViewModel.class)
    abstract ViewModel bindSysFieldViewModel(SysFieldViewModel sysCategoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysAndroidErrorViewModel.class)
    abstract ViewModel bindSysAndroidErrorViewModel(SysAndroidErrorViewModel sysAndroidErrorViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysAndroidUpdateViewModel.class)
    abstract ViewModel bindSysAndroidUpdateViewModel(SysAndroidUpdateViewModel sysAndroidUpdateViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysFormFieldViewModel.class)
    abstract ViewModel bindSysFormFieldViewModel(SysFormFieldViewModel sysFormFieldViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysFormFieldTypeViewModel.class)
    abstract ViewModel bindSysFormFieldTypeViewModel(SysFormFieldTypeViewModel sysFormFieldTypeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysListButtonViewModel.class)
    abstract ViewModel bindSysListButtonViewModel(SysListButtonViewModel sysListButtonViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysListFieldViewModel.class)
    abstract ViewModel bindSysListFieldViewModel(SysListFieldViewModel sysListFieldViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysOrgResViewModel.class)
    abstract ViewModel bindSysOrgResViewModel(SysOrgResViewModel sysOrgResViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysResViewModel.class)
    abstract ViewModel bindSysResViewModel(SysResViewModel sysResViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysRoleViewModel.class)
    abstract ViewModel bindSysRoleViewModel(SysRoleViewModel sysRoleViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysRoleResViewModel.class)
    abstract ViewModel bindSysRoleResViewModel(SysRoleResViewModel sysRoleResViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysRoleUserViewModel.class)
    abstract ViewModel bindSysRoleUserViewModel(SysRoleUserViewModel sysRoleUserViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SysUserResViewModel.class)
    abstract ViewModel bindSysUserResViewModel(SysUserResViewModel sysUserResViewModel);


//    //53_施工牺牲阳极
//    @Binds
//    @IntoMap
//    @ViewModelKey(CSacrificialAnodeViewModel.class)
//    abstract ViewModel bindCSacrificialAnodeViewModel(CSacrificialAnodeViewModel cSacrificialAnodeViewModel);
//
//    //18_施工磁粉检测子表
//    @Binds
//    @IntoMap
//    @ViewModelKey(CMagneticTestSViewModel.class)
//    abstract ViewModel bindCMagneticTestSViewModel(CMagneticTestSViewModel cMagneticTestSViewModel);
//
//    //16_施工渗透检测子表
//    @Binds
//    @IntoMap
//    @ViewModelKey(CPenetrationTestSViewModel.class)
//    abstract ViewModel bindCPenetrationTestSViewModel(CPenetrationTestSViewModel cPenetrationTestSViewModel);
//
//    //12_施工射线检测子表
//    @Binds
//    @IntoMap
//    @ViewModelKey(CRayTestSViewModel.class)
//    abstract ViewModel bindCRayTestSViewModel(CRayTestSViewModel cRayTestSViewModel);
//
//    //14_施工超声波检测子表
//    @Binds
//    @IntoMap
//    @ViewModelKey(CUltrasonicTestSViewModel.class)
//    abstract ViewModel bindCUltrasonicTestSViewModel(CUltrasonicTestSViewModel cUltrasonicTestSViewModel);
//
//    //59.5穿跨越
//    @Binds
//    @IntoMap
//    @ViewModelKey(CCrossViewModel.class)
//    abstract ViewModel bindCCrossViewModel(CCrossViewModel cCrossViewModel);
//

    //功能区
    @Binds
    @IntoMap
    @ViewModelKey(MEntityFuctionViewModel.class)
    abstract ViewModel bindCPipeSegmentViewModel(MEntityFuctionViewModel mEntityFuctionViewModel);

    //单元
    @Binds
    @IntoMap
    @ViewModelKey(MEntityUnitViewModel.class)
    abstract ViewModel bindMEntityUnitViewModel(MEntityUnitViewModel mEntityUnitViewModel);

    //    项目
    @Binds
    @IntoMap
    @ViewModelKey(MProjectInfoViewModel.class)
    abstract ViewModel bindCProjectViewModel(MProjectInfoViewModel mProjectInfoViewModel);

    //子项目
    @Binds
    @IntoMap
    @ViewModelKey(MOneProjectInfoViewModel.class)
    abstract ViewModel bindCPipelineViewModel(MOneProjectInfoViewModel mOneProjectInfoViewModel);

    //标段
    @Binds
    @IntoMap
    @ViewModelKey(MContractorInfoViewModel.class)
    abstract ViewModel bindCSectionViewModel(MContractorInfoViewModel mContractorInfoViewModel);

    //    施工机组
    @Binds
    @IntoMap
    @ViewModelKey(CWeldingUnitViewModel.class)
    abstract ViewModel bindCWeldingUnitViewModel(CWeldingUnitViewModel cWeldingUnitViewModel);

    //    焊接工艺规程
    @Binds
    @IntoMap
    @ViewModelKey(CWeldingProcedureViewModel.class)
    abstract ViewModel bindCWeldingProcedureViewModel(CWeldingProcedureViewModel cWeldingProcedureViewModel);

    //    站场
    @Binds
    @IntoMap
    @ViewModelKey(CStationViewModel.class)
    abstract ViewModel bindCStationViewModel(CStationViewModel cStationViewModel);

    //    阀室
    @Binds
    @IntoMap
    @ViewModelKey(CValveRoomViewModel.class)
    abstract ViewModel bindCValveRoomViewModel(CValveRoomViewModel cValveRoomViewModel);

    //    施工焊工数据
    @Binds
    @IntoMap
    @ViewModelKey(CWelderViewModel.class)
    abstract ViewModel bindCWelderViewModel(CWelderViewModel cWelderViewModel);


    //    //02_施工冷弯管预制
//    @Binds
//    @IntoMap
//    @ViewModelKey(CSitebendPrefabricatViewModel.class)
//    abstract ViewModel bindCSitebendPrefabricatViewModel(CSitebendPrefabricatViewModel cSitebendPrefabricatViewModel);
//
//    //01_施工短节预制
//    @Binds
//    @IntoMap
//    @ViewModelKey(CShortSegmentPrefabricatViewModel.class)
//    abstract ViewModel bindCShortSegmentPrefabricatViewModel(CShortSegmentPrefabricatViewModel cShortSegmentPrefabricatViewModel);
//
    //06_防腐补口
    @Binds
    @IntoMap
    @ViewModelKey(CJointCoatingViewModel.class)
    abstract ViewModel bindCAntiCoatingViewModel(CJointCoatingViewModel cAntiCoatingViewModel);

    //09_施工防腐补伤
    @Binds
    @IntoMap
    @ViewModelKey(CCoatingRepairViewModel.class)
    abstract ViewModel bindCAntiCoatingRepairViewModel(CCoatingRepairViewModel cAntiCoatingRepairViewModel);
//
//    //07施工防腐补口剥离强度试验
//    @Binds
//    @IntoMap
//    @ViewModelKey(CAntiCoatingTestViewModel.class)
//    abstract ViewModel bindCAntiCoatingTestViewModel(CAntiCoatingTestViewModel cAntiCoatingTestViewModel);
//
//    //08_施工电火花检测施工
//    @Binds
//    @IntoMap
//    @ViewModelKey(CElectricDetectionViewModel.class)
//    abstract ViewModel bindCElectricDetectionViewModel(CElectricDetectionViewModel cElectricDetectionViewModel);
//
//    //10_施工中线成果
//    @Binds
//    @IntoMap
//    @ViewModelKey(CLineCompletionpointViewModel.class)
//    abstract ViewModel bindCLineCompletionpointViewModel(CLineCompletionpointViewModel cLineCompletionpointViewModel);
//
    //03_施工焊口
    @Binds
    @IntoMap
    @ViewModelKey(CWeldJointViewModel.class)
    abstract ViewModel bindCWeldViewModel(CWeldJointViewModel cWeldViewModel);

    //
//    //05_施工割口
//    @Binds
//    @IntoMap
//    @ViewModelKey(CWeldcutViewModel.class)
//    abstract ViewModel bindCWeldcutViewModel(CWeldcutViewModel cWeldcutViewModel);
//
//04_施工返修口
    @Binds
    @IntoMap
    @ViewModelKey(CReworkWeldJointViewModel.class)
    abstract ViewModel bindCWeldReworkViewModel(CReworkWeldJointViewModel cWeldReworkViewModel);

    //
//    //21_施工管沟回填
//    @Binds
//    @IntoMap
//    @ViewModelKey(CBackfillViewModel.class)
//    abstract ViewModel bindCBackfillViewModel(CBackfillViewModel cBackfillViewModel);
//
//    //23_施工保温
//    @Binds
//    @IntoMap
//    @ViewModelKey(CInsulationViewModel.class)
//    abstract ViewModel bindCInsulationViewModel(CInsulationViewModel cInsulationViewModel);
//
//    //22_施工地貌恢复验收
//    @Binds
//    @IntoMap
//    @ViewModelKey(CLandrestorAcceptViewModel.class)
//    abstract ViewModel bindCLandrestorAcceptViewModel(CLandrestorAcceptViewModel cLandrestorAcceptViewModel);
//
//    //19_施工测量放线
//    @Binds
//    @IntoMap
//    @ViewModelKey(CSurveyingViewModel.class)
//    abstract ViewModel bindCSurveyingViewModel(CSurveyingViewModel cSurveyingViewModel);
//
//    //20_施工管沟开挖
//    @Binds
//    @IntoMap
//    @ViewModelKey(CTrenchExcavationViewModel.class)
//    abstract ViewModel bindCTrenchExcavationViewModel(CTrenchExcavationViewModel cTrenchExcavationViewModel);
//
//    //32_施工套管
//    @Binds
//    @IntoMap
//    @ViewModelKey(CCasingPipeViewModel.class)
//    abstract ViewModel bindCCasingPipeViewModel(CCasingPipeViewModel cCasingPipeViewModel);
//
//    //33_施工地质灾害监控系统
//    @Binds
//    @IntoMap
//    @ViewModelKey(CGeologicMonitorViewModel.class)
//    abstract ViewModel bindCGeologicMonitorViewModel(CGeologicMonitorViewModel cGeologicMonitorViewModel);
//
//    //
//    //25_施工水工保护
    @Binds
    @IntoMap
    @ViewModelKey(CHydraulicProtectionViewModel.class)
    abstract ViewModel bindCHydraulicProtectionViewModel(CHydraulicProtectionViewModel cHydraulicProtectionViewModel);
//
//    //26_施工数据采集伴行道路
//    @Binds
//    @IntoMap
//    @ViewModelKey(CMaintenanceRoadViewModel.class)
//    abstract ViewModel bindCMaintenanceRoadViewModel(CMaintenanceRoadViewModel cMaintenanceRoadViewModel);
//
//    //27_施工伴行道路转角点
//    @Binds
//    @IntoMap
//    @ViewModelKey(CMaintenanceRoadPointViewModel.class)
//    abstract ViewModel bindCMaintenanceRoadPointViewModel(CMaintenanceRoadPointViewModel cMaintenanceRoadPointViewModel);
//
//    //29_施工标志桩
    @Binds
    @IntoMap
    @ViewModelKey(CMarkStakeViewModel.class)
    abstract ViewModel bindCMarkStakeViewModel(CMarkStakeViewModel cMarkStakeViewModel);

    //
//    //31_施工线路附属物
//    @Binds
//    @IntoMap
//    @ViewModelKey(CPipelineAppurtenanceViewModel.class)
//    abstract ViewModel bindCPipelineAppurtenanceViewModel(CPipelineAppurtenanceViewModel cPipelineAppurtenanceViewModel);
//
//    //28_施工伴行道路桥涵
//    @Binds
//    @IntoMap
//    @ViewModelKey(CRoadBridgeCulvertViewModel.class)
//    abstract ViewModel bindCRoadBridgeCulvertViewModel(CRoadBridgeCulvertViewModel cRoadBridgeCulvertViewModel);
//
//    //24_施工地下障碍物
//    @Binds
//    @IntoMap
//    @ViewModelKey(CUndergroundObstacleViewModel.class)
//    abstract ViewModel bindCUndergroundObstacleViewModel(CUndergroundObstacleViewModel cUndergroundObstacleViewModel);
//
//    //30_施工警示牌
    @Binds
    @IntoMap
    @ViewModelKey(CWarningSignViewModel.class)
    abstract ViewModel bindCWarningSignViewModel(CWarningSignViewModel cWarningSignViewModel);

    //
//    //17_施工磁粉检测主表
//    @Binds
//    @IntoMap
//    @ViewModelKey(CMagneticTestMViewModel.class)
//    abstract ViewModel bindCMagneticTestMViewModel(CMagneticTestMViewModel cMagneticTestMViewModel);
//
//    //15_施工渗透检测主表
//    @Binds
//    @IntoMap
//    @ViewModelKey(CPenetrationTestMViewModel.class)
//    abstract ViewModel bindCPenetrationTestMViewModel(CPenetrationTestMViewModel cPenetrationTestMViewModel);
//
//    //11_施工射线检测主表
//    @Binds
//    @IntoMap
//    @ViewModelKey(CRayTestMViewModel.class)
//    abstract ViewModel bindCRayTestMViewModel(CRayTestMViewModel cRayTestMViewModel);
//
//    //13_施工超声波检测主表
//    @Binds
//    @IntoMap
//    @ViewModelKey(CUltrasonicTestMViewModel.class)
//    abstract ViewModel bindCUltrasonicTestMViewModel(CUltrasonicTestMViewModel cUltrasonicTestMViewModel);
//
//    //37_施工管道测径
//    @Binds
//    @IntoMap
//    @ViewModelKey(CPipelineCaliperViewModel.class)
//    abstract ViewModel bindCPipelineCaliperViewModel(CPipelineCaliperViewModel cPipelineCaliperViewModel);
//
//    //    35_施工管道干燥
//    @Binds
//    @IntoMap
//    @ViewModelKey(CPipelineDryingViewModel.class)
//    abstract ViewModel bindCPipelineDryingViewModel(CPipelineDryingViewModel cPipelineDryingViewModel);
//
//    //36_施工管道清管
//    @Binds
//    @IntoMap
//    @ViewModelKey(CPipelinePiggingViewModel.class)
//    abstract ViewModel bindCPipelinePiggingViewModel(CPipelinePiggingViewModel cPipelinePiggingViewModel);
//
//    //34_施工管道试压
//    @Binds
//    @IntoMap
//    @ViewModelKey(CPressureTestViewModel.class)
//    abstract ViewModel bindCPressureTestViewModel(CPressureTestViewModel cPressureTestViewModel);
//
//    //40_施工箱涵穿越
//    @Binds
//    @IntoMap
//    @ViewModelKey(CTrussAerialCroViewModel.class)
//    abstract ViewModel bindCBoxculvertCrossViewModel(CTrussAerialCroViewModel cBoxculvertCrossViewModel);
//
//    //42_施工跨越
//    @Binds
//    @IntoMap
//    @ViewModelKey(CCrossOverViewModel.class)
//    abstract ViewModel bindCCrossOverViewModel(CCrossOverViewModel cCrossOverViewModel);
//
//    //38_施工开挖穿越
    @Binds
    @IntoMap
    @ViewModelKey(COpenCutCroViewModel.class)
    abstract ViewModel bindCExcavationCrossViewModel(COpenCutCroViewModel cOpenCutCroViewModel);

    //41_施工定向钻穿越
    @Binds
    @IntoMap
    @ViewModelKey(CHddCroViewModel.class)
    abstract ViewModel bindCHddCrossViewModel(CHddCroViewModel cHddCroViewModel);

    //桁架跨越
    @Binds
    @IntoMap
    @ViewModelKey(CTrussAerialCroViewModel.class)
    abstract ViewModel bindCTrussAerialCroViewModel(CTrussAerialCroViewModel cTrussAerialCroViewModel);

//    //39_施工顶管穿越
//    @Binds
//    @IntoMap
//    @ViewModelKey(CPushpipeCrossViewModel.class)
//    abstract ViewModel bindCPushpipeCrossViewModel(CPushpipeCrossViewModel cPushpipeCrossViewModel);
//
//    //45_施工光缆单独穿跨越
//    @Binds
//    @IntoMap
//    @ViewModelKey(CFocCrossViewModel.class)
//    abstract ViewModel bindCFocCrossViewModel(CFocCrossViewModel cFocCrossViewModel);
//
//    //47_施工光缆接头盒
//    @Binds
//    @IntoMap
//    @ViewModelKey(CFocJointBoxViewModel.class)
//    abstract ViewModel bindCFocJointBoxViewModel(CFocJointBoxViewModel cFocJointBoxViewModel);
//
//    //44_施工光缆敷设
//    @Binds
//    @IntoMap
//    @ViewModelKey(CFocLayingViewModel.class)
//    abstract ViewModel bindCFocLayingViewModel(CFocLayingViewModel cFocLayingViewModel);
//
//    //43_施工光缆单盘测试
//    @Binds
//    @IntoMap
//    @ViewModelKey(CFocTestViewModel.class)
//    abstract ViewModel bindCFocTestViewModel(CFocTestViewModel cFocTestViewModel);
//
//    //46_施工手孔
//    @Binds
//    @IntoMap
//    @ViewModelKey(CHandHoleViewModel.class)
//    abstract ViewModel bindCHandHoleViewModel(CHandHoleViewModel cHandHoleViewModel);
//
//    //48_施工通信标石
//    @Binds
//    @IntoMap
//    @ViewModelKey(CTeMarkStoneViewModel.class)
//    abstract ViewModel bindCTeMarkStoneViewModel(CTeMarkStoneViewModel cTeMarkStoneViewModel);
//
//    //52_施工辅助阳极地床
//    @Binds
//    @IntoMap
//    @ViewModelKey(CAuanodeBedViewModel.class)
//    abstract ViewModel bindCAuanodeBedViewModel(CAuanodeBedViewModel cAuanodeBedViewModel);
//
//    //50_施工阴极保护电缆
//    @Binds
//    @IntoMap
//    @ViewModelKey(CCpCableViewModel.class)
//    abstract ViewModel bindCCpCableViewModel(CCpCableViewModel cCpCableViewModel);
//
//    //51_施工阴保电缆连接点
//    @Binds
//    @IntoMap
//    @ViewModelKey(CCpCableConnectionViewModel.class)
//    abstract ViewModel bindCCpCableConnectionViewModel(CCpCableConnectionViewModel cCpCableConnectionViewModel);
//
//    //58_施工阴保电源
//    @Binds
//    @IntoMap
//    @ViewModelKey(CCpPowerViewModel.class)
//    abstract ViewModel bindCCpPowerViewModel(CCpPowerViewModel cCpPowerViewModel);
//
//    //49_施工阴极保护测试桩
//    @Binds
//    @IntoMap
//    @ViewModelKey(CCpTestPostViewModel.class)
//    abstract ViewModel bindCCpTestPostViewModel(CCpTestPostViewModel cCpTestPostViewModel);
//
//    //56_绝缘件
//    @Binds
//    @IntoMap
//    @ViewModelKey(CInsulatingPartViewModel.class)
//    abstract ViewModel bindCInsulatingPartViewModel(CInsulatingPartViewModel cInsulatingPartViewModel);
//
//    //57_施工绝缘接头保护器
//    @Binds
//    @IntoMap
//    @ViewModelKey(CIslulationJointProtectorViewModel.class)
//    abstract ViewModel bindCIslulationJointProtectorViewModel(CIslulationJointProtectorViewModel cIslulationJointProtectorViewModel);
//
//    //
//    //54_施工极性排流器
//    @Binds
//    @IntoMap
//    @ViewModelKey(CPloarDrainagerViewModel.class)
//    abstract ViewModel bindCPloarDrainagerViewModel(CPloarDrainagerViewModel cPloarDrainagerViewModel);
//
//    //55_施工固态去耦合器
//    @Binds
//    @IntoMap
//    @ViewModelKey(CSolidStateDcdecoupViewModel.class)
//    abstract ViewModel bindCSolidStateDcdecoupViewModel(CSolidStateDcdecoupViewModel cSolidStateDcdecoupViewModel);


}
