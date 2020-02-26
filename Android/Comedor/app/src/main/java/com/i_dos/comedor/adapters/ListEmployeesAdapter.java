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
import com.i_dos.comedor.models.Employee;

import java.util.List;

public class ListEmployeesAdapter extends ArrayAdapter<Employee> {

    private static class ViewHolder {
        TextView textCode;
        TextView textName;
        TextView textDepartment;
    }

    public ListEmployeesAdapter(@NonNull Context context, List<Employee> employees) {
        super(context, R.layout.item_employee, employees);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Employee employee = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_employee, parent, Boolean.FALSE);

            viewHolder.textCode = convertView.findViewById(R.id.textCode);
            viewHolder.textName = convertView.findViewById(R.id.textName);
            viewHolder.textDepartment = convertView.findViewById(R.id.textDepartment);

            convertView.setTag(viewHolder);

            viewHolder.textCode.setText(employee.getCode_employee());
            viewHolder.textName.setText(employee.getName());
            viewHolder.textDepartment.setText(employee.getDepartment());
        }

        return convertView;
    }
}
