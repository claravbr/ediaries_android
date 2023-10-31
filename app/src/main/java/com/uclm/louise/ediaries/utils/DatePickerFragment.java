package com.uclm.louise.ediaries.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private EditText editTextFechaLimite;

    public DatePickerFragment(EditText editTextFechaLimite) {
        this.editTextFechaLimite = editTextFechaLimite;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Fecha actual
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String formattedDay = String.format("%02d", day);
            String formattedMonth = String.format("%02d", month + 1);
            editTextFechaLimite.setText(formattedDay + "/" + formattedMonth + "/" + year);
        }
    };
}

