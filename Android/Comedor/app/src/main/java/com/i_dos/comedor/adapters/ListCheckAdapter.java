package com.i_dos.comedor.adapters;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.i_dos.comedor.R;
import com.i_dos.comedor.models.Check;
import com.i_dos.comedor.models.Employee;

import java.util.List;

public class ListCheckAdapter extends ArrayAdapter<Check> {

    private static class ViewHolder {
        TextView textCode;
        TextView textName;
        TextView textDepartment;
        TextView textDateTime;
    }

    public ListCheckAdapter(@NonNull Context context, List<Check> checks) {
        super(context, R.layout.item_check, checks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Check check = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_check, parent, Boolean.FALSE);

            viewHolder.textCode = convertView.findViewById(R.id.textCode);
            viewHolder.textName = convertView.findViewById(R.id.textName);
            viewHolder.textDepartment = convertView.findViewById(R.id.textDepartment);
            viewHolder.textDateTime = convertView.findViewById(R.id.textDateTime);

            convertView.setTag(viewHolder);

            viewHolder.textCode.setText(check.getCode_employee());
            viewHolder.textName.setText(check.getName());
            viewHolder.textDepartment.setText(check.getDepartment());
            viewHolder.textDateTime.setText(check.getDate_time());
        }

        return convertView;
    }
}
