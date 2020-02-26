package com.i_dos.comedor.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.i_dos.comedor.R;
import com.i_dos.comedor.interfaces.IChecks;
import com.i_dos.comedor.interfaces.IEmployees;
import com.i_dos.comedor.models.Check;
import com.i_dos.comedor.models.Employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckInActivity extends Activity {

    private EditText etCode;
    private TextView tvName;
    private TextView tvCode;
    private TextView tvDepartment;

    private void init() {
        etCode = findViewById(R.id.etCode);
        tvName = findViewById(R.id.textName);
        tvCode = findViewById(R.id.textCode);
        tvDepartment = findViewById(R.id.textDepartment);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        init();
    }

    public void onClickBuscar(View view) {
        String code = etCode.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://192.168.100.15:3000/api/v1/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

        IEmployees iEmployees = retrofit.create(IEmployees.class);

        Call<Employee> call = iEmployees.getEmployeeByCode(code);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Employee employee = response.body();
                if (employee != null ) {
                    tvName.setText(employee.getName());
                    tvCode.setText(employee.getCode_employee());
                    tvDepartment.setText(employee.getDepartment());
                } else {
                    tvName.setText("NO SE ENCONTRARON DATOS");
                }

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                tvName.setText("NO SE ENCONTRARON DATOS");
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickCheckIn(View view) {
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date_time = dtfDate.format(now) + " " + dtfTime.format(now);

        Check check = new Check();
        check.setName(tvName.getText().toString());
        check.setCode_employee(tvCode.getText().toString());
        check.setDepartment(tvDepartment.getText().toString());
        check.setDate_time(date_time);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.15:3000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IChecks iChecks = retrofit.create(IChecks.class);

        Call<Check> call = iChecks.saveCheck(check);

        call.enqueue(new Callback<Check>() {
            @Override
            public void onResponse(Call<Check> call, Response<Check> response) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Check> call, Throwable t) {
                Log.e("FSALOOO", t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
