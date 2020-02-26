package com.i_dos.comedor.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.i_dos.comedor.R;
import com.i_dos.comedor.interfaces.IEmployees;
import com.i_dos.comedor.models.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeActivity extends Activity {

    private EditText etName;
    private EditText etCode;
    private EditText etDepartment;

    private void init() {
        etName = findViewById(R.id.etName);
        etCode = findViewById(R.id.etCode);
        etDepartment = findViewById(R.id.etDepartment);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        init();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickSave(View view) {
        Employee employee = new Employee();

        employee.setName(etName.getText().toString());
        employee.setCode_employee(etCode.getText().toString());
        employee.setDepartment(etDepartment.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.15:3000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IEmployees iEmployees = retrofit.create(IEmployees.class);

        try {
            Call<Employee> call = iEmployees.saveEmployee(employee);

            call.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {
                    Log.e("ERROR", t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
