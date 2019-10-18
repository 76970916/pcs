package com.spe.dcs.tree.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.spe.dcs.R;
import com.spe.dcs.system.syscategory.SysCategoryEntity;
import com.unnamed.b.atv.model.TreeNode;

/**
 * 文件名：ParentHolder.java
 * 作  者： cj.rhuang@gmail.com
 * 时  间： 2019/2/19 15:31
 * 描  述： 父节点适配器
 */
public class ParentHolder extends TreeNode.BaseNodeViewHolder<SysCategoryEntity> {
    private CheckedTextView checkedTextView;

    public ParentHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, SysCategoryEntity value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_tree_parent, null, false);
        checkedTextView = view.findViewById(R.id.ctv);
        checkedTextView.setText(value.getName());
        ViewGroup.LayoutParams rootParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(rootParams);
        return view;
    }


    @Override
    public void toggle(boolean active) {
        super.toggle(active);
        checkedTextView.toggle();
    }
}
