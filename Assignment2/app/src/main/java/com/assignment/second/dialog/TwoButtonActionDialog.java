package com.assignment.second.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.assignment.second.R;

public class TwoButtonActionDialog extends AlertDialog {

    private TextView txtCommonMessage;
    private Button btnRetry;
    private Activity activity;
    private Button btnExit;
    private String message;
    private String okayMsg;
    private String cancelMsg;
    private View.OnClickListener okButtonListener;
    private View.OnClickListener cancelButtonListener;

    public TwoButtonActionDialog(Activity activity, String okayMsg, String cancelMsg, String message,
                                 View.OnClickListener okButtonListener, View.OnClickListener cancelButtonListener) {
        super(activity);
        this.activity = activity;
        this.message = message;
        this.okayMsg = okayMsg;
        this.cancelMsg = cancelMsg;
        this.okButtonListener = okButtonListener;
        this.cancelButtonListener = cancelButtonListener;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        iniUI();

    }

    private void iniUI() {

        txtCommonMessage = (TextView) findViewById(R.id.txt_common_msg);
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnRetry = (Button) findViewById(R.id.btn_retry);

        btnRetry.setOnClickListener(okButtonListener);
        btnExit.setOnClickListener(cancelButtonListener);

        txtCommonMessage.setText(message);
        btnRetry.setText(okayMsg);
        btnExit.setText(cancelMsg);

    }
}
