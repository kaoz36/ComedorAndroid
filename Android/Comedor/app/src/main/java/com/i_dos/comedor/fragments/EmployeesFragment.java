package com.i_dos.comedor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.i_dos.comedor.R;
import com.i_dos.comedor.adapters.ListEmployeesAdapter;
import com.i_dos.comedor.models.Employee;

import java.util.List;

public class EmployeesFragment extends Fragment {

    private ListView listEmployees;
    private List<Employee> employeeList;
    private ListEmployeesAdapter listEmployeesAdapter;

    public EmployeesFragment(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employees, container, Boolean.FALSE);
        listEmployees = view.findViewById(R.id.listEmployees);
        if (employeeList != null) {
            listEmployeesAdapter = new ListEmployeesAdapter(getContext(), employeeList);
            listEmployees.setAdapter(listEmployeesAdapter);
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
