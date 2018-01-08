package com.firebase.ameerhamza.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ameerhamza.myapplication.interfaces.DonorInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    EditText name ;
    EditText email ;
    EditText password ;

    Button save ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DonorInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DonorInterface api = retrofit.create(DonorInterface.class);
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        Donor donor = new Donor(Name,Email,Password);
        Call<Donor> call = api.register(donor);
        call.enqueue(new Callback<Donor>() {
            @Override
            public void onResponse(Call<Donor> call, Response<Donor> response) {
                if (response.isSuccessful())
                {
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Not successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Donor> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Failure", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
