package com.uclm.louise.ediaries.utils;

import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class TareaComparator implements Comparator<SearchTareaDiariaResult> {

    @Override
    public int compare(SearchTareaDiariaResult t1, SearchTareaDiariaResult t2) {

        // Criterio de ordenación:
        // Las tareas con fecha límite más cercana aparecen más arriba
        // Si dos tareas tienen la misma fecha límite, se ordena por la prioridad más alta

        int fechaComparison = compareFechaLimite(t1.getFechaLimite(), t2.getFechaLimite());
        if (fechaComparison != 0) {
            return fechaComparison;
        }

        return comparePrioridad(t1, t2);
    }

    private int compareFechaLimite(String fecha1, String fecha2) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = dateFormat.parse(fecha1);
            Date date2 = dateFormat.parse(fecha2);

            return date1.compareTo(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int comparePrioridad(SearchTareaDiariaResult t1, SearchTareaDiariaResult t2) {

        if(Objects.equals(t1.getPrioridad(), t2.getPrioridad())){
            return 0;
        } else if (t1.getPrioridad().toUpperCase().equals("ALTA")){
            return -1;
        } else {
            return 1;
        }
    }
}
