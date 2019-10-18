package com.spe.dcs.tree.holder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spe.dcs.R;
import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.unnamed.b.atv.model.TreeNode;

/**
 * 文件名：ChildHolder.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/19 15:31
 * 描  述： 子节点适配器
 */
public class ChildHolder extends TreeNode.BaseNodeViewHolder<SysCategoryEntity> {
    public ChildHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, SysCategoryEntity value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.item_tree_child, null, false);
        TextView textView = view.findViewById(R.id.child_text);
        textView.setText(value.getName());
        textView.setOnClickListener((View v) -> {
            //连续点击会多次跳转 用share本地保存判断下
            if (value != null) {

                try {
                    String pkg = context.getPackageName() + ".project." + value.getCode().replace("_", "").toLowerCase() + ".";

                    String[] names = value.getCode().toLowerCase().split("_");
                    for (String name : names) {
                        pkg += (new StringBuilder()).append(Character.toUpperCase(name.charAt(0))).append(name.substring(1)).toString();
                    }
                    Class cls;
                    //code:ConstructManager:承包商数据负责人,负责本单位数据的录入。 （流程）,
                    // PDService:数字化服务,数字化数据的校验（流程）,
                    // ConstructionController:监理数据校验,负责数据校验、审核。本角色编号的负责数据校验，审核（流程）,
                    // ProjectManager:业主单位项目负责人, 本单位数据查看、抽检.属于建设单位的可以抽检（流程）.
                    //code如果是admin的话，还需要重新判断
                    //审核人员进入
                    if ("ConstructionController".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
//                        if ("com.spe.dcs.project.analysicofmonthincreasingpipeline.AnalysicofMonthincreasingPipeline".equals(pkg)) {
//                            Toast.makeText(context, "只有业主才有权限查看此功能", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else if ("com.spe.dcs.project.analysicofweldpipeline.AnalysicofWeldPipeline".equals(pkg)) {
//                            Toast.makeText(context, "只有业主才有权限查看此功能", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else if ("com.spe.dcs.project.analysicofweldcondition.AnalysicofWeldCondition".equals(pkg)) {
//                            Toast.makeText(context, "只有业主才有权限查看此功能", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else if ("com.spe.dcs.project.analysicofdatacondition.AnalysicofDataCondition".equals(pkg)) {
//                            Toast.makeText(context, "只有业主才有权限查看此功能", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else {
                        cls = Class.forName(pkg + "ListActivity");
//                            Log.d("ListActivity", pkg);
                        context.startActivity(new Intent(context, cls).putExtra(SysCategoryEntity.class.getSimpleName(), value));
//                        }
                        //录入人员进入
                    } else if ("ConstructManager".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
//                        if ("com.spe.dcs.project.analysicofmonthincreasingpipeline.AnalysicofMonthincreasingPipeline".equals(pkg)) {
//                            Toast.makeText(context, "只有业主才有权限查看此功能", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else if ("com.spe.dcs.project.analysicofweldpipeline.AnalysicofWeldPipeline".equals(pkg)) {
//                            Toast.makeText(context, "只有业主才有权限查看此功能", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else if ("com.spe.dcs.project.analysicofweldcondition.AnalysicofWeldCondition".equals(pkg)) {
//                            Toast.makeText(context, "只有业主才有权限查看此功能", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else if ("com.spe.dcs.project.analysicofdatacondition.AnalysicofDataCondition".equals(pkg)) {
////                            Toast.makeText(context, "只有业主才有权限查看此功能", Toast.LENGTH_SHORT).show();
//                            return;
//                        } else {
                        cls = Class.forName(pkg + "Activity");
                        context.startActivity(new Intent(context, cls).putExtra(SysCategoryEntity.class.getSimpleName(), value).putExtra("isFir", true));
//                        }
//                   admin账户
                    } else if ("admin".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        cls = Class.forName(pkg + "ListActivity");
                        context.startActivity(new Intent(context, cls).putExtra(SysCategoryEntity.class.getSimpleName(), value));
                    } else if ("PDService".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        //数据校验人员
                        cls = Class.forName(pkg + "ListActivity");
                        context.startActivity(new Intent(context, cls).putExtra(SysCategoryEntity.class.getSimpleName(), value));
                    }

                    //业主进入
                    else if ("ProjectManager".equals(PcsApplication.getInstance().getSysRoleEntity().getCode().trim())) {
                        cls = Class.forName(pkg + "ListActivity");
                        context.startActivity(new Intent(context, cls).putExtra(SysCategoryEntity.class.getSimpleName(), value));
                    }
                    PcsApplication.getInstance().setSysCategoryEntity(value);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }


        });
        ViewGroup.LayoutParams rootParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(rootParams);
        return view;
    }

}
