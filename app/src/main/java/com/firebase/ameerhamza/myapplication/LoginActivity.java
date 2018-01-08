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

public class LoginActivity extends AppCompatActivity {

    String token = "" ;
    EditText username ;
    EditText password ;
    Button login ;
    Button register ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.loginBtn);
        register = (Button) findViewById(R.id.registerBtn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }



    // This will use to login a user
    private void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    // This will use to register a user
    private void login() {
        String email = username.getText().toString();
        String pass = password.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DonorInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DonorInterface api = retrofit.create(DonorInterface.class);
        Login login = new Login(email, pass);
        Call<Donor> call = api.login(login);

        call.enqueue(new Callback<Donor>() {
            @Override
            public void onResponse(Call<Donor> call, Response<Donor> response) {
                if (response.isSuccessful())
                {
                    token = response.body().getToken().toString();
                    Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
                    intent.putExtra("token", token);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Username or Password is incorrect!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Donor> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Issue in method",Toast.LENGTH_SHORT).show();
            }
        });



    }
}
