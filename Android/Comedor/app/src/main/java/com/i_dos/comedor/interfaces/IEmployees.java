package com.i_dos.comedor.interfaces;

import com.i_dos.comedor.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IEmployees {

    String API_EMPLOYEES = "employees";
    String API_GET_EMPLOYEE_BY_CODE = "employees/{code}";

    @GET(API_EMPLOYEES)
    Call<List<Employee>> getEmployees();

    @GET(API_GET_EMPLOYEE_BY_CODE)
    Call<Employee> getEmployeeByCode(@Path("code") String id);

    @POST(API_EMPLOYEES)
    Call<Employee> saveEmployee(@Body Employee employee);


}
