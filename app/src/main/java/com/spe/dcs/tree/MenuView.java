package com.spe.dcs.tree;

import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spe.dcs.R;
import com.spe.dcs.app.PcsSharedPreferences;

/**
 * @ProjectName: DCS
 * @Package: com.spe.dcs.tree
 * @ClassName: MenuView
 * @Description:
 * @Author: cj.rhuang@gmail.com
 * @CreateDate: 2019/3/14 10:59
 * @Version: 1.0
 */
public class MenuView extends NavigationView implements View.OnClickListener {
    private ViewGroup mHeadView;
    private Context context;
    private OnMenuClickListener mOnMenuClickListener;

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mHeadView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.main_menu_head, null);
        addHeaderView(mHeadView);

        mHeadView.findViewById(R.id.rl_head_update_config).setOnClickListener(this);
        mHeadView.findViewById(R.id.rl_head_upload_data).setOnClickListener(this);
        mHeadView.findViewById(R.id.rl_head_update_version).setOnClickListener(this);
        mHeadView.findViewById(R.id.rl_head_exit_sys).setOnClickListener(this);


    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        mOnMenuClickListener = onMenuClickListener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_head_update_config://更新配置
                mOnMenuClickListener.onUpdateClick();
                break;
            case R.id.rl_head_upload_data://上传数据
                mOnMenuClickListener.onUploadClick();

                break;
            case R.id.rl_head_update_version://版本更新
                mOnMenuClickListener.onUpgradeClick();

                break;
            case R.id.rl_head_exit_sys://退出系统
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(null);
                builder.setTitle(context.getString(R.string.tip));
                builder.setMessage(context.getString(R.string.sure_exit));
                builder.setPositiveButton(R.string.bt_sure, (dialog, which) -> {
                    PcsSharedPreferences sharedPreferences = new PcsSharedPreferences(context);
//                    sharedPreferences.setAutoLogin(false);
//                    sharedPreferences.setRemeberAccount(false);
//                    sharedPreferences.setUserName("");
//                    sharedPreferences.setPassword("");
                    ((MainActivity) context).finish();
                    System.exit(0);
                });
                builder.setNegativeButton(R.string.bt_cancle, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
                builder.show();
                break;
        }
    }

    public interface OnMenuClickListener {
        void onUpgradeClick();//版本更新

        void onUploadClick();

        void onUpdateClick();
    }
}
