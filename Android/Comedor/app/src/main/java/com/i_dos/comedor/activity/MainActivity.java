package com.i_dos.comedor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.i_dos.comedor.R;

public class MainActivity extends AppCompatActivity {

    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPassword = findViewById(R.id.etPassword);
    }

    public void onClickNuevoEmpleado(View view) {
        if (etPassword.getText().toString().equals("1234")) {
            Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Escribe la contraseña correcta", Toast.LENGTH_LONG).show();
        }

    }

    public void onClickCheckIn(View view) {
        Intent intent = new Intent(getApplicationContext(), CheckInActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickListCheckIn(View view) {
        Intent intent = new Intent(getApplicationContext(), CheckListActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickListEmployee(View view) {
        Intent intent = new Intent(getApplicationContext(), EmployeeListActivity.class);
        startActivity(intent);
        finish();
    }
}
