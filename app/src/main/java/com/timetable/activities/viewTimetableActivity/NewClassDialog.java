package com.timetable.activities.viewTimetableActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.timetable.R;

public class NewClassDialog extends Dialog implements View.OnClickListener {
    Context context;

    public NewClassDialog(@NonNull Context context) {
        super(context);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }

        dismiss();
    }
}
