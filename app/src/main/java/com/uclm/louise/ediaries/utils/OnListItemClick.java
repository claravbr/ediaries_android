package com.uclm.louise.ediaries.utils;

import android.view.View;

import com.uclm.louise.ediaries.enums.ActivityActions;

public interface OnListItemClick {
    void onClick(View view, int position, int idTarea, ActivityActions action);
}