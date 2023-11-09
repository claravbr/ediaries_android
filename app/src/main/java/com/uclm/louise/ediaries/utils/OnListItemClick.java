package com.uclm.louise.ediaries.utils;

import android.view.View;

import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;
import com.uclm.louise.ediaries.enums.ActivityActions;

public interface OnListItemClick {
    void onClick(View view, int position, SearchTareaDiariaResult tarea, ActivityActions action);
}