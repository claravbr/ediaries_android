package com.uclm.louise.ediaries.utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uclm.louise.ediaries.R;
import com.uclm.louise.ediaries.data.responses.SearchTareaDiariaResult;

import java.util.ArrayList;
import java.util.List;

public class ProgresoAdapter extends RecyclerView.Adapter<ProgresoAdapter.ProgresoViewHolder> {

    public List<SearchTareaDiariaResult> listaTareas;
    private List<String> categorias;

    public ProgresoAdapter(List<SearchTareaDiariaResult> listaTareas, List<String> categorias) {
        this.listaTareas = listaTareas;
        this.categorias = categorias;
    }

    public ProgresoAdapter() {
    }

    @NonNull
    @Override
    public ProgresoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progreso, parent, false);
        return new ProgresoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgresoViewHolder holder, int position) {
        String categoria = categorias.get(position);

        int tareasCategoriaTerminadas = getTareasTerminadasCategoria(categoria);
        int tareasCategoriaTotal = getTareasCategoria(categoria);

        Log.i("Debug log", "tareas terminadas: " + tareasCategoriaTerminadas);
        Log.i("Debug log", "tareas totales: " + tareasCategoriaTotal);

        double porcentaje = ((double)tareasCategoriaTerminadas / tareasCategoriaTotal) * 100;

        int porcentajeInt = (int)porcentaje;

        Log.i("Debug log", "Porcentaje hecho: " + porcentaje);

        holder.nombreCategoria.setText(categoria);

        holder.textoProgreso.setText( porcentajeInt + "% completado");

        holder.progressBarTerminadas.setProgress(porcentajeInt);

        holder.tareasHechas.setText(tareasCategoriaTerminadas + "/" + tareasCategoriaTotal);

        getTareasTerminadasCategoria(categoria);

    }

    private int getTareasTerminadasCategoria(String categoria) {
        List<SearchTareaDiariaResult> tareasTerminadas = new ArrayList<>();

        for(SearchTareaDiariaResult tarea : listaTareas){
            if(tarea.getCategoria().getNombre().equals(categoria) && tarea.getTerminada() == 1){
                tareasTerminadas.add(tarea);
            }
        }

        return tareasTerminadas.size();
    }

    private int getTareasCategoria(String categoria) {
        List<SearchTareaDiariaResult> tareas = new ArrayList<>();

        for(SearchTareaDiariaResult tarea : listaTareas){
            if(tarea.getCategoria().getNombre().equals(categoria)){
                tareas.add(tarea);
            }
        }

        return tareas.size();
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public  class ProgresoViewHolder extends  RecyclerView.ViewHolder{

        public TextView nombreCategoria;
        public TextView textoProgreso;
        public ProgressBar progressBarTerminadas;
        public TextView tareasHechas;

        public ProgresoViewHolder(View itemView){
            super(itemView);
            nombreCategoria = itemView.findViewById(R.id.textViewCategory);
            textoProgreso = itemView.findViewById(R.id.percentage_of_tasks_done);
            progressBarTerminadas = itemView.findViewById(R.id.stats_progressbar);
            tareasHechas = itemView.findViewById(R.id.number_of_tasks_done);
        }
    }


}
