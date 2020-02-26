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
import com.i_dos.comedor.adapters.ListCheckAdapter;
import com.i_dos.comedor.adapters.ListEmployeesAdapter;
import com.i_dos.comedor.models.Check;
import com.i_dos.comedor.models.Employee;

import java.util.List;

public class CheckFragment extends Fragment {

    private ListView listChecks;
    private List<Check> checkList;
    private ListCheckAdapter listCheckAdapter;

    public CheckFragment(List<Check> checkList) {
        this.checkList = checkList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checks, container, Boolean.FALSE);
        listChecks = view.findViewById(R.id.listChecks);
        if (checkList != null) {
            listCheckAdapter = new ListCheckAdapter(getContext(), checkList);
            listChecks.setAdapter(listCheckAdapter);
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
