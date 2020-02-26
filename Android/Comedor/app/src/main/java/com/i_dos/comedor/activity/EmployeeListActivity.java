package com.i_dos.comedor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.i_dos.comedor.R;
import com.i_dos.comedor.fragments.EmployeesFragment;
import com.i_dos.comedor.interfaces.IEmployees;
import com.i_dos.comedor.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeListActivity extends AppCompatActivity {

    public void loadFragment(List<Employee> employees) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        ft.replace(R.id.container_frame_employees, new EmployeesFragment(employees), "Emnployees");
        ft.commitAllowingStateLoss();
    }

    private void init() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.15:3000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IEmployees iEmployees = retrofit.create(IEmployees.class);

        Call<List<Employee>> call = iEmployees.getEmployees();

        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> employeesResponse = response.body();
                loadFragment(employeesResponse);
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e("FSALOOO", t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_employees);

        init();
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        onClickBack(null);
    }
}
