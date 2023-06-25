package com.uclm.louise.ediaries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ExpLVAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> listPregunta;
    private Map<String, String> mapChild;
    private Context context;

    public ExpLVAdapter(ArrayList<String> listPregunta, Map<String, String> mapChild, Context context) {
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
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listPregunta.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String pregunta = listPregunta.get(groupPosition);
        return mapChild.get(pregunta);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String tituloPregunta = (String) getGroup(groupPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.elv_group, null);
        TextView lvGroup = (TextView) convertView.findViewById(R.id.lvGroup);
        lvGroup.setText(tituloPregunta);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String item = (String) getChild(groupPosition, childPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.elv_child, null);
        TextView lvChild = (TextView) convertView.findViewById(R.id.lvChild);
        lvChild.setText(item);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
