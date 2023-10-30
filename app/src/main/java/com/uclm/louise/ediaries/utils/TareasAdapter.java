package com.uclm.louise.ediaries.utils;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.divider.MaterialDivider;
import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {

    private List<SearchTareaDiariaResult> listaTareas;

    public TareasAdapter(List<SearchTareaDiariaResult> listaTareas) {
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        SearchTareaDiariaResult tarea = listaTareas.get(position);

        holder.nombreTarea.setText(tarea.getNombre());
        holder.categoria.setText(tarea.getCategoria().getNombre());

        // Ver los días restantes hasta la fecha límite y customizar el recordatorio en función a esos días
        // 0 - 1 días -> Texto rojo
        // 2 - 3 días -> Texto naranja
        // 4+ días -> Texto verde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate fechaLimiteDate = LocalDate.parse(tarea.getFechaLimite(), formatter);
        LocalDate fechaActual = LocalDate.now();

        long diasRestantes = ChronoUnit.DAYS.between(fechaActual, fechaLimiteDate);
        holder.fechaLimite.setText("Te quedan " + diasRestantes + " días.");

        if(diasRestantes <= 1){
            holder.fechaLimite.setTextColor(Color.parseColor("#FF0000"));
        } else if (diasRestantes <= 3){
            holder.fechaLimite.setTextColor(Color.parseColor("#FF8C00"));
        } else {
            holder.fechaLimite.setTextColor(Color.parseColor("#008000"));
        }

    }


    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public class TareaViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreTarea;
        public TextView fechaLimite;
        public TextView categoria;
        public MaterialDivider separator;

        public TareaViewHolder(View itemView) {
            super(itemView);
            nombreTarea = itemView.findViewById(R.id.textViewTask1);
            fechaLimite = itemView.findViewById(R.id.textViewTaskTimeLeft);
            categoria = itemView.findViewById(R.id.textViewCategory);
            separator = itemView.findViewById(R.id.materialDivider);
        }
    }
}