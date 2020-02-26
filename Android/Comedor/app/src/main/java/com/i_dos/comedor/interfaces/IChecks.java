package com.i_dos.comedor.interfaces;

import com.i_dos.comedor.models.Check;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IChecks {

    String API_CHECK = "checks";

    @GET(API_CHECK)
    Call<List<Check>> getChecks();

    @POST(API_CHECK)
    Call<Check> saveCheck(@Body Check check);

}
