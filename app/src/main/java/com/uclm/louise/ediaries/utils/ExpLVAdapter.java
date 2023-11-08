package com.uclm.louise.ediaries.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.uclm.louise.ediaries.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpLVAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> listPregunta;
    private Map<String, List<String>> mapChild;
    private Context context;

    public ExpLVAdapter(ArrayList<String> listPregunta, Map<String, List<String>> mapChild, Context context) {
        this.listPregunta = listPregunta;
        this.mapChild = mapChild;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return listPregunta.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String pregunta = listPregunta.get(groupPosition);
        List<String> childList = mapChild.get(pregunta);
        return childList != null ? childList.size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listPregunta.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String pregunta = listPregunta.get(groupPosition);
        List<String> childList = mapChild.get(pregunta);
        if (childList != null && childPosition < childList.size()) {
            return childList.get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true; // Puedes establecerlo en true si los ID son estables
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String tituloPregunta = (String) getGroup(groupPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.elv_group, null);
        TextView lvGroup = convertView.findViewById(R.id.lvGroup);
        lvGroup.setText(tituloPregunta);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String item = (String) getChild(groupPosition, childPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.elv_child, null);
        TextView lvChild = convertView.findViewById(R.id.lvChild);
        lvChild.setText(item);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
