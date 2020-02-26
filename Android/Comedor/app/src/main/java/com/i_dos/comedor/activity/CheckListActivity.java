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
import com.i_dos.comedor.fragments.CheckFragment;
import com.i_dos.comedor.fragments.EmployeesFragment;
import com.i_dos.comedor.interfaces.IChecks;
import com.i_dos.comedor.interfaces.IEmployees;
import com.i_dos.comedor.models.Check;
import com.i_dos.comedor.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckListActivity extends AppCompatActivity {

    public void loadFragment(List<Check> checks) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        ft.replace(R.id.container_frame_checks, new CheckFragment(checks), "Emnployees");
        ft.commitAllowingStateLoss();
    }

    private void init() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.15:3000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IChecks iChecks = retrofit.create(IChecks.class);

        Call<List<Check>> call = iChecks.getChecks();

        call.enqueue(new Callback<List<Check>>() {
            @Override
            public void onResponse(Call<List<Check>> call, Response<List<Check>> response) {
                List<Check> checkResponse = response.body();
                loadFragment(checkResponse);
            }

            @Override
            public void onFailure(Call<List<Check>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_checks);

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
