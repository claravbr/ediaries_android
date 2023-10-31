package com.uclm.louise.ediaries.utils;

import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;

import java.util.Comparator;
import java.util.Objects;

public class TareaComparator implements Comparator<SearchTareaDiariaResult> {

    @Override
    public int compare(SearchTareaDiariaResult t1, SearchTareaDiariaResult t2) {

        if(Objects.equals(t1.getPrioridad(), t2.getPrioridad())){
            return 0;
        } else if (t1.getPrioridad().toUpperCase().equals("ALTA")){
            return -1;
        } else {
            return 1;
        }
    }
}
