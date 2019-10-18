package com.spe.dcs.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.spe.dcs.R;

/**
 * Desc:
 * Author.
 * Data:${DATA}
 */

public class AlertsDialog extends Dialog {
    private Context context;
    private AlertsDialogListener listener;
    String msg;
    boolean showCancel = false;
    private String cancleText;
    private String okText;

    public interface AlertsDialogListener {
        void onResult(boolean confirmed);
    }

    public AlertsDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    public AlertsDialog(Context context, String msgId, AlertsDialogListener listener, boolean showCancel) {
        super(context, R.style.dialog);
        this.context = context;
        this.msg = msgId;
        this.listener = listener;
        this.showCancel = showCancel;
        this.setCanceledOnTouchOutside(true);

    }

    public AlertsDialog setButtonText(String cancleText, String okText) {
        this.cancleText = cancleText;
        this.okText = okText;
        return this;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_alert);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL); //此处可以设置dialog显示的位置
        window.setBackgroundDrawable(new BitmapDrawable());

        Button cancel = (Button) findViewById(R.id.button_cancle);
        Button ok = (Button) findViewById(R.id.button_sure);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.button_sure) {
                    onOk(view);
                } else if (view.getId() == R.id.button_cancle) {
                    onCancel(view);
                }
            }
        };
        cancel.setOnClickListener(listener);
        ok.setOnClickListener(listener);

        if (showCancel) {
            cancel.setVisibility(View.VISIBLE);
            ok.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_button_sure));
        } else {
            cancel.setVisibility(View.GONE);
            findViewById(R.id.line).setVisibility(View.GONE);
            ok.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_button_sure1));
        }

        if (msg != null)
            ((TextView) findViewById(R.id.alert_message)).setText(msg);

        if (cancleText != null)
            ((Button) findViewById(R.id.button_cancle)).setText(cancleText);

        if (okText != null)
            ((Button) findViewById(R.id.button_sure)).setText(okText);
    }

    public void onOk(View view) {
        this.dismiss();
        if (this.listener != null) {
            this.listener.onResult(true);
        }
    }

    public void onCancel(View view) {
        this.dismiss();
        if (this.listener != null) {
            this.listener.onResult(false);
        }
    }
}
