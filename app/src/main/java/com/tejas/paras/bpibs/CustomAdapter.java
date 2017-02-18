package com.tejas.paras.bpibs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by paras on 2/4/2017.
 */
public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    Context mContext;
    boolean[] checkBoxState;

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView roll;
        CheckBox id;
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.item_layout, data);
        this.dataSet = data;
        this.mContext=context;
        checkBoxState=new boolean[data.size()];

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DataModel dataModel = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.roll = (TextView) convertView.findViewById(R.id.roll);
            viewHolder.id=(CheckBox) convertView.findViewById(R.id.id);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;
        viewHolder.name.setText(dataModel.getName());
        viewHolder.roll.setText(dataModel.getRoll());
        viewHolder.id.setText(dataModel.getID());
        viewHolder.id.setChecked(checkBoxState[position]);
        final int a= position;
        viewHolder.id.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(((CheckBox)v).isChecked())

                    checkBoxState[a]=true;
                else
                    checkBoxState[a]=false;

            }
        });


        return convertView;
    }


}
